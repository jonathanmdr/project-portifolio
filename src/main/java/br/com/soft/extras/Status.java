package br.com.soft.extras;

public enum Status {

    EM_ANALISE("Em Análise"),
    ANALISE_REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String status;

    Status (String status) {
        this.status = status;
    }

    public String getStatus() {
        return  this.status;
    }
}
