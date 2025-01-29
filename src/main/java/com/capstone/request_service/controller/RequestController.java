package com.capstone.request_service.controller;

import com.capstone.request_service.pojo.RequestFilterByCommunityIdAndEmailInputPojo;
import com.capstone.request_service.pojo.RequestFilterByCommunityIdAndStartDateTimeRangeInputPojo;
import com.capstone.request_service.pojo.RequestFilterByCommunityIdInputPojo;
import com.capstone.request_service.pojo.RequestInputPojo;
import com.capstone.request_service.pojo.RequestPojo;
import com.capstone.request_service.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    // Add a new request
    @PostMapping("")
    public ResponseEntity<RequestPojo> addRequest(@RequestBody RequestInputPojo requestPojo) {
        RequestPojo createdRequest = requestService.addRequest(requestPojo);
        return ResponseEntity.ok(createdRequest);
    }

    // Get all requests
    @GetMapping("")
    public ResponseEntity<List<RequestPojo>> getAllRequests() {
        List<RequestPojo> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    // Get request by ID
    @GetMapping("/{requestId}")
    public ResponseEntity<RequestPojo> getRequestById(@PathVariable int requestId) {
        RequestPojo request = requestService.getRequestById(requestId);
        return ResponseEntity.ok(request);
    }

    // Get requests by username
    @GetMapping("/users/{email}")
    public ResponseEntity<List<RequestPojo>> getRequestsByUsername(@PathVariable String email) {
        List<RequestPojo> requests = requestService.getRequestsByEmail(email);
        return ResponseEntity.ok(requests);
    }

    // Get requests by community ID
    @GetMapping("/communities/{communityId}")
    public ResponseEntity<List<RequestPojo>> getRequestsByCommunityId(@PathVariable int communityId) {
        List<RequestPojo> requests = requestService.getRequestsByCommunityId(communityId);
        return ResponseEntity.ok(requests);
    }

    // Get requests by status
    @GetMapping("/communities/status")
    public ResponseEntity<List<RequestPojo>> getByStatusAndCommunityId(
            @RequestBody RequestFilterByCommunityIdInputPojo requestFilterInputPojo) {
        List<RequestPojo> requests = requestService.getByStatusAndCommunityId(requestFilterInputPojo);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/communities/users/status")
    public ResponseEntity<List<RequestPojo>> getByStatusAndCommunityIdAndEmail(
            @RequestBody RequestFilterByCommunityIdAndEmailInputPojo requestFilterInputPojo) {
        List<RequestPojo> requests = requestService.getByStatusAndCommunityIdAndEmail(requestFilterInputPojo);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/communities/timeDiff/status")
    public ResponseEntity<List<RequestPojo>> getByStatusAndCommunityIdAndEmail(
            @RequestBody RequestFilterByCommunityIdAndStartDateTimeRangeInputPojo requestFilterInputPojo) {
        List<RequestPojo> requests = requestService
                .getAllByCommunityIdAndStatusAndRequestDateTimeBetween(requestFilterInputPojo);
        return ResponseEntity.ok(requests);
    }

    // Update request status to approved
    @PutMapping("/status/approved/{id}")
    public ResponseEntity<RequestPojo> updateRequestStatusToApproved(@PathVariable int id) {
        RequestPojo updatedRequest = requestService.updateRequestStatusToApproved(id);
        return ResponseEntity.ok(updatedRequest);
    }

    // Update request status rejected
    @PutMapping("/status/rejected/{id}")
    public ResponseEntity<RequestPojo> updateRequestStatusToRejected(@PathVariable int id) {
        RequestPojo updatedRequest = requestService.updateRequestStatusToRejected(id);
        return ResponseEntity.ok(updatedRequest);
    }
}
