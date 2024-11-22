package br.lpm.main.controlador;

import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.lpm.business.decorators.*;
import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoFactory;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.services.GerenciamentoPedido;
import br.lpm.business.services.ImplPedidoRelatorios;
import br.lpm.business.services.PedidoRelatorios;

public class ControladorPedido {
  private final PedidosSingleton pedidosSingleton;
  private final GerenciamentoPedido gerenciamentoPedido;
  private final PedidoFactory ramenFactory;
  private final JFrame frame;

  public ControladorPedido(PedidosSingleton pedidosSingleton, GerenciamentoPedido gerenciamentoPedido,
      PedidoFactory ramenFactory) {
    this.pedidosSingleton = pedidosSingleton;
    this.gerenciamentoPedido = gerenciamentoPedido;
    this.ramenFactory = ramenFactory;
    this.frame = new JFrame("Gerenciamento de Pedidos");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
  }

  public void exibirMenuPrincipal() {
    String menu = """
        Escolha uma opção:
        1. Fazer pedido
        2. Exibir progresso dos pedidos
        3. Retirar pedido da cozinha
        4. Retirada de pedido
        5. Exibir resumo do balanço
        6. Exibir detalhes do balanço
        7. Sair
        """;
    String escolha = JOptionPane.showInputDialog(frame, menu, "Menu Principal", JOptionPane.QUESTION_MESSAGE);

    if (escolha == null) {
      JOptionPane.showMessageDialog(frame, "Encerrando sistema. Até a próxima!", "Saída",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try {
      executarOpcao(Integer.parseInt(escolha));
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(frame, "Entrada inválida. Insira um número correspondente à opção.", "Erro",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  public void executarOpcao(int escolha) {
    PedidoRelatorios relatorios = new ImplPedidoRelatorios(pedidosSingleton);

    switch (escolha) {
      case 1 -> fazerPedido();
      case 2 -> exibirProgressoPedidos(relatorios);
      case 3 -> retirarPedidoCozinha();
      case 4 -> retirarPedido();
      case 5 -> exibirResumoBalanco(relatorios);
      case 6 -> exibirDetalhesBalanco(relatorios);
      case 7 -> JOptionPane.showMessageDialog(frame, "Encerrando sistema. Até a próxima!", "Saída",
          JOptionPane.INFORMATION_MESSAGE);
      default -> JOptionPane.showMessageDialog(frame, "Opção inválida. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void fazerPedido() {
    try {
      String nomeCliente = JOptionPane.showInputDialog(
          frame,
          "Informe o nome do cliente:",
          "Fazer Pedido",
          JOptionPane.QUESTION_MESSAGE);

      if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Nome do cliente é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }

      String[] tamanhos = { "Pequeno (R$ 9,90)", "Médio (R$ 12,90)", "Grande (R$ 15,90)" };
      String[] tamanhosValores = { "PEQUENO", "MEDIO", "GRANDE" };
      int opcaoTamanho = JOptionPane.showOptionDialog(
          frame,
          "Selecione o tamanho do pedido:",
          "Tamanho do Pedido",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          tamanhos,
          tamanhos[0]);

      if (opcaoTamanho == -1) {
        JOptionPane.showMessageDialog(frame, "Seleção de tamanho é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }
      String tamanho = tamanhosValores[opcaoTamanho];

      String[] proteinas = { "Vegano (+ R$ 3,90)", "Boi (+ R$ 7,90)", "Porco (+ R$ 5,90)" };
      String[] proteinasValores = { "VEGANO", "BOI", "PORCO" };
      int opcaoProteina = JOptionPane.showOptionDialog(
          frame,
          "Selecione a proteína:",
          "Proteína",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          proteinas,
          proteinas[0]);

      if (opcaoProteina == -1) {
        JOptionPane.showMessageDialog(frame, "Seleção de proteína é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }
      String proteina = proteinasValores[opcaoProteina];

      Pedido pedido = ramenFactory.criarPedido(tamanho, proteina, nomeCliente);

      if (pedido == null) {
        JOptionPane.showMessageDialog(frame, "Erro ao criar pedido: verifique os valores selecionados.", "Erro",
            JOptionPane.ERROR_MESSAGE);
        return;
      }

      pedido = adicionarAcrecimos(pedido);
      pedido = adicionarBebida(pedido);
      gerenciamentoPedido.adicionarPedido(pedido);
      exibirInformacoesPedido(pedido);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(frame, "Erro ao criar pedido: " + e.getMessage(), "Erro",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private Pedido adicionarAcrecimos(Pedido pedido) {
    String[] opcoes = {
        "Proteína Extra (+ R$ 4,00)",
        "Chilli (+ R$ 2,50)",
        "Crème Alho (+ R$ 1,50)",
        "Croutons (+ R$ 2,00)",
        "Shitake (+ R$ 6,90)",
        "Tofu (+ R$ 2,70)",
        "Finalizar"
    };

    boolean algumItemAdicionado = false;
    boolean finalizarSelecionado = false;

    int opcao;
    do {
      opcao = JOptionPane.showOptionDialog(
          frame,
          "Deseja adicionar algum acréscimo?",
          "Adicionar Acréscimos",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          opcoes,
          opcoes[0]);

      switch (opcao) {
        case 0 -> {
          pedido = new AcrescimoProteinaExtra(pedido);
          algumItemAdicionado = true;
        }
        case 1 -> {
          pedido = new AcrescimoChilli(pedido);
          algumItemAdicionado = true;
        }
        case 2 -> {
          pedido = new AcrescimoCremeAlho(pedido);
          algumItemAdicionado = true;
        }
        case 3 -> {
          pedido = new AcrescimoCroutons(pedido);
          algumItemAdicionado = true;
        }
        case 4 -> {
          pedido = new AcrescimoShitake(pedido);
          algumItemAdicionado = true;
        }
        case 5 -> {
          pedido = new AcrescimoTofu(pedido);
          algumItemAdicionado = true;
        }
        case 6 -> {
          JOptionPane.showMessageDialog(
              frame,
              algumItemAdicionado
                  ? "Os acréscimos foram adicionados ao pedido."
                  : "Nenhum acréscimo será adicionado.",
              "Finalizar",
              JOptionPane.INFORMATION_MESSAGE);
          finalizarSelecionado = true;
        }
        default -> JOptionPane.showMessageDialog(
            frame,
            "Opção inválida. Tente novamente.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }
    } while (!finalizarSelecionado);

    return pedido;
  }

  private Pedido adicionarBebida(Pedido pedido) {
    String[] opcoes = {
        "Refrigerante (R$ 5,90)",
        "O-Cha (Verde) (R$ 3,90)",
        "Ko-Cha (Preto) (R$ 0,00)",
        "Finalizar"
    };

    boolean algumaBebidaAdicionada = false;
    boolean finalizarSelecionado = false;

    int opcao;
    do {
      opcao = JOptionPane.showOptionDialog(
          frame,
          "Deseja adicionar uma bebida?",
          "Adicionar Bebida",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          opcoes,
          opcoes[0]);

      switch (opcao) {
        case 0 -> {
          pedido = new BebidaRefrigerante(pedido);
          algumaBebidaAdicionada = true;
        }
        case 1 -> {
          pedido = new BebidaOCha(pedido);
          algumaBebidaAdicionada = true;
        }
        case 2 -> {
          pedido = new BebidaKoCha(pedido);
          algumaBebidaAdicionada = true;
        }
        case 3 -> {
          JOptionPane.showMessageDialog(
              frame,
              algumaBebidaAdicionada
                  ? "As bebidas foram adicionadas ao pedido."
                  : "Nenhuma bebida será adicionada.",
              "Finalizar",
              JOptionPane.INFORMATION_MESSAGE);
          finalizarSelecionado = true;
        }
        default -> JOptionPane.showMessageDialog(
            frame,
            "Opção inválida. Tente novamente.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
      }
    } while (!finalizarSelecionado);

    return pedido;
  }

  private void exibirInformacoesPedido(Pedido pedidoPronto) {
    if (pedidoPronto == null) {
      JOptionPane.showMessageDialog(
          frame,
          "Erro: Nenhum pedido fornecido.",
          "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    DecimalFormat dformat = new DecimalFormat("#.##");

    String detalhesPedido = "Pedido registrado com sucesso!\n" +
        "Número do pedido: " + pedidoPronto.getNumeroPedido() + "\n" +
        "Senha para retirada: " + pedidoPronto.getSenhaCliente();

    JOptionPane.showMessageDialog(
        frame,
        detalhesPedido,
        "Pedido Registrado",
        JOptionPane.INFORMATION_MESSAGE);

    String resumoPedido = "Cliente: " + pedidoPronto.getNomeCliente() + "\n" +
        "Total do pedido: R$ " + dformat.format(pedidoPronto.getPrecoTotal());

    JOptionPane.showMessageDialog(
        frame,
        resumoPedido,
        "Informações do Pedido",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private void retirarPedidoCozinha() {
    if (pedidosSingleton.getCozinha().getPedidoPronto() == null) {
      JOptionPane.showMessageDialog(frame, "Nenhum pedido disponível na cozinha para retirada.", "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    try {
      String numeroPedidoStr = JOptionPane.showInputDialog(frame, "Informe o número do pedido para confirmar retirada:",
          "Retirar Pedido da Cozinha", JOptionPane.QUESTION_MESSAGE);
      if (numeroPedidoStr == null || numeroPedidoStr.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Número do pedido é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }

      int numeroPedido = Integer.parseInt(numeroPedidoStr);
      gerenciamentoPedido.retirarPedidoCozinha(numeroPedido);
      JOptionPane.showMessageDialog(frame, "Pedido retirado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(frame, "Número do pedido inválido. Tente novamente.", "Erro",
          JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(frame, "Erro ao retirar pedido: " + e.getMessage(), "Erro",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void retirarPedido() {
    String senha = JOptionPane.showInputDialog(frame, "Informe a senha para retirar o pedido:", "Retirar Pedido",
        JOptionPane.QUESTION_MESSAGE);
    verificarPagamento();
    Pedido pedidoPronto = pedidosSingleton.getCozinha().getPedidoPronto();
    if (pedidoPronto == null || !senha.equals(pedidoPronto.getSenhaCliente())) {
      JOptionPane.showMessageDialog(frame, "Nenhum pedido disponível para retirada.", "Erro",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    gerenciamentoPedido.retirarPedido(senha);
  }

  private void verificarPagamento() {
    int resposta = JOptionPane.showOptionDialog(
        frame,
        "O pagamento foi realizado?",
        "Verificar Pagamento",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        new Object[] { "Sim", "Não" },
        "Sim"
    );

    if (resposta == JOptionPane.YES_OPTION) {
      JOptionPane.showMessageDialog(frame, "Pagamento confirmado. Pode retirar seu pedido.", "Informação",
          JOptionPane.INFORMATION_MESSAGE);
    } else if (resposta == JOptionPane.NO_OPTION) {
      JOptionPane.showMessageDialog(frame, "Lembre-se de realizar o pagamento antes de retirar o pedido.", "Atenção",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  private void exibirResumoBalanco(PedidoRelatorios relatorios) {
    if (pedidosSingleton.getPedidosConcluidos().isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Não há pedidos concluídos para exibir no resumo do balanço.", "Informação",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    relatorios.exibirResumoBalanco();
  }

  private void exibirDetalhesBalanco(PedidoRelatorios relatorios) {
    if (pedidosSingleton.getPedidosConcluidos().isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Não há pedidos concluídos para exibir nos detalhes do balanço.",
          "Informação",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    relatorios.exibirDetalhesBalanco();
  }

  private void exibirProgressoPedidos(PedidoRelatorios relatorios) {
    if (pedidosSingleton.getListaPedidos().isEmpty() && pedidosSingleton.getPedidosConcluidos().isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Não há pedidos para exibir o progresso.", "Informação",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    relatorios.exibirProgressoPedidos();
  }
}
