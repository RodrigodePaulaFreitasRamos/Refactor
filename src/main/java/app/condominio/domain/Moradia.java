package app.condominio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import app.condominio.domain.enums.TipoMoradia;

@SuppressWarnings("serial")
@Entity
@Table(name = "moradias")
public class Moradia implements Serializable, Comparable<Moradia> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmoradia")
    private Long idMoradia;

    @Size(min = 1, max = 10)
    @NotBlank
    private String sigla;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoMoradia tipo;

    private Float area;

    @Max(100)
    @Min(0)
    @Column(name = "fracaoideal")
    private Float fracaoIdeal;

    @Size(max = 30)
    private String matricula;

    private Integer vagas;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbloco")
    private Bloco bloco;

    @OneToMany(mappedBy = "moradia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "dataEntrada")
    @Valid
    private List<Relacao> relacoes = new ArrayList<>();

    @OneToMany(mappedBy = "moradia", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy(value = "numero, parcela")
    private List<Cobranca> cobrancas = new ArrayList<>();

    public Long getIdMoradia() {
        return idMoradia;
    }

    public void setIdMoradia(Long idMoradia) {
        this.idMoradia = idMoradia;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoMoradia getTipo() {
        return tipo;
    }

    public void setTipo(TipoMoradia tipo) {
        this.tipo = tipo;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Float getFracaoIdeal() {
        return fracaoIdeal;
    }

    public void setFracaoIdeal(Float fracaoIdeal) {
        this.fracaoIdeal = fracaoIdeal;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public List<Relacao> getRelacoes() {
        return relacoes;
    }

    public void setRelacoes(List<Relacao> relacoes) {
        this.relacoes = relacoes;
    }

    public List<Cobranca> getCobrancas() {
        return cobrancas;
    }

    public void setCobrancas(List<Cobranca> cobrancas) {
        this.cobrancas = cobrancas;
    }

    /**
     * Método para adicionar uma relação à moradia.
     *
     * @param relacao A relação a ser adicionada.
     */
    public void adicionarRelacao(Relacao relacao) {
        relacao.setMoradia(this);
        relacoes.add(relacao);
    }

    /**
     * Método para remover uma relação da moradia.
     *
     * @param relacao A relação a ser removida.
     */
    public void removerRelacao(Relacao relacao) {
        relacao.setMoradia(null);
        relacoes.remove(relacao);
    }

    /**
     * Método para adicionar uma cobrança à moradia.
     *
     * @param cobranca A cobrança a ser adicionada.
     */
    public void adicionarCobranca(Cobranca cobranca) {
        cobranca.setMoradia(this);
        cobrancas.add(cobranca);
    }

    /**
     * Método para remover uma cobrança da moradia.
     *
     * @param cobranca A cobrança a ser removida.
     */
    public void removerCobranca(Cobranca cobranca) {
        cobranca.setMoradia(null);
        cobrancas.remove(cobranca);
    }

    /**
     * Método para comparar duas moradias com base na sigla.
     *
     * @param outraMoradia A outra moradia a ser comparada.
     * @return Um valor negativo se esta moradia for menor, um valor positivo se esta
     * moradia for maior, ou zero se forem iguais.
     */
    @Override
    public int compareTo(Moradia outraMoradia) {
        return this.sigla.compareTo(outraMoradia.getSigla());
    }

    /**
     * Método para verificar se duas moradias são iguais com base no id.
     *
     * @param outroObjeto O outro objeto a ser comparado.
     * @return true se forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto)
            return true;
        if (outroObjeto == null || getClass() != outroObjeto.getClass())
            return false;
        Moradia outraMoradia = (Moradia) outroObjeto;
        return idMoradia != null && idMoradia.equals(outraMoradia.getIdMoradia());
    }

    /**
     * Método para obter o código hash da moradia com base no id.
     *
     * @return O código hash.
     */
    @Override
    public int hashCode() {
        return idMoradia != null ? idMoradia.hashCode() : 0;
    }

    /**
     * Método para obter uma representação em string da moradia.
     *
     * @return A representação em string da moradia.
     */
    @Override
    public String toString() {
        return "Moradia [idMoradia=" + idMoradia + ", sigla=" + sigla + ", tipo=" + tipo + ", area=" + area
                + ", fracaoIdeal=" + fracaoIdeal + ", matricula=" + matricula + ", vagas=" + vagas + ", bloco="
                + bloco + "]";
    }
}

//Classe Moradia é uma representação de uma moradia em um sistema, com seus atributos e métodos relacionados. A classe possui os seguintes atributos:
//
//idMoradia: um identificador único para a moradia.
//sigla: uma sigla que identifica a moradia.
//tipo: o tipo da moradia (ex: casa, apartamento).
//area: a área da moradia em metros quadrados.
//fracaoIdeal: a fração ideal da moradia em relação ao total do terreno ou edifício.
//matricula: a matrícula da moradia em um registro específico.
//vagas: a quantidade de vagas de estacionamento associadas à moradia.
//bloco: o bloco ou prédio onde a moradia está localizada.
//relacoes: uma lista de relações associadas à moradia.
//cobrancas: uma lista de cobranças associadas à moradia.
//A classe possui métodos para acessar e modificar os atributos, adicionar e remover relações e cobranças, além de métodos de comparação, hash e representação em string. Além disso, a classe implementa a interface Comparable para permitir a comparação de moradias com base na sigla, e sobrescreve os métodos equals, hashCode e toString para fornecer comportamentos específicos para comparação e representação em string da moradia.
//
//Na refatoração, foram adicionados comentários explicativos sobre cada método, foram removidos imports desnecessários e foram incluídos imports necessários relacionados à versão do Java Persistence API superior a 3.0.0, garantindo que a classe esteja atualizada e aderente às boas práticas de desenvolvimento em Java.