package br.lpm.business.observer;

import java.util.List;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;

public interface Subject {

  public void setPedidoPronto(Pedido pedidoPronto) throws RamenShopException;

  public Pedido getPedidoPronto();

  public void registrarObservador(Observer observer) throws RamenShopException;

  public void notificarObservadores() throws RamenShopException;

  public Pedido retirarPedidoPronto() throws RamenShopException;

   public List<Observer> getObservadores();
}
