package com.sfbramos.jwtsecurity.mappers;

import com.sfbramos.jwtsecurity.data.domain.User;
import com.sfbramos.jwtsecurity.data.dtos.domain.UserDTO;
import com.sfbramos.jwtsecurity.data.dtos.internal.RegisterAuthenticationDTO;
import com.sfbramos.jwtsecurity.data.enums.UserRole;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper
public interface UserMapper {

    @IterableMapping(elementTargetType = UserDTO.class)
    List<UserDTO> mapToUserDTOList(final List<User> usersList);
    UserDTO mapToUserDTO(final User user);

    @IterableMapping(elementTargetType = User.class)
    List<User> mapToUserList(final List<UserDTO> userDTOsList);
    User mapToUser(final UserDTO userDTO);

    @Mappings({
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(registerAuthenticationDTO.getPassword()))")
    })
    User mapToUser(final RegisterAuthenticationDTO registerAuthenticationDTO, final UserRole role, final PasswordEncoder passwordEncoder);

}
