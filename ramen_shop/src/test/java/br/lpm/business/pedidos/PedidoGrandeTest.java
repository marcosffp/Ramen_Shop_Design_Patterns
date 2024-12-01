package br.lpm.business.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.util.GeradorIdPedido;

public class PedidoGrandeTest {
Pedido pedidoGrande;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoGrande = new PedidoGrande("Marcos", Tamanho.GRANDE, Proteina.BOI);
  }

  @Test
  void testExibirDetalhes() {
    String detalhesEsperados = "Pedido Grande com proteina: BOI";
    assertEquals(detalhesEsperados, pedidoGrande.exibirDetalhes(),
        "Testando se o exibirDetalhes() está correto");
  }
  
  @Test
  void testGetNumeroPedido() {
    assertEquals(1, pedidoGrande.getNumeroPedido(), "Testando se o número do pedido está correto");
  }

  @Test 
  void testGetTamanho() {
    assertEquals(Tamanho.GRANDE, pedidoGrande.getTamanhoPedido(), "Testando se o tamanho do pedido está correto");
  }

  @Test
  void testGetProteina() {
    assertEquals(Proteina.BOI, pedidoGrande.getProteinaPedido(), "Testando se a proteína do pedido está correta");
  }

    @Test
  void testGetPrecoTotal() {
    double precoEsperado = Tamanho.GRANDE.getPreco() + Proteina.BOI.getPreco();
    assertEquals(precoEsperado, pedidoGrande.getPrecoTotal(),
        "Testando se o preço total está correto");
  }

  @Test
  void testSetStatusPedido() {
    pedidoGrande.setStatusPedido(Status.EM_PREPARO);
    assertEquals(Status.EM_PREPARO, pedidoGrande.getStatusPedido(),
        "Testando se o status do pedido foi alterado corretamente");

    pedidoGrande.setStatusPedido(Status.RETIRADO);
    assertEquals(Status.RETIRADO, pedidoGrande.getStatusPedido(),
        "Testando se o status do pedido foi alterado para RETIRADO");
  }
}

