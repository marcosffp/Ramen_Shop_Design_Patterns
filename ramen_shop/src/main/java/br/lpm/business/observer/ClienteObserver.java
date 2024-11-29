package br.lpm.business.observer;

public class ClienteObserver implements Observer {
  protected Subject subject;
  private final String nomeCliente;

  public ClienteObserver(Subject subject, String nomeCliente) {
    this.subject = subject;
    this.nomeCliente = nomeCliente;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }


  @Override
  public String notificar() {
    return "Notificação para o cliente " + nomeCliente + ": Seu pedido com o número " + subject.getPedidoPronto().getNumeroPedido() + " está pronto!";
  }
}
