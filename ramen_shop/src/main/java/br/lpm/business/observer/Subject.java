package br.lpm.business.observer;

import br.lpm.business.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Subject {
  private final List<Observer> observadores = new ArrayList<>();
  private Pedido pedidoPronto;

  public Pedido getPedidoPronto() {
    return pedidoPronto;
  }

  public void setPedidoPronto(Pedido pedidoPronto) {
    if (pedidoPronto != null && !pedidoPronto.equals(this.pedidoPronto)) {
      this.pedidoPronto = pedidoPronto;
      notificarObservadores();
    }
  }

  public void registrarObservador(Observer observer) {
    if (!observadores.contains(observer)) {
      observadores.add(observer);
    }
  }

  public void removerObservador(Observer observador) {
    observadores.remove(observador);
  }

  private void notificarObservadores() {
    observadores.forEach(Observer::atualizar);
  }

  public List<Observer> getObservadores() {
    return observadores;
  }
}
