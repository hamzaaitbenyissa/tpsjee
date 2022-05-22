package com.benyissa.digitalbankback.DTOs;

import com.benyissa.digitalbankback.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private OperationType type;
    private double amount;
    private String description;
    private Date date;
}
