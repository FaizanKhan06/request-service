package com.capstone.request_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityPojo {
    private int communityId;
    private String communityName;
    private String communityHead;
    private double currentAmount;
    private int ruleId;
    private boolean isPublic;
    private boolean isActive;
    private boolean isDeleted;
}
