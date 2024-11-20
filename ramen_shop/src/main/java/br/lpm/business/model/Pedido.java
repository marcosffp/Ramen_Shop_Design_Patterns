package br.lpm.business.model;

public interface Pedido {
  public void exibirDetalhes();

  public double getPrecoTotal();
  
  public int getNumeroPedido();
  
  public String getNomeCliente();

  public String getSenhaCliente();

}
