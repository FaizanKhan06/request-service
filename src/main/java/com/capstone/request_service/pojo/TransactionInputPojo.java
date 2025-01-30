package com.capstone.request_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TransactionInputPojo {
    private String email;
    private int communityId;
    private String transactionType;
    private double amount;
    private double interestAmount;
}
