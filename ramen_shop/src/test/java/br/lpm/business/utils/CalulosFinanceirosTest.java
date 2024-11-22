package br.lpm.business.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.pedidos.PedidosSingleton;


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
    double expected=pedido1.getPrecoTotal()+pedido2.getPrecoTotal();
    assertEquals(expected, receitaTotal, 0.01, "Testando o cálculo da receita total");
  }

  @Test
  void testCalcularTicketMedio() {
    double receitaTotal = CalulosFinanceiros.calcularReceitaTotal(pedidosSingleton);
    double ticketMedio = CalulosFinanceiros.calcularTicketMedio(receitaTotal, pedidosSingleton);
    double expected = receitaTotal / pedidosSingleton.getPedidosConcluidos().size();
    assertEquals(expected, ticketMedio, 0.01, "Testando o cálculo do ticket médio");
  }
}
