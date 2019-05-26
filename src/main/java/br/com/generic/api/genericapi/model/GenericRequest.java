package br.com.generic.api.genericapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRequest implements Serializable {
    private Object body;
    private String key;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> params;
}
