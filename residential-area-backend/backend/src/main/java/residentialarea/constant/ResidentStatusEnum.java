package residentialarea.constant;


import lombok.Getter;

public enum ResidentStatusEnum {

    ACTIVE("Active"),
    PENDING("Pending");

    @Getter
    private final String status;

    ResidentStatusEnum(String status) {
        this.status = status;
    }
}
