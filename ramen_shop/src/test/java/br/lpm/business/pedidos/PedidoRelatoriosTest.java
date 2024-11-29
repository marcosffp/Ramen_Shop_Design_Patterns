package br.lpm.business.pedidos;

import br.lpm.business.balanco.ImplPedidoRelatorios;
import br.lpm.business.balanco.PedidoRelatorios;
import br.lpm.business.model.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PedidoRelatoriosTest {

  private PedidosSingleton pedidosSingleton;
  private PedidoRelatorios pedidoRelatorios;

  @BeforeEach
  void setUp() {
    pedidosSingleton = PedidosSingleton.getInstancia();

    pedidosSingleton.getListaPedidos().clear();
    pedidosSingleton.getPedidosConcluidos().clear();

    Pedido pedido1 = new PedidoPequeno("BOI", "Marcos", "1234");
    Pedido pedido2 = new PedidoMedio("PORCO", "Alvim", "2345");
    Pedido pedido3 = new PedidoGrande("VEGANO", "Jamilly", "3456");


    pedidosSingleton.getListaPedidos().add(pedido1);
    pedidosSingleton.getPedidosConcluidos().add(pedido2);
    pedidosSingleton.getPedidosConcluidos().add(pedido3);

    pedidoRelatorios = new ImplPedidoRelatorios(pedidosSingleton);
  }

  @Test
  void testExibirDetalhesBalanco() {
    assertDoesNotThrow(() -> pedidoRelatorios.exibirDetalhesBalanco(),
        "O método exibirDetalhesBalanco deve ser executado sem lançar exceções");
  }

  @Test
  void testExibirProgressoPedidos() {
    assertDoesNotThrow(() -> pedidoRelatorios.exibirProgressoPedidos(),
        "O método exibirProgressoPedidos deve ser executado sem lançar exceções");
  }

  @Test
  void testExibirResumoBalanco() {
    assertDoesNotThrow(() -> pedidoRelatorios.exibirResumoBalanco(),
        "O método exibirResumoBalanco deve ser executado sem lançar exceções");
  }
}
