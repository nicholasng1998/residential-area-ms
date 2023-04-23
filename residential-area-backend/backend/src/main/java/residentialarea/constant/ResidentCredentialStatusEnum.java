package residentialarea.constant;

import lombok.Getter;

public enum ResidentCredentialStatusEnum {
    ACTIVE("Active"),
    PENDING("Pending");

    @Getter
    private final String status;

    ResidentCredentialStatusEnum(String status) {
        this.status = status;
    }
}
