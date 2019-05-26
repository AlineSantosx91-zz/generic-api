package br.com.generic.api.genericapi.component;

import br.com.generic.api.genericapi.model.Config;
import br.com.generic.api.genericapi.model.GenericRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenericApiComponent {

    private final RestTemplate restTemplate;

    public GenericApiComponent(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Object callAPI(GenericRequest genericRequest, Config config) {

        final String url = config.getProject().concat(config.getEndpoint());
        Object response = genericRequest.getBody();

        //TODO enviar headers e parameters

        switch (genericRequest.getMethod().toUpperCase()) {
            case "GET":
                response = restTemplate.getForObject(url, Object.class);
                break;
            case "POST":
                response = restTemplate.postForLocation(url, genericRequest.getBody());
                break;
            case "PUT":
                restTemplate.put(url, genericRequest.getBody());
                break;
            case "PATCH":
                return restTemplate.patchForObject(url, genericRequest.getBody(), Object.class);
            case "DELETE":
                restTemplate.delete(url);
                break;
        }

        return response;

    }

}
