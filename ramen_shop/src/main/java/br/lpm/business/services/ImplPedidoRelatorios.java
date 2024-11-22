package br.lpm.business.services;

import javax.swing.JOptionPane;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.utils.CalulosFinanceiros;

public class ImplPedidoRelatorios extends PedidoRelatorios{
  public ImplPedidoRelatorios(PedidosSingleton pedidosSingleton) {
    super(pedidosSingleton);
  }

  @Override
  public void exibirProgressoPedidos() {
    StringBuilder progresso = new StringBuilder("Progresso dos Pedidos:\n");
    progresso.append(exibirPedidos("Em Espera", super.getPedidosSingleton().getListaPedidos()));
    progresso.append(exibirPedidos("\nConcluídos", super.getPedidosSingleton().getPedidosConcluidos()));

    JOptionPane.showMessageDialog(null, progresso.toString(), "Progresso dos Pedidos", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public String exibirPedidos(String titulo, Iterable<Pedido> pedidos) {
    StringBuilder detalhes = new StringBuilder(titulo).append(":\n");
    for (Pedido pedido : pedidos) {
      detalhes.append("- Pedido #").append(pedido.getNumeroPedido()).append("\n");
    }
    return detalhes.toString();
  }

  @Override
  public void exibirDetalhesBalanco() {
    String detalhesPedidos = obterDetalhesPedidosConcluidos();

    JOptionPane.showMessageDialog(null, detalhesPedidos, "Detalhes dos Pedidos Concluídos",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void exibirResumoBalanco() {
    double receitaTotal = CalulosFinanceiros.calcularReceitaTotal(super.getPedidosSingleton());
    double ticketMedio = CalulosFinanceiros.calcularTicketMedio(receitaTotal, super.getPedidosSingleton());

    String resumoBalanco = obterResumoBalanco(receitaTotal, ticketMedio);

    JOptionPane.showMessageDialog(null, resumoBalanco, "Resumo do Balanço", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public String obterDetalhesPedidosConcluidos() {
    StringBuilder detalhes = new StringBuilder("Detalhes dos pedidos concluídos:\n");
    for (Pedido pedido : super.getPedidosSingleton().getPedidosConcluidos()) {
      detalhes.append("- Número do Pedido: ").append(pedido.getNumeroPedido()).append("\n");
      detalhes.append("  Descrição:\n");
      detalhes.append(pedido.exibirDetalhes());
      detalhes.append("  Valor Total: R$ ").append(String.format("%.2f", pedido.getPrecoTotal())).append("\n\n");
    }
    return detalhes.toString();
  }

  @Override
  public String obterResumoBalanco(double receitaTotal, double ticketMedio) {
    StringBuilder resumo = new StringBuilder("Resumo do Balanço:\n");
    resumo.append("Número de pedidos concluídos: ").append(
        super.getPedidosSingleton().getPedidosConcluidos().size()).append("\n");
    resumo.append("Receita total: R$ ").append(String.format("%.2f", receitaTotal)).append("\n");
    resumo.append("Ticket médio: R$ ").append(String.format("%.2f", ticketMedio)).append("\n");
    return resumo.toString();
  }
}
