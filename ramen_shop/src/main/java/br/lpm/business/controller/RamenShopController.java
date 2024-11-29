package br.lpm.business.controller;

import java.util.List;

import br.lpm.business.balanco.Balanco;
import br.lpm.business.decorators.*;
import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.observer.ClienteObserver;
import br.lpm.business.observer.Observer;
import br.lpm.business.observer.Subject;
import br.lpm.business.pedidos.ListaPedidos;
import br.lpm.business.pedidos.PedidoFactory;

public class RamenShopController {

  private final ListaPedidos listaPedidos;
  private final Balanco balanco;
  private final Subject cozinha;
  private final PedidoFactory ramenFactory;
  private static Observer cliente;
  private static Pedido pedido;

  public RamenShopController(ListaPedidos listaPedidos, Balanco balanco, PedidoFactory ramenFactory, Subject cozinha) {
    this.listaPedidos = listaPedidos;
    this.balanco = balanco;
    this.ramenFactory = ramenFactory;
    this.cozinha = cozinha;
  }

  // Método para fazer o pedido
  public Pedido fazerPedido(String nomeCliente, String tamanho, String proteina, List<Integer> opcoesSelecionadas)
      throws RamenShopException {

    validarNomeCliente(nomeCliente);

    pedido = ramenFactory.criarPedido(tamanho, proteina, nomeCliente);

    adicionarAcrecimosEBeberagens(opcoesSelecionadas);

    listaPedidos.addPedido(pedido);

    registrarCliente(nomeCliente);

    return pedido;
  }

  // Valida o nome do cliente
  private void validarNomeCliente(String nomeCliente) throws RamenShopException {
    if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
      throw new RamenShopException("Nome do cliente é obrigatório.");
    }
  }

  private void adicionarAcrecimosEBeberagens(List<Integer> opcoesSelecionadas) {
    for (int opcao : opcoesSelecionadas) {
      if (opcao >= 1 && opcao <= 3) { // Bebidas
        adicionarBebida(opcao);
      } else if (opcao >= 4 && opcao <= 9) { // Acréscimos
        adicionarAcrescimo(opcao);
      } else {
        throw new IllegalArgumentException("Opção inválida.");
      }
    }
  }

  private void adicionarBebida(int opcao) {
    switch (opcao) {
      case 1:
        pedido = new BebidaRefrigerante(pedido);
        break;
      case 2:
        pedido = new BebidaOCha(pedido);
        break;
      case 3:
        pedido = new BebidaKoCha(pedido);
        break;
      default:
        throw new IllegalArgumentException("Opção de bebida inválida.");
    }
  }

  private void adicionarAcrescimo(int opcao) {
    switch (opcao) {
      case 4:
        pedido = new AcrescimoProteinaExtra(pedido);
        break;
      case 5:
        pedido = new AcrescimoChilli(pedido);
        break;
      case 6:
        pedido = new AcrescimoCremeAlho(pedido);
        break;
      case 7:
        pedido = new AcrescimoCroutons(pedido);
        break;
      case 8:
        pedido = new AcrescimoShitake(pedido);
        break;
      case 9:
        pedido = new AcrescimoTofu(pedido);
        break;
      default:
        throw new IllegalArgumentException("Opção de acréscimo inválida.");
    }
  }

  // Registra o cliente como observador e notifica a cozinha
  private void registrarCliente(String nomeCliente) throws RamenShopException {
    cliente = new ClienteObserver(cozinha, nomeCliente);
    cozinha.registrarObservador(cliente);
  }

  // Retorna as informações do pedido como uma String formatada
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

  // Realiza a lógica para retirar o pedido da cozinha e retorna as mensagens
  // geradas
  public String retirarPedidoCozinha(String numeroPedidoStr) throws RamenShopException {
    try {
      int numeroPedido = Integer.parseInt(numeroPedidoStr);
      Pedido pedido = listaPedidos.retirarPedido(numeroPedido);
      cozinha.setPedidoPronto(pedido);
      String notificacao = cliente.notificar();
      pedido = cozinha.retirarPedidoPronto();
      balanco.addPedidoConcluidos(pedido);

      return notificacao;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Número do pedido inválido.");
    }
  }

  // Retorna o balanço financeiro como uma String
  public String obterBalanco() throws RamenShopException {
    return balanco.exibirBalanco();
  }

  public Pedido getPedido(int numeroPedido) throws RamenShopException {
    return listaPedidos.getPedido(numeroPedido);
  }
}
