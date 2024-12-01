package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.enums.Bebida;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.util.GeradorIdPedido;

public class BebidaOChaTest {

  private BebidaOCha bebidaOCha;
  private PedidoPequeno pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("Marcos", Tamanho.PEQUENO, Proteina.BOI);

    bebidaOCha = new BebidaOCha(pedidoBase);
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = bebidaOCha.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI, Bebida O-Cha",
        detalhes,
        "Testando se a bebida O-Cha foi aplicada corretamente");
  }

  @Test
  void testGetNomeCliente() {
    String nomeCliente = bebidaOCha.getNomeCliente();
    assertEquals("Marcos", nomeCliente, "Testando se o nome do cliente é o mesmo do pedido base");
  }

  @Test
  void testGetNumeroPedido() {
    int numeroPedido = bebidaOCha.getNumeroPedido();
    assertEquals(1, numeroPedido, "Testando se o número do pedido é o mesmo do pedido base");
  }

  @Test
  void testGetPrecoTotal() {
    double precoTotal = bebidaOCha.getPrecoTotal();
    assertEquals(
        pedidoBase.getPrecoTotal() + Bebida.O_CHA.getPreco(),
        precoTotal,
        "Testando se o preço total é o mesmo do pedido base mais o acréscimo da bebida O-Cha");
  }
}
