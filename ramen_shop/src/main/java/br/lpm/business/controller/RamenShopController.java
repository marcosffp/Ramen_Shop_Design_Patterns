package br.lpm.business.controller;

import java.util.List;

import br.lpm.business.balanco.Balanco;
import br.lpm.business.decorators.*;
import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.observer.Cliente;
import br.lpm.business.observer.Observer;
import br.lpm.business.observer.Subject;
import br.lpm.business.pedidos.ListaPedidos;
import br.lpm.business.pedidos.PedidoFactory;

public class RamenShopController {

  private final ListaPedidos listaPedidos;
  private final Balanco balanco;
  private final Subject cozinha;
  private final PedidoFactory ramenFactory;
  private  Observer cliente;
  private  Pedido pedido;

  public RamenShopController(ListaPedidos listaPedidos, Balanco balanco, PedidoFactory ramenFactory, Subject cozinha) {
    this.listaPedidos = listaPedidos;
    this.balanco = balanco;
    this.ramenFactory = ramenFactory;
    this.cozinha = cozinha;
  }

  public Pedido fazerPedido(String nomeCliente, String tamanho, String proteina, List<Integer> opcoesSelecionadas)
      throws RamenShopException {
    validarNomeCliente(nomeCliente);
    pedido = ramenFactory.criarPedido(tamanho, proteina, nomeCliente);
    adicionarAcrecimosEBeberagens(opcoesSelecionadas);
    listaPedidos.addPedido(pedido);
    registrarCliente(nomeCliente);
    return pedido;
  }

  private void validarNomeCliente(String nomeCliente) throws RamenShopException {
    if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
      throw new RamenShopException("Nome do cliente é obrigatório.");
    }
  }

  private void adicionarAcrecimosEBeberagens(List<Integer> opcoesSelecionadas)throws RamenShopException {
    for (int opcao : opcoesSelecionadas) {
      if (opcao >= 1 && opcao <= 3) { 
        adicionarBebida(opcao);
      } else if (opcao >= 4 && opcao <= 9) { 
        adicionarAcrescimo(opcao);
      } else {
        throw new RamenShopException("Opção inválida.");
      }
    }
  }

  private void adicionarBebida(int opcao) throws RamenShopException {
    switch (opcao) {
      case 1 -> pedido = new BebidaRefrigerante(pedido);
      case 2 -> pedido = new BebidaOCha(pedido);
      case 3 -> pedido = new BebidaKoCha(pedido);
      default -> throw new RamenShopException("Opção de bebida inválida.");
    }
  }

  private void adicionarAcrescimo(int opcao) throws RamenShopException {
    switch (opcao) {
      case 4 -> pedido = new AcrescimoProteinaExtra(pedido);
      case 5 -> pedido = new AcrescimoChilli(pedido);
      case 6 -> pedido = new AcrescimoCremeAlho(pedido);
      case 7 -> pedido = new AcrescimoCroutons(pedido);
      case 8 -> pedido = new AcrescimoShitake(pedido);
      case 9 -> pedido = new AcrescimoTofu(pedido);
      default -> throw new RamenShopException("Opção de acréscimo inválida.");
    }
  }

  private void registrarCliente(String nomeCliente) throws RamenShopException {
    cliente = new Cliente(cozinha, nomeCliente);
  }

  public String obterInformacoesPedido(Pedido pedidoPronto) {
    if (pedidoPronto == null) {
      throw new IllegalArgumentException("Erro: Nenhum pedido fornecido.");
    }
    return "Pedido #" + pedidoPronto.getNumeroPedido() +
        "\nStatus: " + pedidoPronto.getStatusPedido() +
        "\nCliente: " + pedidoPronto.getNomeCliente() +
        "\nPreço Total: R$ " + String.format("%.2f", pedidoPronto.getPrecoTotal()) +
        "\nDetalhes do Pedido: " + pedidoPronto.exibirDetalhes();
  }

  public String processarPedido() throws RamenShopException {
    Pedido pedido = listaPedidos.proximoPedido();
    cozinha.setPedidoPronto(pedido);
    String notificacao = cliente.notificar();
    pedido = cozinha.retirarPedidoPronto();
    balanco.addPedidoConcluidos(pedido);
    return notificacao;
  }

  public String obterBalanco() throws RamenShopException {
    return balanco.exibirBalanco();
  }
}
