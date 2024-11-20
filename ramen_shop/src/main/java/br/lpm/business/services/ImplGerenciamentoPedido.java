package br.lpm.business.services;

import java.time.LocalDateTime;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.PedidoRepository;
import br.lpm.business.utils.CalculoTempo;

public class ImplGerenciamentoPedido implements GerenciamentoPedido {

  private final PedidosSingleton pedidosSingleton;
  private final PedidoRepository pedidoRepository;

  public ImplGerenciamentoPedido(PedidosSingleton pedidosSingleton, PedidoRepository pedidoRepository) {
    this.pedidosSingleton = pedidosSingleton;
    this.pedidoRepository = pedidoRepository;
  }

  @Override
  public void adicionarPedido(Pedido pedido) {
    if (pedido != null) {
      pedidosSingleton.getListaPedidos().add(pedido);
      pedidosSingleton.getTemposPreparo().put(pedido.getNumeroPedido(), LocalDateTime.now());
      System.out.println("Pedido número " + pedido.getNumeroPedido() + " enviado para a cozinha.");
      pedidosSingleton.getCozinha().setPedidoPronto(pedido);
  
    }
  }

  @Override
  public void retirarPedidoCozinha(int numeroPedido) {
    Pedido pedido = pedidoRepository.buscarPedidoPorNumero(numeroPedido);
    if (pedido != null) {
      System.out.println("Pedido número " + numeroPedido + " está pronto para retirada.");

      CalculoTempo.duracaoProcessamentoPedido(pedido, pedidosSingleton);
    } else {
      System.out.println("Pedido não encontrado na cozinha.");
    }
  }

  @Override
  public void retirarPedido(String senha) {
    Pedido pedidoEncontrado = pedidoRepository.buscarPedidoPorSenha(senha);
    if (pedidoEncontrado != null) {
      System.out.println("Senha válida. Pedido retirado pelo cliente.");
      CalculoTempo.duracaoProcessamentoPedido(pedidoEncontrado, pedidosSingleton);
      pedidosSingleton.getListaPedidos().remove(pedidoEncontrado);
      this.concluirPedido(pedidoEncontrado);
    } else {
      System.out.println("Senha inválida ou pedido não encontrado.");
    }
  }

  @Override
  public void concluirPedido(Pedido pedido) {
    if (pedido != null) {
      pedidosSingleton.getPedidosConcluidos().add(pedido);
      System.out.println("Pedido número " + pedido.getNumeroPedido() + " concluído.");
    } else {
      System.out.println("Erro ao concluir pedido: Pedido não encontrado.");
    }
  }

  @Override
  public void verificarPedidoFoiCozinha(Pedido pedido) {
    if (pedido != null) {
      System.out.println("Pedido número " + pedido.getNumeroPedido() + " confirmado como retirado.");
    } else {
      System.out.println("Erro: Pedido não encontrado nos pedidos concluídos.");
    }
  }
}
