package br.lpm.business.repository;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoMedio;
import br.lpm.business.pedidos.PedidoPequeno;
import br.lpm.business.pedidos.PedidosSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImplPedidoRepositorioTest {

  private PedidoRepository pedidoRepositorio;
  private PedidosSingleton pedidosSingleton;
  private Pedido pedido1;
  private Pedido pedido2;

  @BeforeEach
  void setUp() {
    
    pedidosSingleton = PedidosSingleton.getInstancia();

    pedidosSingleton.getListaPedidos().clear();

    pedido1 = new PedidoPequeno("BOI", "Marcos", "1234");
    pedido2 = new PedidoMedio("PORCO", "Alvim", "2345");
    pedidosSingleton.getListaPedidos().add(pedido1);
    pedidosSingleton.getListaPedidos().add(pedido2);

    pedidoRepositorio = new ImplPedidoRepositorio(pedidosSingleton);
  }

  @Test
  void testBuscarPedidoPorNumero() {
    PedidoPequeno.resetContador();
    PedidoPequeno pedido = new PedidoPequeno("BOI", "Marcos", "senha123");
    PedidosSingleton.getInstancia().getListaPedidos().add(pedido);
    Pedido resultado = pedidoRepositorio.buscarPedidoPorNumero(1);
    assertEquals("Marcos", resultado.getNomeCliente(), "Testando se o número do pedido corresponde.");
  }

  @Test
  void testBuscarPedidoPorSenha() {
    Pedido resultado = pedidoRepositorio.buscarPedidoPorSenha("2345");
    assertEquals("2345", resultado.getSenhaCliente(), "Testando se a senha do pedido corresponde.");
  }
}
