package PacoteUsers;

import java.util.Date;
import PacoteItens.Item;

public class UsuarioAluno extends Usuario {
    private Date dataLimite;

    public UsuarioAluno(String nome, Date dataLimite) {
        super(nome);
        this.dataLimite = dataLimite;
    }

    public void renovaCartao(Date novaDataLimite) {
        this.dataLimite = novaDataLimite;
    }

    public boolean isRegular() {
        Date hoje = new Date();
        return dataLimite.after(hoje);
    }

    public boolean isARenovar() {
        return !isRegular();
    }
    
    @Override
    public String toString() {
        // Retorna uma representação do usuário aluno
        return "Aluno: " + this.getNome();
    }

    @Override
    public int getCotaMaxima() {
        // Define a cota máxima com base na regularidade do aluno
        if (isRegular()) {
            return 3;  // Maior cota para alunos regulares
        }
        return super.getCotaMaxima();  // Cota padrão para outros casos
    }

    @Override
    public int getPrazoMaximo() {
        // Define o prazo máximo com base na regularidade do aluno
        if (isRegular()) {
            return 7;  // Prazo maior para alunos regulares
        }
        return super.getPrazoMaximo();  // Prazo padrão para outros casos
    }
}
