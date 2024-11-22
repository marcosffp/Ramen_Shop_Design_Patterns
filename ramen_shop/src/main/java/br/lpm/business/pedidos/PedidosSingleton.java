package br.lpm.business.pedidos;

import br.lpm.business.model.Pedido;
import br.lpm.business.observer.Subject;

import java.util.*;

public class PedidosSingleton {
  private static PedidosSingleton INSTANCIA= new PedidosSingleton();
  private final Queue<Pedido> listaPedidos = new LinkedList<>();
  private final List<Pedido> pedidosConcluidos = new ArrayList<>();
  private final Subject cozinha = new Subject();

  private PedidosSingleton() {
  }

  public static PedidosSingleton getInstancia() {
    return PedidosSingleton.INSTANCIA;
  }

  public Queue<Pedido> getListaPedidos() {
    return listaPedidos;
  }

  public List<Pedido> getPedidosConcluidos() {
    return pedidosConcluidos;
  }

  public Subject getCozinha() {
    return cozinha;
  }
}
