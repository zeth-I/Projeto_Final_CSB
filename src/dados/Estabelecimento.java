package dados;

import java.util.ArrayList;
import java.util.Locale;

public class Estabelecimento {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */
    private TipoEstabelecimento tipo;
    private char[] codigo;
    private  String  nome;
    private  String endereco;


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR */

    public Estabelecimento(char[] codigo, String nome, TipoEstabelecimento tipo, String endereco){
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.endereco = endereco;
    }
    public Estabelecimento(String codigo, String nome, TipoEstabelecimento tipo, String endereco){
        this(codigo.substring(0,3).toCharArray(), nome, tipo, endereco);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    public static ArrayList<Estabelecimento> geraLista(){
        var lista = new ArrayList<Estabelecimento>();
        lista.add(new Estabelecimento("MDJ", "Mercadinho do João", TipoEstabelecimento.MERCADO, "Goiânia"));
        lista.add(new Estabelecimento("RDM", "Restaurante da Maria", TipoEstabelecimento.RESTAURANTE, "Salvador"));
        lista.add(new Estabelecimento("POP", "Posto Petrobrás", TipoEstabelecimento.POSTO_COMBUSTIVEL, "São Paulo"));
        lista.add(new Estabelecimento("FDP", "Farmacia da Praia", TipoEstabelecimento.FARMACIA, "Pernambuco"));

        return lista;
    }

    public char[] getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public TipoEstabelecimento getTipo(){ return this.tipo; }

}