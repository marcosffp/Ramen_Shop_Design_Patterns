package br.lpm.business.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.Pedido;

public class PedidoFactoryTest {
  private PedidoFactory pedidoFactory;

  @BeforeEach
  void setUp() {
    pedidoFactory = new PedidoFactory();
  }

  @Test
  void testCriarPedido() {
    Pedido pedidoPequeno = pedidoFactory.criarPedido("PEQUENO", "BOI", "Marcos");
    Pedido pedidoMedio = pedidoFactory.criarPedido("MEDIO", "FRANGO", "Alvim");
    Pedido pedidoGrande = pedidoFactory.criarPedido("GRANDE", "PORCO", "Jamilly");

    assertEquals("Marcos", pedidoPequeno.getNomeCliente(), "Testando se o pedido pequeno foi criado corretamente");

    assertEquals("Alvim", pedidoMedio.getNomeCliente(), "Testando se o pedido médio foi criado corretamente");

    assertEquals("Jamilly", pedidoGrande.getNomeCliente(), "Testando se o pedido grande foi criado corretamente");
  }
}
