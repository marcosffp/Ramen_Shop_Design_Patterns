package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.enums.Bebida;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.util.GeradorIdPedido;

public class BebidaRefrigeranteTest {

  private BebidaRefrigerante bebidaRefrigerante;
  private PedidoPequeno pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("Marcos", Tamanho.PEQUENO, Proteina.BOI);

    bebidaRefrigerante = new BebidaRefrigerante(pedidoBase);
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = bebidaRefrigerante.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI, Bebida Refrigerante",
        detalhes,
        "Testando se a bebida Refrigerante foi aplicada corretamente");
  }

  @Test
  void testGetNomeCliente() {
    String nomeCliente = bebidaRefrigerante.getNomeCliente();
    assertEquals("Marcos", nomeCliente, "Testando se o nome do cliente é o mesmo do pedido base");
  }

  @Test
  void testGetNumeroPedido() {
    int numeroPedido = bebidaRefrigerante.getNumeroPedido();
    assertEquals(1, numeroPedido, "Testando se o número do pedido é o mesmo do pedido base");
  }

  @Test
  void testGetPrecoTotal() {
    double precoTotal = bebidaRefrigerante.getPrecoTotal();
    assertEquals(
        pedidoBase.getPrecoTotal() + Bebida.REFRIGERANTE.getPreco(),
        precoTotal,
        "Testando se o preço total é o mesmo do pedido base mais o acréscimo da bebida Refrigerante");
  }
}
