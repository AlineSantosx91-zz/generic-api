package br.com.generic.api.genericapi.service;

import br.com.generic.api.genericapi.component.GenericApiComponent;
import br.com.generic.api.genericapi.exception.ValidationException;
import br.com.generic.api.genericapi.model.GenericRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
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

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setup() {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Test
    public void deve_retornar_body() {
        when(genericApiComponent.callAPI(any(GenericRequest.class))).thenReturn(new Object());
        Object genericResponse = configService.call(montarRequest());
        assertNotNull(genericResponse);
    }

    @Test
    public void deve_retornar_body_populado() throws IOException {

        String json = "{\"nome\": \"aline\"}";

        when(genericApiComponent.callAPI(any(GenericRequest.class))).thenReturn(mapper.readValue(json, Object.class));

        Object genericResponse = configService.call(montarRequest());
        Map map = mapper.convertValue(genericResponse, Map.class);
        assertEquals("aline", map.get("nome"));
    }

    @Test
    public void deve_chamar_API_component() {
        GenericRequest genericRequest = montarRequest();
        configService.call(genericRequest);
        verify(genericApiComponent, times(1)).callAPI(eq(genericRequest));
    }

    @Test(expected = ValidationException.class)
    public void deve_cair_na_validacao() {
        GenericRequest genericRequest = new GenericRequest();
        configService.call(genericRequest);
    }

    private GenericRequest montarRequest() {
        return GenericRequest.builder()
                .key("xptoKey")
                .method("GET")
                .body(new Object())
                .build();

    }

}