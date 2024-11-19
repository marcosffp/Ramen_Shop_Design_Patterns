package br.lpm.business.model;

import br.lpm.business.utils.Observer;
import br.lpm.business.utils.Subject;

public class ClienteObserver extends Observer {
  private String nomeCliente;

  public ClienteObserver(Subject subject, String nomeCliente) {
    super(subject);
    this.nomeCliente = nomeCliente;
  }

  @Override
  public void update() {
    Pedido pedidoPronto = (Pedido) subject.getPedidoPronto(); 
    if (pedidoPronto != null) {
      System.out.println("Notificação para " + nomeCliente + ":");
      System.out.println("Seu pedido está pronto!");
      System.out.println("Detalhes do Pedido:");
      System.out.println("- Número: " + pedidoPronto.getNumeroPedido());
      System.out.println("- Valor Total: R$ " + pedidoPronto.getPrecoTotal());
    } else {
      System.out.println("Erro: Pedido pronto não registrado no sistema.");
    }
  }
}
