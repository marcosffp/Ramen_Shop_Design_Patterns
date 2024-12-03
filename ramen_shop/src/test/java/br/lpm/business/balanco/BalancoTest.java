package br.lpm.business.balanco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.pedidos.PedidoGrande;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.util.GeradorIdPedido;

public class BalancoTest {

  private Balanco balanco;
  private Pedido pedido1;
  private Pedido pedido2;
  private Pedido pedido3;
  private Pedido pedidoForaDaLista;

  @BeforeEach
  void setUp() throws RamenShopException {
    GeradorIdPedido.reset();
    balanco = Balanco.getInstance();
    balanco.removerTodosPedidosConcluidos();
    pedido1 = new PedidoMedio("Alvim", Tamanho.PEQUENO, Proteina.BOI);
    pedido1.setStatusPedido(Status.RETIRADO);
    pedido2 = new PedidoGrande("Matheus", Tamanho.MEDIO, Proteina.BOI);
    pedido2.setStatusPedido(Status.RETIRADO);
    pedido3 = new PedidoMedio("João", Tamanho.GRANDE, Proteina.BOI);
    pedido3.setStatusPedido(Status.RETIRADO);
    pedidoForaDaLista = new PedidoMedio("João", Tamanho.MEDIO, Proteina.VEGANO);
    pedidoForaDaLista.setStatusPedido(Status.EM_PREPARO);
    balanco.addPedidoConcluidos(pedido1);
    balanco.addPedidoConcluidos(pedido2);
  }

  @Test
  void testGetInstance() {
    Balanco instance = Balanco.getInstance();
    assertSame(balanco, instance, "Testando se a instância de Balanco é a mesma");
  }

  @Test
  void testAddPedidoConcluidos() throws RamenShopException {

    balanco.addPedidoConcluidos(pedido3);

    assertEquals(3, balanco.getQuantidadePedidosConcluidos(),
        "Testando se adicionou um pedido na lista de pedidos concluídos");

    assertThrows(RamenShopException.class,
        () -> {
          balanco.addPedidoConcluidos(null);
        },
        "Testando se não adicionou um pedido nulo na lista de pedidos concluídos");

    assertThrows(RamenShopException.class,
        () -> {
          balanco.addPedidoConcluidos(pedidoForaDaLista);
        },
        "Testando se não adicionou um pedido com status inválido na lista de pedidos concluídos");
  }


  @Test
  void testExibirBalanco() throws RamenShopException {
    String balancoEsperado = "=== Balanço Final do Restaurante ===\n" +
        "Lista de Pedidos Realizados:\n" +
        "- Pedido Nº 1: Cliente: Alvim, Tamanho: PEQUENO, Proteína: BOI" +
        "Pedido Médio com proteina: BOI, Status: RETIRADO\n" +
        "- Pedido Nº 2: Cliente: Matheus, Tamanho: MEDIO, Proteína: BOI" +
        "Pedido Grande com proteina: BOI, Status: RETIRADO\n\n" +
        "Quantidade de Pedidos: 2\n" +
        "Receita Total: R$ 38,60\n" +
        "Ticket Médio: R$ 19,30";

    String balancoRecebido = balanco.exibirBalanco();
    assertEquals(balancoEsperado, balancoRecebido, "Testando se o balanço está correto");

  }
}
