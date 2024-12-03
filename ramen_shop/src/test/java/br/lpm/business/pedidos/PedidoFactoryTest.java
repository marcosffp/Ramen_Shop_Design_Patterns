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
        private Pedido pedidoGrande;
        private Pedido pedidoMedio;
        private Pedido pedidoPequeno;

        @BeforeEach
        void setUp() throws RamenShopException {
                pedidoFactory = new PedidoFactory();
                pedidoGrande = pedidoFactory.criarPedido("GRANDE", "BOI", "Marcos");
                pedidoMedio = pedidoFactory.criarPedido("MEDIO", "BOI", "João");
                pedidoPequeno = pedidoFactory.criarPedido("PEQUENO", "BOI", "Matheus");

        }

        @Test
        void testCriarPedidoComEntradasValidas() throws RamenShopException {

                assertNotNull(pedidoGrande, "Testando se o pedido não é nulo para entradas válidas");
                assertEquals(Tamanho.GRANDE, pedidoGrande.getTamanhoPedido(), "Testando se o tamanho é GRANDE");
                assertEquals(Proteina.BOI, pedidoGrande.getProteinaPedido(), "Testando se a proteína é BOI");
                assertEquals("Marcos", pedidoGrande.getNomeCliente(), "Testando se o nome do cliente é 'Marcos'");
                assertTrue(pedidoGrande instanceof PedidoGrande, "Testando se o pedido criado é do tipo PedidoGrande");
                assertTrue(pedidoMedio instanceof PedidoMedio, "Testando se o pedido criado é do tipo PedidoMedio");
                assertTrue(pedidoPequeno instanceof PedidoPequeno, "Testando se o pedido criado é do tipo PedidoPequeno");

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
                                IllegalArgumentException.class,
                                () -> pedidoFactory.criarPedido("INVALIDO", "BOI", "Marcos"),
                                "Testando se é lançada uma exceção quando o tamanho é inválido");

                assertThrows(
                                IllegalArgumentException.class,
                                () -> pedidoFactory.criarPedido("GRANDE", "INVALIDO", "Marcos"),
                                "Testando se é lançada uma exceção quando a proteína é inválida");

        }
}