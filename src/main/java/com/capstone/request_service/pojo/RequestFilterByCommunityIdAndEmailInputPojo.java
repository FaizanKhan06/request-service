package com.capstone.request_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFilterByCommunityIdAndEmailInputPojo {

    private String email;
    private int communityId;
    private String status;
}
