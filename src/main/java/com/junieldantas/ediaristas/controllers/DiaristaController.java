package com.junieldantas.ediaristas.controllers;

import com.junieldantas.ediaristas.models.Diarista;
import com.junieldantas.ediaristas.repositories.DiaristaRepository;
import com.junieldantas.ediaristas.services.FileService;
import com.junieldantas.ediaristas.services.ViaCepService;
import com.junieldantas.ediaristas.validators.CepValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/admin/diaristas")
public class DiaristaController {

    @Autowired
    private DiaristaRepository repository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private CepValidator cepValidator;

    @InitBinder("diarista")
    private void initBinder(WebDataBinder binder){
        binder.addValidators(cepValidator);
    }

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("admin/diaristas/listar");
        modelAndView.addObject("diaristas", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/diaristas/form");
        return modelAndView.addObject("diarista", new Diarista());
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam MultipartFile imagem, @Valid Diarista diarista, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "/admin/diaristas/form";
        }

        var fileName = fileService.salvar(imagem);
        diarista.setFoto(fileName);

        var endereco = viaCepService.buscarEnderecoPorCep(diarista.getCep());
        diarista.setCodigoIbge(endereco.getIbge());

        repository.save(diarista);

        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/diaristas/form");
        modelAndView.addObject("diarista", repository.findById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@RequestParam MultipartFile imagem, @PathVariable Long id, @Valid Diarista diarista, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "/admin/diaristas/form";
        }

        //Mantem Imagem Atual caso nao tenha sido enviado nova imagem
        var diaristaAtual = repository.getById(id);
        if (imagem.isEmpty()) {
            diarista.setFoto(diaristaAtual.getFoto());
        } else {
            var fileName = fileService.salvar(imagem);
            diarista.setFoto(fileName);
        }
        //Popula codigoIbge
        if (diaristaAtual.getCep() != diarista.getCep() || Objects.isNull(diaristaAtual.getCodigoIbge())) {
            var endereco = viaCepService.buscarEnderecoPorCep(diarista.getCep());
            diarista.setCodigoIbge(endereco.getIbge());
        }

        repository.save(diarista);

        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return "redirect:/admin/diaristas";
    }

}
