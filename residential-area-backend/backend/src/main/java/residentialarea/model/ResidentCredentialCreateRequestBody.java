package residentialarea.model;

import lombok.Data;

@Data
public class ResidentCredentialCreateRequestBody {
    private String username;
    private String password;
    private String confirmPassword;
}
