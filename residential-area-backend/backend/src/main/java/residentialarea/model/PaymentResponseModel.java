package residentialarea.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponseModel {
    private int id;
    private BigDecimal amount;
    private String method;
    private String reference;
    private String status;
}
