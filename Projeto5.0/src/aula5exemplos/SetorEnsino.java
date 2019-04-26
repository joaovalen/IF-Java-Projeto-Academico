
package aula5exemplos;

import java.io.IOException;
import java.util.Scanner;

public class SetorEnsino {

    private Disciplina disciplinas[];
    private Aluno[] alunos;
    private Curso cursos[];
    private Professor professores[];
    private String diretor;
    private String coordenador;

    public boolean novoProfessor(String nome, long siape, String[] areas) {
        for (int i = 0; i < professores.length; i++) {
            if (professores[i] == null) {
                professores[i] = new Professor(nome, siape, areas);
                System.out.println("Professor " + professores[i].getNome() + " cadastrado com sucesso");
                return true;
            }
        }
        return false;
    }

    public boolean demitirProfessor(long siape) {
        for (int i = 0; i < professores.length; i++) {
            if (professores[i] != null && professores[i].getSiape() == siape) {
                professores[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean novoCurso(String nome, String ppc) {
        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] == null) {
                cursos[i] = new Curso(nome, ppc);
                System.out.println("Curso " + cursos[i].getNome() + " Cadastrado com sucesso");
                return true;
            }
        }
        return false;
    }

    public boolean removerCurso(String nome) {
        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] != null && cursos[i].getNome().equals(nome)) {
                cursos[i] = null;
                return true;

            }
        }
        return false;
    }

    public boolean novoAluno(String nome, long matricula, long anoIngresso) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                alunos[i] = new Aluno(nome, matricula, anoIngresso);
                return true;
            }
        }
        return false;
    }

    public boolean desligaAluno(long matricula) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getMatricula() == matricula) {
                alunos[i] = null;
                return true;
            }
        }
        return false;
    }

    public Professor encontraProfessor(long siape) {
        for (Professor prof : professores) {
            if (prof.getSiape() == siape) {
                return prof;
            }
        }
        return null;
    }
    
    public Disciplina encontraDisciplina(String disciplina, Curso curso){
        if (curso != null && curso.getDisciplinas() != null){
            for (Disciplina disc : curso.getDisciplinas()){
                if (disc != null && disc.getNome().equals(disciplina)){
                    return disc;
                }
            }
        }
        return null;
    }
    
    public Curso encontraCurso(String nome){
        if (cursos != null) {
            for (Curso curso : cursos) {
                if (curso != null && curso.getNome().equals(nome)) {
                    return curso;
                }
            }
        }
        return null;
    }

    public boolean criaDisciplina(String nome, int ano, long siape, String curso) {
        for (Curso c : cursos) {
            if (c != null && c.getNome().equals(curso)) {
                for (int j = 0; j < c.getDisciplinas().length; j++) {
                    if (c.getDisciplinas()[j] == null) {
                        c.getDisciplinas()[j] = new Disciplina(encontraProfessor(siape), nome, ano);
                        System.out.println("Disciplina " + disciplinas[j].getNome() + "Cadastrada com sucesso");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void darNotas(String disc , String c) throws IOException{
        Curso curso = encontraCurso(c);
        
        if (curso == null){
           System.err.println("Curso " + c + " não encontrado.");
        }else {
            Disciplina d = encontraDisciplina(disc,curso);

            if (d == null) {
                System.err.println("Disciplina " + disc + " não encontrada.");
            } else {
                if (d != null && d.getAlunos() != null) {
                    System.out.println("Informe as notas dos alunos: ");
                    float notas[] = new float[d.getAlunos().length];
                    int i = 0;

                    while (i < d.getAlunos().length) {
                        System.out.println("Nota do aluno " + d.getAlunos()[i].getNome());
                        notas[i] = inputFloat();
                        i++;
                    }
                    salvaNotas(notas, curso, d);
                }
            }  
        }       
    }
    
    public void salvaNotas(float[] notas,
            Curso curso,
            Disciplina disciplina) {
        for (Curso c : cursos) {
            if (c.equals(curso)) {
                for (Disciplina d : c.getDisciplinas()) {
                    if (d.equals(disciplina)) {
                        d.setNotas(notas);
                    }
                }
            }
        }
    }
    
    
    
    
    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }
    public Aluno[] getAlunos() {
        return alunos;
    }
    public void setAlunos(Aluno[] alunos) {
        this.alunos = alunos;
    }
    public Curso[] getCursos() {
        return cursos;
    }
    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }
    public Professor[] getProfessores() {
        return professores;
    }
    public void setProfessores(Professor[] professores) {
        this.professores = professores;
    }
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public String getCoordenador() {
        return coordenador;
    }
    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }
    
     public SetorEnsino(String diretor, String coordenador) {
        this.coordenador = coordenador;
        this.diretor = diretor;
        this.alunos = new Aluno[2000];
        this.professores = new Professor[80];
        this.cursos = new Curso[200];
    }

    public SetorEnsino(Curso[] cursos,
            Professor[] professores,
            String diretor,
            String coordenador) {
        this.cursos = cursos;
        this.professores = professores;
        this.diretor = diretor;
        this.coordenador = coordenador;
    }
    
     private static String inputString(){
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        return x;
    }
     
     private static Float inputFloat(){
         Scanner sc = new Scanner(System.in);
         float x = sc.nextFloat();
         return x;
     }
}
