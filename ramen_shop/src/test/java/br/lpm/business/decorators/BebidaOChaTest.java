package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.utils.GeradorIdPedido;

public class BebidaOChaTest {

  private BebidaDecorator decorator;
  private PedidoPequeno pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("BOI", "Marcos", "1234");
    decorator = new BebidaOCha(pedidoBase);
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = decorator.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI com a bebida O-Cha.",
        detalhes,
        "Testando se a bebida O-Cha foi adicionada corretamente ao pedido.");
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
    assertEquals(pedidoBase.getPrecoTotal() + 3.90, precoTotal, "Testando se o preço total é o valor correto.");
  }

  @Test
  void testGetSenhaCliente() {
    String senhaCliente = decorator.getSenhaCliente();
    assertEquals("1234", senhaCliente, "Testando se a senha do cliente é a mesma do pedido base.");
  }
}