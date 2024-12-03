package br.lpm.business.observer;

import br.lpm.business.exception.RamenShopException;

public class Cliente implements Observer {
  protected Subject subject;
  private final String nomeCliente;

  public Cliente(Subject subject, String nomeCliente) throws RamenShopException {
    this.subject = subject;
    this.subject.registrarObservador(this);
    this.nomeCliente = nomeCliente;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }


  @Override
  public String notificar() {
    return "Notificação para o cliente " + subject.getPedidoPronto().getNomeCliente() + ": Seu pedido com o número " + subject.getPedidoPronto().getNumeroPedido() + " está pronto!";
  }
}
