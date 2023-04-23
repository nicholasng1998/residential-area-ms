package org.residentialarea.model;

import lombok.Data;

@Data
public class CreateResidentCredentialRequestBody {
    private String username;
    private String password;
    private String confirmPassword;
}
