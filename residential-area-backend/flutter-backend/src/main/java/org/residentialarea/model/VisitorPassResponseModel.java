package org.residentialarea.model;

import lombok.Data;

@Data
public class VisitorPassResponseModel {
    private String uuid;
    private String visitorName;
    private String status;
    private String imageStr;
    private int residentId;
}
