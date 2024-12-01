package br.lpm.business.model;

import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.util.GeradorIdPedido;

public abstract class Pedido {

  private int numeroPedido;
  private String nomeCliente;
  private Tamanho tamanhoPedido;
  private Status statusPedido;
  private Proteina proteinaPedido;

  public Pedido(String nomeCliente, Tamanho tamanhoPedido, Proteina proteinaPedido) {
    this.numeroPedido = GeradorIdPedido.gerarId();
    this.nomeCliente = nomeCliente;
    this.tamanhoPedido = tamanhoPedido;
    this.statusPedido = Status.PENDENTE;
    this.proteinaPedido = proteinaPedido;
  }


  public void setStatusPedido(Status statusPedido) {
    this.statusPedido = statusPedido;
  }

  public int getNumeroPedido() {
    return numeroPedido;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public Tamanho getTamanhoPedido() {
    return tamanhoPedido;
  }

  public Status getStatusPedido() {
    return statusPedido;
  }

  public Proteina getProteinaPedido() {
    return proteinaPedido;
  }

  public double getPrecoTotal() {
    return tamanhoPedido.getPreco() + proteinaPedido.getPreco();
  }

  public abstract String exibirDetalhes();
}
