package dados;

import interface_com_usuario.Impressora;

public class ValeMulti extends CartaoBeneficio {


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public ValeMulti(char[] _senha) {
        super(_senha);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    //todo

    @Override
    public boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra) {


        // Testar se está vencido
        if(this.seVencido()){
            Impressora.msgAtencao("Cartão vencido!");
            return false;
        }

        // Testar o sistema anti-fraude
        if(this.tentarPassarNoAntiFraude(valorCompra, estabelecimento) == false){
            return false;
        }

        // Testar se é do tipo combustível
        if (estabelecimento.getTipo() != TipoEstabelecimento.FARMACIA) {
            Impressora.msgBasica(" Cartão inválido para este estabelecimento");
            return false;
        }

        // Testar se o saldo é suficiente
        if (this.saldo < valorCompra) {
            Impressora.msgBasica("Seu saldo é insuficiente para esta compra");
            return false;
        }

        // Taxa
        valorCompra -= valorCompra * 0.04;

        // Nova Transação
        var novaTransacao = new Transacao(valorCompra, estabelecimento);
        listaTransacoes.add(novaTransacao);

        // Mudar saldo
        this.saldo -= valorCompra;

        return true;
    }

    @Override
    public void imprimeDados(){
        Impressora.msgBasica(TipoCartaoBeneficio.VALE_MULTI.label()+ ":");
        Impressora.aumentarIndentacao();
        Impressora.msgBasica("Saldo: " + getSaldo());
        Impressora.msgBasica("Data de validade: " + dataValidade);
        Impressora.diminuirIndentacao();
    }

    @Override
    public TipoCartaoBeneficio getTipo(){
        return TipoCartaoBeneficio.VALE_MULTI;
    }

}
