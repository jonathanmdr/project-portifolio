package br.com.soft.controller;

import br.com.soft.model.Pessoa;
import br.com.soft.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PessoaController {

    @Autowired
    private PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @RequestMapping("/listpessoa")
    public ModelAndView listarPessoa() {
        ModelAndView view = new ModelAndView("listpessoa");
        view.addObject("pessoas", service.findAll());

        return view;
    }

    @RequestMapping("/cadpessoa")
    public ModelAndView cadastrarPessoa(Pessoa pessoa) {
        ModelAndView view = new ModelAndView("cadpessoa");
        view.addObject("pessoa", pessoa);

        return view;
    }

    @PostMapping("/salvarpessoa")
    public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return cadastrarPessoa(pessoa);
        }

        service.saveAndFlush(pessoa);

        redirectAttributes.addFlashAttribute("msg", "Salvo com sucesso!");

        return new ModelAndView("redirect:/listpessoa");
    }

    @GetMapping("/editarpessoa/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        return cadastrarPessoa(service.findById(id));
    }

    @GetMapping("/excluirpessoa/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        service.deletebyId(id);

        redirectAttributes.addFlashAttribute("msg", "Excluído com sucesso!");

        return new ModelAndView("redirect:/listpessoa");
    }
}
