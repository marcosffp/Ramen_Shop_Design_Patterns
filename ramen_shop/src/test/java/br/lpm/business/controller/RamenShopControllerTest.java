package br.lpm.business.controller;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import br.lpm.business.balanco.Balanco;
import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;
import br.lpm.business.observer.Cozinha;
import br.lpm.business.observer.Subject;
import br.lpm.business.pedidos.ListaPedidos;
import br.lpm.business.pedidos.PedidoFactory;


public class RamenShopControllerTest {

  private static ListaPedidos listaPedidos;
  private static Balanco balanco;
  private static Subject cozinha;
  private static PedidoFactory ramenFactory;
  private static RamenShopController ramenController;
  private static Pedido pedido;

  @BeforeEach
  void setUp() throws RamenShopException {
    listaPedidos = ListaPedidos.getInstance();
    balanco = Balanco.getInstance();
    cozinha = new Cozinha();
    ramenFactory = new PedidoFactory();
    ramenController = new RamenShopController(listaPedidos, balanco, ramenFactory, cozinha);
   pedido = ramenController.fazerPedido("Marcos", "Pequeno", "Boi", List.of(2, 9));
  }

  @Test
  void testFazerPedido() throws RamenShopException {
    assertNotNull(pedido, "Testando se o pedido não é nulo para entradas válidas");
    assertEquals("Marcos", pedido.getNomeCliente(), "Testando se o nome do cliente é 'Marcos'");
    assertEquals(Tamanho.PEQUENO, pedido.getTamanhoPedido(), "Testando se o tamanho é Pequeno");
    assertEquals(Proteina.BOI, pedido.getProteinaPedido(), "Testando se a proteína é Boi");
    assertTrue(pedido.getPrecoTotal() > 0, "Testando se o preço total é maior que zero");
    assertTrue(listaPedidos.getQuantidadePedidos() > 0, "Testando se a lista de pedidos não está vazia");

    assertThrows(RamenShopException.class, () -> {
      ramenController.fazerPedido("", "Pequeno", "Boi", List.of(2, 9));
    }, "Testando se é lançada uma exceção quando o nome do cliente é vazio");

    assertThrows(RamenShopException.class, () -> {
      ramenController.fazerPedido(null, "Pequeno", "Boi", List.of(2, 9));
    }, "Testando se é lançada uma exceção quando o nome do cliente é nulo");

    assertThrows(RamenShopException.class, () -> {
      ramenController.fazerPedido("Marcos", "", "Boi", List.of(2, 9));
    }, "Testando se é lançada uma exceção quando o tamanho é vazio");

    assertThrows(RamenShopException.class, () -> {
      ramenController.fazerPedido("Marcos", null, "Boi", List.of(2, 9));
    }, "Testando se é lançada uma exceção quando o tamanho é nulo");

    assertThrows(RamenShopException.class, () -> {
      ramenController.fazerPedido("Marcos", "Pequeno", "", List.of(2, 9));
    }, "Testando se é lançada uma exceção quando a proteína é vazia");

    assertThrows(RamenShopException.class, () -> {
      ramenController.fazerPedido("Marcos", "Pequeno", null, List.of(2, 9));
    }, "Testando se é lançada uma exceção quando a proteína é nula");

  }

  @Test
  void testGetPedido() throws RamenShopException {
    Pedido pedidoRecuperado = ramenController.getPedido(pedido.getNumeroPedido());
    assertNotNull(pedidoRecuperado, "Testando se o pedido recuperado não é nulo");
    assertEquals(pedido.getNumeroPedido(), pedidoRecuperado.getNumeroPedido(),
        "Testando se o número do pedido é o mesmo");
  }

  @Test
  void testObterBalanco() throws RamenShopException {
    String balancoAtual = ramenController.obterBalanco();
    assertNotNull(balancoAtual, "Testando se o balanço não é nulo");
    String num=String.valueOf(pedido.getNumeroPedido());
    ramenController.retirarPedidoCozinha(num);
    String balancoAtual2 = ramenController.obterBalanco();
    assertTrue(balancoAtual2.contains("Marcos"), "Testando se o balanço contém o nome do cliente");
  }

  @Test
  void testObterInformacoesPedido() throws RamenShopException {
    String informacoesPedido = ramenController.obterInformacoesPedido(pedido);
    assertNotNull(informacoesPedido, "Testando se as informações do pedido não são nulas");
    assertTrue(informacoesPedido.contains("Pedido #"), "Testando se as informações do pedido contêm o número do pedido");
    assertTrue(informacoesPedido.contains("Cliente: Marcos"), "Testando se as informações do pedido contêm o nome do cliente");
    assertTrue(informacoesPedido.contains("Preço Total: R$"), "Testando se as informações do pedido contêm o preço total");
  }

  @Test
  void testRetirarPedido() throws RamenShopException {
    String resultadoRetirada = ramenController.retirarPedidoCozinha(String.valueOf(pedido.getNumeroPedido()));
    assertNotNull(resultadoRetirada, "Testando se o resultado da retirada não é nulo");

    String mensagemEsperada = "Notificação para o cliente " + pedido.getNomeCliente() + ": Seu pedido com o número "
        + cozinha.getPedidoPronto().getNumeroPedido() + " está pronto!";

    assertTrue(resultadoRetirada.contains(mensagemEsperada),
        "Testando se a mensagem de retirada contém a notificação para o cliente");
    String numeroPedidoInvalido = "999999"; 
    RamenShopException thrown = assertThrows(RamenShopException.class, () -> {
      ramenController.retirarPedidoCozinha(numeroPedidoInvalido);
    }, "Testando se uma exceção é lançada para número de pedido inválido");
    assertEquals("Número do pedido inválido.", thrown.getMessage(), "Testando se a mensagem de erro é correta");

  }
}
