package PacoteItens;

import java.util.Calendar;
import java.util.GregorianCalendar;

import PacoteUsers.Usuario;

public class MapaTematico extends Item {
    private int nivPriv;

    public MapaTematico(String nome, int nivelPriv) {
        super(nome);
        this.nivPriv = nivelPriv;
    }

    @Override
    public boolean empresta(Usuario u, int prazo) {
        GregorianCalendar cal = new GregorianCalendar();
        if (this.isDisponivel() && u.getNivelPriv() >= this.nivPriv) {
            this.retiradoPor = u;
            this.dtEmprestimo = cal.getTime();
            cal.add(Calendar.DATE, 2);  // Define um prazo fixo de 2 dias para devolução
            this.dtDevolucao = cal.getTime();
            return true;
        }
        return false;
    }
}
