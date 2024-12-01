package br.lpm.business.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class GeradorIdPedidoTest {

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset(); 
  }

  @Test
  void testGerarId() {
    int id1 = GeradorIdPedido.gerarId();
    int id2 = GeradorIdPedido.gerarId();

    assertEquals(1, id1, "Testando se o primeiro ID gerado é 1");
    assertEquals(2, id2, "Testando se o segundo ID gerado é 2");
  }

  @Test
  void testReset() {
    GeradorIdPedido.gerarId(); 
    GeradorIdPedido.reset(); 
    int idDepoisDoReset = GeradorIdPedido.gerarId();
    assertEquals(1, idDepoisDoReset, "Testando se o ID gerado após o reset é 1");

    GeradorIdPedido.reset();
    int id1 = GeradorIdPedido.gerarId();
    int id2 = GeradorIdPedido.gerarId();
    assertEquals(1, id1, "Testando se o primeiro ID após o reset é 1");
    assertEquals(2, id2, "Testando se o segundo ID após o reset é 2");
  }
}
