package interface_com_usuario;


public class Main {

    public static void main(String[] args) {


        /* Variáveis locais */
        var rodarPrograma = true;

        /* Início do Programa */
        Impressora.titulo("Bem-vindo ao programa de gerenciamento de cartões | Projeto Final Alelo CSB");
        Impressora.msgBasica("Base: Projeto All Beneficios");

        /* Configurações iniciais */
        Impressora.msgBasica("Antes de iniciar, é necessário que sejam feitas as configurações do programa.");
        if(ModoAdministrador.tentarEntrar()){

            /* Rodar configurações iniciais */
            Impressora.linhaVazia();
            Impressora.aumentarIndentacao();
            ModoAdministrador.rodar();

            /* Sair das configurações iniciais */
            Impressora.diminuirIndentacao();
            Impressora.linhaVazia();
            Impressora.msgRedirecionamento("Saindo das configurações iniciais e abrindo o programa");
            Impressora.linhaVazia();
            Impressora.linhaSeparadoraDupla();

        }else{
            Impressora.msgAtencao("Como foi excedido o limite de tentativas, o programa será abortado");
            rodarPrograma = false;
        }

        /* Programa em si (pós configurações iniciais) */
        if(rodarPrograma){

            char opcaoModo;
            do{

                /* Menu para escolher qual modo do programa deseja acessar */
                Impressora.linhaVazia();
                Impressora.titulo("Escolha do Modo (Administrador | Beneficiário)");
                Impressora.linhaVazia();
                Impressora.msgBasica("Deseja voltar ao Modo Administrador para alterar configurações do programa,");
                Impressora.msgBasica("ou entrar no Modo Beneficiário para acessar o uso dos cartões?");
                Impressora.linhaVazia();
                Impressora.subtitulo("Menu de Escolha de Modo");
                Impressora.msgOpcao('1', "Beneficiário");
                Impressora.msgOpcao('2', "Administrador");
                Impressora.msgOpcao('s', "Sair");
                Impressora.linhaSeparadora();
                Impressora.linhaVazia();
                Impressora.msgBasica("Digite a opção desejada:");
                opcaoModo = Leitor.lerOpcao(new char[]{'1', '2', 's'});

                /* Organização e indentação */
                if(opcaoModo == 's'){
                    continue;
                }else{
                    Impressora.linhaVazia();
                    Impressora.aumentarIndentacao();
                }

                /* Executar opção escolhida */
                switch (opcaoModo) {

                    /* MODO BENEFICIÁRIO */
                    case '1' -> {

                        /* Título */
                        Impressora.titulo("Modo Beneficiário");

                        /* Tentar login como beneficiário */
                        if (ModoBeneficiario.tentarLogin()) {
                            Impressora.msgRedirecionamento("Fazendo login");
                            Impressora.linhaVazia();
                            Impressora.linhaSeparadoraDupla();
                            Impressora.linhaVazia();
                            ModoBeneficiario.rodar();
                        }

                        /* Fim do Modo Beneficiário */
                        Impressora.msgRedirecionamento("Saindo do Modo Beneficiário");
                    }

                    /* MODO ADMINISTRADOR */
                    case '2' -> {

                        /* Título */
                        Impressora.titulo("Modo Administrador");

                        /* Tentar entrar no Modo Administrador novamente */
                        if (ModoAdministrador.tentarEntrar()) {
                            ModoAdministrador.rodar();
                        } else {
                            Impressora.msgAtencao("Como foi excedido o limite de tentativas, o programa sera abortado");
                            opcaoModo = 's';
                        }

                        /* Fim do Modo Administrador */
                        Impressora.msgRedirecionamento("Saindo do Modo Administrador");
                    }
                }

                Impressora.diminuirIndentacao();

            }while(opcaoModo != 's');

            /* Final do programa */
            Impressora.linhaVazia();
            Impressora.msgRedirecionamento("Fechando o programa");
            Impressora.linhaSeparadoraDupla();
            Impressora.diminuirIndentacao();
            Impressora.linhaVazia();
            Impressora.titulo("Até mais!");

        }

    }

}
