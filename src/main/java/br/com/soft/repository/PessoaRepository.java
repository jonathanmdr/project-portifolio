package br.com.soft.repository;

import br.com.soft.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Iterable<Pessoa> findAllByFuncionarioIsTrue();

}
