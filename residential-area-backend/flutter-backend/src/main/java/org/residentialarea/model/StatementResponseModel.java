package org.residentialarea.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatementResponseModel {

    private int id;
    private int year;
    private int month;
    private String status;
    private BigDecimal amount;
}
