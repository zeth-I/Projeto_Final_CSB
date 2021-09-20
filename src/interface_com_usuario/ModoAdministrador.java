package interface_com_usuario;

import dados.*;

import java.util.*;

public class ModoAdministrador {

    /** -------------------------------------------------------------
    /** ATRIBUTOS */

    private final static int senhaAdm = 1234;
    private static ArrayList<Beneficiario> listaBeneficiariosCadastrados = new ArrayList<>();
    private static TipoCartaoBeneficio[] tiposDeCartao = TipoCartaoBeneficio.values();


    /** -------------------------------------------------------------
    /** CONSTRUTOR DE CLASSE ESTÁTICA */

    private ModoAdministrador(){}


    /** -------------------------------------------------------------
    /** MÉTODOS DE APOIO */

    /** Método que lê dados dos cartões de um novo beneficiário */
    private static ArrayList<CartaoBeneficio> lerDadosCartoes(){

        /* Variáveis locais */
        var cartoesBeneficiario = new ArrayList<CartaoBeneficio>();
        Map<TipoCartaoBeneficio, char[]> senhas = new HashMap<>();

        /* Senhas dos Cartões */
        Impressora.msgBasica("Deseja definir senhas aleatórias para os cartões, ou definir manualmente?:");
        Impressora.msgBasica("('a' - aleatório  |  'm' - manual)");
        char opcao = Leitor.lerOpcao(new char[]{'a', 'm'});
        Impressora.aumentarIndentacao();

        if(opcao == 'a'){
            for (var tipo : tiposDeCartao) {
                var senhaGerada = CartaoBeneficio.gerarSenhaAleatoria();
                senhas.put(tipo, senhaGerada);
                Impressora.msgSenha("Senha do " + tipo.label(), senhaGerada);
            }
        }
        else{
            for (var tipo : tiposDeCartao) {
                Impressora.msgBasica("Senha do " + tipo.label() + " (4 dígitos):");
                var senhaDada = Leitor.lerArrayDeDigitos(4);
                senhas.put(tipo, senhaDada);
            }
        }
        Impressora.diminuirIndentacao();

        /* Validade dos Cartões */
        Impressora.msgBasica("Deseja usar a validade padrão para os cartões (12 meses), ou defini-las manualmente?");
        Impressora.msgBasica("('p' - padrão  |  'm' - manual)");
        opcao = Leitor.lerOpcao(new char[]{'p', 'm'});
        Impressora.aumentarIndentacao();
        if(opcao == 'p'){
            Impressora.msgAtencao("Validade dos 3 cartões definida para daqui 12 meses");
            for (var tipo : tiposDeCartao) {
                var senhaNova = senhas.get(tipo);
                var cartaoNovo = tipo.fabricar(senhaNova);
                cartoesBeneficiario.add(cartaoNovo);
            }
        }
        else{
            Impressora.msgBasica("Favor informar a validade, em meses, de cada cartão:");
            Impressora.msgBasica("   (Obs.: para cartões vencidos, digite uma quatidade negativa de meses)");
            for (var tipo : tiposDeCartao) {
                Impressora.msgBasica(tipo.label());
                var dateValidade = CartaoBeneficio.calcularDataValidade(Leitor.lerInteiro());
                var senhaNova = senhas.get(tipo);
                var cartaoNovo = tipo.fabricar(senhaNova, dateValidade);
                cartoesBeneficiario.add(cartaoNovo);
            }
        }

        /* Retorno */
        Impressora.diminuirIndentacao();
        return cartoesBeneficiario;
    }

    /** Método que recebe um nome e retorna o beneficiário correspondente */
    public static Beneficiario buscarBeneficiario(String nome){
        for(var beneficiario : listaBeneficiariosCadastrados){
            if(beneficiario.getNome().equals(nome)){
                return beneficiario;
            }
        }
        return null;
    }

    /** Método que checa se há algum beneficiário cadastrado com um certo nome e uma certa senha */
    public static Beneficiario checarDadosLoginBeneficiario(String nome, char[] senha){
        for(var beneficiario : listaBeneficiariosCadastrados){
            if(beneficiario.checarDadosLogin(nome, senha)){
                return beneficiario;
            }
        }
        return null;
    }


