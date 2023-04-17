package residentialarea.model;

import lombok.Data;

@Data
public class ClientCreateRequestModel {

    private String name;
    private int age;
    private String email;
    private String phoneNo;
    private String unitNo;
    private String status;
}
