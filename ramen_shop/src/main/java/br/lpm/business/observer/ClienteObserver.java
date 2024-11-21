package br.lpm.business.observer;

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
    if (senhaCliente.equals(pedidoPronto.getSenhaCliente())) {
      System.out.println("Notificação para " + nomeCliente + ":");
      System.out.println("Seu pedido está pronto!");
      System.out.println("Detalhes do Pedido:");
      System.out.println("- Número: " + pedidoPronto.getNumeroPedido());
      System.out.println("- Cliente: " + pedidoPronto.getNomeCliente());
      System.out.println("- Valor Total: R$ " + pedidoPronto.getPrecoTotal());
    } else {
      System.out.println("Erro: Pedido não corresponde ao cliente.");
    }
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public String getSenhaCliente() {
    return senhaCliente;
  }
}
