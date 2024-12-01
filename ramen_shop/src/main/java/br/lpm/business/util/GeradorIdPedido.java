package br.lpm.business.util;

public class GeradorIdPedido {
  private static int idAtual = 1;
  
  private GeradorIdPedido() {
  }


  public static synchronized int gerarId() {
    return idAtual++; 
  }


  public static synchronized void reset() {
    idAtual = 1;
  }
}
