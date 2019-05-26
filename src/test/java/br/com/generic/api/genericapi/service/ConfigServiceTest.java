package br.com.generic.api.genericapi.service;

import br.com.generic.api.genericapi.component.GenericApiComponent;
import br.com.generic.api.genericapi.exception.NotFoundException;
import br.com.generic.api.genericapi.exception.ValidationException;
import br.com.generic.api.genericapi.model.Config;
import br.com.generic.api.genericapi.model.GenericRequest;
import br.com.generic.api.genericapi.repository.ConfigRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConfigServiceTest {

    @InjectMocks
    private ConfigService configService;

    @Mock
    private GenericApiComponent genericApiComponent;
    
    @Mock
    private ConfigRepository configRepository;

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setup() {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        when(configRepository.findByKey(anyString())).thenReturn(new Config());
    }

    @Test
    public void deve_retornar_body() throws IOException {
        when(genericApiComponent. callAPI(any(GenericRequest.class), any(Config.class))).thenReturn(new Object());
        Object genericResponse = configService.call(montarRequest());
        assertNotNull(genericResponse);
    }

    @Test
    public void deve_retornar_body_populado() throws IOException {

        String json = "{\"nome\": \"aline\"}";

        when(genericApiComponent. callAPI(any(GenericRequest.class), any(Config.class))).thenReturn(mapper.readValue(json, Object.class));

        Object genericResponse = configService.call(montarRequest());
        Map map = mapper.convertValue(genericResponse, Map.class);
        assertEquals("aline", map.get("nome"));
    }

    @Test
    public void deve_chamar_API_component() throws IOException {
        GenericRequest genericRequest = montarRequest();
        configService.call(genericRequest);
        verify(genericApiComponent, times(1)).callAPI(eq(genericRequest), any(Config.class));
    }

    @Test(expected = ValidationException.class)
    public void dado_header_invalido_deve_cair_na_validacao() {
        GenericRequest genericRequest = GenericRequest.builder()
                .key("xptoKey")
                .method("GET")
                .headers(null)
                .body(new Object())
                .build();
        configService.call(genericRequest);
    }

    @Test(expected = ValidationException.class)
    public void dado_body_invalido_deve_cair_na_validacao() throws IOException {
        List<Object> headers = getHeaders();
        GenericRequest genericRequest = GenericRequest.builder()
                .key("xptoKey")
                .method("GET")
                .body(null)
                .headers(headers)
                .build();
        configService.call(genericRequest);
    }

    @Test(expected = ValidationException.class)
    public void dado_key_invalido_deve_cair_na_validacao() throws IOException {
        List<Object> headers = getHeaders();
        GenericRequest genericRequest = GenericRequest.builder()
                .key(null)
                .method("GET")
                .headers(headers)
                .build();
        configService.call(genericRequest);
    }

    @Test(expected = ValidationException.class)
    public void dado_method_invalido_deve_cair_na_validacao() throws IOException {
        List<Object> headers = getHeaders();
        GenericRequest genericRequest = GenericRequest.builder()
                .key("xptoKey")
                .method("")
                .headers(headers)
                .build();
        configService.call(genericRequest);
    }

    @Test(expected = NotFoundException.class)
    public void dado_key_inexistente_deve_cair_na_validacao() throws IOException {
        when(configRepository.findByKey(eq("xptoKey"))).thenReturn(null);
        configService.call(montarRequest());
    }

    private GenericRequest montarRequest() throws IOException {
        List<Object> headers = getHeaders();
        return GenericRequest.builder()
                .key("xptoKey")
                .headers(headers)
                .method("GET")
                .body(new Object())
                .build();

    }

    private List<Object> getHeaders() throws IOException {
        String json = "{\"ContentType\": \"application/json\"}";
        Object header = mapper.readValue(json, Object.class);
        List<Object> headers = new ArrayList<>();
        headers.add(header);
        return headers;
    }

}