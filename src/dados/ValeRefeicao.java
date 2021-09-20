package dados;

import interface_com_usuario.Impressora;

public class ValeRefeicao extends CartaoBeneficio{

    public ValeRefeicao(char[] _senha) {
        super(_senha);
    }

    //todo
    /** Sobrescrita do método tentarPagamento da classe-pai */
    @Override
    public boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra) {

        // caso passe todos os testes, fazer:
        //   > adicionar a compra às transações do cartão


        // Testar se está vencido
        if(this.seVencido()){
            Impressora.msgAtencao("Cartão vencido!");
            return false;
        }

        // Testar o sistema anti-fraude
        if(this.tentarPassarNoAntiFraude(valorCompra, estabelecimento) == false){
            return false;
        }

        // Testar se o saldo é suficiente
        if (this.saldo < valorCompra) {
            Impressora.msgBasica("Seu saldo é insuficiente para esta compra");
            return false;
        }

        // Cashback
        valorCompra -= valorCompra * 0.02;

        // Nova Transação
        var novaTransacao = new Transacao(valorCompra, estabelecimento);
        listaTransacoes.add(novaTransacao);

        // Mudar saldo
        this.saldo -= valorCompra;

        // Retornar verdadeiro pois passou por todos os testes
        return true;

    }

    @Override
    public void imprimeDados(){
        Impressora.msgBasica(TipoCartaoBeneficio.VALE_REFEICAO.label()+ ":");
        Impressora.aumentarIndentacao();
        Impressora.msgBasica("Saldo: " + getSaldo());
        Impressora.msgBasica("Data de validade: " + dataValidade);
        Impressora.diminuirIndentacao();
    }

    @Override
    public TipoCartaoBeneficio getTipo(){
        return TipoCartaoBeneficio.VALE_REFEICAO;
    }

}
