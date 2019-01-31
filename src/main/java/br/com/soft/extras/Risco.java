package br.com.soft.extras;

public enum Risco {

    ALTO("Alto"),
    MEDIO("Médio"),
    BAIXO("Baixo");

    private final String risco;

    Risco(String risco) {
        this.risco = risco;
    }

    public String getRisco() {
        return this.risco;
    }
}
