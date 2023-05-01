package residentialarea.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePaymentRequestModel {
    private int statementId;
    private String username;
    private String method;
    private BigDecimal amount;
    private String reference;
}
