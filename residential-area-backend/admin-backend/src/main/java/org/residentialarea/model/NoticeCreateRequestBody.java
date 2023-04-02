package org.residentialarea.model;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeCreateRequestBody {
    private String title;
    private String message;
    private Boolean isActive;
    private Date expiryDate;
}
