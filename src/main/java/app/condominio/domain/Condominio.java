package app.condominio.domain;

import javax.persistence.*;

@Entity
@Table(name = "condominio")
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "endereco", nullable = false, length = 200)
    private String endereco;

    @Column(name = "numero_apartamentos", nullable = false)
    private int numeroApartamentos;

    @Column(name = "valor_condominio", nullable = false)
    private double valorCondominio;

    // Construtor padrão
    public Condominio() {
    }

    // Construtor com parâmetros
    public Condominio(String nome, String endereco, int numeroApartamentos, double valorCondominio) {
        this.nome = nome;
        this.endereco = endereco;
        this.numeroApartamentos = numeroApartamentos;
        this.valorCondominio = valorCondominio;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumeroApartamentos() {
        return numeroApartamentos;
    }

    public void setNumeroApartamentos(int numeroApartamentos) {
        this.numeroApartamentos = numeroApartamentos;
    }

    public double getValorCondominio() {
        return valorCondominio;
    }

    public void setValorCondominio(double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    // ToString
    @Override
    public String toString() {
        return "Condominio [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", numeroApartamentos="
                + numeroApartamentos + ", valorCondominio=" + valorCondominio + "]";
    }
}

/*
 * Classe Condominio representa um condomínio com seus atributos básicos, como
 * nome, endereço, número de apartamentos e valor do condomínio. A classe é
 * mapeada como uma entidade do banco de dados com a anotação @Entity e é
 * associada à tabela "condominio" com a anotação @Table(name = "condominio").
 * Os atributos da classe são mapeados como colunas do banco de dados com as
 * anotações @Column. A classe possui construtores, getters, setters e um
 * método toString para representação em string do objeto. O atributo id é
 * gerado automaticamente pelo banco de dados com a anotação @GeneratedValue.
 * Essa classe utiliza a versão do Java Persistence API superior a 3.0.0.
 */
