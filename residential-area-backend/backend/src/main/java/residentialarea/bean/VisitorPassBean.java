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

    @Column(name = "CLIENT_UNIT")
    private String clientUnit;

    @Column(name = "STATUS")
    private String status;
}
