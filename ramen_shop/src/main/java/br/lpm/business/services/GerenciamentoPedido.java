package br.lpm.business.services;

import br.lpm.business.model.Pedido;

public interface GerenciamentoPedido {
    public void adicionarPedido(Pedido pedido);

    public void retirarPedido(String senha);

    public void retirarPedidoCozinha(int numeroPedido);

    public void concluirPedido(Pedido pedido);

    public void verificarPedidoFoiCozinha(Pedido pedido);
}
