package dados;

import java.time.LocalDate;
import java.util.Date;

public enum TipoCartaoBeneficio {

    VALE_ALIMENTACAO {
        @Override
        public CartaoBeneficio fabricar(char[] senha) {
            return new ValeAlimentacao(senha);
        }

        @Override
        public CartaoBeneficio fabricar(char[] senha, LocalDate validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Alelo Alimentação";
        }
    },
    VALE_REFEICAO {
        @Override
        public CartaoBeneficio fabricar(char[] senha) {
            return new ValeRefeicao(senha);
        }

        @Override
        public CartaoBeneficio fabricar(char[] senha, LocalDate validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Alelo Refeição";
        }
    },
    VALE_MULTI {
        @Override
        public CartaoBeneficio fabricar(char[] senha) {
            return new ValeMulti(senha);
        }

        @Override
        public CartaoBeneficio fabricar(char[] senha, LocalDate validade) {
            return new ValeMulti(senha);
        }

        @Override
        public String label() {
            return "Alelo Multibenefícios";
        }
    },
    VALE_COMBUSTIVEL {
        @Override
        public CartaoBeneficio fabricar(char[] senha) {
            return new ValeCombustivel(senha);
        }

        @Override
        public CartaoBeneficio fabricar(char[] senha, LocalDate validade) {
            return new ValeCombustivel(senha);
        }

        @Override
        public String label() {
            return "Alelo Mobilidade";
        }
    };

    public abstract CartaoBeneficio fabricar(char[] senha);
    public abstract CartaoBeneficio fabricar(char[] senha, LocalDate validade);

    public abstract String label();
}