package com.sfbramos.jwtsecurity.services;

import com.sfbramos.jwtsecurity.data.domain.User;
import com.sfbramos.jwtsecurity.data.dtos.internal.AuthenticateAuthenticationDTO;
import com.sfbramos.jwtsecurity.data.dtos.internal.RegisterAuthenticationDTO;
import com.sfbramos.jwtsecurity.data.enums.UserRole;
import com.sfbramos.jwtsecurity.mappers.UserMapper;
import com.sfbramos.jwtsecurity.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Transactional
    public String register(final RegisterAuthenticationDTO registerAuthenticationDTO) {
        final User userSaved = this.userService.save(this.userMapper.mapToUser(registerAuthenticationDTO, UserRole.USER, this.passwordEncoder));
        return this.jwtService.generateToken(userSaved);
    }

    @Transactional
    public String authenticate(final AuthenticateAuthenticationDTO authenticateAuthenticationDTO) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticateAuthenticationDTO.getEmail(), authenticateAuthenticationDTO.getPassword());
        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        final User userFound = this.userRepository.findByEmail(authenticateAuthenticationDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        return this.jwtService.generateToken(userFound);
    }
}
