package br.lpm.business.pedidos;

import br.lpm.business.model.Pedido;
import br.lpm.business.observer.Subject;

import java.time.LocalDateTime;
import java.util.*;

public class PedidosSingleton {
  private static PedidosSingleton INSTANCIA;
  private final Queue<Pedido> listaPedidos = new LinkedList<>();
  private final List<Pedido> pedidosConcluidos = new ArrayList<>();
  private final Map<Integer, LocalDateTime> temposPreparo = new HashMap<>();
  private final Subject cozinha = new Subject();

  private PedidosSingleton() {
  }

  public static PedidosSingleton getInstancia() {
    if (INSTANCIA == null) {
      INSTANCIA = new PedidosSingleton();
    }
    return INSTANCIA;
  }

  public Queue<Pedido> getListaPedidos() {
    return listaPedidos;
  }

  public List<Pedido> getPedidosConcluidos() {
    return pedidosConcluidos;
  }

  public Map<Integer, LocalDateTime> getTemposPreparo() {
    return temposPreparo;
  }

  public Subject getCozinha() {
    return cozinha;
  }
}
