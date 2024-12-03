package br.lpm.business.pedidos;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class PedidoFactoryTest {

        private PedidoFactory pedidoFactory;
        private Pedido pedido;

        @BeforeEach
        void setUp() throws RamenShopException {
                pedidoFactory = new PedidoFactory();
                pedido = pedidoFactory.criarPedido("GRANDE", "BOI", "Marcos");
        }

        @Test
        void testCriarPedidoComEntradasValidas() throws RamenShopException {

                assertNotNull(pedido, "Testando se o pedido não é nulo para entradas válidas");
                assertEquals(Tamanho.GRANDE, pedido.getTamanhoPedido(), "Testando se o tamanho é GRANDE");
                assertEquals(Proteina.BOI, pedido.getProteinaPedido(), "Testando se a proteína é BOI");
                assertEquals("Marcos", pedido.getNomeCliente(), "Testando se o nome do cliente é 'Marcos'");
                assertTrue(pedido instanceof PedidoGrande, "Testando se o pedido criado é do tipo PedidoGrande");

                assertThrows(
                                RamenShopException.class,
                                () -> {
                                        pedidoFactory.criarPedido("", "BOI", "Alvim");
                                }, "Testando se é lançada uma exceção quando o tamanho é vazio");

                assertThrows(
                                RamenShopException.class,
                                () -> {
                                        pedidoFactory.criarPedido("GRANDE", "", "Alvim");
                                }, "Testando se é lançada uma exceção quando a proteína é vazia");

                assertThrows(
                                RamenShopException.class,
                                () -> {
                                        pedidoFactory.criarPedido("GRANDE", "BOI", "");
                                }, "Testando se é lançada uma exceção quando o nome do cliente é vazio");

                assertThrows(
                                RamenShopException.class,
                                () -> {
                                        pedidoFactory.criarPedido("INVALIDO", "BOI", "Marcos");
                                }, "Testando se é lançada uma exceção quando o tamanho é inválido");

                assertThrows(
                                RamenShopException.class,
                                () -> {
                                        pedidoFactory.criarPedido("GRANDE", "INVALIDO", "Marcos");
                                }, "Testando se é lançada uma exceção quando a proteína é inválida");

        }
}