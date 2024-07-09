package com.sfbramos.jwtsecurity.data.dtos.web.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListRecordResponseDTO<T> extends ResponseDTO {

    @JsonProperty("data")
    private List<T> data;

}
