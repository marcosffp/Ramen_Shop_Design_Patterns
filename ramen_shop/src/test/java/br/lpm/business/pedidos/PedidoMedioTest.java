package br.lpm.business.pedidos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.utils.GeradorIdPedido;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoMedioTest {

  private PedidoMedio pedidoMedioBoi;
  private PedidoMedio pedidoMedioPorco;
  private PedidoMedio pedidoMedioVegano;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoMedioBoi = new PedidoMedio("BOI", "Marcos", "1234");
    pedidoMedioPorco = new PedidoMedio("PORCO", "Alvim", "2345");
    pedidoMedioVegano = new PedidoMedio("VEGANO", "Jamilly", "3456");
  }

  @Test
  void testExibirDetalhes() {
    assertEquals("Pedido Médio com proteina: BOI", pedidoMedioBoi.exibirDetalhes(),
        "Testando se o pedido médio com proteína de boi foi criado corretamente");
    assertEquals("Pedido Médio com proteina: PORCO", pedidoMedioPorco.exibirDetalhes(),
        "Testando se o pedido médio com proteína de porco foi criado corretamente");
    assertEquals("Pedido Médio com proteina: VEGANO", pedidoMedioVegano.exibirDetalhes(),
        "Testando se o pedido médio com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetNomeCliente() {
    assertEquals("Marcos", pedidoMedioBoi.getNomeCliente(),
        "Testando se o nome do cliente do pedido médio com proteína de boi foi criado corretamente");
    assertEquals("Alvim", pedidoMedioPorco.getNomeCliente(),
        "Testando se o nome do cliente do pedido médio com proteína de porco foi criado corretamente");
    assertEquals("Jamilly", pedidoMedioVegano.getNomeCliente(),
        "Testando se o nome do cliente do pedido médio com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetNumeroPedido() {
    assertEquals(1, pedidoMedioBoi.getNumeroPedido(),
        "Testando se o número do pedido médio com proteína de boi foi criado corretamente");
    assertEquals(2, pedidoMedioPorco.getNumeroPedido(),
        "Testando se o número do pedido médio com proteína de porco foi criado corretamente");
    assertEquals(3, pedidoMedioVegano.getNumeroPedido(),
        "Testando se o número do pedido médio com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetPrecoTotal() {
    assertEquals(12.90 + 7.90, pedidoMedioBoi.getPrecoTotal(), 0.01,
        "Testando se o preço total do pedido médio com proteína de boi foi calculado corretamente");
    assertEquals(12.90 + 5.90, pedidoMedioPorco.getPrecoTotal(), 0.01,
        "Testando se o preço total do pedido médio com proteína de porco foi calculado corretamente");
    assertEquals(12.90 + 3.90, pedidoMedioVegano.getPrecoTotal(), 0.01,
        "Testando se o preço total do pedido médio com proteína vegana foi calculado corretamente");
  }

  @Test
  void testGetProteinaPedido() {
    assertEquals("BOI", pedidoMedioBoi.getProteinaPedido(),
        "Testando se a proteína do pedido médio com proteína de boi foi criado corretamente");
    assertEquals("PORCO", pedidoMedioPorco.getProteinaPedido(),
        "Testando se a proteína do pedido médio com proteína de porco foi criado corretamente");
    assertEquals("VEGANO", pedidoMedioVegano.getProteinaPedido(),
        "Testando se a proteína do pedido médio com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetSenhaCliente() {
    assertEquals("1234", pedidoMedioBoi.getSenhaCliente(),
        "Testando se a senha do cliente do pedido médio com proteína de boi foi criado corretamente");
    assertEquals("2345", pedidoMedioPorco.getSenhaCliente(),
        "Testando se a senha do cliente do pedido médio com proteína de porco foi criado corretamente");
    assertEquals("3456", pedidoMedioVegano.getSenhaCliente(),
        "Testando se a senha do cliente do pedido médio com proteína vegana foi criado corretamente");
  }
}