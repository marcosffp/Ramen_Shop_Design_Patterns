package br.lpm.business.pedido;

public class PedidoPequeno implements Pedido {
  private static final double PRECO_BASE = 9.90;
  private static final double PRECO_VEGANO = 3.90;
  private static final double PRECO_BOI = 7.90;
  private static final double PRECO_PORCO = 5.90;
  private String proteina;

  public PedidoPequeno(String proteina) {
    this.proteina = proteina;
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
}
