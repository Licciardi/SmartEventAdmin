package br.com.fiap.SmartEvent.controllers;

import br.com.fiap.SmartEvent.models.Evento;
import br.com.fiap.SmartEvent.repository.EventoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository repository;


    @GetMapping("/novo")
    public String AddEvento(Model model){

        model.addAttribute("eventos", new Evento());
        return "eventos/novo-evento";
    }


    @PostMapping("/salvar")
    public String insertEvento(Evento evento){
        repository.save(evento);
        //System.out.println(evento.toString());
        return "redirect:/eventos/novo";
    }

}
