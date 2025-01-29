package com.capstone.request_service.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFilterByCommunityIdAndStartDateTimeRangeInputPojo {

    private int communityId;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
