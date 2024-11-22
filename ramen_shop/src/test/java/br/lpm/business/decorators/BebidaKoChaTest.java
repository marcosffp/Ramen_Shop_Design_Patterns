package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.utils.GeradorIdPedido;

public class BebidaKoChaTest {

  private BebidaDecorator decorator;
  private PedidoPequeno pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("BOI", "Marcos", "1234");
    decorator = new BebidaKoCha(pedidoBase);
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = decorator.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI com a bebida Ko-Cha.",
        detalhes,
        "Testando se a bebida Ko-Cha foi adicionada corretamente ao pedido.");
  }

  @Test
  void testGetNomeCliente() {
    String nomeCliente = decorator.getNomeCliente();
    assertEquals("Marcos", nomeCliente, "Testando se o nome do cliente é o mesmo do pedido base.");
  }

  @Test
  void testGetNumeroPedido() {
    int numeroPedido = decorator.getNumeroPedido();
    assertEquals(1, numeroPedido, "Testando se o número do pedido é o mesmo do pedido base.");
  }

  @Test
  void testGetPrecoTotal() {
    double precoTotal = decorator.getPrecoTotal();
    assertEquals(pedidoBase.getPrecoTotal(), precoTotal, "Testando se o preço total permanece o mesmo.");
  }

  @Test
  void testGetSenhaCliente() {
    String senhaCliente = decorator.getSenhaCliente();
    assertEquals("1234", senhaCliente, "Testando se a senha do cliente é a mesma do pedido base.");
  }
}
