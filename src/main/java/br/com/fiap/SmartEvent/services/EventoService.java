package br.com.fiap.SmartEvent.services;

import br.com.fiap.SmartEvent.models.Evento;
import br.com.fiap.SmartEvent.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

//listar evento metodo
    @Autowired
    private EventoRepository repository;

    @Transactional(readOnly = true)
    public List<Evento> findAll(){

        return repository.findAll();
    }

//inserir evento metodo
@Transactional
    public Evento insert(Evento evento){

        return repository.save(evento);
}

//metodo para buscar evento por id
    @Transactional(readOnly = true)
    public Evento findById(Long id){

        Evento evento = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Recurso invalido - " + id)
        );

        return evento;
    }


    //update Evento
@Transactional
    public Evento update(Long id, Evento entity) {
        try {
            Evento evento = repository.getReferenceById(id);
            copyToEvento(entity, evento);
            evento = repository.save(evento);
            return evento;
        } catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado");
        }

}

    private void copyToEvento(Evento entity, Evento evento) {
        evento.setNome(entity.getNome());
        evento.setData(entity.getData());
        evento.setUrl(entity.getUrl());
    }
//Deletar Evento

    @Transactional
    public void  delete(long id){
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Evento Invalido Id-" + id);

        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Evento Invalido Id- "+ id);
        }
    }

}
