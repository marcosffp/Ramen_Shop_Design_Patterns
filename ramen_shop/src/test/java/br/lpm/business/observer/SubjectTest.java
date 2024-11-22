package br.lpm.business.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.lpm.business.pedidos.PedidoPequeno;

public class SubjectTest {
  private Subject subject;
  private Observer observer;
  private PedidoPequeno pedido;

  @BeforeEach
  void setUp() {
    subject = new Subject();
    observer = new ClienteObserver(subject, "Marcos", "1234");
    pedido = new PedidoPequeno("BOI", "Marcos", "1234");
    subject.registrarObservador(observer);
  }

  @Test
  void testGetPedidoPronto() {
    subject.setPedidoPronto(pedido);
    assertEquals(pedido, subject.getPedidoPronto(), "Testando se o pedido pronto foi atualizado corretamente.");
  }

  @Test
  void testRegistrarObservador() {
    assertTrue(subject.getObservadores().contains(observer), "Testando se o observador foi registrado corretamente.");
  }

  @Test
  void testRemoverObservador() {
    subject.removerObservador(observer);
    assertFalse(subject.getObservadores().contains(observer), "Testando se o observador foi removido corretamente.");
  }

  @Test
  void testSetPedidoPronto() {
    PedidoPequeno outroPedido = new PedidoPequeno("PORCO", "João", "5678");
    subject.setPedidoPronto(pedido);
    assertEquals(pedido, subject.getPedidoPronto(), "Testando se o pedido pronto foi atualizado corretamente.");
    assertNotEquals(outroPedido, subject.getPedidoPronto(), "Testando se o pedido pronto foi atualizado corretamente.");
    subject.setPedidoPronto(outroPedido); 
    assertDoesNotThrow(() -> subject.setPedidoPronto(outroPedido),
        "Testando se o pedido pronto foi atualizado corretamente.");
  }
}
