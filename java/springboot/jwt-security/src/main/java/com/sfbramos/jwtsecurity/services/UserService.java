package com.sfbramos.jwtsecurity.services;

import com.sfbramos.jwtsecurity.data.domain.User;
import com.sfbramos.jwtsecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(final User userToSave) {
        return this.userRepository.save(userToSave);
    }

}
