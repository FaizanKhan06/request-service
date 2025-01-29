package com.capstone.request_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RequestInputPojo {
    private String email;
    private int communityId;
    private String requestReason;
    private Double amount;
}
