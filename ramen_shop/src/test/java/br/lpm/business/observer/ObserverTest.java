package br.lpm.business.observer;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.pedidos.PedidoPequeno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObserverTest {

  private Subject subject;
  private ClienteObserver clienteObserver1;
  private ClienteObserver clienteObserver2;

  @BeforeEach
  void setUp() {
    subject = new Subject();
    clienteObserver1 = new ClienteObserver(subject, "João", "1234");
    clienteObserver2 = new ClienteObserver(subject, "Maria", "5678");
  }

  @Test
  void testAtualizar() {
    Pedido pedidoJoao = new PedidoPequeno("PORCO", "Marcos", "1234");
    subject.setPedidoPronto(pedidoJoao);

    assertEquals(pedidoJoao, subject.getPedidoPronto(),"Testando se o pedido pronto foi atualizado corretamente.");
    clienteObserver1.atualizar();
    clienteObserver2.atualizar();

    Pedido pedidoMaria = new PedidoMedio("PORCO", "Alvim", "2345");
    subject.setPedidoPronto(pedidoMaria);

    assertEquals(pedidoMaria, subject.getPedidoPronto(),"Testando se o pedido pronto foi atualizado corretamente.");
    clienteObserver1.atualizar();
    clienteObserver2.atualizar();
  }
}
