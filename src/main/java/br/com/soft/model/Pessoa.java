package br.com.soft.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "pessoa")
@SessionScope
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datanascimento;

    @Column(nullable = true, length = 14)
    private String cpf;

    @Column(nullable = true)
    private boolean funcionario;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, Date datanascimento, String cpf, boolean funcionario) {
        this.id = id;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.cpf = cpf;
        this.funcionario = funcionario;
    }

    public boolean isFuncionario() {
        return funcionario;
    }
}
