package br.com.soft.controller;

import br.com.soft.model.Projeto;
import br.com.soft.service.PessoaService;
import br.com.soft.service.ProjetoService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjetoControllerTest {

    private ProjetoController subject;

    private Long ID_TEST = new Long(1);

    @Mock
    private ProjetoService service;

    @Mock
    private PessoaService pessoaService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        subject = new ProjetoController(service, pessoaService);
    }

    @Test
    public void listarProjeto() {
        subject.listarProjeto();

        Iterable<Projeto> iterable = mock(ArrayList.class);
        when(service.findAll()).thenReturn(iterable);

        verify(service).findAll();
    }

    @Test
    public void cadastrarProjeto() {
        Projeto projeto = mock(Projeto.class);
        subject.cadastrarProjeto(projeto);
    }

    @Test
    public void salvar() {
        Projeto projeto = mock(Projeto.class);
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        subject.salvar(projeto, result, redirectAttributes);

        when(result.hasErrors()).thenReturn(true);
        when(result.hasErrors()).thenReturn(false);

        verify(service).saveAndFlush(projeto);
    }

    @Test
    public void editar() {
        when(service.findById(ID_TEST)).thenReturn(mock(Projeto.class));
        subject.editar(ID_TEST);

        verify(service).findById(ID_TEST);
    }

    @Test
    public void excluir() {
        Projeto projeto = mock(Projeto.class);
        when(service.findById(ID_TEST)).thenReturn(mock(Projeto.class));

        when(projeto.permiteExclusao()).thenReturn(true);
        when(projeto.permiteExclusao()).thenReturn(false);

        doThrow(NullPointerException.class).when(projeto).permiteExclusao();
        subject.excluir(ID_TEST, mock(RedirectAttributes.class));
    }
}
