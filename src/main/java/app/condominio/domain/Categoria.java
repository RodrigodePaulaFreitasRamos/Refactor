package app.condominio.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import app.condominio.domain.enums.TipoCategoria;

@SuppressWarnings("serial")
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable, Comparable<Categoria> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcategoria")
	private Long idCategoria;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCategoria tipo;

	@Size(min = 1, max = 50)
	@NotBlank
	private String descricao;

	@Max(4)
	private Integer nivel;

	@Size(min = 1, max = 255)
	@NotBlank
	private String ordem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcategoriapai")
	private Categoria categoriaPai;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;

	@OneToMany(mappedBy = "categoriaPai", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Categoria> categoriasFilhas = new ArrayList<>();

	@OneToMany(mappedBy = "categoriaPai", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy(value = "descricao")
	private List<Subcategoria> subcategorias = new ArrayList<>();

	// Construtores

	public Categoria() {
	}

	// Getters e Setters

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public TipoCategoria getTipo() {
		return tipo;
	}

	public void setTipo(TipoCategoria tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public List<Categoria> getCategoriasFilhas() {
		return categoriasFilhas;
	}

	public void setCategoriasFilhas(List<Categoria> categoriasFilhas) {
		this.categoriasFilhas = categoriasFilhas;
	}

	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

// Métodos de Comparação e Ordenação

	@Override
	public int compareTo(Categoria outraCategoria) {
		if (this.nivel == outraCategoria.getNivel()) {
			return this.descricao.compareTo(outraCategoria.getDescricao());
		}
		return this.nivel.compareTo(outraCategoria.getNivel());
	}

	// Métodos de Manipulação das Listas

	public void adicionarCategoriaFilha(Categoria categoriaFilha) {
		this.categoriasFilhas.add(categoriaFilha);
	}

	public void removerCategoriaFilha(Categoria categoriaFilha) {
		this.categoriasFilhas.remove(categoriaFilha);
	}

	public void adicionarSubcategoria(Subcategoria subcategoria) {
		this.subcategorias.add(subcategoria);
	}

	public void removerSubcategoria(Subcategoria subcategoria) {
		this.subcategorias.remove(subcategoria);
	}

	// Método Equals e HashCode

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Categoria))
			return false;
		Categoria outraCategoria = (Categoria) obj;
		return this.idCategoria != null && this.idCategoria.equals(outraCategoria.getIdCategoria());
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// toString

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", tipo=" + tipo + ", descricao=" + descricao + ", nivel="
				+ nivel + ", ordem=" + ordem + "]";
	}

	// Comentário de Explicação

	/*
	 * Classe Categoria é uma entidade JPA que representa as categorias de um
	 * sistema de condomínio. Ela possui os atributos necessários para descrever uma
	 * categoria, como tipo, descrição, nível e ordem. Além disso, possui
	 * relacionamentos com outras entidades, como CategoriaPai, Condominio,
	 * CategoriasFilhas e Subcategorias.
	 *
	 * A classe implementa a interface Serializable e Comparable<Categoria> para
	 * permitir a serialização e comparação de objetos do tipo Categoria. Ela também
	 * possui anotações JPA para mapeamento das colunas do banco de dados, como
	 * @Entity, @Table, @Id, @GeneratedValue, @Column, @ManyToOne e @OneToMany.
	 *
	 * Foram adicionadas validações com as anotações @NotNull, @Size, @NotBlank e
	 * @Max para garantir a integridade dos dados armazenados nas colunas do banco
	 * de dados.
	 *
	 * A classe possui métodos de manipulação das listas de categorias filhas e
	 * subcategorias, como adicionar e remover. Também implementa os métodos equals,
	 * hashCode e toString para possibilitar a comparação, identificação e
	 * representação em forma de string dos objetos do tipo Categoria.
	 *
	 * Além disso, foi atualizado o uso das versões mais recentes do Java Persistence
	 * API, corrigido os imports desnecessários e incluídos os imports necessários
	 * para a correta execução do código.
	 */
}
