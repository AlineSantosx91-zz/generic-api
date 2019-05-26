package br.com.generic.api.genericapi.service;

import br.com.generic.api.genericapi.component.GenericApiComponent;
import br.com.generic.api.genericapi.model.GenericRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigService {

    @Autowired
    private GenericApiComponent genericApiComponent;


    public Object call(GenericRequest genericRequest) throws IOException {

        return genericApiComponent.callAPI(genericRequest);

    }
}
