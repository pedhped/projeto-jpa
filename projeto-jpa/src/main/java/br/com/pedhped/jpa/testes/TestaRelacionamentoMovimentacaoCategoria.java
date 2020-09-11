package br.com.pedhped.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pedhped.jpa.modelo.Categoria;
import br.com.pedhped.jpa.modelo.Conta;
import br.com.pedhped.jpa.modelo.Movimentacao;
import br.com.pedhped.jpa.modelo.TipoMovimentacao;

public class TestaRelacionamentoMovimentacaoCategoria {

		
	public static void main(String[] args) {
		
		Categoria ct = new Categoria("Viagem");		
		Categoria ct2 = new Categoria("Negócios");
		
		Conta conta = new Conta();
		conta.setId(1L);
		
		Movimentacao mov1 = new Movimentacao();
		mov1.setDescricao("Viagem a São-Paulo");
		mov1.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		mov1.setData(LocalDateTime.now());
		mov1.setValor(new BigDecimal(250.55));
		mov1.setCategorias(Arrays.asList(ct, ct2));
		mov1.setConta(conta);
		
		Movimentacao mov2 = new Movimentacao();
		mov2.setDescricao("Viagem ao Rio de Janeiro");
		mov2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		mov2.setData(LocalDateTime.now());
		mov2.setValor(new BigDecimal(400.55));
		mov2.setCategorias(Arrays.asList(ct, ct2));/*Cria uma lista de forma mais simples com Arrays.asList*/
		mov2.setConta(conta);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		//Insere as categorias
		em.persist(ct);
		em.persist(ct2);
		
		//Insere as movimentações
		em.persist(mov1);
		em.persist(mov2);
		em.getTransaction().commit();
		
		em.close();
		
	}	
	
}
