package br.com.generic.api.genericapi.service;

import br.com.generic.api.genericapi.component.GenericApiComponent;
import br.com.generic.api.genericapi.exception.NotFoundException;
import br.com.generic.api.genericapi.exception.ValidationException;
import br.com.generic.api.genericapi.model.Config;
import br.com.generic.api.genericapi.model.GenericRequest;
import br.com.generic.api.genericapi.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private GenericApiComponent genericApiComponent;

    @Autowired
    ConfigRepository configRepository;


    public Object call(GenericRequest genericRequest) {
        requestValidate(genericRequest);
        return genericApiComponent.callAPI(genericRequest, getConfig(genericRequest));
    }

    private void requestValidate(GenericRequest genericRequest) {

        if (isNullOrEmpty(genericRequest.getKey()))
            throw new ValidationException("Key é obrigatória");

        if (isNullOrEmpty(genericRequest.getMethod()))
            throw new ValidationException("Method é obrigatório");

        if (genericRequest.getBody() == null)
            throw new ValidationException("Body é obrigatório");

        if (genericRequest.getHeaders() == null || genericRequest.getHeaders().isEmpty())
            throw new ValidationException("Headers é obrigatório");
    }

    private boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    private Config getConfig(GenericRequest genericRequest) {
        final Config config = configRepository.findByKey(genericRequest.getKey());
        if (config == null)
            throw new NotFoundException("Configuracao para key " + genericRequest.getKey() + " nao encontrada");

        return config;
    }
}
