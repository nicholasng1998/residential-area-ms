package org.residentialarea.model;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeResponseModel {
    private int id;
    private String title;
    private String content;
    private boolean isActive;
    private Date expiryDate;
}
