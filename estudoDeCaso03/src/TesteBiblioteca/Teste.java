package TesteBiblioteca;

import java.util.Date; // Importa a classe Date, caso seja necessário para instanciar objetos

import PacoteItens.*;
import PacoteUsers.*;

public class Teste {
    public static void main(String[] args) {
        // Cria uma instância do sistema
        Sistema sistema = new Sistema();
        
        // Inicia o menu do sistema
        sistema.menu();
    }
}
