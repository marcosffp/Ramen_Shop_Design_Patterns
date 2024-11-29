package br.lpm.business.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.util.GeradorIdPedido;

public class PedidoMedioTest {
 Pedido pedidoMedio;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedidoMedio = new PedidoMedio("Marcos", Tamanho.PEQUENO, Proteina.BOI);
  }

  @Test
  void testExibirDetalhes() {
    String detalhesEsperados = "Pedido Médio com proteina: BOI";
    assertEquals(detalhesEsperados, pedidoMedio.exibirDetalhes(),
        "Testando se o exibirDetalhes() está correto");
  }
  
  @Test
  void testGetNumeroPedido() {
    assertEquals(1, pedidoMedio.getNumeroPedido(), "Testando se o número do pedido está correto");
  }

  @Test 
  void testGetTamanho() {
    assertEquals(Tamanho.PEQUENO, pedidoMedio.getTamanhoPedido(), "Testando se o tamanho do pedido está correto");
  }

  @Test
  void testGetProteina() {
    assertEquals(Proteina.BOI, pedidoMedio.getProteinaPedido(), "Testando se a proteína do pedido está correta");
  }
}
