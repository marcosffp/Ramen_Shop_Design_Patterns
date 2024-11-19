package br.lpm.business.model;

public class PedidoGrande implements Pedido {
  private static final double PRECO_BASE = 15.90;
  private static final double PRECO_VEGANO = 3.90;
  private static final double PRECO_BOI = 7.90;
  private static final double PRECO_PORCO = 5.90;
  private String proteina;
  private int numeroPedido;
  private static int contador = 1;
  private String nomeCliente;

  @Override
  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public PedidoGrande(String proteina, String nomeCliente) {
    this.proteina = proteina;
    this.nomeCliente = nomeCliente;
    this.numeroPedido = contador++;
  }

  @Override
  public void exibirDetalhes() {
    System.out.println("Pedido Grande com proteína: " + proteina);
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

  public void setProteina(String proteina) {
    this.proteina = proteina;
  }

  @Override
  public int getNumeroPedido() {
    return numeroPedido;
  }

}
