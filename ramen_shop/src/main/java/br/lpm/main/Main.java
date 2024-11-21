package br.lpm.main;

import javax.swing.*;
import br.lpm.business.controller.PedidoController;
import br.lpm.business.pedidos.PedidoFactory;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.ImplPedidoRepositorio;
import br.lpm.business.repository.PedidoRepository;
import br.lpm.business.services.GerenciamentoPedido;
import br.lpm.business.services.ImplGerencimanetoPedido;

public class Main extends JFrame {
    private final PedidoController pedidoController;

    public Main() {
        // Configuração inicial do JFrame
        setTitle("Ramen Shop");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialização de dependências
        PedidosSingleton pedidosSingleton = PedidosSingleton.getInstancia();
        PedidoRepository pedidoRepository = new ImplPedidoRepositorio(pedidosSingleton);
        GerenciamentoPedido gerenciamentoPedido = new ImplGerencimanetoPedido(pedidosSingleton, pedidoRepository);
        PedidoFactory ramenFactory = new PedidoFactory();
        pedidoController = new PedidoController(null, pedidosSingleton, gerenciamentoPedido, ramenFactory);

        // Criação e configuração do menu principal
        JPanel panel = new JPanel();
        JButton btnFazerPedido = new JButton("Fazer Pedido");
        JButton btnRetirarCozinha = new JButton("Retirar da Cozinha");
        JButton btnRetirarPedido = new JButton("Retirar Pedido");
        JButton btnBalanco = new JButton("Exibir Balanço");
        JButton btnProgresso = new JButton("Exibir Progresso");
        JButton btnSair = new JButton("Sair");

        // Adicionando ações aos botões
        btnFazerPedido.addActionListener(e -> pedidoController.executarOpcao(1));
        btnRetirarCozinha.addActionListener(e -> pedidoController.executarOpcao(2));
        btnRetirarPedido.addActionListener(e -> pedidoController.executarOpcao(3));
        btnBalanco.addActionListener(e -> pedidoController.executarOpcao(4));
        btnProgresso.addActionListener(e -> pedidoController.executarOpcao(5));
        btnSair.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sistema encerrado.");
            System.exit(0);
        });

        // Adicionando botões ao painel
        panel.add(btnFazerPedido);
        panel.add(btnRetirarCozinha);
        panel.add(btnRetirarPedido);
        panel.add(btnBalanco);
        panel.add(btnProgresso);
        panel.add(btnSair);

        // Adicionando o painel ao JFrame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
