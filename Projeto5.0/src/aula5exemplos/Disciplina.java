
package aula5exemplos;

import java.io.Serializable;
import java.util.Objects;

public class Disciplina implements Serializable {
    
    public static final int MAX_ALUNOS = 30;
    
    private String nome;
    private Aluno alunos[];
    private Professor professor;
    private int ano;
    private float notas[];
    
    public boolean registrarNota(float nota, String nome){
        for(int i = 0; i< notas.length; i++){
            if (alunos[i] !=null && alunos[i].getNome().equals(nome)) {
                notas[i] = nota;
                return true; 
            }
        }
        return false;
    } 

    public boolean novoAluno(String nome){
        for ( int i = 0; i < alunos.length; i++) {
            if (alunos[i]==null) {
                alunos[i] = new Aluno(nome);
                return true;
            }
        }
        return false;
    }
    
    public boolean registrarAluno(String nome, Curso curso, int anoIngresso, long matricula) {
            for (int i = 0; i < alunos.length; i++) {
                if (alunos[i] == null) {
                    alunos[i] = new Aluno(nome, curso, anoIngresso, matricula);
                    return true;
                }
        }
        return false;
    }
    
     public void registrarAluno(Aluno a) {
        registrarAluno(a.getNome(), a.getCurso(), (int) a.getAnoIngresso(), a.getMatricula());
    }
    
    public  boolean removerAluno(String nome) {
        for ( int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getNome().equals(nome)) {
                alunos[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public boolean alterarNota(float nota, String nome) {
        return registrarNota(nota, nome);
    } 
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public float[] getNotas() {
        return notas;
    }
    public void setNotas(float[] notas) {
         this.notas = notas;
    }
    public Aluno[] getAlunos() {
         return alunos;
    }
    public void setAlunos(Aluno[] alunos) {
         this.alunos = alunos;
    }
    public Professor getProfessor() {
         return professor;
    }
    public void setProfessor(Professor professor) {
         this.professor = professor;
    }
    
    
    public Disciplina(Professor professor,String nome,int ano){
        this.professor = professor;
        this.nome = nome;
        this.ano = ano;
        this.alunos = new Aluno[MAX_ALUNOS];
        this.notas = new float[MAX_ALUNOS];
    }
    
    public Disciplina(int quantAlunos,Professor professor,String nome,int ano){
        this.alunos = new Aluno[quantAlunos];
        this.notas = new float[quantAlunos];
        this.professor = professor;
        this.nome = nome;
        this.ano = ano;
    }
    
    public Disciplina() {
        alunos = new Aluno[MAX_ALUNOS];
        notas = new float[MAX_ALUNOS];
    }
    
    ////////////////////////// equals e toString ///////////////////////////////
    
    @Override
    public boolean equals(Object obj) {
        if (obj != null
                && getClass() == obj.getClass()) {
            final Disciplina other = (Disciplina) obj;

            if (this.professor.equals(other.professor)
                    && this.nome.equals(other.nome)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.professor);
        hash = 97 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public String toString() {
        String notas_alunos = "";

        for (int i = 0; i < alunos.length; i++) {
            Aluno aluno = alunos[i];
            float nota = notas[i];

            if (aluno != null) {
                notas_alunos += aluno.toString() + " Nota: " + nota + "\n";
            }
        }
        return "\n professor: " + professor
                + "\n nome: " + nome
                + "\n ano: " + ano
                + "\n Notas: " + notas_alunos;
    }
}
    