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
import br.lpm.business.util.GeradorIdPedido;

public class RamenShopControllerTest {

  private static ListaPedidos listaPedidos;
  private static Balanco balanco;
  private static Subject cozinha;
  private static PedidoFactory ramenFactory;
  private static RamenShopController ramenController;
  private static Pedido pedido;

  @BeforeEach
  void setUp() throws RamenShopException {
    GeradorIdPedido.reset();
    listaPedidos = ListaPedidos.getInstance();
    listaPedidos.removerTodosPedidos();
    balanco = Balanco.getInstance();
    balanco.removerTodosPedidosConcluidos();
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
  void testObterBalanco() throws RamenShopException {
    ramenController.processarPedido();
    String balancoAtual = ramenController.obterBalanco();
    assertNotNull(balancoAtual, "Testando se o balanço não é nulo");
    assertTrue(balancoAtual.contains("Receita Total: R$"),
        "Testando se o balanço contém a receita total");
    assertTrue(balancoAtual.contains("Ticket Médio: R$"), "Testando se o balanço contém o ticket médio");
  }

  @Test
  void testObterInformacoesPedido() throws RamenShopException {
    String informacoesPedido = ramenController.obterInformacoesPedido(pedido);
    assertNotNull(informacoesPedido, "Testando se as informações do pedido não são nulas");
    assertTrue(informacoesPedido.contains("Pedido #"),
        "Testando se as informações do pedido contêm o número do pedido");
    assertTrue(informacoesPedido.contains("Cliente: Marcos"),
        "Testando se as informações do pedido contêm o nome do cliente");
    assertTrue(informacoesPedido.contains("Preço Total: R$"),
        "Testando se as informações do pedido contêm o preço total");
    
        
  }

  @Test
  void testProcessarPedido() throws RamenShopException {
    String notificacao = ramenController.processarPedido();
    assertNotNull(notificacao, "Testando se a notificação não é nula");
    assertTrue(notificacao.contains("Seu pedido com o número"), "Testando se a notificação contém 'Seu pedido'");
    assertThrows(RamenShopException.class, () -> {
      listaPedidos.proximoPedido();
    },
        "Testando se é lançada uma exceção quando não há pedidos na lista");
  }
}
