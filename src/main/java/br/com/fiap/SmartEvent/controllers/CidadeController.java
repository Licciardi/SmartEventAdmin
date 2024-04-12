package br.com.fiap.SmartEvent.controllers;


import br.com.fiap.SmartEvent.models.Cidade;
import br.com.fiap.SmartEvent.services.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/cidades")
public class CidadeController {


    @Autowired
    private CidadeService service;



    @GetMapping("/form")
    public String loadForm(Model model){

        model.addAttribute("cidades", new Cidade());

        List<String> estados = Arrays.asList("Acre", "Alagoas", "Amapá", "Amazonas", "Bahia","Brasilia", "Ceará",  "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins");
        model.addAttribute("estados", estados);

        return "cidades/novo-cidade";
    }


    //salvar
    @PostMapping()
    @Transactional
    public String insert(@Valid Cidade cidade,
                         BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return "/cidades/novo-cidade";
        }
        service.insert(cidade);
        attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
        return "redirect:/cidades/form";
    }

    //listar
    @GetMapping("/listar")
    @Transactional(readOnly = true)
    public String findAll(Model model){

        model.addAttribute("cidades", service.findAll());
        return "/cidades/listar-cidade";
    }

    //editar

    @GetMapping("/editar/{id}")
    @Transactional(readOnly = true)
    public String findById(@PathVariable("id") Long id, Model model ){
        Cidade cidade = service.findById(id);
        model.addAttribute("cidade", cidade);
        // Lista de estados ordenada alfabeticamente
        List<String> estados = Arrays.asList("Acre", "Alagoas", "Amapá", "Amazonas", "Bahia","Brasilia", "Ceará", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins");
        model.addAttribute("estados", estados);

        return "/cidades/editar-cidade";
    }


    @PutMapping("/editar/{id}")
    @Transactional
    public String uptade(@PathVariable ("id") Long id, @Valid Cidade cidade,
                         BindingResult result){
        if (result.hasErrors()){
            cidade.setId(id);
            return "/cidades/editar-cidade";
        } else {
            service.update(id, cidade);
            return "redirect:/cidades/listar";
        }


    }


    //deletar
    @GetMapping("/deletar/{id}")
    @Transactional
    public String delete(@PathVariable("id") Long id, Model model){

        service.delete(id);
        return "redirect:/cidades/listar";
    }


}
