package TesteBiblioteca;

import java.util.*;

import PacoteItens.*;
import PacoteUsers.*;

public class ClasseControle {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> usuariosExt;
    private ArrayList<UsuarioProfessor> professores;
    private ArrayList<UsuarioAluno> alunos;
    private ArrayList<MapaTematico> mapasTematicos;
    private ArrayList<Livro> periodicos;

    public ClasseControle() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.usuariosExt = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.mapasTematicos = new ArrayList<>();
        this.periodicos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Este livro é um periódico? Digite 1 para SIM ou 2 para NÃO: ");
        int isPer = scanner.nextInt();
        if (isPer == 1) {
            livro.setPeriodico();
            this.periodicos.add(livro);
        }
        this.livros.add(livro);
        System.out.println("Livro adicionado com sucesso.");
    }

    public void removerLivro(Livro livro) {
        if (this.livros.remove(livro)) {
            System.out.println("Livro removido com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void adicionarAluno(UsuarioAluno aluno) {
        this.alunos.add(aluno);
        this.usuarios.add(aluno);
        System.out.println("Aluno adicionado com sucesso.");
    }

    public void removerAluno(UsuarioAluno aluno) {
        if (this.alunos.remove(aluno) && this.usuarios.remove(aluno)) {
            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void adicionarProfessor(UsuarioProfessor professor) {
        this.professores.add(professor);
        this.usuarios.add(professor);
        System.out.println("Professor adicionado com sucesso.");
    }

    public void removerProfessor(UsuarioProfessor professor) {
        if (this.professores.remove(professor) && this.usuarios.remove(professor)) {
            System.out.println("Professor removido com sucesso.");
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    public void adicionarUsuarioExt(Usuario usuario) {
        this.usuariosExt.add(usuario);
        this.usuarios.add(usuario);
        System.out.println("Usuário externo adicionado com sucesso.");
    }

    public void removerUsuarioExt(Usuario usuario) {
        if (this.usuariosExt.remove(usuario) && this.usuarios.remove(usuario)) {
            System.out.println("Usuário externo removido com sucesso.");
        } else {
            System.out.println("Usuário externo não encontrado.");
        }
    }

    public void adicionarMapaTematico(MapaTematico mapa) {
        this.mapasTematicos.add(mapa);
        System.out.println("Mapa temático adicionado com sucesso.");
    }

    public void removerMapaTematico(MapaTematico mapa) {
        if (this.mapasTematicos.remove(mapa)) {
            System.out.println("Mapa temático removido com sucesso.");
        } else {
            System.out.println("Mapa temático não encontrado.");
        }
    }

    public boolean emprestarLivro(String titulo, Usuario usuario) {
        Livro livro = this.getLivro(titulo);
        if (livro != null) {
            if (!livro.isBloqueado() && !this.periodicos.contains(livro)) {
                System.out.println("Livro emprestado para: " + usuario.getNome());
                return livro.empresta(usuario, usuario.getPrazoMaximo());
            } else {
                System.out.println("Não é possível emprestar o livro. Detalhes: " + livro.toString());
                System.out.println("Livro não retirado!");
            }
        } else {
            System.out.println("Livro com o título '" + titulo + "' não encontrado.");
        }
        return false;
    }

    public boolean emprestarPer(String titulo, UsuarioProfessor usuario) {
        Livro livro = this.getLivro(titulo);
        if (livro != null && this.periodicos.contains(livro)) {
            System.out.println("Periódico emprestado para Professor(a): " + usuario.getNome());
            return livro.empresta(usuario, usuario.getPrazoMaximo());
        } else {
            System.out.println("Periódico com o título '" + titulo + "' não encontrado ou não disponível.");
        }
        return false;
    }

    public boolean devolverLivro(String titulo, Usuario usuario) {
        Livro livro = this.getLivro(titulo);
        if (livro != null) {
            System.out.println("Livro devolvido com sucesso!");
            return livro.retorna(usuario);
        } else {
            System.out.println("Livro com o título '" + titulo + "' não encontrado.");
        }
        return false;
    }

    public boolean devolverPeriodico(String titulo, UsuarioProfessor usuario) {
        Livro livro = this.getLivro(titulo);
        if (livro != null && this.periodicos.contains(livro)) {
            System.out.println("Periódico devolvido com sucesso!");
            return livro.retorna(usuario);
        } else {
            System.out.println("Periódico com o título '" + titulo + "' não encontrado ou não é um periódico.");
        }
        return false;
    }

    public boolean emprestarMapa(String titulo, Usuario usuario) {
        MapaTematico mapa = this.getMapaTematico(titulo);
        if (mapa != null) {
            System.out.println("Mapa emprestado para: " + usuario.getNome());
            return mapa.empresta(usuario, 2);
        } else {
            System.out.println("Mapa temático com o título '" + titulo + "' não encontrado.");
        }
        return false;
    }

    public boolean devolverMapaTematico(String titulo, Usuario usuario) {
        MapaTematico mapa = this.getMapaTematico(titulo);
        if (mapa != null) {
            System.out.println("Mapa temático devolvido com sucesso!");
            return mapa.retorna(usuario);
        } else {
            System.out.println("Mapa temático com o título '" + titulo + "' não encontrado.");
        }
        return false;
    }

    public Usuario getUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        System.out.println("Usuário com o nome '" + nome + "' não encontrado.");
        return null;
    }

    public UsuarioAluno getAluno(String nome) {
        for (UsuarioAluno aluno : alunos) {
            if (aluno.getNome().equals(nome)) {
                return aluno;
            }
        }
        System.out.println("Aluno com o nome '" + nome + "' não encontrado.");
        return null;
    }

    public UsuarioProfessor getProfessor(String nome) {
        for (UsuarioProfessor professor : professores) {
            if (professor.getNome().equals(nome)) {
                return professor;
            }
        }
        System.out.println("Professor com o nome '" + nome + "' não encontrado.");
        return null;
    }

    public Livro getLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        System.out.println("Livro com o título '" + titulo + "' não encontrado.");
        return null;
    }

    public MapaTematico getMapaTematico(String titulo) {
        for (MapaTematico mapa : mapasTematicos) {
            if (mapa.getTitulo().equals(titulo)) {
                return mapa;
            }
        }
        System.out.println("Mapa temático com o título '" + titulo + "' não encontrado.");
        return null;
    }

    public void bloqueiaLivro(UsuarioProfessor professor, String titulo, int prazo) {
        Livro livro = this.getLivro(titulo);
        if (livro != null) {
            if (professor.bloqueiaLivro(livro, prazo)) {
                System.out.println("Livro bloqueado com sucesso!");
            } else {
                System.out.println("Não foi possível bloquear o livro.");
            }
        } else {
            System.out.println("Livro com o título '" + titulo + "' não encontrado.");
        }
    }

    public void desbloqueiaLivro(UsuarioProfessor professor, String titulo) {
        Livro livro = this.getLivro(titulo);
        if (livro != null) {
            if (livro.isBloqueado()) {
                if (professor.desbloqueiaLivro(livro)) {
                    System.out.println("Livro desbloqueado com sucesso!");
                } else {
                    System.out.println("Não foi possível desbloquear o livro.");
                }
            } else {
                System.out.println("O livro já está desbloqueado!");
            }
        } else {
            System.out.println("Livro com o título '" + titulo + "' não encontrado.");
        }
    }

    public ArrayList<Livro> getLivros() {
        return this.livros;
    }

    public ArrayList<MapaTematico> getMapas() {
        return this.mapasTematicos;
    }


}
