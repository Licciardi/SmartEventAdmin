package br.com.fiap.SmartEvent.controllers;

import br.com.fiap.SmartEvent.models.Cidade;
import br.com.fiap.SmartEvent.models.Evento;
import br.com.fiap.SmartEvent.repository.CidadeRepository;
import br.com.fiap.SmartEvent.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @Autowired
    private CidadeRepository cidadeRepository;


    @ModelAttribute("cidades")
    public List<Cidade> cidades(){
        return cidadeRepository.findAll();
    }



    @GetMapping("/form")
    public String loadForm(Model model){

        model.addAttribute("eventos", new Evento());
        return "eventos/novo-evento";
    }


    //salvar
    @PostMapping()
    @Transactional
    public String insert(@Valid Evento evento,
                               BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return "/eventos/novo-evento";
        }
        service.insert(evento);
        attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso!");
        return "redirect:/eventos/form";
    }

    //listar
    @GetMapping("/listar")
    @Transactional(readOnly = true)
    public String findAll(Model model){

        model.addAttribute("eventos", service.findAll());
        return "/eventos/listar-evento";
    }

    //editar

    @GetMapping("/editar/{id}")
    @Transactional(readOnly = true)
    public String findById(@PathVariable ("id") Long id, Model model ){
      Evento evento = service.findById(id);
        model.addAttribute("evento", evento);
        return "/eventos/editar-evento";
    }


    @PutMapping("/editar/{id}")
    @Transactional
    public String uptade(@PathVariable ("id") Long id, @Valid Evento evento,
                             BindingResult result){
if (result.hasErrors()){
    evento.setId(id);
    return "/eventos/editar-evento";
} else {
    service.update(id, evento);
    return "redirect:/eventos/listar";
}


    }


    //deletar
    @GetMapping("/deletar/{id}")
    @Transactional
    public String delete(@PathVariable("id") Long id, Model model){

    service.delete(id);
        return "redirect:/eventos/listar";
    }



}
