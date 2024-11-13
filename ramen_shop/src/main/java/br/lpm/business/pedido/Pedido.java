package br.lpm.business.pedido;

public interface Pedido {
  public void exibirDetalhes();

  public double calcularTotal();

  public double getPrecoTotal();
}
