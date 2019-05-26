package br.com.generic.api.genericapi.service;

import br.com.generic.api.genericapi.component.GenericApiComponent;
import br.com.generic.api.genericapi.exception.ValidationException;
import br.com.generic.api.genericapi.model.GenericRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private GenericApiComponent genericApiComponent;


    public Object call(GenericRequest genericRequest) {
        requestValidate(genericRequest);
        return genericApiComponent.callAPI(genericRequest);
    }

    private void requestValidate(GenericRequest genericRequest){

        if(isNullOrEmpty(genericRequest.getKey()))
            throw new ValidationException("Key é obrigatória");

        if(isNullOrEmpty(genericRequest.getMethod()))
            throw new ValidationException("Method é obrigatório");

        if(genericRequest.getBody() == null)
            throw new ValidationException("Body é obrigatório");
    }

    private boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
}
