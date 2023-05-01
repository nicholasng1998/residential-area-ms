package residentialarea.constant;

import lombok.Getter;

public enum VisitorPassStatusEnum {
    PENDING_IN("PENDING-IN"),
    PENDING_OUT("PENDING-OUT"),
    USED("USED");

    @Getter
    private final String status;

    VisitorPassStatusEnum(String status) {
        this.status = status;
    }
}
