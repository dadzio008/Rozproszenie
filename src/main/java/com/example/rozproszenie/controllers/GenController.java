package com.example.rozproszenie.controllers;

import com.example.rozproszenie.interfaces.GeneratorService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GenController extends HttpServlet {
    GeneratorService generatorService;

    public GenController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Home");
        return modelAndView;
    }

    @PostMapping("/generateId")
    public ModelAndView generateId(@RequestParam String url){
        ModelAndView mv = new ModelAndView();
        Long id = generatorService.getId(url);
        mv.addObject("id",id.toString());
        mv.setViewName("Generate");
        return mv;
    }

}
