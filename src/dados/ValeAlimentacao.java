package dados;

import interface_com_usuario.Impressora;

/** CLASSE VALE_ALIMENTAÇÃO (do tipo CARTÃO_BENEFÍCIO) */

public class ValeAlimentacao extends CartaoBeneficio {


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public ValeAlimentacao(char[] _senha) {
        super(_senha);
    }


    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    //todo
    /** Sobrescrita do método tentarPagamento da classe-pai */
    @Override
    public boolean tentarPagamento(Estabelecimento estabelecimento, Double valorCompra) {

        // não deve realizar a compra se:
        //   > o estabelecimento é do tipo POSTO_COMBUSTIVEL
        //   > o cartão está vencido
        //   > o teste anti-fraude falhar
        //   > não houver saldo suficiente

        // caso passe todos os testes, fazer:
        //   > adicionar a compra às transações do cartão
        //   > mudar o saldo (levando em conta o cashback de 1.5%)

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
        if (estabelecimento.getTipo() == TipoEstabelecimento.POSTO_COMBUSTIVEL) {
            Impressora.msgBasica(" Cartão inválido para este estabelecimento");
            return false;
        }

        // Testar se o saldo é suficiente
        if (this.saldo < valorCompra) {
            Impressora.msgBasica("Seu saldo é insuficiente para esta compra");
            return false;
        }

        // Cashback
        valorCompra -= valorCompra * 0.03;

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
        Impressora.msgBasica(TipoCartaoBeneficio.VALE_ALIMENTACAO.label()+ ":");
        Impressora.aumentarIndentacao();
        Impressora.msgBasica("Saldo: " + getSaldo());
        Impressora.msgBasica("Data de validade: " + dataValidade);
        Impressora.diminuirIndentacao();
    }

    @Override
    public TipoCartaoBeneficio getTipo(){
        return TipoCartaoBeneficio.VALE_ALIMENTACAO;
    }
}
