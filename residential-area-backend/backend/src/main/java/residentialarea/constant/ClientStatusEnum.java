package residentialarea.constant;


import lombok.Getter;

public enum ClientStatusEnum {

    ACTIVE("Active"),
    PENDING("Pending");

    @Getter
    private final String status;

    ClientStatusEnum(String status) {
        this.status = status;
    }
}
