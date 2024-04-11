package br.com.fiap.SmartEvent.services;

import br.com.fiap.SmartEvent.models.Evento;
import br.com.fiap.SmartEvent.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Transactional(readOnly = true)
    public List<Evento> findAll(){

        return repository.findAll();
    }





}
