package residentialarea.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMERGENCY_REQUEST")
public class EmergencyRequestBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "STATUS")
    private String status;
}
