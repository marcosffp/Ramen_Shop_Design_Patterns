package br.lpm.business.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;



public class GeradorSenhaTest {
  @Test
  void testGerarSenha() {
    String senha = GeradorSenha.gerarSenha();
    assertNotNull(senha, "Testando se a senha gerada não é nula.");
    assertEquals(4, senha.length(), "Testando se a senha gerada tem 4 caracteres.");
  }
}
