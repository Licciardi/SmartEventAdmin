package br.com.fiap.SmartEvent.services;

import br.com.fiap.SmartEvent.models.Cidade;
import br.com.fiap.SmartEvent.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {


    @Autowired
    private CidadeRepository repository;

    @Transactional(readOnly = true)
    public List<Cidade> findAll(){

        return repository.findAll();
    }

    //inserir cidade metodo
    @Transactional
    public Cidade insert(Cidade cidade){

       return repository.save(cidade);
    }

    //metodo para buscar cidade por id
    @Transactional(readOnly = true)
    public Cidade findById(Long id){

        Cidade cidade = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Recurso invalido - " + id)
        );

        return cidade;
    }


    //update Cidade
    @Transactional
    public Cidade update(Long id, Cidade entity) {
        try {
            Cidade cidade = repository.getReferenceById(id);
            copyToCidade(entity, cidade);
            cidade = repository.save(cidade);
            return cidade;
        } catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado");
        }

    }

    private void copyToCidade(Cidade entity, Cidade cidade) {
        cidade.setNome(entity.getNome());
        cidade.setEstado(entity.getEstado());
    }
//Deletar Cidade

    @Transactional
    public void  delete(long id){
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Cidade Invalida Id-" + id);

        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Cidade Invalida Id- "+ id);
        }
    }
}
