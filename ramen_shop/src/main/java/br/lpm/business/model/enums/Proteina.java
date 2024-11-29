package br.lpm.business.model.enums;

public enum Proteina {
    VEGANO(3.90),
    BOI(7.90),
    PORCO(5.90);

    private final double preco;

    Proteina(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }
}

