package br.lpm.business.utils;

import br.lpm.business.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class Subject {
  private List<Observer> observers = new ArrayList<>();
  private Pedido pedidoPronto; 

  public Pedido getPedidoPronto() {
    return pedidoPronto;
  }

  public void setPedidoPronto(Pedido pedidoPronto) {
    this.pedidoPronto = pedidoPronto;
    notifyObservers();
  }

  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }
}
