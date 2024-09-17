package PacoteUsers;

import PacoteItens.Item;
import PacoteItens.Livro;

public class UsuarioProfessor extends Usuario {

    public UsuarioProfessor(String nome) {
        super(nome);
        this.nivelDePrivilegio = 3;  // Define o nível de privilégio como 3 para professores
    }

    @Override
    public boolean retiraItem(Item item) {
        if (item.isDisponivel()) {
            // Verifica se o item está disponível para empréstimo
            if (item.empresta(this, getPrazoMaximo())) {
                // Tenta emprestar o item e adiciona à lista de itens retirados se o empréstimo for bem-sucedido
                this.itensRetirados.add(item);
                return true;
            }
        }
        return false;  // Retorna false se o item não estiver disponível ou o empréstimo falhar
    }

    @Override
    public boolean devolveItem(Item item) {
        // Remove o item da lista de itens retirados e o devolve ao estoque
        this.itensRetirados.remove(item);
        return true;  // Retorna true após a devolução
    }

    public boolean bloqueiaLivro(Livro livro, int prazo) {
        // Bloqueia o livro para que outros usuários não possam retirá-lo
        return livro.bloqueia(this, prazo);  // Chama o método bloqueia da classe Livro
    }

    public boolean desbloqueiaLivro(Livro livro) {
        // Desbloqueia o livro para que outros usuários possam retirá-lo novamente
        return livro.desbloqueia(this);  // Chama o método desbloqueia da classe Livro
    }

    @Override
    public int getCotaMaxima() {
        // Define a quantidade máxima de livros que o professor pode retirar
        return 5;  // Professores podem retirar até 5 livros
    }

    @Override
    public int getPrazoMaximo() {
        // Define o prazo máximo para devolução de livros por um professor
        return 14;  // Professores têm 14 dias para devolver os livros
    }

    @Override
    public String toString() {
        // Retorna uma representação do usuário professor
        return "Prof. " + super.getNome();
    }

    @Override
    public boolean isProfessor() {
        // Indica que o usuário é um professor
        return true;
    }
}
