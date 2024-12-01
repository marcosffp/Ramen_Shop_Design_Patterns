package br.lpm.business.model;

import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Status;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.util.GeradorIdPedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

  private Pedido pedido1;
  private Pedido pedido2;

  @BeforeEach
  void setUp() {
    GeradorIdPedido.reset();
    pedido1 = new Pedido("Marcos", Tamanho.GRANDE, Proteina.BOI) {
      @Override
      public String exibirDetalhes() {
        return "Pedido #" + getNumeroPedido() + " - " + getNomeCliente() +
            ": " + getTamanhoPedido() + ", " + getProteinaPedido();
      }
    };
    pedido2 = new Pedido("Alvim", Tamanho.PEQUENO, Proteina.PORCO) {
      @Override
      public String exibirDetalhes() {
        return "Pedido #" + getNumeroPedido() + " - " + getNomeCliente() +
            ": " + getTamanhoPedido() + ", " + getProteinaPedido();
      }
    };
  }

  @Test
  void testExibirDetalhes() {
    String detalhes1 = pedido1.exibirDetalhes();
    String detalhes2 = pedido2.exibirDetalhes();

    assertEquals("Pedido #1 - Marcos: GRANDE, BOI", detalhes1, "Testando se o exibirDetalhes() está correto");
    assertEquals("Pedido #2 - Alvim: PEQUENO, PORCO", detalhes2, "Testando se o exibirDetalhes() está correto");
  }

  @Test
  void testGetNomeCliente() {
    assertEquals("Marcos", pedido1.getNomeCliente(), "Testando se o nome do cliente está correto");
    assertEquals("Alvim", pedido2.getNomeCliente(), "Testando se o nome do cliente está correto");
  }

  @Test
  void testSequenciaDeNumeros() {
    assertEquals(1, pedido1.getNumeroPedido(), "Testando se o número do pedido 1 está correto");
    assertEquals(2, pedido2.getNumeroPedido(), "Testando se o número do pedido 2 está correto");
  }

  @Test
  void testGetNumeroPedido() {
    assertNotEquals(pedido1.getNumeroPedido(), pedido2.getNumeroPedido(), 0.01, "Testando se os números dos pedidos são diferentes");
    assertEquals(pedido1.getNumeroPedido() + 1, pedido2.getNumeroPedido(), 0.01, "Testando se os números dos pedidos são sequenciais");
  }

  @Test
  void testGetPrecoTotal() {
    double precoPedido1 = pedido1.getPrecoTotal();
    double precoPedido2 = pedido2.getPrecoTotal();

    assertEquals(Tamanho.GRANDE.getPreco() + Proteina.BOI.getPreco(), precoPedido1, 0.01, "Testando se o preço total do pedido 1 está correto");
    assertEquals(Tamanho.PEQUENO.getPreco() + Proteina.PORCO.getPreco(), precoPedido2, 0.01, "Testando se o preço total do pedido 2 está correto");
  }

  @Test
  void testGetProteinaPedido() {
    assertEquals(Proteina.BOI, pedido1.getProteinaPedido(), "Testando se a proteína do pedido 1 está correta");
    assertEquals(Proteina.PORCO, pedido2.getProteinaPedido(), "Testando se a proteína do pedido 2 está correta");
  }

  @Test
  void testGetStatusPedido() {
    assertEquals(Status.PENDENTE, pedido1.getStatusPedido(), "Testando se o status do pedido 1 está correto");
    assertEquals(Status.PENDENTE, pedido2.getStatusPedido(), "Testando se o status do pedido 2 está correto");
  }

  @Test
  void testGetTamanhoPedido() {
    assertEquals(Tamanho.GRANDE, pedido1.getTamanhoPedido(), "Testando se o tamanho do pedido 1 está correto");
    assertEquals(Tamanho.PEQUENO, pedido2.getTamanhoPedido(), "Testando se o tamanho do pedido 2 está correto");
  }

  @Test
  void testSetStatusPedido() {
    pedido1.setStatusPedido(Status.EM_PREPARO);
    pedido2.setStatusPedido(Status.EM_PREPARO);

    assertEquals(Status.EM_PREPARO, pedido1.getStatusPedido(), "Testando se o status do pedido 1 foi alterado corretamente");
    assertEquals(Status.EM_PREPARO, pedido2.getStatusPedido(), "Testando se o status do pedido 2 foi alterado corretamente");
  }
}
