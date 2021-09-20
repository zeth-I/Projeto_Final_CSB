package interface_com_usuario;

import java.util.Locale;

public class Impressora {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private static int indentacao = 0;
    private static int tamanhoLinhas = 100;


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR DE CLASSE ESTÁTICA */

    private Impressora(){}

    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS */

    /** Método que gera uma indentação padronizada para o programa */
    private static String espacoIndentacao(){
        String retorno = " ";
        for(int i=0; i<indentacao; i++){
            retorno += "---||";
        }
        if(indentacao > 0){
            retorno += " ";
        }
        return retorno;
    }

    /** Método que gera uma linha do tamanho correto */
    private static String linha(char traco){
        String retorno = "";
        for(int i=0; i<tamanhoLinhas; i++){
            retorno += traco;
        }
        int posicaoFinal = retorno.length();
        for(int i=0; i<indentacao; i++){
            posicaoFinal -= 5;
        }
        return retorno.substring(0, posicaoFinal);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PÚBLICOS */

    /** Métodos para manipulação da indentação */
    public static void aumentarIndentacao(){
        indentacao++;
    }
    public static void diminuirIndentacao(){
        indentacao--;
    }

    /** O que aparece para que o usuário entenda que deve digitar algo */
    public static void inputFlag(){
        System.out.print(espacoIndentacao() + "> ");
    }

    /** Imprime uma longa linha na tela */
    public static void linhaSeparadora(){
        System.out.println(espacoIndentacao() + linha('-'));
    }

    /** Imprime uma longa linha na tela de iguais */
    public static void linhaSeparadoraDupla(){
        System.out.println(espacoIndentacao() + linha('='));
    }

    /** Pula uma linha */
    public static void linhaVazia(){
        System.out.println(espacoIndentacao() + "");
    }

    /** Imprime um título */
    public static void titulo(String texto){
        linhaSeparadoraDupla();
        System.out.println(espacoIndentacao() + "  >>>  " + texto.toUpperCase(Locale.ROOT) + "  <<<  ");
        linhaSeparadoraDupla();
    }

    /** Imprime um subtítulo */
    public static void subtitulo(String texto){
        linhaSeparadoraDupla();
        msgBasica(">>> " + texto.toUpperCase(Locale.ROOT) + " <<<");
        linhaVazia();
    }

    /** Imprime uma mensagem qualquer */
    public static void msgBasica(String texto){
        System.out.println(espacoIndentacao()+ texto);
    }

    /** Imprime uma mensagem de aviso ou atenção */
    public static void msgAtencao(String texto){
        msgBasica("* " + texto + "!");
    }

    /** Imprime uma opção que o usuário pode escolher */
    public static void msgOpcao(char flag, String label){
        msgBasica("'" + flag + "' - " + label);
    }
    public static void msgOpcao(int flag, String label){
        msgOpcao(Integer.toString(flag).charAt(0), label);
    }

    /** Imprime uma mensagem avisando ao usuário para onde ele vai agora */
    public static void msgRedirecionamento(String texto){
        msgBasica(texto + "...");
    }

    /** Imprime uma senha na tela */
    public static void msgSenha(String label, char[] senha){
        String senhaString = "";
        for(int i=0; i<senha.length; i++){
            senhaString += senha[i];
        }
        msgBasica(label + ": " + senhaString);
    }
}
