package br.lpm.business.pedidos;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.util.GeradorIdPedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListaPedidosTest {

  private ListaPedidos listaPedidos;
  private Pedido pedido1;
  private Pedido pedido2;

  @BeforeEach
  void setUp() throws RamenShopException {
    GeradorIdPedido.reset();
    listaPedidos = ListaPedidos.getInstance();
    listaPedidos.removerTodosPedidos();
    pedido1 = new PedidoMedio("Alvim", Tamanho.PEQUENO, Proteina.BOI);
    pedido2 = new PedidoGrande("Matheus", Tamanho.MEDIO, Proteina.BOI);
    listaPedidos.addPedido(pedido1);
    listaPedidos.addPedido(pedido2);

  }

  @Test
  void testAddPedido() throws RamenShopException {
    Pedido pedido3 = new PedidoPequeno("Alvim", Tamanho.PEQUENO, Proteina.BOI);
    listaPedidos.addPedido(pedido3);
    assertEquals(3, listaPedidos.getQuantidadePedidos(),
        "Testando se adicionou um pedido na lista de pedidos");

    assertThrows(RamenShopException.class, () -> {
      listaPedidos.addPedido(pedido1);
    }, "Testando se não adicionou um pedido duplicado na lista de pedidos");

    assertThrows(RamenShopException.class, () -> {
      listaPedidos.addPedido(null);
    }, "Testando se não adicionou um pedido nulo na lista de pedidos");

  }

  @Test
  void testGetQuantidadePedidos() throws RamenShopException {
    assertEquals(2, listaPedidos.getQuantidadePedidos(),
        "Testando se a quantidade de pedidos é 2 após adicionar dois pedidos");
  }

  @Test
  void testRemoverTodosPedidos() {
    listaPedidos.removerTodosPedidos();
    assertEquals(0, listaPedidos.getQuantidadePedidos(),
        "Testando se a quantidade de pedidos é 0 após remover todos os pedidos");
  }

  @Test
  void testSingletonInstance() {
    ListaPedidos novaLista = ListaPedidos.getInstance();
    assertSame(listaPedidos, novaLista, "Testando se ListaPedidos é um singleton");
  }
}
