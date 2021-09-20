package dados;

import interface_com_usuario.ModoBeneficiario;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.time.LocalDateTime;

public class Transacao {

    /** ------------------------------------------------------------- */
    /** ATRIBUTOS */

    private double valor;
    private char[] codigoEstabelecimento;
    private LocalDateTime dataHoraTransacao;


    /** ------------------------------------------------------------- */
    /** CONSTRUTORES */

    public Transacao(double _valor, char[] _codigoEstabelecimento) {
        this.valor = _valor;
        this.codigoEstabelecimento = _codigoEstabelecimento;
        this.dataHoraTransacao = LocalDateTime.now();
    }
    public Transacao(double _valor, Estabelecimento _estabelecimento) {
        this(_valor, _estabelecimento.getCodigo());
    }

    /** ------------------------------------------------------------- */
    /** MÉTODOS */

    public double getValor() {
        return this.valor;
    }

    public boolean isValor(double valorChecar){
        return (valorChecar == this.valor);
    }

    public boolean isEstabelecimento(Estabelecimento estabelecimentoChecar){
        return (Arrays.equals(estabelecimentoChecar.getCodigo(), this.codigoEstabelecimento));
    }

    public char[] getEstabelecimento() {
        return this.codigoEstabelecimento;
    }

    public LocalDateTime getDataHoraTransacao() {
        return this.dataHoraTransacao;
    }

    @Override
    public String toString() {
        String retorno = "";
        retorno += this.dataHoraTransacao + ": ";
        retorno += CartaoBeneficio.formataReais(this.valor) + " - ";
        retorno += ModoBeneficiario.buscaEstabelecimento(this.codigoEstabelecimento).getNome();
        return retorno;
    }

    public int segundosDesdeTransacao(){
        var horaAgora = LocalDateTime.now();
        return (int) ChronoUnit.SECONDS.between(this.dataHoraTransacao, horaAgora);
    }

}