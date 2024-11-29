package br.lpm.business.model.enums;

public enum Bebida {
  REFRIGERANTE(5.90),
  O_CHA(3.90),
  KO_CHA(0.00);

  private final double preco;

  Bebida(double preco) {
    this.preco = preco;
  }

  public double getPreco() {
    return preco;
  }
}
