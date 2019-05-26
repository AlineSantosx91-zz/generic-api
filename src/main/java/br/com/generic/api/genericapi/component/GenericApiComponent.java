package br.com.generic.api.genericapi.component;

import br.com.generic.api.genericapi.model.Config;
import br.com.generic.api.genericapi.model.GenericRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GenericApiComponent {

    RestTemplate restTemplate;

    public Object callAPI(GenericRequest genericRequest, Config config) {
        final String url = config.getProject().concat(config.getEndpoint());
        final MultiValueMap<String, String> headers = mapToMultiValueMap(genericRequest.getHeaders());
        final Map<String, String> params = genericRequest.getParams();

        ResponseEntity<Object> response = null;

        //TODO enviar parameters

        switch (genericRequest.getMethod().toUpperCase()) {
            case "GET":
                response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Object.class, params);
                break;
            case "POST":
                response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(genericRequest.getBody(), headers), Object.class, params);
                break;
            case "PUT":
                response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(genericRequest.getBody(), headers), Object.class, params);
                break;
            case "PATCH":
                response = restTemplate.exchange(url, HttpMethod.PATCH, new HttpEntity<>(genericRequest.getBody(), headers), Object.class, params);
                break;
            case "DELETE":
                response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), Object.class, params);
                break;
            default:
                throw new IllegalArgumentException("Method invalido: " + genericRequest.getMethod());
        }

        return response;
    }

    private MultiValueMap<String, String> mapToMultiValueMap(Map<String, String> headersMap) {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headersMap.forEach(headers::add);
        return headers;
    }

}
