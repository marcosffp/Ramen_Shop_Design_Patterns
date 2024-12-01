package br.lpm.business.pedidos;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoFactoryTest {

    private final PedidoFactory pedidoFactory = new PedidoFactory();

    @Test
    void testCriarPedidoComEntradasValidas() throws RamenShopException {
        Pedido pedido = pedidoFactory.criarPedido("GRANDE", "BOI", "Marcos");
        assertNotNull(pedido, "Testando se o pedido não é nulo para entradas válidas");
        assertEquals(Tamanho.GRANDE, pedido.getTamanhoPedido(), "Testando se o tamanho é GRANDE");
        assertEquals(Proteina.BOI, pedido.getProteinaPedido(), "Testando se a proteína é BOI");
        assertEquals("Marcos", pedido.getNomeCliente(), "Testando se o nome do cliente é 'Marcos'");
        assertTrue(pedido instanceof PedidoGrande, "Testando se o pedido criado é do tipo PedidoGrande");
    }

    @Test
    void testCriarPedidoComEntradasInvalidas() {
        RamenShopException tamanhoVazioEx = assertThrows(
                RamenShopException.class,
                () -> pedidoFactory.criarPedido("", "BOI", "Alvim"));
        assertEquals("Tamanho do pedido não pode ser vazio", tamanhoVazioEx.getMessage(), "Testando se é lançada uma exceção quando o tamanho é vazio");

        RamenShopException proteinaVaziaEx = assertThrows(
                RamenShopException.class,
                () -> pedidoFactory.criarPedido("GRANDE", "", "Alvim"));
        assertEquals("Proteína do pedido não pode ser vazia", proteinaVaziaEx.getMessage(), "Testando se é lançada uma exceção quando a proteína é vazia");

        RamenShopException nomeClienteVazioEx = assertThrows(
                RamenShopException.class,
                () -> pedidoFactory.criarPedido("GRANDE", "BOI", ""));
        assertEquals("Nome do cliente não pode ser vazio", nomeClienteVazioEx.getMessage(), "Testando se é lançada uma exceção quando o nome do cliente é vazio");

        IllegalArgumentException tamanhoInvalidoEx = assertThrows(
                IllegalArgumentException.class,
                () -> pedidoFactory.criarPedido("INVALIDO", "BOI", "Marcos"));
        assertTrue(
                tamanhoInvalidoEx.getMessage().contains("No enum constant br.lpm.business.model.enums.Tamanho"),
                "Testando se a mensagem de erro menciona enum Tamanho");

        IllegalArgumentException proteinaInvalidaEx = assertThrows(
                IllegalArgumentException.class,
                () -> pedidoFactory.criarPedido("GRANDE", "INVALIDO", "Marcos"),
                "Testando se uma exceção é lançada para proteína inválida");
        assertTrue(
                proteinaInvalidaEx.getMessage().contains("No enum constant br.lpm.business.model.enums.Proteina"),
                "Testando se a mensagem de erro menciona enum Proteina");
    }
}
