package br.com.soft.controller;

import br.com.soft.extras.Risco;
import br.com.soft.extras.Status;
import br.com.soft.model.Projeto;
import br.com.soft.service.PessoaService;
import br.com.soft.service.ProjetoService;
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
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @Autowired
    private PessoaService pessoaService;

    public ProjetoController(ProjetoService service, PessoaService pessoaService) {
        this.service = service;
        this.pessoaService = pessoaService;
    }

    @RequestMapping("/listprojeto")
    public ModelAndView listarProjeto() {
        ModelAndView view = new ModelAndView("listprojeto");
        view.addObject("projetos", service.findAll());

        return view;
    }

    @RequestMapping("/cadprojeto")
    public ModelAndView cadastrarProjeto(Projeto projeto) {
        ModelAndView view = new ModelAndView("cadprojeto");
        view.addObject("projeto", projeto);
        view.addObject("gerentes", pessoaService.findAllFuncionario());
        view.addObject("status", Status.values());
        view.addObject("riscos", Risco.values());

        return view;
    }

    @PostMapping("/salvarprojeto")
    public ModelAndView salvar(@Valid Projeto projeto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return cadastrarProjeto(projeto);
        }

        service.saveAndFlush(projeto);

        redirectAttributes.addFlashAttribute("msg", "Salvo com sucesso!");

        return new ModelAndView("redirect:/listprojeto");
    }

    @GetMapping("/editarprojeto/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        return cadastrarProjeto(service.findById(id));
    }

    @GetMapping("/excluirprojeto/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Projeto projeto = service.findById(id);

        if (projeto.permiteExclusao()) {
            service.deletebyId(id);

            redirectAttributes.addFlashAttribute("msg", "Excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Não é permitida a exlusão do projeto selecionado!");
        }

        return new ModelAndView("redirect:/listprojeto");
    }
}
