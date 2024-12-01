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
    assertEquals(1, cozinha.getObservadores().size(), "Testando se o observador foi registrado corretamente");
    ClienteObserver clienteObserver2 = new ClienteObserver(cozinha, "Alvim");
    cozinha.registrarObservador(clienteObserver2);
    assertEquals(2, cozinha.getObservadores().size(), "Testando se o observador foi registrado corretamente");
  }


  @Test
  void testSetPedidoProntoStatusInvalido() {
    pedido.setStatusPedido(Status.PENDENTE);
    RamenShopException exception = assertThrows(RamenShopException.class, () -> cozinha.setPedidoPronto(pedido));
    assertEquals("O pedido deve estar com status EM_PREPARO para ser concluído. Status atual: PENDENTE",
        exception.getMessage(), "Testando se o status do pedido é inválido");
  }

  @Test
  void testSetPedidoProntoCorretamente() throws RamenShopException {
    pedido.setStatusPedido(Status.EM_PREPARO);
    cozinha.setPedidoPronto(pedido);
    assertEquals(Status.PRONTO, pedido.getStatusPedido(),
        "Testando se o status do pedido foi alterado corretamente");
  }

  @Test
  void testNotificarObservadores() throws RamenShopException {
    cozinha.registrarObservador(clienteObserver);
    pedido.setStatusPedido(Status.EM_PREPARO);
    cozinha.setPedidoPronto(pedido);

    String mensagemNotificacao = clienteObserver.notificar();
    assertTrue(mensagemNotificacao.contains("Seu pedido com o número"), "Testando se a notificação foi enviada corretamente");
  }

  @Test
  void testNotificarObservadoresSemRegistro() {
    Cozinha novaCozinha = new Cozinha();
    RamenShopException exception = assertThrows(RamenShopException.class, novaCozinha::notificarObservadores);
    assertEquals("Não há observadores registrados para notificar.", exception.getMessage(),
        "Testando se a exceção foi lançada corretamente");
  }

  @Test
  void testRetirarPedidoPronto() throws RamenShopException {
    pedido.setStatusPedido(Status.PRONTO);
    cozinha.setPedidoPronto(pedido);
    Pedido pedidoRetirado = cozinha.retirarPedidoPronto();
    assertEquals(Status.RETIRADO, pedidoRetirado.getStatusPedido(),
        "Testando se o status do pedido foi alterado para RETIRADO");
  }


}
