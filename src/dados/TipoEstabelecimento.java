package dados;

public enum TipoEstabelecimento {

    //Quando o enum for chamado na class, e for adicionado um dos estabelicimento abaixo será apresentado
    //a descrição com informação para o usuário.
    POSTO_COMBUSTIVEL("Compra negada para cartão alumentção ou refeição, só aceita vale combustível"),

    RESTAURANTE(" Não possivel realizar compra vale combustível"),

    MERCADO("Não possivel realizar compra vale combustível"),

    FARMACIA("Não possivel realizar compra vale combustível");

    private final String descricao;

    TipoEstabelecimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
