package com.sfbramos.jwtsecurity.mappers;

import com.sfbramos.jwtsecurity.data.dtos.internal.AuthenticateAuthenticationDTO;
import com.sfbramos.jwtsecurity.data.dtos.internal.RegisterAuthenticationDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.requests.AuthenticateAuthenticationRequestDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.requests.RegisterAuthenticationRequestDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.responses.AuthenticateAuthenticationResponseDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.responses.ListRecordResponseDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.responses.RegisterAuthenticationResponseDTO;
import com.sfbramos.jwtsecurity.data.dtos.web.responses.SingleRecordResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ControllerMapper {

    // RESPONSES
    // using default due to mapstruct not allowing methods with list input and object output.
    default ListRecordResponseDTO mapToListRecordResponseDTO(final List data) {
        return mapToListRecordResponseDTO(Boolean.TRUE, data);
    }

    @Mappings({
            @Mapping(source = "success", target = "success"),
            @Mapping(source = "data", target = "data")
    })
    ListRecordResponseDTO mapToListRecordResponseDTO(final Boolean success, final List data);

    // using Boolean due to mapstruct not allowing methods with only one primitive type input and Object output.
    @Mappings({
            @Mapping(source = "success", target = "success"),
            @Mapping(target = "data", ignore = true)
    })
    SingleRecordResponseDTO mapToSingleRecordResponseDTO(final Boolean success);

    // using default due to mapstruct not allowing methods with no input.
    // using named to avoid mapstruct creating infinite loop when instantiating the SingleRecordResponseDTO instance.
    @Named("successSingleRecordResponse")
    default SingleRecordResponseDTO getSingleRecordResponseDTOSuccess() {
        return mapToSingleRecordResponseDTO(Boolean.TRUE);
    };

    RegisterAuthenticationResponseDTO mapToRegisterAuthenticationResponseDTO(final String token);
    AuthenticateAuthenticationResponseDTO mapToAuthenticateAuthenticationResponseDTO(final String token);

    // REQUESTS
    RegisterAuthenticationDTO mapToRegisterAuthenticationDTO(final RegisterAuthenticationRequestDTO registerAuthenticationRequestDTO);
    AuthenticateAuthenticationDTO mapToAuthenticateAuthenticationDTO(final AuthenticateAuthenticationRequestDTO authenticateAuthenticationRequestDTO);

}
