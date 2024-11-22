package br.lpm.business.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeradorIdPedidoTest {

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset(); 
  }

  @Test
  void testGerarId() {
    int id1 = GeradorIdPedido.gerarId();
    int id2 = GeradorIdPedido.gerarId();
    int id3 = GeradorIdPedido.gerarId();

    assertEquals(1, id1, "Testando se o primeiro ID é 1.");
    assertEquals(2, id2, "Testando se o segundo ID é 2.");
    assertEquals(3, id3, "Testando se o terceiro ID é 3.");
  }

  @Test
  void testReset() {
    GeradorIdPedido.gerarId();
    GeradorIdPedido.gerarId();
    GeradorIdPedido.reset();
    int idAfterReset = GeradorIdPedido.gerarId();
    assertEquals(1, idAfterReset, "Testando se o ID após o reset é 1.");
  }
}
