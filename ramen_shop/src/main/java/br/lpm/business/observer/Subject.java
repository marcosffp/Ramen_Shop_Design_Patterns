package br.lpm.business.observer;

import br.lpm.business.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Subject {
  private List<Observer> observadores = new ArrayList<>();
  private Pedido pedidoPronto;

  public Pedido getPedidoPronto() {
    return pedidoPronto;
  }

  public void setPedidoPronto(Pedido pedidoPronto) {
    this.pedidoPronto = pedidoPronto;
    notificarObservadores();
  }

  public void registrarObservador(Observer observer) {
    observadores.add(observer);
  }

  public void removerObservador(Observer observador) {
    observadores.remove(observador);
  }

  public void notificarObservadores() {
    for (Observer observador : observadores) {
      observador.atualizar();
    }
  }

  public void removerPedidoPronto() {
    this.pedidoPronto = null;
  }
}
