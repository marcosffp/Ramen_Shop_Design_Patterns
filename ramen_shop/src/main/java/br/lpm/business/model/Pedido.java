package br.lpm.business.model;

import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.util.GeradorIdPedido;

public abstract class Pedido {

  private final int numeroPedido;
  private String nomeCliente;
  private static int contadorPedidos = 1; // Contador de pedidos
  private Tamanho tamanhoPedido;
  private Status statusPedido;
  private Proteina proteinaPedido;

  // Construtor
  public Pedido(String nomeCliente, Tamanho tamanhoPedido, Proteina proteinaPedido) {
    this.numeroPedido = GeradorIdPedido.gerarId();
    this.nomeCliente = nomeCliente;
    this.tamanhoPedido = tamanhoPedido;
    this.statusPedido = Status.PENDENTE;
    this.proteinaPedido = proteinaPedido;
  }

  // Método sincronizado para garantir que o contador seja atualizado de forma
  // segura
  private synchronized int gerarNumeroPedido() {
    return contadorPedidos++; // Incrementa o contador de forma segura
  }

  // Métodos de acesso
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
