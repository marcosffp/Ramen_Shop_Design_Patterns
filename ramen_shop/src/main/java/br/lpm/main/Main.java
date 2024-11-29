package br.lpm.main;

import javax.swing.*;
import java.awt.GridLayout;

import br.lpm.business.balanco.GerenciamentoPedido;
import br.lpm.business.balanco.ImplGerenciamentoPedido;
import br.lpm.business.pedidos.PedidoFactory;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.ImplPedidoRepositorio;
import br.lpm.business.repository.PedidoRepository;
import br.lpm.main.controlador.ControladorPedido;


public class Main extends JFrame {
    private final ControladorPedido controladorPedido;

    public Main() {
        setTitle("Ramen Shop");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PedidosSingleton pedidosSingleton = PedidosSingleton.getInstancia();
        PedidoRepository pedidoRepository = new ImplPedidoRepositorio();
        GerenciamentoPedido gerenciamentoPedido = new ImplGerenciamentoPedido(pedidosSingleton, pedidoRepository);
        PedidoFactory ramenFactory = new PedidoFactory();
        controladorPedido = new ControladorPedido(pedidosSingleton, gerenciamentoPedido, ramenFactory);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JButton btnFazerPedido = new JButton("Fazer Pedido");
        JButton btnRetirarCozinha = new JButton("Retirar da Cozinha");
        JButton btnRetirarPedido = new JButton("Retirar Pedido");
        JButton btnResumoBalanco = new JButton("Resumo do Balanço");
        JButton btnDetalhesBalanco = new JButton("Detalhes do Balanço");
        JButton btnProgresso = new JButton("Exibir Progresso");
        JButton btnSair = new JButton("Sair");

        btnFazerPedido.addActionListener(e -> executarOpcao(1));
        btnProgresso.addActionListener(e -> executarOpcao(2));
        btnRetirarCozinha.addActionListener(e -> executarOpcao(3));
        btnRetirarPedido.addActionListener(e -> executarOpcao(4));
        btnResumoBalanco.addActionListener(e -> executarOpcao(5));
        btnDetalhesBalanco.addActionListener(e -> executarOpcao(6));
        btnSair.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sistema encerrado.");
            System.exit(0);
        });

        panel.add(btnFazerPedido);
        panel.add(btnProgresso);
        panel.add(btnRetirarCozinha);
        panel.add(btnRetirarPedido);
        panel.add(btnResumoBalanco);
        panel.add(btnDetalhesBalanco);
        panel.add(btnSair);

        add(panel);
    }

    private void executarOpcao(int opcao) {
        try {
            controladorPedido.executarOpcao(opcao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
