package org.residentialarea.model;

import lombok.Data;

@Data
public class CreateClientCredentialRequestBody {
    private String username;
    private String password;
    private String confirmPassword;
}
