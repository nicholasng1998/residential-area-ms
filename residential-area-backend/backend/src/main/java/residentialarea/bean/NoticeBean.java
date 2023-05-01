package residentialarea.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "NOTICE")
public class NoticeBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @Column(name = "CREATED_BY")
    private String createdBy;
}
