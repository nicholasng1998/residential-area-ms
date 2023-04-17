package residentialarea.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STATEMENT")
public class StatementBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "YEAR")
    private int year;

    @Column(name = "MONTH")
    private int month;

    @Column(name = "STATUS")
    private String status;
}
