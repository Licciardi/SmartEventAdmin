package br.com.fiap.SmartEvent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


  @RequestMapping("/")
    public String index(Model model){
      model.addAttribute("msg", "Bem vindo(a) ao Admin SmartEvent");
        return "eventos/index";
    }

}
