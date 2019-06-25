
package aula5exemplos;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Disciplina implements Serializable, Comparable {
    
    public static final int MAX_ALUNOS = 30;
    
    private String nome;
    private HashMap<Aluno, Float> alunos;
    private Professor professor;
    private int ano;
    
    // COMO FAZER PRA TESTAR SE FUNCIONOU 
    //
    //
    //
    //
    //
    
    public boolean registrarNota(float nota, Aluno aluno){
       alunos.replace(aluno, nota);
       return true;
    } 

    public boolean novoAluno(String nome){
        return alunos.put(
        new Aluno(nome),0f) != null; 
    }
    
    
    public boolean registrarAluno(String nome, Curso curso, int anoIngresso, long matricula) {
        return alunos.put(
        new Aluno(nome, curso, anoIngresso, matricula),0f) != null; 
    }
    
     public void registrarAluno(Aluno a) {
        registrarAluno(a.getNome(), a.getCurso(), (int) a.getAnoIngresso(), a.getMatricula());
    }
    
    public  boolean removerAluno(String nome) {
        /// COMO FUNCIONA O ITERATOR E PAH 
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        for (Iterator<Aluno> iterator = alunos.keySet().iterator(); iterator.hasNext();) {
            Aluno aluno = iterator.next();
            
            if (aluno.getNome().equals(nome)) {
                iterator.remove();
                return true;
            }
        }
        return false;
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
    //////////////////////////// O QUE SERIA O COLECTION //////////////////
    //
    //
    // O SET NÃO É NECESSÁRIO CORRETO
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    
    public Collection<Float> getNotas() {
        return alunos.values();
    }
    
    public HashMap<Aluno, Float> getAlunos() {
         return alunos;
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
        this.alunos = new HashMap();
    }
    
    public Disciplina(int quantAlunos,Professor professor,String nome,int ano){
        this.alunos = new HashMap();
        this.professor = professor;
        this.nome = nome;
        this.ano = ano;
    }
    
    public Disciplina() {
        alunos = new HashMap();
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
// COMO FUNCIONA O FOR DESSE TO STRING 
    //
    //
    //
    //
    //
    //
    //
    //
    //
    @Override
    public String toString() {
        String notas_alunos = "";

        for (Map.Entry<Aluno, Float> entry : alunos.entrySet()) {
            Aluno aluno = entry.getKey();
            Float nota = entry.getValue();

            if (aluno != null) {
                notas_alunos += aluno.toString() + " Nota: " + nota + "\n";
            }
        }
        return "\n professor: " + professor
                + "\n nome: " + nome
                + "\n ano: " + ano
                + "\n Notas: " + notas_alunos;
    }

    @Override
    public int compareTo(Object outro) {
        return this.nome.compareTo(((Disciplina) outro).nome)
                + this.professor.compareTo(((Disciplina)outro).professor);
    }
}
    