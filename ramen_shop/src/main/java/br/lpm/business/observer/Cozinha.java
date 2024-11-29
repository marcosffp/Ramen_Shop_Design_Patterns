package br.lpm.business.observer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Status;

public class Cozinha implements Subject {

  private final Set<Observer> observadores = new HashSet<>();
  private Pedido pedidoPronto;

  @Override
  public Pedido getPedidoPronto() {
    return pedidoPronto;
  }

  @Override
  public void setPedidoPronto(Pedido pedidoPronto) throws RamenShopException {
    if (pedidoPronto == null) {
      throw new RamenShopException("O pedido não pode ser nulo.");
    }

    if (pedidoPronto.getStatusPedido() == Status.PENDENTE) {
      throw new RamenShopException("O pedido deve estar com status EM_PREPARO para ser concluído. Status atual: "
          + pedidoPronto.getStatusPedido());
    }

    pedidoPronto.setStatusPedido(Status.EM_PREPARO);
    this.pedidoPronto = pedidoPronto;
    notificarObservadores();
    this.pedidoPronto.setStatusPedido(Status.PRONTO);
  }

  @Override
  public void registrarObservador(Observer observer) throws RamenShopException {
    if (observer == null) {
      throw new RamenShopException("O observador não pode ser nulo.");
    }
    this.observadores.add(observer);
  }

  @Override
  public void notificarObservadores() throws RamenShopException {
    if (observadores.isEmpty()) {
      throw new RamenShopException("Não há observadores registrados para notificar.");
    }

    this.observadores.forEach(Observer::notificar);
  }

  @Override
  public List<Observer> getObservadores() {
    return List.copyOf(observadores);
  }

  @Override
  public Pedido retirarPedidoPronto() throws RamenShopException {
    if (pedidoPronto == null) {
      throw new RamenShopException("Não há pedido pronto para retirar.");
    }
    pedidoPronto.setStatusPedido(Status.RETIRADO);
    return pedidoPronto;
  }

}
