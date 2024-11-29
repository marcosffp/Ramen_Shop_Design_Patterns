package br.lpm.business.model.enums;

public enum Acrescimo {
  PROTEINA_EXTRA(4.00),
  CHILLI(2.50),
  CREME_ALHO(1.50),
  CROUTONS(2.00),
  SHITAKE(6.90),
  TOFU(2.70);

  private final double preco;

  Acrescimo(double preco) {
    this.preco = preco;
  }

  public double getPreco() {
    return preco;
  }
}
