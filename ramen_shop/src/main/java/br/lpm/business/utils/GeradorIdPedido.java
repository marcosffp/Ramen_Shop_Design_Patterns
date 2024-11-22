package br.lpm.business.utils;

public class GeradorIdPedido {
  private static int idAtual = 1;

  public static synchronized int gerarId() {
    return idAtual++;
  }

  public static synchronized void reset() {
    idAtual = 1;
  }
}
