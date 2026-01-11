package com.jxt.jxtbank.transaction.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jxt.jxtbank.account.dto.AccountDTO;
import com.jxt.jxtbank.enums.TransactionStatus;
import com.jxt.jxtbank.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private String description;
    private LocalDateTime transactionDate;
    private TransactionStatus transactionStatus;
    @JsonBackReference
    private AccountDTO account;
    private String sourceAccount;
    private String destinationAccount;
}


