package app.condominio.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "contas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Conta implements Serializable, Comparable<Conta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconta")
    private Long idConta;

    @Size(min = 1, max = 2)
    @NotBlank
    private String sigla;

    @Size(max = 30)
    private String descricao;

    @Column(name = "saldoinicial")
    private BigDecimal saldoInicial;

    @Column(name = "saldoatual")
    private BigDecimal saldoAtual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominio")
    private Condominio condominio;

    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Movimento> movimentos = new ArrayList<>();

    @OneToMany(mappedBy = "contaInversa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Transferencia> transferenciasRecebidas = new ArrayList<>();

    // Construtor vazio
    public Conta() {
    }

    // Getters e Setters
    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }

    public List<Transferencia> getTransferenciasRecebidas() {
        return transferenciasRecebidas;
    }

    public void setTransferenciasRecebidas(List<Transferencia> transferenciasRecebidas) {
        this.transferenciasRecebidas = transferenciasRecebidas;
    }

    // Método toString para representação em String do objeto
    @Override
    public String toString() {
        if (descricao != null) {
            return sigla + " - " + descricao;
        } else {
            return sigla;
        }
    }

    // Método compareTo para comparação de Contas com base na sigla
    @Override
    public int compareTo(Conta outraConta) {
        return sigla.compareTo(outraConta.getSigla());
    }

    // Método para adicionar um movimento à lista de movimentos da conta
    public void adicionarMovimento(Movimento movimento) {
        movimentos.add(movimento);
        movimento.setConta(this);
    }

    // Método para remover um movimento da lista de movimentos da conta
    public void removerMovimento(Movimento movimento) {
        movimentos.remove(movimento);
        movimento.setConta(null);
    }

    // Método para adicionar uma transferência à lista de transferências recebidas da conta
    public void adicionarTransferenciaRecebida(Transferencia transferencia) {
        transferenciasRecebidas.add(transferencia);
        transferencia.setContaInversa(this);
    }

    // Método para remover uma transferência da lista de transferências recebidas da conta
    public void removerTransferenciaRecebida(Transferencia transferencia) {
        transferenciasRecebidas.remove(transferencia);
        transferencia.setContaInversa(null);
    }

    // Método para calcular o saldo atual da conta com base nos movimentos
    public void calcularSaldoAtual() {
        saldoAtual = saldoInicial;
        for (Movimento movimento : movimentos) {
            saldoAtual = saldoAtual.add(movimento.getValor());
        }
    }

    // Método para verificar se a conta possui saldo suficiente para realizar um pagamento
    public boolean possuiSaldo(BigDecimal valor) {
        return saldoAtual.compareTo(valor) >= 0;
    }
}

/*
 * Explicação da classe:
 *
 * A classe Conta representa uma conta genérica em um sistema de gerenciamento
 * de contas de condomínio. Ela possui atributos como sigla, descrição, saldo
 * inicial e saldo atual, e está associada a um condomínio. Além disso, possui
 * listas de movimentos e transferências recebidas relacionadas a essa conta.
 *
 * A classe implementa a interface Serializable para possibilitar a serialização
 * dos objetos em formato de bytes. Além disso, implementa a interface Comparable,
 * permitindo a comparação de contas com base na sigla.
 *
 * Através dos métodos presentes na classe, é possível adicionar e remover
 * movimentos e transferências da lista de relacionamentos, calcular o saldo
 * atual da conta com base nos movimentos, e verificar se a conta possui saldo
 * suficiente para realizar um pagamento. A classe também possui anotações do
 * Java Persistence API para mapeamento da entidade no banco de dados.
 *
 * A classe Conta é uma classe abstrata e é utilizada como base para outras
 * classes de contas específicas, que herdam seus atributos e métodos. O tipo
 * de herança utilizado é de Joined Inheritance, onde as subclasses têm suas
 * próprias tabelas no banco de dados e compartilham a chave primária com a
 * classe Conta.
 *
 */
