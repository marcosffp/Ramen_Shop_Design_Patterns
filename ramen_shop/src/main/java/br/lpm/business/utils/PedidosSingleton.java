package br.lpm.business.utils;

import br.lpm.business.model.Pedido;
import java.time.LocalDateTime;
import java.util.*;

public class PedidosSingleton {
  private static PedidosSingleton INSTANCE;
  private Queue<Pedido> listaPedidos = new LinkedList<>();
  private List<Pedido> pedidosConcluidos = new ArrayList<>();
  private Map<Integer, LocalDateTime> temposPreparo = new HashMap<>(); 
  private Subject cozinha = new Subject();

  private PedidosSingleton() {
  }

  public static PedidosSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PedidosSingleton();
    }
    return INSTANCE;
  }

  public void addPedido(Pedido pedido) {
    PedidoUtils.addPedido(pedido, this);
  }

  public void retirarPedido() {
    PedidoUtils.retirarPedido(this);
  }

  public void confirmarRetirada(int numeroPedido) {
    PedidoUtils.confirmarRetirada(numeroPedido, this);
  }

  public void exibirProgressoPedidos() {
    PedidoRelatorios.exibirProgressoPedidos(this);
  }

  public void exibirBalanco() {
    PedidoRelatorios.exibirBalanco(this);
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
