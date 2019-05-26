package br.com.generic.api.genericapi.repository;

import br.com.generic.api.genericapi.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {

    Config findByKey(String key);
}
