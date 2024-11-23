package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.utils.GeradorIdPedido;

public class AcrescimoTofuTest {

  private AcrescimoDecorator decorator;
  private Pedido pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("BOI", "Marcos", "1234");
    decorator = new AcrescimoTofu(pedidoBase);
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = decorator.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI com acréscimo de tofu.",
        detalhes,
        "Testando se o acréscimo de tofu foi aplicado corretamente.");
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
    assertEquals(
        pedidoBase.getPrecoTotal() + 2.70,
        precoTotal,
        "Testando se o preço total é o mesmo do pedido base mais o acréscimo de tofu.");
  }

  @Test
  void testGetSenhaCliente() {
    String senhaCliente = decorator.getSenhaCliente();
    assertEquals("1234", senhaCliente, "Testando se a senha do cliente é a mesma do pedido base.");
  }

  @Test
  void testGetQuantidadeItens() {
    int quantidadeItens = decorator.getQuantidadeItens();
    assertEquals(2, quantidadeItens, "Testando se a quantidade de itens é a mesma do pedido base.");
  }
}
