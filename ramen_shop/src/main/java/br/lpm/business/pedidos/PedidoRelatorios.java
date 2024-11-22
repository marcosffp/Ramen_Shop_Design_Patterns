package br.lpm.business.pedidos;

import javax.swing.*;
import java.util.Map;
import br.lpm.business.model.Pedido;
import br.lpm.business.utils.CalulosFinanceiros;

public class PedidoRelatorios {
  private final PedidosSingleton pedidosSingleton;

  public PedidoRelatorios(PedidosSingleton pedidosSingleton) {
    this.pedidosSingleton = pedidosSingleton;
  }

  public void exibirProgressoPedidos() {
    StringBuilder progresso = new StringBuilder("Progresso dos Pedidos:\n");
    progresso.append(exibirPedidos("Em Espera", pedidosSingleton.getListaPedidos()));
    progresso.append(exibirPedidos("\nConcluídos", pedidosSingleton.getPedidosConcluidos()));

    JOptionPane.showMessageDialog(null, progresso.toString(), "Progresso dos Pedidos", JOptionPane.INFORMATION_MESSAGE);
  }

  private String exibirPedidos(String titulo, Iterable<Pedido> pedidos) {
    StringBuilder detalhes = new StringBuilder(titulo).append(":\n");
    for (Pedido pedido : pedidos) {
      detalhes.append("- Pedido #").append(pedido.getNumeroPedido()).append("\n");
    }
    return detalhes.toString();
  }

  public void exibirBalanco() {
    exibirDetalhesBalanco();
    exibirResumoBalanco();
  }

  public void exibirDetalhesBalanco() {
    Map<String, Long> itensVendidos = CalulosFinanceiros.calularItensVendidos(pedidosSingleton);
    String detalhesPedidos = obterDetalhesPedidosConcluidos(itensVendidos);

    JOptionPane.showMessageDialog(null, detalhesPedidos, "Detalhes dos Pedidos Concluídos",
        JOptionPane.INFORMATION_MESSAGE);
  }

  public void exibirResumoBalanco() {
    double receitaTotal = CalulosFinanceiros.calcularReceitaTotal(pedidosSingleton);
    double ticketMedio = CalulosFinanceiros.calcularTicketMedio(receitaTotal, pedidosSingleton);
    Map<String, Long> itensVendidos = CalulosFinanceiros.calularItensVendidos(pedidosSingleton);

    String resumoBalanco = obterResumoBalanco(receitaTotal, ticketMedio, itensVendidos);

    JOptionPane.showMessageDialog(null, resumoBalanco, "Resumo do Balanço", JOptionPane.INFORMATION_MESSAGE);
  }

  private String obterDetalhesPedidosConcluidos(Map<String, Long> itensVendidos) {
    StringBuilder detalhes = new StringBuilder("Detalhes dos pedidos concluídos:\n");
    for (Pedido pedido : pedidosSingleton.getPedidosConcluidos()) {
      detalhes.append("- Número do Pedido: ").append(pedido.getNumeroPedido()).append("\n");
      detalhes.append("  Descrição:\n");
      detalhes.append(pedido.exibirDetalhes());
      detalhes.append("  Valor Total: R$ ").append(String.format("%.2f", pedido.getPrecoTotal())).append("\n\n");
    }
    return detalhes.toString();
  }

  private String obterResumoBalanco(double receitaTotal, double ticketMedio,
      Map<String, Long> itensVendidos) {
    StringBuilder resumo = new StringBuilder("Resumo do Balanço:\n");
    resumo.append("Número de pedidos concluídos: ").append(pedidosSingleton.getPedidosConcluidos().size()).append("\n");
    resumo.append("Receita total: R$ ").append(String.format("%.2f", receitaTotal)).append("\n");
    resumo.append("Ticket médio: R$ ").append(String.format("%.2f", ticketMedio)).append("\n");
    resumo.append("Itens mais vendidos:\n");
    itensVendidos.forEach(
        (item, quantidade) -> resumo.append("- ").append(item).append(": ").append(quantidade).append(" vendidos.\n"));
    return resumo.toString();
  }
}
