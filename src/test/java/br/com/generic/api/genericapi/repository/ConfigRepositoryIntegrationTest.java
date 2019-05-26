package br.com.generic.api.genericapi.repository;

import br.com.generic.api.genericapi.model.Config;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetups({
        @DatabaseSetup(value = "/datasets/ZerarBanco.xml", type = DatabaseOperation.TRUNCATE_TABLE),
        @DatabaseSetup("/datasets/Config.xml")})
@DatabaseTearDown(value = "/datasets/ZerarBanco.xml", type = DatabaseOperation.TRUNCATE_TABLE)
public class ConfigRepositoryIntegrationTest {

    @Autowired
    private ConfigRepository configRepository;

    @Test
    public void deve_retornar_config(){
        Config byKey = configRepository.findByKey("minhaKey1");
        assertNotNull(byKey);
    }
}
