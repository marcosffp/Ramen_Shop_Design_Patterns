package br.lpm.business.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.ImplPedidoRepositorio;

public class ImplGerencimanetoPedidoTest {
  private GerenciamentoPedido gerenciamentoPedido;
  private PedidosSingleton pedidosSingleton;
  private Pedido pedido1;
  private Pedido pedido2;

  @BeforeEach
  public void setUp() {
    pedidosSingleton = PedidosSingleton.getInstancia();
    pedidosSingleton.getListaPedidos().clear();
    pedido1 = new PedidoPequeno("BOI", "Marcos", "1234");
    pedido2 = new PedidoMedio("PORCO", "Jamilly", "2345");
    pedidosSingleton.getListaPedidos().add(pedido1);
    pedidosSingleton.getListaPedidos().add(pedido2);
    gerenciamentoPedido = new ImplGerencimanetoPedido(pedidosSingleton, new ImplPedidoRepositorio());
  }
  
  @Test
  void testAdicionarPedido() {
    Pedido pedido = new PedidoPequeno("VEGANO", "Alvim", "3456");
    gerenciamentoPedido.adicionarPedido(pedido);
    assertEquals(3, pedidosSingleton.getListaPedidos().size(), "Testando se o pedido foi adicionado corretamente");
  }

  @Test
  void testRetirarPedido() {
    gerenciamentoPedido.retirarPedido("1234");
    assertEquals(1, pedidosSingleton.getListaPedidos().size(), "Testando se o pedido foi retirado corretamente");
  }

  @Test
  void testRetirarPedidoCozinha() {
    gerenciamentoPedido.retirarPedidoCozinha(2);
    assertEquals(2, pedidosSingleton.getListaPedidos().size(), "Testando se o pedido foi retirado corretamente");
  }
}
