package br.lpm.business.pedidos;
import java.util.Map;

import br.lpm.business.model.Pedido;
import br.lpm.business.utils.CalculoTempo;
import br.lpm.business.utils.CalulosFinanceiros;

public class PedidoRelatorios {
  private final PedidosSingleton pedidosSingleton;

  public PedidoRelatorios(PedidosSingleton pedidosSingleton) {
    this.pedidosSingleton = pedidosSingleton;
  }

  public void exibirProgressoPedidos() {
    System.out.println("Progresso dos Pedidos:");
    exibirPedidos("Em Espera", pedidosSingleton.getListaPedidos());
    exibirPedidos("\nConcluídos", pedidosSingleton.getPedidosConcluidos());
  }

  private void exibirPedidos(String titulo, Iterable<Pedido> pedidos) {
    System.out.println(titulo + ":");
    for (Pedido pedido : pedidos) {
      System.out.println("- Pedido #" + pedido.getNumeroPedido());
    }
  }

  public void exibirBalanco() {
    double receitaTotal = CalulosFinanceiros.calcularReceitaTotal(pedidosSingleton);
    Map<String, Long> itensVendidos = CalulosFinanceiros.calularItensVendidos(pedidosSingleton);
    double ticketMedio = CalulosFinanceiros.calcularTicketMedio(receitaTotal, pedidosSingleton);
    double tempoMedioPreparo = CalculoTempo.calcularTempoMedioPreparo(pedidosSingleton);

    exibirDetalhesPedidosConcluidos(itensVendidos);
    exibirResumoBalanco(receitaTotal, ticketMedio, tempoMedioPreparo, itensVendidos);
  }

  private void exibirDetalhesPedidosConcluidos(Map<String, Long> itensVendidos) {
    System.out.println("Detalhes dos pedidos concluídos:");
    for (Pedido pedido : pedidosSingleton.getPedidosConcluidos()) {
      System.out.println("- Número do Pedido: " + pedido.getNumeroPedido());
      System.out.println("  Descrição:");
      pedido.exibirDetalhes();
      System.out.println("  Valor Total: R$ " + String.format("%.2f", pedido.getPrecoTotal()));
      System.out.println();
    }
  }

  private void exibirResumoBalanco(double receitaTotal, double ticketMedio, double tempoMedioPreparo,
      Map<String, Long> itensVendidos) {
    System.out.println("Resumo do Balanço:");
    System.out.println("Número de pedidos concluídos: " + pedidosSingleton.getPedidosConcluidos().size());
    System.out.println("Receita total: R$ " + String.format("%.2f", receitaTotal));
    System.out.println("Ticket médio: R$ " + String.format("%.2f", ticketMedio));
    System.out.println("Tempo médio de preparo: " + String.format("%.2f", tempoMedioPreparo) + " segundos.");
    System.out.println("Itens mais vendidos:");
    itensVendidos.forEach((item, quantidade) -> System.out.println("- " + item + ": " + quantidade + " vendidos."));
  }
}
