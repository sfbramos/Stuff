package com.sfbramos.jwtsecurity.data.dtos.web.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleRecordResponseDTO<T> extends ResponseDTO {

    @JsonProperty("data")
    private T data;

}
