package br.lpm.business.model;

public interface Pedido {
  public String exibirDetalhes();

  public double getPrecoTotal();
  
  public int getNumeroPedido();
  
  public String getNomeCliente();

  public String getSenhaCliente();

  public int getQuantidadeItens();

}
