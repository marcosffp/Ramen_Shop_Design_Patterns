# Ramen Shop - Sistema de Pedidos

## Descrição do Projeto

O Ramen Shop é um sistema de gerenciamento de pedidos para uma loja de ramen, desenvolvido em Java. O sistema permite:

- Criar pedidos personalizados com diferentes tamanhos, proteínas, acréscimos e bebidas
- Processar pedidos e notificar clientes quando estiverem prontos
- Gerar um balanço financeiro com o total de vendas

## Funcionalidades Principais

1. **Fazer Pedido**:
   - Selecionar tamanho do ramen (Pequeno, Médio, Grande)
   - Escolher proteína (Vegano, Boi, Porco)
   - Adicionar acréscimos (Proteína Extra, Chilli, Creme de Alho, etc.)
   - Selecionar bebidas (Refrigerante, Chá Oolong, Chá Preto)

2. **Processar Pedido**:
   - Marcar pedidos como prontos
   - Notificar clientes automaticamente

3. **Balanço Financeiro**:
   - Visualizar total de vendas
   - Ver histórico de pedidos concluídos

## Tecnologias e Padrões Utilizados

- **Padrão Factory**: Para criação de pedidos (`PedidoFactory`)
- **Padrão Decorator**: Para adicionar acréscimos e bebidas aos pedidos
- **Padrão Observer**: Para notificar clientes quando pedidos estão prontos (`Subject`, `Observer`, `Cozinha`, `Cliente`)
- **Singleton**: Para gerenciar a lista de pedidos e o balanço financeiro (`ListaPedidos`, `Balanco`)

## Estrutura do Projeto

```
br.lpm
├── main
│   └── Main.java                # Classe principal com interface de menu
├── business
│   ├── balanco
│   │   └── Balanco.java         # Gerenciamento do balanço financeiro
│   ├── controller
│   │   └── RamenShopController.java # Controlador principal do sistema
│   ├── decorators               # Implementações do padrão Decorator
│   │   ├── AcrescimoChilli.java
│   │   ├── AcrescimoCremeAlho.java
│   │   ├── AcrescimoCroutons.java
│   │   ├── AcrescimoProteinaExtra.java
│   │   ├── AcrescimoShitake.java
│   │   ├── AcrescimoTofu.java
│   │   ├── BebidaKoCha.java
│   │   ├── BebidaOCha.java
│   │   └── BebidaRefrigerante.java
│   ├── exception
│   │   └── RamenShopException.java # Exceções personalizadas
│   ├── model
│   │   └── Pedido.java          # Modelo base para pedidos
│   ├── observer                 # Implementação do padrão Observer
│   │   ├── Cliente.java
│   │   ├── Cozinha.java
│   │   ├── Observer.java
│   │   └── Subject.java
│   ├── pedidos
│   │   ├── ListaPedidos.java    # Gerenciamento da lista de pedidos
│   │   └── PedidoFactory.java   # Factory para criação de pedidos
│   └── util
│       └── GeradorIdPedido.java # Gerador de IDs para pedidos
```

## Como Executar

1. Certifique-se de ter o Java JDK instalado (versão 8 ou superior)
2. Compile todos os arquivos .java:
   ```
   javac br/lpm/main/Main.java
   ```
3. Execute o programa:
   ```
   java br.lpm.main.Main
   ```

## Instruções de Uso

1. **Menu Principal**:
   - Digite o número correspondente à opção desejada
   - 1: Fazer novo pedido
   - 2: Processar próximo pedido
   - 3: Ver balanço financeiro
   - 4: Sair do sistema

2. **Fazendo um Pedido**:
   - Informe o nome do cliente
   - Selecione o tamanho do ramen
   - Escolha a proteína
   - Adicione acréscimos (digite 7 para finalizar)
   - Selecione bebidas (digite 4 para finalizar)

3. **Processando Pedidos**:
   - O sistema automaticamente processa o próximo pedido na fila
   - O cliente é notificado quando o pedido está pronto

## Exemplo de Uso

```
--- Ramen Shop ---
1. Fazer Pedido
2. Processar Pedido
3. Ver Balanço
4. Sair
Escolha uma opção: 1

Nome do Cliente: João Silva

Escolha o tamanho do Ramen:
1. Pequeno (R$ 9,90)
2. Médio (R$ 12,90)
3. Grande (R$ 15,90)
1

Escolha a proteína:
1. Vegano (+ R$ 3,90)
2. Boi (+ R$ 7,90)
3. Porco (+ R$ 5,90)
2

Escolha os acréscimos (Digite 7 para finalizar):
1. Proteína Extra (+ R$ 4,00)
2. Chilli (+ R$ 2,50)
3. Creme de Alho (+ R$ 1,50)
4. Croutons (+ R$ 2,00)
5. Shitake (+ R$ 6,90)
6. Tofu (+ R$ 2,70)
7. Finalizar Acréscimos
1
7

Escolha as bebidas (Digite 4 para finalizar):
1. Refrigerante (R$ 5,90)
2. Chá Oolong (R$ 3,90)
3. Chá Preto (R$ 0,00)
4. Finalizar Bebidas
2
4

Pedido criado com sucesso! Detalhes:
---------------------------------------
Pedido #1
Status: Pendente
Cliente: João Silva
Preço Total: R$ 29,70
Detalhes do Pedido: Ramen [Pequeno] com Boi, Proteína Extra, Bebida: Chá Oolong
---------------------------------------
```

## Observações

- O sistema utiliza tratamento de exceções para entradas inválidas
- Todos os pedidos são armazenados até serem processados
- O balanço financeiro mostra o total acumulado de todos os pedidos concluídos
