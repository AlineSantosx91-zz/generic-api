package br.com.generic.api.genericapi.component;

import br.com.generic.api.genericapi.model.Config;
import br.com.generic.api.genericapi.model.GenericRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenericApiComponentTest {

    @InjectMocks
    private GenericApiComponent genericApiComponent;

    @Mock
    private RestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setup() {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Test
    public void dado_method_GET_deve_chamar_api() throws IOException {

        GenericRequest genericRequest = montarRequest("GET");

        genericApiComponent.callAPI(genericRequest, montarConfig("xptoKey", "http://localhost:8081/", "teste"));

        verify(restTemplate, times(1)).exchange(eq("http://localhost:8081/teste"), eq(HttpMethod.GET), any(HttpEntity.class), eq(Object.class), eq(genericRequest.getParams()));
    }

    @Test
    public void dado_method_POST_deve_chamar_api() throws IOException {

        GenericRequest genericRequest = montarRequest("POST");

        genericApiComponent.callAPI(genericRequest, montarConfig("xptoKey", "http://localhost:8085/", "bla"));

        verify(restTemplate, times(1)).exchange(eq("http://localhost:8085/bla"), eq(HttpMethod.POST), any(HttpEntity.class), eq(Object.class), eq(genericRequest.getParams()));
    }

    @Test
    public void dado_method_PUT_deve_chamar_api() throws IOException {

        GenericRequest genericRequest = montarRequest("PUT");

        genericApiComponent.callAPI(genericRequest, montarConfig("xptoKey", "http://localhost:8081/", "teste"));

        verify(restTemplate, times(1)).exchange(eq("http://localhost:8081/teste"), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Object.class), eq(genericRequest.getParams()));
    }

    @Test
    public void dado_method_PATCH_deve_chamar_api() throws IOException {

        GenericRequest genericRequest = montarRequest("Patch");

        genericApiComponent.callAPI(genericRequest, montarConfig("xptoKey", "http://localhost:8081/", "teste"));

        verify(restTemplate, times(1)).exchange(eq("http://localhost:8081/teste"), eq(HttpMethod.PATCH), any(HttpEntity.class), eq(Object.class), eq(genericRequest.getParams()));
    }

    @Test
    public void dado_method_DELETE_deve_chamar_api() throws IOException {

        GenericRequest genericRequest = montarRequest("delete");

        genericApiComponent.callAPI(genericRequest, montarConfig("xptoKey", "http://localhost:8081/", "teste"));

        verify(restTemplate, times(1)).exchange(eq("http://localhost:8081/teste"), eq(HttpMethod.DELETE), any(HttpEntity.class), eq(Object.class), eq(genericRequest.getParams()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void dado_method_invalido_deve_lancar_excessao() throws IOException {
        GenericRequest genericRequest = montarRequest("bla");
        genericApiComponent.callAPI(genericRequest, montarConfig("xptoKey", "http://localhost:8081/", "teste"));
    }

    private GenericRequest montarRequest(String method) throws IOException {
        Map<String, String> headers = getHeaders();
        return GenericRequest.builder()
                .key("xptoKey")
                .headers(headers)
                .method(method)
                .body(new Object())
                .build();

    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("ContentType", "application/json");
        return headers;
    }

    private Config montarConfig(String key, String project, String endpoint) {
        return Config.builder()
                .key(key)
                .project(project)
                .endpoint(endpoint)
                .build();
    }

}