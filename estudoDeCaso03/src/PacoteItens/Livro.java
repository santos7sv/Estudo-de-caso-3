package PacoteItens;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import PacoteUsers.UsuarioProfessor;

public class Livro extends Item {
    private UsuarioProfessor bloqueadoPor;
    private Date dtBloqueio;
    private Date dtDesbloqueio;
    private boolean isPeriodico;

    public Livro(String tit) {
        super(tit);
        this.titulo = tit;
    }
    
    public void setPeriodico() {
        this.isPeriodico = true;
    }

    public boolean isPeriodico() {
        return isPeriodico;
    }
    
    public UsuarioProfessor getBloqueador() {
        return this.bloqueadoPor;
    }

    @Override
    public boolean isDisponivel() {
        Date hoje = new Date();
        return this.retiradoPor == null && 
               (this.bloqueadoPor == null || this.dtDesbloqueio.before(hoje));
    }

    public boolean isBloqueado() {
        Date hoje = new Date();
        return this.retiradoPor == null &&
               this.bloqueadoPor != null && 
               !this.dtDesbloqueio.before(hoje);
    }

    public boolean bloqueia(UsuarioProfessor u, int prazo) {
        GregorianCalendar cal = new GregorianCalendar();
        if (this.isDisponivel() && u.isProfessor()) {
            this.bloqueadoPor = u;
            this.dtBloqueio = cal.getTime();
            cal.add(Calendar.DATE, Math.min(prazo, 20));
            this.dtDesbloqueio = cal.getTime();
            return true;
        }
        return false;
    }

    public boolean desbloqueia(UsuarioProfessor u) {
        if (u.equals(this.bloqueadoPor)) {
            this.bloqueadoPor = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.isDisponivel()) {
            return this.isPeriodico ? 
                   this.titulo + " (disponível APENAS PARA PROFESSORES)" :
                   this.titulo + " (disponível)";
        }
        
        if (this.isEmprestado()) {
            return this.titulo + " retirado por " + this.retiradoPor + 
                   " em " + formatarData(this.dtEmprestimo) + 
                   " até " + formatarData(this.dtDevolucao);
        } else {
            return this.titulo + " bloqueado por " + this.bloqueadoPor + 
                   " em " + formatarData(this.dtBloqueio) + 
                   " até " + formatarData(this.dtDesbloqueio);
        }
    }

    protected String formatarData(Date dt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);
        return cal.get(Calendar.DATE) + "/" + 
               (cal.get(Calendar.MONTH) + 1) + "/" + 
               cal.get(Calendar.YEAR);
    }
}
