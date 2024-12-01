package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.util.GeradorIdPedido;
import br.lpm.business.model.enums.Acrescimo;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;

public class AcrescimoTofuTest {

  private AcrescimoTofu decorator;
  private PedidoPequeno pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("Marcos", Tamanho.PEQUENO, Proteina.BOI);
    decorator = new AcrescimoTofu(pedidoBase);
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = decorator.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI, Acréscimo de tofu",
        detalhes,
        "Testando se o acréscimo de tofu foi aplicado corretamente");
  }

  @Test
  void testGetPrecoTotal() {
    double precoTotal = decorator.getPrecoTotal();
    assertEquals(
        pedidoBase.getPrecoTotal() + Acrescimo.TOFU.getPreco(),
        precoTotal,
        "Testando se o preço total está correto após o acréscimo de tofu");
  }

  @Test
  void testGetNomeCliente() {
    String nomeCliente = decorator.getNomeCliente();
    assertEquals("Marcos", nomeCliente, "Testando se o nome do cliente é o mesmo do pedido base");
  }

  @Test
  void testGetNumeroPedido() {
    int numeroPedido = decorator.getNumeroPedido();
    assertEquals(1, numeroPedido, "Testando se o número do pedido é o mesmo do pedido base");
  }
}
