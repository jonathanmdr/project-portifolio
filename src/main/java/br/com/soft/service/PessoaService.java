package br.com.soft.service;

import br.com.soft.model.Pessoa;
import br.com.soft.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa findById(Long id) {
        return repository.findById(id).get();
    }

    public Iterable<Pessoa> findAll() {
        return repository.findAll();
    }

    public Iterable<Pessoa> findAllFuncionario() {
        return repository.findAllByFuncionarioIsTrue();
    }

    public void saveAndFlush(Pessoa pessoa) {
        repository.saveAndFlush(pessoa);
    }

    public void deletebyId(Long id) {
        repository.deleteById(id);
    }
}
