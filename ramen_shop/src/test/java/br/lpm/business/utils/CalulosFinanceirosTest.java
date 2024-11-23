package br.lpm.business.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.pedidos.PedidosSingleton;

import java.util.Map;

public class CalulosFinanceirosTest {

  private PedidosSingleton pedidosSingleton;
  private Pedido pedido1;
  private Pedido pedido2;

  @BeforeEach
  public void setUp() {
    pedidosSingleton = PedidosSingleton.getInstancia();
    pedidosSingleton.getPedidosConcluidos().clear();

    pedido1 = new PedidoPequeno("BOI", "Marcos", "1234");
    pedido2 = new PedidoMedio("PORCO", "Jamilly", "2345");

    pedidosSingleton.getPedidosConcluidos().add(pedido1);
    pedidosSingleton.getPedidosConcluidos().add(pedido2);
  }

  @Test
  void testCalcularReceitaTotal() {
    double receitaTotal = CalulosFinanceiros.calcularReceitaTotal(pedidosSingleton);
    double expected = pedido1.getPrecoTotal() + pedido2.getPrecoTotal();
    assertEquals(expected, receitaTotal, 0.01, "Testando o cálculo da receita total");
  }

  @Test
  void testCalcularTicketMedio() {
    double receitaTotal = CalulosFinanceiros.calcularReceitaTotal(pedidosSingleton);
    double ticketMedio = CalulosFinanceiros.calcularTicketMedio(receitaTotal, pedidosSingleton);
    double expected = receitaTotal / pedidosSingleton.getPedidosConcluidos().size();
    assertEquals(expected, ticketMedio, 0.01, "Testando o cálculo do ticket médio");
  }

  @Test
  void testCalcularTicketMedioPorPedido() {
    Map<Integer, Double> ticketMedioPorPedido = CalulosFinanceiros.calcularTicketMedioPorPedido(pedidosSingleton);

    double ticketMedioPedido1 = pedido1.getPrecoTotal() / (double) pedido1.getQuantidadeItens();
    double ticketMedioPedido2 = pedido2.getPrecoTotal() / (double) pedido2.getQuantidadeItens();

    assertEquals(ticketMedioPedido1, ticketMedioPorPedido.get(pedido1.getNumeroPedido()), 0.01,
        "Testando ticket médio do pedido 1");
    assertEquals(ticketMedioPedido2, ticketMedioPorPedido.get(pedido2.getNumeroPedido()), 0.01,
        "Testando ticket médio do pedido 2");
  }

  @Test
  void testCalcularTicketUltimoPedido() {
    double ticketUltimoPedido = CalulosFinanceiros.calcularTicketUltimoPedido(pedidosSingleton);

    double expected = pedido2.getPrecoTotal() / (double) pedido2.getQuantidadeItens();
    assertEquals(expected, ticketUltimoPedido, 0.01, "Testando o cálculo do ticket do último pedido");
  }

}
