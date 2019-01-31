package br.com.soft.service;

import br.com.soft.model.Projeto;
import br.com.soft.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    public Projeto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<Projeto> findAll() {
        return repository.findAll();
    }

    public void saveAndFlush(Projeto projeto) {
        if (!projeto.getMembros().contains(projeto.getGerente())) {
            projeto.incluirMembroNoProjeto(projeto.getGerente());
        }

        repository.saveAndFlush(projeto);
    }

    public void deletebyId(Long id) {
        repository.deleteById(id);
    }

}
