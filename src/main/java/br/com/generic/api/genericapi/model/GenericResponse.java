package br.com.generic.api.genericapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class GenericResponse implements Serializable {
    private Object body;
}
