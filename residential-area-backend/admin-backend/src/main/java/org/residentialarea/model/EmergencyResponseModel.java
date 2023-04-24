package org.residentialarea.model;

import lombok.Data;

@Data
public class EmergencyResponseModel {
    private String title;
    private String message;
    private String status;
    private String residentName;
    private String unitNo;
}