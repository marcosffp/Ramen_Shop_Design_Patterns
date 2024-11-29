package br.lpm.business.balanco;

import java.util.ArrayList;
import java.util.List;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Status;


public class Balanco {
  private static final Balanco INSTANCE = new Balanco();
  private final List<Pedido> pedidosConcluidos = new ArrayList<>();

  private Balanco() {
  }

  public static Balanco getInstance() {
    return INSTANCE;
  }

  public void addPedidoConcluidos(Pedido pedido) {
    pedido.setStatusPedido(Status.RETIRADO);
    pedidosConcluidos.add(pedido);
  }

  public void removePedidoConcluidos(Pedido pedido) {
    pedidosConcluidos.remove(pedido);
  }

  


  public String exibirBalanco() {
    double receita= pedidosConcluidos.stream()
        .mapToDouble(p -> p.getProteinaPedido().getPreco())
        .sum();
  
    double ticketMedio;
    if (pedidosConcluidos.size() == 0) {
      ticketMedio = 0.0;
    }
    else {
      ticketMedio = receita / pedidosConcluidos.size();
    }

    String
      
    

  }

}
