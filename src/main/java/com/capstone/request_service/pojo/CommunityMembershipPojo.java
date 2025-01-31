package com.capstone.request_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityMembershipPojo {
    private int communityMembershipId;
    private int communityId;
    private String email;
    private double amount;
    private boolean isAccepted;
    private int remainingTermPeriod;
    private boolean isLoanTaken;
    private boolean isLoanDefaulter;
}
