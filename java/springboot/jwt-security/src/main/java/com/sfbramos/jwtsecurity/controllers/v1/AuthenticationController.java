package com.sfbramos.jwtsecurity.controllers.v1;

import com.sfbramos.jwtsecurity.data.dtos.internal.AuthenticateAuthenticationDTO;
import com.sfbramos.jwtsecurity.data.dtos.internal.RegisterAuthenticationDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.requests.AuthenticateAuthenticationRequestDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.requests.RegisterAuthenticationRequestDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.responses.AuthenticateAuthenticationResponseDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.responses.RegisterAuthenticationResponseDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.responses.SingleRecordResponseDTO;
import com.sfbramos.jwtsecurity.mappers.ControllerMapper;
import com.sfbramos.jwtsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final ControllerMapper controllerMapper;

    @PostMapping("/register")
    public <T> ResponseEntity<T> register(@RequestBody RegisterAuthenticationRequestDTO registerAuthenticationRequestDTO) {
        final SingleRecordResponseDTO singleRecordResponseDTO = this.controllerMapper.mapToSingleRecordResponseDTO(Boolean.TRUE);
        final RegisterAuthenticationDTO registerAuthenticationDTO = this.controllerMapper.mapToRegisterAuthenticationDTO(registerAuthenticationRequestDTO);
        final RegisterAuthenticationResponseDTO registerAuthenticationResponseDTO = this.controllerMapper.mapToRegisterAuthenticationResponseDTO(this.authenticationService.register(registerAuthenticationDTO));
        singleRecordResponseDTO.setData((T) registerAuthenticationResponseDTO);
        return ResponseEntity.ok((T) singleRecordResponseDTO);
    }

    @PostMapping("/authenticate")
    public <T> ResponseEntity<T> authenticate(@RequestBody AuthenticateAuthenticationRequestDTO authenticateAuthenticationRequestDTO) {
        final SingleRecordResponseDTO singleRecordResponseDTO = this.controllerMapper.mapToSingleRecordResponseDTO(Boolean.TRUE);
        final AuthenticateAuthenticationDTO authenticateAuthenticationDTO = this.controllerMapper.mapToAuthenticateAuthenticationDTO(authenticateAuthenticationRequestDTO);
        final AuthenticateAuthenticationResponseDTO authenticateAuthenticationResponseDTO = this.controllerMapper.mapToAuthenticateAuthenticationResponseDTO(this.authenticationService.authenticate(authenticateAuthenticationDTO));
        singleRecordResponseDTO.setData((T) authenticateAuthenticationResponseDTO);
        return ResponseEntity.ok((T) singleRecordResponseDTO);
    }

}
