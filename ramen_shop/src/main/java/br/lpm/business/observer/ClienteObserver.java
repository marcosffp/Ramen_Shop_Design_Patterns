package br.lpm.business.observer;

import javax.swing.JOptionPane;

import br.lpm.business.model.Pedido;

public class ClienteObserver extends Observer {
  private final String nomeCliente;
  private final String senhaCliente;

  public ClienteObserver(Subject subject, String nomeCliente, String senha) {
    super(subject);
    this.nomeCliente = nomeCliente;
    this.senhaCliente = senha;
  }

  @Override
  public void atualizar() {
    Pedido pedidoPronto = (Pedido) subject.getPedidoPronto();

    if (pedidoPronto == null) {
      JOptionPane.showMessageDialog(
          null,
          "Erro: Nenhum pedido está pronto para ser notificado.",
          "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    if (senhaCliente.equals(pedidoPronto.getSenhaCliente())) {

      String resumoPedido = String.format(
          "Pedido Pronto!\n\nCliente: %s\nNúmero do Pedido: %d\nValor Total: R$ %.2f",
          pedidoPronto.getNomeCliente(),
          pedidoPronto.getNumeroPedido(),
          pedidoPronto.getPrecoTotal());

      JOptionPane.showMessageDialog(
          null,
          resumoPedido,
          "Notificação do cliente",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null,
          "Erro: Pedido não corresponde ao cliente.",
          "Erro",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public String getSenhaCliente() {
    return senhaCliente;
  }
}
