package residentialarea.constant;

import lombok.Getter;

public enum EmergencyRequestStatusEnum {
    ACTIVE("ACTIVE"),
    PENDING("PENDING"),
    RESOLVED("RESOLVED"),
    REJECTED("REJECTED");

    @Getter
    private final String status;

    EmergencyRequestStatusEnum(String status) {
        this.status = status;
    }
}
