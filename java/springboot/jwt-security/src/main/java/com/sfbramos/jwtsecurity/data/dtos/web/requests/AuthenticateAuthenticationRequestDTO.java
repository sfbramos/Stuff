package com.sfbramos.jwtsecurity.data.dtos.web.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticateAuthenticationRequestDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}
