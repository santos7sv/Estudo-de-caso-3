package PacoteUsers;

import java.util.ArrayList;
import PacoteItens.Item;

public class Usuario {
    private String nome;
    protected int nivelDePrivilegio;
    protected ArrayList<Item> itensRetirados;

    public Usuario(String nome) {
        this.nome = nome;
        this.nivelDePrivilegio = 1;  // Nível de privilégio padrão para usuários
        this.itensRetirados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean retiraItem(Item item) {
        if (item.isDisponivel()) {
            // Tenta emprestar o item e adiciona à lista de itens retirados se o empréstimo for bem-sucedido
            if (item.empresta(this, getPrazoMaximo())) {
                this.itensRetirados.add(item);
                return true;
            }
        }
        return false;
    }

    public boolean devolveItem(Item item) {
        // Remove o item da lista de itens retirados e o devolve ao estoque
        if (this.itensRetirados.remove(item)) {
            item.retorna(this);
            return true;
        }
        return false;
    }

    public int getCotaMaxima() {
        return 2;  // Limite padrão de itens que o usuário pode retirar
    }

    public int getPrazoMaximo() {
        return 4;  // Prazo padrão máximo para devolução de itens (em dias)
    }

    public boolean isADevolver() {
        // Verifica se o usuário excedeu a cota máxima ou se algum item está em atraso
        return this.itensRetirados.size() >= this.getCotaMaxima() || this.temPrazoVencido();
    }

    public boolean isAptoARetirar() {
        // O usuário está apto a retirar novos itens se não tiver excedido a cota e todos os itens estejam em dia
        return !this.isADevolver();
    }

    public boolean temPrazoVencido() {
        // Verifica se algum dos itens retirados está com o prazo de devolução vencido
        for (Item item : this.itensRetirados) {
            if (item.isEmAtraso()) {
                return true;
            }
        }
        return false;
    }

    public boolean isProfessor() {
        return false;  // Valor padrão; subclasses podem sobrescrever este método
    }

    @Override
    public String toString() {
        return "Usuário " + nome;
    }

    public String listaCarga() {
        StringBuilder carga = new StringBuilder();
        carga.append(this.toString())
             .append(" Limite: ").append(this.getCotaMaxima())
             .append(" Carga atual: ").append(this.itensRetirados.size())
             .append("\n");
        for (Item item : this.itensRetirados) {
            carga.append(item.toString()).append("\n");
        }
        return carga.toString();
    }

    public int getNivelPriv() {
        return this.nivelDePrivilegio;
    }

    public ArrayList<Item> getItens() {
        return this.itensRetirados;
    }
}
