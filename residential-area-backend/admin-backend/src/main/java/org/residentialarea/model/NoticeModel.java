package org.residentialarea.model;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeModel {
    private Integer id;
    private String title;
    private String message;
    private String isActive;
    private Date createdDate;
    private Date expiryDate;
    private String createdBy;
}
