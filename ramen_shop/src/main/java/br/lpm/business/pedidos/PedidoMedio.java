package br.lpm.business.pedidos;

import br.lpm.business.model.Pedido;
import br.lpm.business.utils.GeradorIdPedido;

public class PedidoMedio implements Pedido {
  private static final double PRECO_BASE = 12.90;
  private static final double PRECO_VEGANO = 3.90;
  private static final double PRECO_BOI = 7.90;
  private static final double PRECO_PORCO = 5.90;
  private String nomeCliente;
  private String senhaCliente;
  private String proteinaPedido;
  private int numeroPedido;

  public PedidoMedio(String proteinaPedido, String nomeCliente, String senhaCliente) {
    this.proteinaPedido = proteinaPedido;
    this.nomeCliente = nomeCliente;
    this.senhaCliente = senhaCliente;
        this.numeroPedido = GeradorIdPedido.gerarId();
  }

  public int getNumeroPedido() {
    return numeroPedido;
  }

  public String getProteinaPedido() {
    return proteinaPedido;
  }

  @Override
  public String exibirDetalhes() {
    return "Pedido Médio com proteina: " + proteinaPedido;
  }

  @Override
  public String getNomeCliente() {
    return nomeCliente;
  }

  @Override
  public String getSenhaCliente() {
    return senhaCliente;
  }

  @Override
  public double getPrecoTotal() {
    return switch (proteinaPedido.toUpperCase()) {
      case "VEGANO" -> PRECO_BASE + PRECO_VEGANO;
      case "BOI" -> PRECO_BASE + PRECO_BOI;
      case "PORCO" -> PRECO_BASE + PRECO_PORCO;
      default -> PRECO_BASE;
    };
  }

  @Override
  public int getQuantidadeItens() {
    return 1;
  }
}
