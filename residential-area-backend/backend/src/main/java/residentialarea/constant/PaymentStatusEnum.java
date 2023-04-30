package residentialarea.constant;

import lombok.Getter;

public enum PaymentStatusEnum {
    PENDING("RECEIVED"),
    COMPLETE("COMPLETE"),
    REJECTED("REJECTED");

    @Getter
    private final String status;

    PaymentStatusEnum(String status) {
        this.status = status;
    }
}
