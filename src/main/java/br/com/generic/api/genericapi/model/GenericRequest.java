package br.com.generic.api.genericapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRequest implements Serializable {

    private Object body;
    private List<Object> headers;
    private String key;
    private String method;

}
