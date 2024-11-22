package br.lpm.business.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoGrandeTest {
  private PedidoGrande pedidoGrandeBoi;
  private PedidoGrande pedidoGrandePorco;
  private PedidoGrande pedidoGrandeVegano;

  @BeforeEach
  void setUp() {
    PedidoGrande.resetContador();
    pedidoGrandeBoi = new PedidoGrande("BOI", "Marcos", "1234");
    pedidoGrandePorco = new PedidoGrande("PORCO", "Alvim", "2345");
    pedidoGrandeVegano = new PedidoGrande("VEGANO", "Jamilly", "3456");
  }

  @Test
  void testExibirDetalhes() {
    assertEquals("Pedido Grande com proteina: BOI", pedidoGrandeBoi.exibirDetalhes(),
        "Testando se o pedido grande com proteína de boi foi criado corretamente");
    assertEquals("Pedido Grande com proteina: PORCO", pedidoGrandePorco.exibirDetalhes(),
        "Testando se o pedido grande com proteína de porco foi criado corretamente");
    assertEquals("Pedido Grande com proteina: VEGANO", pedidoGrandeVegano.exibirDetalhes(),
        "Testando se o pedido grande com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetNomeCliente() {
    assertEquals("Marcos", pedidoGrandeBoi.getNomeCliente(),
        "Testando se o nome do cliente do pedido grande com proteína de boi foi criado corretamente");
    assertEquals("Alvim", pedidoGrandePorco.getNomeCliente(),
        "Testando se o nome do cliente do pedido grande com proteína de porco foi criado corretamente");
    assertEquals("Jamilly", pedidoGrandeVegano.getNomeCliente(),
        "Testando se o nome do cliente do pedido grande com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetNumeroPedido() {
    assertEquals(1, pedidoGrandeBoi.getNumeroPedido(),
        "Testando se o número do pedido grande com proteína de boi foi criado corretamente");
    assertEquals(2, pedidoGrandePorco.getNumeroPedido(),
        "Testando se o número do pedido grande com proteína de porco foi criado corretamente");
    assertEquals(3, pedidoGrandeVegano.getNumeroPedido(),
        "Testando se o número do pedido grande com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetPrecoTotal() {
    assertEquals(15.90+ 7.90, pedidoGrandeBoi.getPrecoTotal(), 0.01,
        "Testando se o preço total do pedido grande com proteína de boi foi calculado corretamente");
    assertEquals(15.90 + 5.90, pedidoGrandePorco.getPrecoTotal(), 0.01,
        "Testando se o preço total do pedido grande com proteína de porco foi calculado corretamente");
    assertEquals(15.90 + 3.90, pedidoGrandeVegano.getPrecoTotal(), 0.01,
        "Testando se o preço total do pedido grande com proteína vegana foi calculado corretamente");
  }

  @Test
  void testGetProteinaPedido() {
    assertEquals("BOI", pedidoGrandeBoi.getProteinaPedido(),
        "Testando se a proteína do pedido grande com proteína de boi foi criada corretamente");
    assertEquals("PORCO", pedidoGrandePorco.getProteinaPedido(),
        "Testando se a proteína do pedido grande com proteína de porco foi criada corretamente");
    assertEquals("VEGANO", pedidoGrandeVegano.getProteinaPedido(),
        "Testando se a proteína do pedido grande com proteína vegana foi criada corretamente");
  }

  @Test
  void testGetSenhaCliente() {
    assertEquals("1234", pedidoGrandeBoi.getSenhaCliente(),
        "Testando se a senha do cliente do pedido grande com proteína de boi foi criada corretamente");
    assertEquals("2345", pedidoGrandePorco.getSenhaCliente(),
        "Testando se a senha do cliente do pedido grande com proteína de porco foi criada corretamente");
    assertEquals("3456", pedidoGrandeVegano.getSenhaCliente(),
        "Testando se a senha do cliente do pedido grande com proteína vegana foi criada corretamente");
  }

  @Test
  void testResetContador() {
    new PedidoGrande("BOI", "Marcos", "1234");
    new PedidoGrande("PORCO", "Alvim", "4565");
    new PedidoGrande("VEGANO", "Jamilly", "7896");
    PedidoGrande.resetContador();
    PedidoGrande novoPedido = new PedidoGrande("BOI", "Carlos", "000");
    assertEquals(1, novoPedido.getNumeroPedido(), "Testando se o contador foi resetado corretamente");
  }
}
