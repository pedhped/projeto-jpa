package br.com.pedhped.jpa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/*NÃO ESQUEÇA DE PERSISTIR ESTA CLASSE MOVIMENTAÇÃO NO persistence.xml*/

@Entity
public class Movimentacao {
	
	/*Geração da primary key "Id" auto_increment do tipo Long*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/* TipoMovimentacao é um ENUM: ao utilizar enums limitamos os valores que podem ser atribuídos a uma variável. */
	@Enumerated(EnumType.STRING) /*define qual valor do ENUM será salvo no banco*/
	private TipoMovimentacao tipoMovimentacao = TipoMovimentacao.ENTRADA; //ENTRADA ou SAIDA
	
	private LocalDateTime data;
	private String descricao;
	private BigDecimal valor;

	
	/*VINCULANDO Movimentacao.java a Conta.java
		A JPA criará chaves primárias, chaves estrangeiras e etc.
		
		@ManyToOne: Singnifica que existe várias movimentações para uma conta.
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
	public LocalDateTime getDate() {
		return data;
	}
	public void setDate(LocalDateTime date) {
		this.data = date;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}
