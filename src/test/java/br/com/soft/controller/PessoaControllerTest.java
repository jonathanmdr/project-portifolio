package br.com.soft.controller;

import br.com.soft.model.Pessoa;
import br.com.soft.service.PessoaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PessoaControllerTest {

    private PessoaController subject;

    private Long ID_TEST = new Long(1);

    @Mock
    private PessoaService service;

    @Before
    public void before() {
        subject = new PessoaController(service);
    }

    @Test
    public void listarPessoa() {
        subject.listarPessoa();

        Iterable<Pessoa> iterable = mock(ArrayList.class);
        when(service.findAll()).thenReturn(iterable);

        verify(service).findAll();
    }

    @Test
    public void cadastrarPessoa() {
        Pessoa pessoa = mock(Pessoa.class);
        subject.cadastrarPessoa(pessoa);
    }

    @Test
    public void salvar() {
        Pessoa pessoa = mock(Pessoa.class);
        BindingResult result = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        subject.salvar(pessoa, result, redirectAttributes);

        when(result.hasErrors()).thenReturn(true);
        when(result.hasErrors()).thenReturn(false);

        verify(service).saveAndFlush(pessoa);
    }

    @Test
    public void editar() {
        subject.editar(ID_TEST);

        verify(service).findById(ID_TEST);
    }

    @Test
    public void excluir() {
        subject.excluir(ID_TEST, mock(RedirectAttributes.class));

        verify(service).deletebyId(ID_TEST);
    }
}
