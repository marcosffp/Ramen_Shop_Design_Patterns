package br.lpm.business.utils;

import java.util.UUID;

public class GeradorSenha {
  public static String gerarSenha() {
    return UUID.randomUUID().toString().substring(0, 4);
  }
}