    /** -------------------------------------------------------------
    /** MÉTODOS PRIVADOS QUE EXECUTAM AS OPÇÕES DO USUÁRIO

    /** Método que lê dados no novo beneficiário, e o adiciona à listaUsuariosCadastrados */
    private static void cadastrarNovoBeneficiario(){

        Impressora.msgBasica("Nome do Beneficiário:");
        var nome = Leitor.lerString();

        Impressora.msgBasica("Data de nascimento do Beneficiário:");
        var BirthDate = Leitor.lerString();

        Impressora.msgBasica("CPF do Beneficiário:");
        var CPF = Leitor.lerString();

        Impressora.msgBasica("Senha do Beneficiário (6 dígitos):");
        var senha = Leitor.lerArrayDeDigitos(6);

        var cartoes = lerDadosCartoes();

        var novoBeneficiario = new Beneficiario(nome, senha, cartoes);
        listaBeneficiariosCadastrados.add(novoBeneficiario);
        Impressora.msgAtencao("Cadastro realizado com sucesso");

    }

    /** Método que imprime a lista de beneficiários cadastrado em forma de texto */
    private static void visualizarBeneficiarios(){
        if(listaBeneficiariosCadastrados.size() == 0){
            Impressora.msgAtencao("Não há nenhum beneficiário cadastrado ainda");
        }
        int posicao = 1;
        Impressora.linhaSeparadora();
        for(var beneficiario : listaBeneficiariosCadastrados){
            beneficiario.imprimeDados();
            Impressora.linhaSeparadora();
        }
    }


    /** -------------------------------------------------------------
    /** MÉTODOS PÚBLICOS */

    public static boolean tentarEntrar(){

        /* Variáveis locais */
        boolean acertou = false;
        int tentativas = 3;
        int chute = 0;

        /* Interação com o usuário */
        while (tentativas > 0 && acertou == false) {
            Impressora.msgBasica("Favor informar a senha do administrador:");
            chute = Leitor.lerInteiro();

            if (chute == senhaAdm) {
                Impressora.msgAtencao("Senha correta");
                acertou = true;
            }
            else {
                --tentativas;
                Impressora.msgAtencao("Senha incorreta");
                Impressora.msgBasica(tentativas + " tentativas restantes.");
            }
            if (tentativas == 0) {
                Impressora.msgAtencao("Tentativas esgotadas");
                acertou = false;
            }
        }

        /* Retorno */
        return acertou;

    }

    public static void rodar(){

        /* Título */
        Impressora.titulo("Modo Administrador");

        /* Escolher e executar as opções */
        char opcao;
        do{

            /* Menu */
            Impressora.linhaVazia();
            Impressora.subtitulo("Menu do Administrador");
            Impressora.msgOpcao('1', "Cadastrar novo beneficiário");
            Impressora.msgOpcao('2', "Ver lista de beneficiários cadastrados");
            Impressora.msgOpcao('s', "Sair do Modo Administrador");
            Impressora.linhaSeparadora();

            /* Escolher a opção */
            Impressora.linhaVazia();
            Impressora.msgBasica("Digite a opção desejada:");
            opcao = Leitor.lerOpcao(new char[]{'1', '2','s'});

            /* Organização e indentação */
            if(opcao == 's'){
                continue;
            }else{
                Impressora.linhaVazia();
                Impressora.aumentarIndentacao();
            }

            /* Executar opção escolhida */
            switch (opcao) {

                /* OPÇÃO 1: Cadastrar novo beneficiário */
                case '1' -> {
                    Impressora.subtitulo("Cadastrar novo Beneficiário:");
                    cadastrarNovoBeneficiario();
                }

                /* OPÇÃO 2: Mostrar lista beneficiários cadastrados */
                case '2' -> {
                    Impressora.subtitulo("Beneficiários Cadastrados:");
                    visualizarBeneficiarios();
                }

            }

            Impressora.msgRedirecionamento("Voltando ao menu do Modo Administrador");
            Impressora.linhaVazia();
            Impressora.linhaSeparadoraDupla();
            Impressora.diminuirIndentacao();

        }while(opcao != 's');

        /* Saindo do Modo Administrador */
        Impressora.linhaVazia();
        Impressora.msgRedirecionamento("Fechando o Modo Administrador");
        Impressora.linhaVazia();
        Impressora.linhaSeparadoraDupla();

    }

}
