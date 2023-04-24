package residentialarea.model;

import lombok.Data;

@Data
public class EmergencyResponseModel {
    private int id;
    private String title;
    private String message;
    private String status;
    private String residentName;
    private String unitNo;
}
