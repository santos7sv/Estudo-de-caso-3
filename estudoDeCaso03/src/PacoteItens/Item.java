package PacoteItens;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import PacoteUsers.Usuario;

public abstract class Item {
    protected Usuario retiradoPor;
    protected Date dtEmprestimo;
    protected Date dtDevolucao;
    protected String titulo;
    protected String state;
    
    public Item(String nome) {
        this.titulo = nome;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Usuario getDono() {
        return this.retiradoPor;
    }

    public boolean empresta(Usuario u, int prazo) {
        GregorianCalendar cal = new GregorianCalendar();
        if (this.isDisponivel()) {
            this.retiradoPor = u;
            this.dtEmprestimo = cal.getTime();
            cal.add(Calendar.DATE, prazo);
            this.dtDevolucao = cal.getTime();
            return true;
        }
        return false;
    }

    public boolean retorna(Usuario u) {
        if (u.equals(this.retiradoPor)) {
            this.retiradoPor = null;
            return true;
        }
        return false;
    }

    public boolean isDisponivel() {
        return this.retiradoPor == null;
    }

    public boolean isEmprestado() {
        return this.retiradoPor != null;
    }

    public boolean isEmAtraso() {
        Date hoje = new Date();
        return (this.dtDevolucao.before(hoje) && this.retiradoPor != null);
    }

    @Override
    public String toString() {
        if (this.isDisponivel()) {
            return this.titulo + " (disponível)";
        }
        state = this.titulo + " (retirado por " + this.retiradoPor + " em " + formatarData(this.dtEmprestimo) + " até "
                + formatarData(this.dtDevolucao) + ")";
        return state;
    }

    protected String formatarData(Date dt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);
        return cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
    }
}
