package br.com.soft.service;

import br.com.soft.model.Projeto;
import br.com.soft.repository.ProjetoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjetoServiceTest {

    private ProjetoService subject;

    private Long ID_TEST = new Long(1);

    @Mock
    private ProjetoRepository repository;

    @Before
    public void before() {
        subject = new ProjetoService(repository);
    }

    @Test
    public void findById() {
        when(repository.findById(ID_TEST)).thenReturn(any(Optional.class));
        when(repository.findById(ID_TEST)).thenReturn(null);
    }

    @Test
    public void findAll() {
        List<Projeto> iterable = mock(ArrayList.class);
        when(repository.findAll()).thenReturn(iterable);

        subject.findAll();

        verify(repository).findAll();
    }

    @Test
    public void saveAndFlush() {
        Projeto projeto = mock(Projeto.class);
        subject.saveAndFlush(projeto);

        when(repository.saveAndFlush(projeto)).thenReturn(projeto);

        verify(repository).saveAndFlush(projeto);
    }

    @Test
    public void deletebyId() {
        subject.deletebyId(ID_TEST);

        verify(repository).deleteById(ID_TEST);
    }
}