package br.com.fiap.SmartEvent.controllers;

import br.com.fiap.SmartEvent.models.Evento;
import br.com.fiap.SmartEvent.repository.EventoRepository;
import br.com.fiap.SmartEvent.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @Autowired
    private EventoRepository repository;


    @GetMapping("/novo")
    public String AddEvento(Model model){

        model.addAttribute("eventos", new Evento());
        return "eventos/novo-evento";
    }


    //salvar
    @PostMapping("/salvar")
    @Transactional
    public String insertEvento(@Valid Evento evento,
                               BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return "/eventos/novo-evento";
        }
        repository.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso!");
        return "redirect:/eventos/novo";
    }

    //listar
    @GetMapping("/listar")
    @Transactional
    public String findAll(Model model){

        model.addAttribute("eventos", service.findAll());
        return "/eventos/listar-evento";
    }

    //editar

    @GetMapping("/editar/{id}")
    @Transactional(readOnly = true)
    public String editarEvento(@PathVariable ("id") Long id, Model model ){
        Evento evento = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Evento inválido - id: " + id)
        );
        model.addAttribute("evento", evento);
        return "/evento/editar-produto";
    }


    @PostMapping("/editar/{id}")
    @Transactional
    public String editEvento(@PathVariable ("id") Long id, @Valid Evento evento,
                             BindingResult result){
if (result.hasErrors()){
    evento.setId(id);
    return "/evento/editar-evento";
} else {
    repository.save(evento);
    return "redirect:/eventos/listar";
}


    }

    @GetMapping("/deletar/{id}")
    @Transactional
    public String deletarEvento(@PathVariable("id") Long id, Model model){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Evento inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Evento inválido - id: " + id);
        }
        return "redirect:/eventos/listar";
    }



}
