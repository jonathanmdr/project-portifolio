package br.com.soft.model;

import br.com.soft.extras.Risco;
import br.com.soft.extras.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@SessionScope
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @Column(name = "data_inicio", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @Column(name = "data_previsao_fim", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataPrevisaoFim;

    @Column(name = "data_fim", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFim;

    @Column(nullable = true, length = 5000)
    private String descricao;

    @Column(nullable = true, length = 45)
    private Status status;

    @Column(nullable = true)
    private double orcamento;

    @Column(nullable = true, length = 45)
    private Risco risco;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idgerente")
    private Pessoa gerente;

    @ManyToMany
    @JoinTable(name = "membros", joinColumns = @JoinColumn(name = "idprojeto"), inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    private Set<Pessoa> membros;

    public Projeto() {
        this.membros = new HashSet<>();
    }

    public Projeto(String nome, Date dataInicio, Date dataPrevisaoFim, Date dataFim, String descricao, Status status, double orcamento, Risco risco, Pessoa gerente, Set<Pessoa> membros) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataPrevisaoFim = dataPrevisaoFim;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
        this.gerente = gerente;
        this.membros = membros;
    }

    public boolean permiteExclusao() {
        if (this.getStatus() != Status.INICIADO && this.getStatus() != Status.EM_ANDAMENTO && this.getStatus() != Status.ENCERRADO) {
            return true;
        }

        return false;
    }

    public void incluirMembroNoProjeto(Pessoa pessoa) {
        if (pessoa.isFuncionario()) {
            if (this.getMembros() == null ) {
                this.membros.add(pessoa);
            } else {
                this.getMembros().add(pessoa);
            }
        }
    }
}
