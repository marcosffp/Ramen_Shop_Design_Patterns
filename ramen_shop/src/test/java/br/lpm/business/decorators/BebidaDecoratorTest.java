package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.util.GeradorIdPedido;

public class BebidaDecoratorTest {

  private BebidaDecorator decorator;
  private PedidoPequeno pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("Marcos", Tamanho.PEQUENO, Proteina.BOI);

    decorator = new BebidaDecorator(pedidoBase) {
      @Override
      public String exibirDetalhes() {
        return pedidoBase.exibirDetalhes() + ", Bebida de refrigerante.";
      }

      @Override
      public double getPrecoTotal() {
        return pedidoBase.getPrecoTotal() + 3.00; 
      }
    };
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = decorator.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI, Bebida de refrigerante.",
        detalhes,
        "Testando se o acréscimo de refrigerante foi aplicado corretamente");
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

  @Test
  void testGetPrecoTotal() {
    double precoTotal = decorator.getPrecoTotal();
    assertEquals(
        pedidoBase.getPrecoTotal() + 3.00,
        precoTotal,
        "Testando se o preço total é o mesmo do pedido base mais o acréscimo de refrigerante");
  }
}
