package residentialarea.bean;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PAYMENT")
public class PaymentBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "METHOD")
    private String method;

    @Column(name = "REFERENCE")
    private String reference;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STATEMENT_ID")
    private int statementId;
}
