package br.lpm.business.model;

public class PedidoPequeno implements Pedido {
  private static final double PRECO_BASE = 9.90;
  private static final double PRECO_VEGANO = 3.90;
  private static final double PRECO_BOI = 7.90;
  private static final double PRECO_PORCO = 5.90;
  private String nomeCliente;

  @Override
  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  private String proteina;
  private int numeroPedido;
  private static int contador = 1;

  public PedidoPequeno(String proteina, String nomeCliente) {
    this.proteina = proteina;
    this.nomeCliente = nomeCliente;
    this.numeroPedido = contador++;
  }

  public int getNumeroPedido() {
    return numeroPedido;
  }

  @Override
  public void exibirDetalhes() {
    System.out.println("Pedido Pequeno com proteina: " + proteina);
  }

  @Override
  public double calcularTotal() {
    if (proteina.equalsIgnoreCase("Vegano")) {
      return PRECO_BASE + PRECO_VEGANO;
    } else if (proteina.equalsIgnoreCase("Boi")) {
      return PRECO_BASE + PRECO_BOI;
    } else if (proteina.equalsIgnoreCase("Porco")) {
      return PRECO_BASE + PRECO_PORCO;
    }
    return PRECO_BASE;
  }

  @Override
  public double getPrecoTotal() {
    return calcularTotal();
  }

  public String getProteina() {
    return proteina;
  }


}
