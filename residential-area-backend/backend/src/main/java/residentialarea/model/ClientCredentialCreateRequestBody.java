package residentialarea.model;

import lombok.Data;

@Data
public class ClientCredentialCreateRequestBody {
    private String username;
    private String password;
    private String confirmPassword;
}
