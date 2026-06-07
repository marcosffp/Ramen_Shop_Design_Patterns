<p align="center">
  <img width="100%" style="border-radius:12px" alt="banner" src="images/banner.png" />
</p>

# 🍜 Ramen Shop — Sistema de Pedidos

> Sistema de gerenciamento de pedidos para uma loja de ramen, escrito em Java como estudo prático de padrões de projeto (Factory Method, Decorator, Observer e Singleton) aplicados a um fluxo real de pedido, produção e balanço financeiro.

---

## 🛠️ Stack Principal

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit-5.8-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-5.5-78A641?style=for-the-badge&logoColor=white)

---

## 📑 Sumário

- [Sobre o projeto](#-sobre-o-projeto)
- [Arquitetura e fluxo de execução](#-arquitetura-e-fluxo-de-execução)
- [Padrões de projeto utilizados](#-padrões-de-projeto-utilizados)
- [Estrutura de pastas](#-estrutura-de-pastas)
- [Funcionalidades](#-funcionalidades)
- [Como executar](#-como-executar)
- [Exemplo de uso](#-exemplo-de-uso)
- [Testes](#-testes)
- [Tecnologias e dependências](#-tecnologias-e-dependências)
- [Licença](#-licença)

---

## 📖 Sobre o projeto

O **Ramen Shop** é um sistema de linha de comando para gerenciar os pedidos de uma loja de ramen. Pelo menu interativo, o atendente monta um pedido sob medida — escolhendo tamanho da tigela, proteína, acréscimos e bebidas —, acompanha o pedido até ficar pronto e consulta o balanço financeiro acumulado da loja.

Mais do que um sistema de pedidos, o projeto é um exercício guiado de **padrões de projeto (GoF)**: cada decisão de modelagem — como criar um pedido, como compor acréscimos sem multiplicar subclasses, como avisar o cliente quando o prato fica pronto e como manter fila e balanço consistentes em toda a aplicação — foi resolvida aplicando um padrão clássico, descrito em detalhes em [Padrões de projeto utilizados](#-padrões-de-projeto-utilizados).

---

## 🏛️ Arquitetura e fluxo de execução

A aplicação segue uma separação simples entre **apresentação** (`Main`, menu via `Scanner`), **orquestração** (`RamenShopController`) e **domínio** (modelos, fábrica, decoradores, observadores e repositórios em memória). O controller é o único ponto que conhece e conecta todas as peças:

```
Main (menu interativo)
   │
   ▼
RamenShopController — orquestra todo o ciclo do pedido
   │
   ├── PedidoFactory      → cria PedidoPequeno / PedidoMedio / PedidoGrande     [Factory Method]
   ├── Decorators         → empilham acréscimos e bebidas sobre o Pedido        [Decorator]
   ├── ListaPedidos       → enfileira e libera os pedidos pendentes             [Singleton]
   ├── Cozinha + Cliente  → avisam o cliente quando o pedido fica pronto        [Observer]
   └── Balanco            → acumula receita, ticket médio e histórico           [Singleton]
```

**Ciclo de vida de um pedido:**

1. `PedidoFactory` instancia o `Pedido` correto (`PedidoPequeno`, `PedidoMedio` ou `PedidoGrande`) a partir do tamanho escolhido.
2. Cada acréscimo e bebida selecionados envolve o pedido em um `AcrescimoDecorator`/`BebidaDecorator`, empilhando preço e descrição sem alterar a classe original.
3. O pedido entra na fila do `ListaPedidos` (Singleton) com status `PENDENTE`.
4. Ao processar, o `Cliente` se registra como `Observer` da `Cozinha` (`Subject`); quando o status avança para `PRONTO`, a cozinha notifica o cliente automaticamente.
5. O pedido retirado (`RETIRADO`) é registrado no `Balanco` (Singleton), que acumula receita total, ticket médio e histórico de pedidos concluídos.

---

## 🧩 Padrões de projeto utilizados

| Padrão | Onde se aplica | Por que foi escolhido |
|---|---|---|
| **Factory Method** | `PedidoFactory` decide, a partir do tamanho informado, se cria um `PedidoPequeno`, `PedidoMedio` ou `PedidoGrande` | Centraliza a lógica de criação dos pedidos em um único ponto, mantendo o controller livre de cadeias de `if`/`switch` para escolher subtipos |
| **Decorator** | `AcrescimoDecorator` e `BebidaDecorator` envolvem um `Pedido` para somar preço e descrição de cada acréscimo (Chilli, Tofu, Shitake...) e bebida (Refrigerante, Chá Oolong...) | Permite combinar qualquer quantidade de acréscimos e bebidas dinamicamente, em tempo de execução, sem precisar de uma subclasse para cada combinação possível |
| **Observer** | `Cozinha` implementa `Subject` e notifica `Cliente` (`Observer`) assim que o pedido muda para o status `PRONTO` | Desacopla quem prepara o pedido de quem precisa ser avisado — a cozinha dispara a notificação sem conhecer os detalhes de como o cliente reage a ela |
| **Singleton** | `ListaPedidos` (fila de pedidos pendentes) e `Balanco` (relatório financeiro) expõem uma única instância via `getInstance()` | Garante que toda a aplicação compartilhe a mesma fila de produção e o mesmo balanço, evitando estados duplicados ou relatórios divergentes |

> O diagrama de classes completo está em [`ramen_shop/uml/uml-class-diagram.uxf`](ramen_shop/uml/uml-class-diagram.uxf) (formato UMLet).

---

## 📁 Estrutura de pastas

```
ramen_shop/
├── pom.xml
├── uml/
│   └── uml-class-diagram.uxf             # Diagrama de classes (UMLet)
└── src/main/java/br/lpm/
    ├── main/
    │   └── Main.java                      # Menu principal e ponto de entrada
    └── business/
        ├── balanco/
        │   └── Balanco.java               # Balanço financeiro (Singleton)
        ├── controller/
        │   └── RamenShopController.java   # Orquestra pedido → preparo → balanço
        ├── decorators/                    # Acréscimos e bebidas (Decorator)
        │   ├── AcrescimoDecorator.java
        │   ├── AcrescimoChilli.java
        │   ├── AcrescimoCremeAlho.java
        │   ├── AcrescimoCroutons.java
        │   ├── AcrescimoProteinaExtra.java
        │   ├── AcrescimoShitake.java
        │   ├── AcrescimoTofu.java
        │   ├── BebidaDecorator.java
        │   ├── BebidaKoCha.java
        │   ├── BebidaOCha.java
        │   └── BebidaRefrigerante.java
        ├── exception/
        │   └── RamenShopException.java    # Exceção de negócio do domínio
        ├── model/
        │   ├── Pedido.java                # Modelo abstrato base
        │   └── enums/                     # Tamanho, Proteina, Acrescimo, Bebida, Status
        ├── observer/                      # Notificação de pedido pronto (Observer)
        │   ├── Subject.java
        │   ├── Observer.java
        │   ├── Cozinha.java
        │   └── Cliente.java
        ├── pedidos/
        │   ├── PedidoFactory.java         # Fábrica de pedidos (Factory Method)
        │   ├── PedidoPequeno.java
        │   ├── PedidoMedio.java
        │   ├── PedidoGrande.java
        │   └── ListaPedidos.java          # Fila de pedidos pendentes (Singleton)
        └── util/
            └── GeradorIdPedido.java       # Gerador sequencial de IDs de pedido
```

---

## ✨ Funcionalidades

### 1. Fazer pedido
- Cadastro do nome do cliente, com validação de campo obrigatório
- Escolha do tamanho do ramen — **Pequeno**, **Médio** ou **Grande**
- Escolha da proteína — **Vegano**, **Boi** ou **Porco**
- Adição de acréscimos — Proteína Extra, Chilli, Creme de Alho, Croutons, Shitake, Tofu
- Seleção de bebidas — Refrigerante, Chá Oolong, Chá Preto

### 2. Processar pedido
- Retira o próximo pedido da fila e avança seu status (`PENDENTE → EM_PREPARO → PRONTO → RETIRADO`)
- Notifica o cliente automaticamente assim que o pedido fica pronto
- Registra o pedido concluído no balanço financeiro

### 3. Balanço financeiro
- Lista todos os pedidos concluídos, com cliente, tamanho, proteína, acréscimos e status
- Exibe receita total acumulada e ticket médio

---

## 🚀 Como executar

### Pré-requisitos
- Java JDK 17+
- Maven 3.9+

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/marcosffp/Ramen_Shop_Design_Patterns.git
cd Ramen_Shop_Design_Patterns/ramen_shop

# 2. Compile o projeto
mvn compile

# 3. Execute o programa
java -cp target/classes br.lpm.main.Main
```

> Alternativamente, importe a pasta `ramen_shop` em uma IDE com suporte a Maven (IntelliJ, Eclipse, VS Code com extensão Java) e execute `Main.java` diretamente.

---

## 🧭 Exemplo de uso

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

---

## ✅ Testes

A suíte cobre modelos, fábrica, decoradores, observadores, balanço e controller — **22 classes de teste** construídas com **JUnit 5** e **Mockito** (usado para isolar dependências como `Subject`/`Observer` e simular cenários de erro do domínio), espelhando a estrutura de pacotes de `src/main`.

```bash
cd ramen_shop

# Executa toda a suíte de testes
mvn test
```

---

## 📦 Tecnologias e dependências

| Categoria | Tecnologia | Versão |
|---|---|---|
| Linguagem | Java | 17 |
| Build | Maven | 3.9+ |
| Testes unitários | JUnit Jupiter (API + Engine) | 5.8.2 |
| Mocks | Mockito (`mockito-core`, `mockito-junit-jupiter`) | 5.5.0 |
| Modelagem | Diagrama de classes UML (UMLet, `.uxf`) | — |

---

## 📄 Licença

Este projeto está sob a licença [MIT](LICENSE) — veja o arquivo de licença para mais detalhes.

---

<div align="center">
  <img width="70%" alt="pucminas" src="images/banner-institucional.svg"/>
</div>
<p align="center">Fonte do banner: <a href="https://github.com/joaopauloaramuni">João Paulo Carneiro Aramuni</a></p>

---
