package interface_com_usuario;

import java.util.Locale;
import java.util.Scanner;

public class Leitor {


    /** ------------------------------------------------------------- */
    /** CONSTRUTOR DE CLASSE ESTÁTICA */

    private Leitor(){}


    /** ------------------------------------------------------------- */
    /** MÉTODOS PRIVADOS */

    /** Gera o scanner */
    private static Scanner scanner(){
        return new Scanner(System.in);
    }

    /** Tenta ler uma string, retorna null caso falhe */
    private static String tryLerString(){
        Impressora.inputFlag();
        try{
            return scanner().nextLine();
        }catch(Exception e){
            return null;
        }
    }

    /** Tenta ler um inteiro, retorna null caso falhe */
    private static Integer tryLerInteiro(){
        Impressora.inputFlag();
        try{
            return scanner().nextInt();
        }catch(Exception e){
            return null;
        }
    }

    /** Tenta ler um double, retorna null caso falhe */
    private static Double tryLerDouble(){
        Impressora.inputFlag();
        try{
            return scanner().nextDouble();
        }catch(Exception e){
            return null;
        }
    }

    /** Verifica se um dado int está presente num dado array */
    private static boolean checarOpcao(char opcao, char[] array){
        for(int i=0; i<array.length; i++){
            if(array[i] == opcao){
                return true;
            }
        }
        return false;
    }

    /** Verifica se uma dada string é composta apenas de dígitos numéricos */
    private static boolean checarDigitos(String texto){
        for(char l : texto.toCharArray()){
            try{
                Integer.parseInt(l + "");
            }catch(Exception e){
                return false;
            }
        }
        return true;
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS PÚBLICOS */

    /** Lê uma string */
    public static String lerString(){
        String leitura = tryLerString();
        while(leitura == null || leitura.isBlank() || leitura.isEmpty()){
            Impressora.msgAtencao("Digite algo");
            leitura = tryLerString();
        }
        return leitura;
    }

    /** Lê um valor inteiro */
    public static int lerInteiro(){
        Integer leitura = tryLerInteiro();
        while(leitura == null){
            Impressora.msgAtencao("Digite um número inteiro");
            leitura = tryLerInteiro();
        }
        return leitura;
    }

    /** Lê um caractere */
    public static char lerCaractere(){
        return lerCaractere(false);
    }
    public static char lerCaractere(boolean seConverterParaMinusculo){
        String leituraString = lerString().toLowerCase(Locale.ROOT);
        while(leituraString.length() != 1){
            Impressora.msgAtencao("Digite exatamente um caractere");
            leituraString = lerString().toLowerCase(Locale.ROOT);
        }
        return leituraString.charAt(0);
    }

    /** Lê um valor numérico */
    public static double lerDouble(){
        Double leitura = tryLerDouble();
        while(leitura == null){
            Impressora.msgAtencao("Digite um número");
            leitura = tryLerDouble();
        }
        return leitura;
    }

    /** Lê um valor numérico positivo, incluindo zero ou não */
    public static double lerDoublePositivo(boolean seZeroIncluso){
        double leitura = lerDouble();
        if(seZeroIncluso){
            while(leitura < 0){
                Impressora.msgAtencao("Digite um número positivo, incluindo zero");
                leitura = lerDouble();
            }
        }else{
            while(leitura <= 0){
                Impressora.msgAtencao("Digite um número positivo, excluindo zero");
                leitura = lerDouble();
            }
        }
        return leitura;
    }

    /** Lê um valor em reais */
    public static double lerValorEmReais(){
        double valor = lerDoublePositivo(false);
        return Math.round(valor * 100.0) / 100.0;
    }

    /** Lê uma opção do usuário do tipo char, entre as disponíveis */
    public static char lerOpcao(char[] opcoes){
        char leitura = lerCaractere(true);
        while(!checarOpcao(leitura, opcoes)){
            Impressora.msgAtencao("Digite uma das opções dadas");
            leitura = lerCaractere(true);
        }
        return leitura;
    }
    public static int lerOpcao(int primeiro, int ultimo){
        int length = (ultimo - primeiro + 1);
        char[] opcoes = new char[length];
        for(int i=primeiro; i<ultimo; i++){
            opcoes[i] = Integer.toString(i).charAt(0);
        }
        return Integer.parseInt(lerOpcao(opcoes) + "");
    }

    /** Lê um array de inteiros de um tamanho específico */
    public static char[] lerArrayDeDigitos(int tamanhoArray){
        String leitura = lerString();
        while(!checarDigitos(leitura) || leitura.length() != tamanhoArray){
            Impressora.msgAtencao("Digite apenas a quantidade pedida de dígitos numéricos; sem espaços, traços ou letras");
            leitura = lerString();
        }
        return leitura.toCharArray();
    }

}
