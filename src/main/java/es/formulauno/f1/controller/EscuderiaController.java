package es.formulauno.f1.controller;

import es.formulauno.f1.entity.Escuderia;
import es.formulauno.f1.exception.F1Exception;
import es.formulauno.f1.service.EscuderiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class EscuderiaController {
    private final EscuderiaService escuderiaService;

    public EscuderiaController(EscuderiaService escuderiaService){
        this.escuderiaService = escuderiaService;
    }

    //thymelief
    @GetMapping
    public String homePage(){
        return "index";
    }

    @GetMapping("/escuderias")
    public String getEscuderias(Model model) throws F1Exception {
        model.addAttribute("escuderias", escuderiaService.findAll());
        return "escuderia/escuderias";
    }

    @PostMapping("/add")
    public String saveTodo(@ModelAttribute("e") Escuderia escuderia) throws F1Exception {
        escuderiaService.create(escuderia);
        return "redirect:/view/escuderias";
    }

    @GetMapping("escuderia/create")
    public String addNewEscuderia(Model model) {
        model.addAttribute("e", new Escuderia());
        return "escuderia/add";
    }

    @GetMapping("/update/{name}")
    public String update(@PathVariable("name") String name, Model model) throws F1Exception {
        Escuderia escuderia = escuderiaService.findByName(name);
        model.addAttribute("escuderia", escuderia);
        return "escuderia/update";
    }

    @GetMapping("/delete/{name}")
    public String deleteByName(@PathVariable("name") String name) {
        escuderiaService.delete(name);
        return "redirect:/view/escuderias";
    }
}
