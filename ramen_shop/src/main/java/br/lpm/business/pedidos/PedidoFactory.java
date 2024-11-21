package br.lpm.business.pedidos;

import br.lpm.business.model.Pedido;
import br.lpm.business.utils.GeradorSenha;

public class PedidoFactory {

  public Pedido criarPedido(String tipoTamanho, String proteina, String nomeCliente) {
    if (tipoTamanho == null || proteina == null || tipoTamanho.isBlank() || proteina.isBlank()) {
      System.out.println("Erro ao criar pedido: tipo de tamanho ou proteína não podem ser nulos ou vazios.");
    }

    String senha = GeradorSenha.gerarSenha(); 

    return switch (tipoTamanho.toUpperCase()) {
      case "PEQUENO" -> new PedidoPequeno(proteina, nomeCliente, senha);
      case "MEDIO" -> new PedidoMedio(proteina, nomeCliente, senha);
      case "GRANDE" -> new PedidoGrande(proteina, nomeCliente, senha);
      default -> {
        System.out.println("Erro ao criar pedido: tipo de tamanho inválido.");
        yield null;
      }
    };
  }
}
