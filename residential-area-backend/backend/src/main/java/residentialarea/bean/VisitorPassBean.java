package residentialarea.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "VISITOR_PASS")
public class VisitorPassBean {

    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "VISITOR_NAME")
    private String visitorName;

    @Column(name = "RESIDENT_UNIT")
    private String residentUnit;

    @Column(name = "IMAGE_STR")
    private String imageStr;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "RESIDENT_ID")
    private int residentId;
}
