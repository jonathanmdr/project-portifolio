package br.com.soft.service;

import br.com.soft.model.Pessoa;
import br.com.soft.repository.PessoaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    private PessoaService subject;

    private Long ID_TEST = new Long(1);

    @Mock
    private PessoaRepository repository;

    @Before
    public void before() {
        subject = new PessoaService(repository);
    }

    @Test
    public void findById() {
        when(repository.findById(ID_TEST)).thenReturn(any(Optional.class));
        when(repository.findById(ID_TEST)).thenReturn(null);
    }

    @Test
    public void findAll() {
        List<Pessoa> iterable = mock(ArrayList.class);
        when(repository.findAll()).thenReturn(iterable);

        subject.findAll();

        verify(repository).findAll();
    }

    @Test
    public void findAllByFuncionario() {
        List<Pessoa> iterable = mock(ArrayList.class);
        when(repository.findAllByFuncionarioIsTrue()).thenReturn(iterable);

        subject.findAllFuncionario();

        verify(repository).findAllByFuncionarioIsTrue();
    }

    @Test
    public void saveAndFlush() {
        Pessoa pessoa = mock(Pessoa.class);
        subject.saveAndFlush(pessoa);

        when(repository.saveAndFlush(pessoa)).thenReturn(pessoa);

        verify(repository).saveAndFlush(pessoa);
    }

    @Test
    public void deletebyId() {
        subject.deletebyId(ID_TEST);

        verify(repository).deleteById(ID_TEST);
    }
}