package br.com.pedhped.jpa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/*N�O ESQUE�A DE PERSISTIR ESTA CLASSE MOVIMENTA��O NO persistence.xml*/

@Entity
public class Movimentacao {
	
	/*Gera��o da primary key "Id" auto_increment do tipo Long*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/* TipoMovimentacao � um ENUM: ao utilizar enums limitamos os valores que podem ser atribu�dos a uma vari�vel. */
	@Enumerated(EnumType.STRING) /*define qual valor do ENUM ser� salvo no banco*/
	private TipoMovimentacao tipoMovimentacao = TipoMovimentacao.ENTRADA; //ENTRADA ou SAIDA
	
	private LocalDateTime data;
	private String descricao;
	private BigDecimal valor;

	/*@ManyToMany: relacionamento N:N*/
	@ManyToMany
	private List<Categoria> categorias;

	/*VINCULANDO Movimentacao.java a Conta.java
		A JPA criar� chaves prim�rias, chaves estrangeiras e etc.
		
		@ManyToOne: Singnifica que existe v�rias movimenta��es para uma conta.
	 */
	@ManyToOne
	private Conta conta; 
		
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
