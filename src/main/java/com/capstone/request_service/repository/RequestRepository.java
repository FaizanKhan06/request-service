package com.capstone.request_service.repository;

import com.capstone.request_service.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    List<RequestEntity> findByEmail(String email);

    List<RequestEntity> findByCommunityIdAndStatus(int communityId, String status);

    Optional<RequestEntity> findByCommunityIdAndEmail(int communityId, String email);

    List<RequestEntity> findByStatus(String status);

    // pending //approved //rejected

    List<RequestEntity> findByStatusAndCommunityId(String status, int communityId);

    List<RequestEntity> findByStatusAndCommunityIdAndEmail(String status, int communityId, String email);

    List<RequestEntity> findAllByCommunityIdAndStatusAndRequestDateTimeBetween(
            int communityId,
            String status,
            LocalDateTime startDate,
            LocalDateTime endDate);
}
