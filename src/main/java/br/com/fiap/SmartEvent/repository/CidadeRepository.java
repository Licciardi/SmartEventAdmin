package br.com.fiap.SmartEvent.repository;

import br.com.fiap.SmartEvent.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
