package br.lpm.business.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.enums.Bebida;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.util.GeradorIdPedido;

public class BebidaKoChaTest {

  private BebidaKoCha bebidaKoCha;
  private PedidoPequeno pedidoBase;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoBase = new PedidoPequeno("Marcos", Tamanho.PEQUENO, Proteina.BOI);

    bebidaKoCha = new BebidaKoCha(pedidoBase);
  }

  @Test
  void testExibirDetalhes() {
    String detalhes = bebidaKoCha.exibirDetalhes();
    assertEquals(
        "Pedido Pequeno com proteina: BOI com a bebida Ko-Cha.",
        detalhes,
        "Testando se a bebida Ko-Cha foi aplicada corretamente");
  }

  @Test
  void testGetNomeCliente() {
    String nomeCliente = bebidaKoCha.getNomeCliente();
    assertEquals("Marcos", nomeCliente, "Testando se o nome do cliente é o mesmo do pedido base");
  }

  @Test
  void testGetNumeroPedido() {
    int numeroPedido = bebidaKoCha.getNumeroPedido();
    assertEquals(1, numeroPedido, "Testando se o número do pedido é o mesmo do pedido base");
  }

  @Test
  void testGetPrecoTotal() {
    double precoTotal = bebidaKoCha.getPrecoTotal();
    assertEquals(
        pedidoBase.getPrecoTotal() + Bebida.KO_CHA.getPreco(),
        precoTotal,
        "Testando se o preço total é o mesmo do pedido base mais o acréscimo da bebida Ko-Cha");
  }
}
