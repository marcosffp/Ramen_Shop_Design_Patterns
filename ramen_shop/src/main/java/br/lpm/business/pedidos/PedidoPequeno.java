package br.lpm.business.pedidos;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;

public class PedidoPequeno extends Pedido {

  public PedidoPequeno(String nomeCliente, Tamanho tamanho, Proteina  proteinaPedido) {
    super(nomeCliente, tamanho, proteinaPedido);
  }

  @Override
  public String exibirDetalhes() {
    return "Pedido Pequeno com proteina: " + getProteinaPedido();
  }
}
 
