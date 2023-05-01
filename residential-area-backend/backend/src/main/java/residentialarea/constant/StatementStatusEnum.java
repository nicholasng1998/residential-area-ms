package residentialarea.constant;

import lombok.Getter;

public enum StatementStatusEnum {
    PENDING("PENDING"),
    COMPLETE("COMPLETE");

    @Getter
    private final String status;

    StatementStatusEnum(String status) {
        this.status = status;
    }
}
