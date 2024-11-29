package br.lpm.business.observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.exception.RamenShopException;

public class CozinhaTest {

  private Cozinha cozinha;
  private Pedido pedido;
  private Observer clienteObserver;

  @BeforeEach
  void setUp() throws RamenShopException {
    cozinha = new Cozinha();
    pedido = new PedidoMedio("Marcos", Tamanho.PEQUENO, Proteina.BOI);
    clienteObserver = new ClienteObserver(cozinha, "Marcos");
    cozinha.registrarObservador(clienteObserver);
  }

  @Test
  void testRegistrarObservador() throws RamenShopException {
    cozinha.registrarObservador(clienteObserver);
    assertEquals(1, cozinha.getObservadores().size(), "Testando se o observador foi registrado corretamente");
  }

  @Test
  void testSetPedidoPronto() throws RamenShopException {
    assertThrows(RamenShopException.class, () -> {
      cozinha.setPedidoPronto(null);
    }, "Testando se o status do pedido foi alterado para PRONTO");
    pedido.setStatusPedido(Status.PENDENTE);
    assertThrows(RamenShopException.class, () -> {
      cozinha.setPedidoPronto(pedido);
    }, "Testando se o status do pedido foi alterado para PRONTO");
    pedido.setStatusPedido(Status.EM_PREPARO);
    cozinha.setPedidoPronto(pedido);
    assertEquals(Status.PRONTO, pedido.getStatusPedido(), "Testando se o status do pedido foi alterado para PRONTO");
  }

  @Test
  void testNotificarObservadores() throws RamenShopException {
    cozinha.registrarObservador(clienteObserver);
    pedido.setStatusPedido(Status.EM_PREPARO);
    cozinha.setPedidoPronto(pedido);

    String mensagemNotificacao = clienteObserver.notificar();
    assertTrue(mensagemNotificacao.contains("Seu pedido com o número"), "O cliente não foi notificado corretamente.");
  }

  @Test
  void testRetirarPedidoPronto() throws RamenShopException {
    pedido.setStatusPedido(Status.PRONTO);
    cozinha.setPedidoPronto(pedido);
    Pedido pedidoRetirado = cozinha.retirarPedidoPronto();
    assertEquals(Status.RETIRADO, pedidoRetirado.getStatusPedido(),
        "Testando se o status do pedido foi alterado para RETIRADO");
    assertThrows(RamenShopException.class, () -> {
      cozinha.retirarPedidoPronto();
    }, "Testando se o pedido foi retirado corretamente");
  }

}
