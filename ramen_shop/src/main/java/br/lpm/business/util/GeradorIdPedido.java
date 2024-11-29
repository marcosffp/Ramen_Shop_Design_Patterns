package br.lpm.business.util;

public class GeradorIdPedido {
  private static int idAtual = 1; // Inicia em 1 por padrão

  // Método sincronizado para gerar ID único
  public static synchronized int gerarId() {
    return idAtual++; // Incrementa após retornar o ID atual
  }

  // Reset deve ser usado apenas para testes
  public static synchronized void reset() {
    idAtual = 1;
  }

  // Método para verificar o ID atual (opcional, para depuração)
  public static synchronized int getIdAtual() {
    return idAtual;
  }
}
