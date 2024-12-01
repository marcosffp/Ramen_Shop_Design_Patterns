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
  }

  @Test
  void testAddPedidoDuplicado() {
    RamenShopException exception = assertThrows(RamenShopException.class, () -> {
      listaPedidos.addPedido(pedido1); 
    });
    assertEquals("Já existe um pedido com o número " + pedido1.getNumeroPedido() + ".", exception.getMessage(),
        "Testando se não adicionou um pedido duplicado na lista de pedidos");
  }

  @Test
  void testAddPedidoNulo() {
    RamenShopException exception = assertThrows(RamenShopException.class, () -> {
      listaPedidos.addPedido(null);
    });
    assertEquals("O pedido não pode ser nulo.", exception.getMessage(),
        "Testando se não adicionou um pedido nulo na lista de pedidos");
  }

  @Test
  void testRetirarPedidoPorNumero() throws RamenShopException {
    Pedido pedidoRetirado = listaPedidos.retirarPedido(1);
    assertEquals(pedido1, pedidoRetirado, "Testando se retirou o pedido correto com o número 1");
    assertEquals(1, listaPedidos.getQuantidadePedidos(),
        "Testando se a quantidade de pedidos é 1 após retirar um pedido");
  }

  @Test
  void testRetirarPedidoPorNumeroNaoExistente() throws RamenShopException {
    RamenShopException exception = assertThrows(RamenShopException.class, () -> {
      listaPedidos.retirarPedido(999); 
    });
    assertEquals("Número do pedido inválido.", exception.getMessage(),
        "Testando se não retirou um pedido com número não existente");
  }

  @Test
  void testGetPedidoPorNumero() throws RamenShopException {
    Pedido pedidoBuscado = listaPedidos.getPedido(2);
    assertEquals(pedido2, pedidoBuscado, "Testando se retornou o pedido correto com o número 2");
  }

  @Test
  void testGetPedidoPorNumeroNaoExistente() throws RamenShopException {
    RamenShopException exception = assertThrows(RamenShopException.class, () -> {
      listaPedidos.getPedido(999); 
    });
    assertEquals("Pedido com número 999 não encontrado.", exception.getMessage(),
        "Testando se não retornou um pedido com número não existente");
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
