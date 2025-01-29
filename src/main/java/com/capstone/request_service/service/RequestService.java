package com.capstone.request_service.service;

import com.capstone.request_service.entity.RequestEntity;
import com.capstone.request_service.exception.ResourceNotFoundException;
import com.capstone.request_service.pojo.CommunityPojo;
import com.capstone.request_service.pojo.RequestFilterByCommunityIdAndEmailInputPojo;
import com.capstone.request_service.pojo.RequestFilterByCommunityIdAndStartDateTimeRangeInputPojo;
import com.capstone.request_service.pojo.RequestFilterByCommunityIdInputPojo;
import com.capstone.request_service.pojo.RequestInputPojo;
import com.capstone.request_service.pojo.RequestPojo;
import com.capstone.request_service.pojo.UserOutputDataPojo;
import com.capstone.request_service.repository.RequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    private RequestPojo convertEntityToPojo(RequestEntity requestEntity) {
        RequestPojo requestPojo = new RequestPojo();
        BeanUtils.copyProperties(requestEntity, requestPojo);
        RestClient restClient = RestClient.create();
        UserOutputDataPojo responseUser = restClient.get()
                .uri("http://localhost:5001/api/users/email/" + requestEntity.getEmail())
                .retrieve().body(UserOutputDataPojo.class);
        requestPojo.setUser(responseUser);
        CommunityPojo responseCommunity = restClient.get()
                .uri("http://localhost:5002/api/communities/" + requestEntity.getCommunityId())
                .retrieve().body(CommunityPojo.class);
        requestPojo.setCommunity(responseCommunity);

        return requestPojo;
    }

    // Get All Requests
    public List<RequestPojo> getAllRequests() {
        List<RequestEntity> requestEntities = requestRepository.findAll();
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Get Request by ID
    public RequestPojo getRequestById(int requestId) {
        RequestEntity requestEntity = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + requestId));
        return convertEntityToPojo(requestEntity);
    }

    // Get Requests by Email
    public List<RequestPojo> getRequestsByEmail(String email) {
        List<RequestEntity> requestEntities = requestRepository.findByEmail(email);
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Get Requests by Community ID
    public List<RequestPojo> getRequestsByCommunityId(int communityId) {
        List<RequestEntity> requestEntities = requestRepository.findByCommunityId(communityId);
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    public List<RequestPojo> getByStatusAndCommunityId(RequestFilterByCommunityIdInputPojo requestDataPojo) {
        List<RequestEntity> requestEntities = requestRepository.findByStatusAndCommunityId(requestDataPojo.getStatus(),
                requestDataPojo.getCommunityId());
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    public List<RequestPojo> getByStatusAndCommunityIdAndEmail(
            RequestFilterByCommunityIdAndEmailInputPojo requestDataPojo) {
        List<RequestEntity> requestEntities = requestRepository.findByStatusAndCommunityIdAndEmail(
                requestDataPojo.getStatus(),
                requestDataPojo.getCommunityId(), requestDataPojo.getEmail());
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    public List<RequestPojo> getAllByCommunityIdAndStatusAndRequestDateTimeBetween(
            RequestFilterByCommunityIdAndStartDateTimeRangeInputPojo requestDataPojo) {
        List<RequestEntity> requestEntities = requestRepository.findAllByCommunityIdAndStatusAndRequestDateTimeBetween(
                requestDataPojo.getCommunityId(),
                requestDataPojo.getStatus(),
                requestDataPojo.getStartDate(),
                requestDataPojo.getEndDate());
        List<RequestPojo> requestPojos = new ArrayList<>();
        for (RequestEntity requestEntity : requestEntities) {
            requestPojos.add(convertEntityToPojo(requestEntity));
        }
        return requestPojos;
    }

    // Add Request
    public RequestPojo addRequest(RequestInputPojo requestPojo) {
        RequestEntity requestEntity = new RequestEntity();
        BeanUtils.copyProperties(requestPojo, requestEntity);
        requestEntity.setRequestDateTime(LocalDateTime.now());
        requestEntity.setStatus("pending");
        RequestEntity savedEntity = requestRepository.saveAndFlush(requestEntity);
        return convertEntityToPojo(savedEntity);
    }

    // Update Request Status To Rejected
    public RequestPojo updateRequestStatusToRejected(int requestId) {
        RequestEntity requestEntity = requestRepository.findById(requestId).orElse(null);
        requestEntity.setStatus("rejected");
        RequestEntity updatedEntity = requestRepository.save(requestEntity);
        return convertEntityToPojo(updatedEntity);
    }

    // Update Request Status To Approved
    public RequestPojo updateRequestStatusToApproved(int requestId) {
        RequestEntity requestEntity = requestRepository.findById(requestId).orElse(null);
        requestEntity.setStatus("approved");
        RequestEntity updatedEntity = requestRepository.save(requestEntity);
        return convertEntityToPojo(updatedEntity);
    }

}
