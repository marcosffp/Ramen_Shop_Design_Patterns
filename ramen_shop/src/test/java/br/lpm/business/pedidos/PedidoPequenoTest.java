package br.lpm.business.pedidos;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.util.GeradorIdPedido;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class PedidoPequenoTest {
  Pedido pedidoPequeno;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoPequeno = new PedidoPequeno("Marcos", Tamanho.PEQUENO, Proteina.BOI);
  }

  @Test
  void testExibirDetalhes() {
    String detalhesEsperados = "Pedido Pequeno com proteina: BOI";
    assertEquals(detalhesEsperados, pedidoPequeno.exibirDetalhes(),
        "Testando se o exibirDetalhes() está correto");
  }
  
  @Test
  void testGetNumeroPedido() {
    assertEquals(1, pedidoPequeno.getNumeroPedido(), "Testando se o número do pedido está correto");
  }

  @Test 
  void testGetTamanho() {
    assertEquals(Tamanho.PEQUENO, pedidoPequeno.getTamanhoPedido(), "Testando se o tamanho do pedido está correto");
  }

  @Test
  void testGetProteina() {
    assertEquals(Proteina.BOI, pedidoPequeno.getProteinaPedido(), "Testando se a proteína do pedido está correta");
  }

  @Test
  void testGetPrecoTotal() {
    double precoEsperado = Tamanho.PEQUENO.getPreco() + Proteina.BOI.getPreco();
    assertEquals(precoEsperado, pedidoPequeno.getPrecoTotal(),
        "Testando se o preço total está correto");
  }

  @Test
  void testSetStatusPedido() {
    pedidoPequeno.setStatusPedido(Status.EM_PREPARO);
    assertEquals(Status.EM_PREPARO, pedidoPequeno.getStatusPedido(),
        "Testando se o status do pedido foi alterado corretamente");

    pedidoPequeno.setStatusPedido(Status.RETIRADO);
    assertEquals(Status.RETIRADO, pedidoPequeno.getStatusPedido(),
        "Testando se o status do pedido foi alterado para RETIRADO");
  }

}
