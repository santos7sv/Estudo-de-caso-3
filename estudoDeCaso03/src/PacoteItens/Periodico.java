package PacoteItens;

import PacoteUsers.Usuario;

public class Periodico extends Livro {
    
    public Periodico(String titulo) {
        super(titulo);
    }

    @Override
    public boolean empresta(Usuario usuario, int prazo) {
        if (usuario.isProfessor()) {
            // Permite o empréstimo apenas se o usuário for professor
            return super.empresta(usuario, 7);  // Define um prazo fixo de 7 dias para o empréstimo
        } else {
            return false;  // Não permite o empréstimo para outros usuários
        }
    }
}
