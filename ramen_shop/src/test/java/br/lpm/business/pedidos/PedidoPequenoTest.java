package br.lpm.business.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.utils.GeradorIdPedido;

public class PedidoPequenoTest {

  private PedidoPequeno pedidoPequenoBoi;
  private PedidoPequeno pedidoPequenoPorco;
  private PedidoPequeno pedidoPequenoVegano;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoPequenoBoi = new PedidoPequeno("BOI", "Marcos", "1234");
    pedidoPequenoPorco = new PedidoPequeno("PORCO", "Alvim", "2345");
    pedidoPequenoVegano = new PedidoPequeno("VEGANO", "Jamilly", "3456");
  }

  @Test
  void testExibirDetalhes() {
    assertEquals("Pedido Pequeno com proteina: BOI", pedidoPequenoBoi.exibirDetalhes(), "Testando se o pedido pequeno com proteína de boi foi criado corretamente");
    assertEquals("Pedido Pequeno com proteina: PORCO", pedidoPequenoPorco.exibirDetalhes(), "Testando se o pedido pequeno com proteína de porco foi criado corretamente");
    assertEquals("Pedido Pequeno com proteina: VEGANO", pedidoPequenoVegano.exibirDetalhes(), "Testando se o pedido pequeno com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetNomeCliente() {
    assertEquals("Marcos", pedidoPequenoBoi.getNomeCliente(), "Testando se o nome do cliente do pedido pequeno com proteína de boi foi criado corretamente");
    assertEquals("Alvim", pedidoPequenoPorco.getNomeCliente(), "Testando se o nome do cliente do pedido pequeno com proteína de porco foi criado corretamente");
    assertEquals("Jamilly", pedidoPequenoVegano.getNomeCliente(), "Testando se o nome do cliente do pedido pequeno com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetNumeroPedido() {
    assertEquals(1, pedidoPequenoBoi.getNumeroPedido(), "Testando se o número do pedido pequeno com proteína de boi foi criado corretamente");
    assertEquals(2, pedidoPequenoPorco.getNumeroPedido(), "Testando se o número do pedido pequeno com proteína de porco foi criado corretamente");
    assertEquals(3, pedidoPequenoVegano.getNumeroPedido(), "Testando se o número do pedido pequeno com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetPrecoTotal() {
    assertEquals(9.90 + 7.90, pedidoPequenoBoi.getPrecoTotal(), 0.01, "Testando se o preço total do pedido pequeno com proteína de boi foi calculado corretamente"); 
    assertEquals(9.90 + 5.90, pedidoPequenoPorco.getPrecoTotal(), 0.01, "Testando se o preço total do pedido pequeno com proteína de porco foi calculado corretamente"); 
    assertEquals(9.90 + 3.90, pedidoPequenoVegano.getPrecoTotal(), 0.01, "Testando se o preço total do pedido pequeno com proteína vegana foi calculado corretamente"); 
  }

  @Test
  void testGetProteinaPedido() {
    assertEquals("BOI", pedidoPequenoBoi.getProteinaPedido(), "Testando se a proteína do pedido pequeno com proteína de boi foi criado corretamente");
    assertEquals("PORCO", pedidoPequenoPorco.getProteinaPedido(), "Testando se a proteína do pedido pequeno com proteína de porco foi criado corretamente");
    assertEquals("VEGANO", pedidoPequenoVegano.getProteinaPedido(), "Testando se a proteína do pedido pequeno com proteína vegana foi criado corretamente");
  }

  @Test
  void testGetSenhaCliente() {
    assertEquals("1234", pedidoPequenoBoi.getSenhaCliente(), "Testando se a senha do cliente do pedido pequeno com proteína de boi foi criado corretamente");
    assertEquals("2345", pedidoPequenoPorco.getSenhaCliente(), "Testando se a senha do cliente do pedido pequeno com proteína de porco foi criado corretamente");
    assertEquals("3456", pedidoPequenoVegano.getSenhaCliente(), "Testando se a senha do cliente do pedido pequeno com proteína vegana foi criado corretamente");
  }
}
