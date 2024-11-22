package br.lpm.business.pedidos;

import br.lpm.business.model.Pedido;
import br.lpm.business.observer.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidosSingletonTest {

  private PedidosSingleton pedidosSingleton;
  private Subject cozinha;

  @BeforeEach
  void setUp() {
    pedidosSingleton = PedidosSingleton.getInstancia();
    cozinha = pedidosSingleton.getCozinha();
  }

  @Test
  void testGetInstancia() {
    PedidosSingleton instancia1 = PedidosSingleton.getInstancia();
    PedidosSingleton instancia2 = PedidosSingleton.getInstancia();
    assertEquals(instancia1, instancia2, "Testando se a instância é única");
  }

  @Test
  void testGetCozinha() {
    assertEquals(cozinha, pedidosSingleton.getCozinha(), "Testando se a cozinha é um objeto do tipo Subject");
  }

  @Test
  void testGetListaPedidos() {
    assertTrue(pedidosSingleton.getListaPedidos().isEmpty(), "Testando se a lista de pedidos está vazia inicialmente");
    Pedido pedido = new PedidoPequeno(null, null, null);
    pedidosSingleton.getListaPedidos().add(pedido);
    assertFalse(pedidosSingleton.getListaPedidos().isEmpty(),
        "Testando se a lista de pedidos não está vazia após adicionar um pedido");
  }

  @Test
  void testGetPedidosConcluidos() {
    assertTrue(pedidosSingleton.getPedidosConcluidos().isEmpty(),
        "Testando se a lista de pedidos concluídos está vazia inicialmente");
     Pedido pedido = new PedidoPequeno(null, null, null);
    pedidosSingleton.getPedidosConcluidos().add(pedido);
    assertFalse(pedidosSingleton.getPedidosConcluidos().isEmpty(),
        "Testando se a lista de pedidos concluídos não está vazia após adicionar um pedido");
  }
}
