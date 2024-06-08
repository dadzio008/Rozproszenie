package com.example.rozproszenie.controllers;

import com.example.rozproszenie.interfaces.GeneratorService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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
    public ModelAndView generateId(@RequestParam String url) {
        ModelAndView mv = new ModelAndView();
        Long id = generatorService.getId(url);
        mv.addObject("id",id.toString());
        mv.setViewName("Generate");
        return mv;
    }

    @DeleteMapping("/byLastUsageDate")
    public HttpStatus deleteByLastUsageDate(@RequestParam Date date) {
       return generatorService.deleteUrlByLastUsageDate(date);
    }

    @DeleteMapping("/deleteUrlByCreationDateOnCassandra")
    public HttpStatus deleteUrlByCreationDateOnCassandra(@RequestParam String genId) {
        return generatorService.deleteUrlByCreationDateOnCassandra(genId);
    }
}
