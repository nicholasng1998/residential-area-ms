package residentialarea.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "RESIDENT")
public class ResidentBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private int age;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NO")
    private String phoneNo;

    @Column(name = "UNIT_NO")
    private String unitNo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
