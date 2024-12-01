package br.lpm.business.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.util.GeradorIdPedido;

public class ClienteObserverTest {

  private Subject subject;
  private ClienteObserver clienteObserver;
  private Pedido pedido;

  @BeforeEach
  void setUp() throws RamenShopException {
    GeradorIdPedido.reset();
    subject = new Cozinha();
    pedido =  new PedidoMedio("Marcos", Tamanho.PEQUENO, Proteina.BOI);
    clienteObserver = new ClienteObserver(subject, "Marcos");
    subject.registrarObservador(clienteObserver);
  }

  @Test
  void testNotificar() throws RamenShopException {
    pedido.setStatusPedido(Status.EM_PREPARO);
    subject.setPedidoPronto(pedido); 

    String mensagemEsperada = "Notificação para o cliente Marcos: Seu pedido com o número 1 está pronto!";
    String mensagemRecebida = clienteObserver.notificar();

    assertEquals(mensagemEsperada, mensagemRecebida, "Testando se a notificação foi enviada corretamente");
  }

  @Test
  void testNotificarComOutroCliente() throws RamenShopException {
    ClienteObserver outroClienteObserver = new ClienteObserver(subject, "Ana");
    subject.registrarObservador(outroClienteObserver); 

    Pedido novoPedido = new PedidoMedio("Marcos", Tamanho.PEQUENO, Proteina.BOI);
    novoPedido.setStatusPedido(Status.EM_PREPARO);
    subject.setPedidoPronto(novoPedido); 

    String mensagemEsperadaAna = "Notificação para o cliente Ana: Seu pedido com o número 2 está pronto!";
    String mensagemRecebidaAna = outroClienteObserver.notificar();

    assertEquals(mensagemEsperadaAna, mensagemRecebidaAna,
        "Testando se a notificação foi enviada corretamente para o outro cliente");
  }

  @Test
  void testGetNomeCliente() {
    assertEquals("Marcos", clienteObserver.getNomeCliente(), "Testando se o nome do cliente está correto");
  }

  @Test
  void testSetPedidoProntoComStatusInvalido() {
    pedido.setStatusPedido(Status.PENDENTE);
    RamenShopException exception = assertThrows(RamenShopException.class, () -> subject.setPedidoPronto(pedido));
    assertEquals("O pedido deve estar com status EM_PREPARO para ser concluído. Status atual: PENDENTE", exception.getMessage(),
        "Testando se o status do pedido é inválido");
  }
}