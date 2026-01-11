package com.jxt.jxtbank.account.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jxt.jxtbank.auth_users.dto.UserDTO;
import com.jxt.jxtbank.auth_users.entity.User;
import com.jxt.jxtbank.enums.AccountStatus;
import com.jxt.jxtbank.enums.AccountType;
import com.jxt.jxtbank.enums.Currency;
import com.jxt.jxtbank.transaction.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class AccountDTO {

    private Long id;

    private String accountNumber;

    private BigDecimal balance = BigDecimal.ZERO;

    @JsonBackReference
    private AccountType accountType;


    private UserDTO userDTO;


    private Currency currency;


    private AccountStatus accountStatus;

    @JsonManagedReference
    private List<TransactionDTO> transactions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
