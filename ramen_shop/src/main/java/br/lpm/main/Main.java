package br.lpm.main;

import java.util.Scanner;

import br.lpm.business.controller.PedidoController;
import br.lpm.business.pedidos.PedidoFactory;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.ImplPedidoRepositorio;
import br.lpm.business.repository.PedidoRepository;
import br.lpm.business.services.GerenciamentoPedido;
import br.lpm.business.services.ImplGerenciamentoPedido;
import br.lpm.business.services.NotificacaoService;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializa os componentes
        PedidosSingleton pedidosSingleton = PedidosSingleton.getInstancia();
        PedidoRepository pedidoRepository = new ImplPedidoRepositorio(pedidosSingleton);
        GerenciamentoPedido gerenciamentoPedido = new ImplGerenciamentoPedido(pedidosSingleton, pedidoRepository);
        PedidoFactory ramenFactory = new PedidoFactory();
        NotificacaoService notificacaoService = new NotificacaoService();

        // Cria o controller
        PedidoController pedidoController = new PedidoController(scanner, pedidosSingleton, gerenciamentoPedido,
                ramenFactory, notificacaoService);

        System.out.println("Bem-vindo à Ramen Factory!");

        while (true) {
            pedidoController.exibirMenuPrincipal();
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (escolha == 6)
                break;

            pedidoController.executarOpcao(escolha);
        }

        System.out.println("Sistema encerrado.");
        scanner.close();
    }
}
