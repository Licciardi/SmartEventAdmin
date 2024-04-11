package br.com.fiap.SmartEvent.repository;

import br.com.fiap.SmartEvent.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long> {
}
