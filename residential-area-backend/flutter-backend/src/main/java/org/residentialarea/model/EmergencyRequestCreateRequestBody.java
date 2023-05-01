package org.residentialarea.model;

import lombok.Data;

@Data
public class EmergencyRequestCreateRequestBody {
    private String username;
    private String title;
    private String message;
}
