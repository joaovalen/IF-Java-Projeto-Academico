
package projeto_academico;

import java.io.Serializable;
import java.util.Objects;

public class Aluno implements Serializable, Comparable<Aluno> {
    private String nome;
    private Curso curso;
    private long anoIngresso;
    private boolean ehFormado;
    private long matricula;

    public String verificaStatus() {
        if (ehFormado) {
            return "O aluno ainda não completou os créditos";
        }
        return "O aluno entrou no ano" + anoIngresso + "e se formou";
    }

    
     public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public long getAnoIngresso() {
        return anoIngresso;
    }
    public void setAnoIngresso(long anoIngresso) {
        this.anoIngresso = anoIngresso;
    }
    public boolean isEhFormado() {
        return ehFormado;
    }
    public void setEhFormado(boolean ehFormado) {
        this.ehFormado = ehFormado;
    }
    public long getMatricula() {
        return matricula;
    }
    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }
    
     public Aluno(String nome){
        this.nome = nome;    
    }
    
    public Aluno(String nome, 
            Curso curso, 
            long anoIngresso, 
            long matricula){
        this.nome = nome;
        this.curso = curso;
        this.anoIngresso = anoIngresso;
        this.matricula = matricula;
        this.ehFormado = false;
    }
    
    public Aluno(String nome, 
            long anoIngresso, 
            long matricula){
        this.nome = nome;
        this.anoIngresso = anoIngresso;
        this.matricula = matricula;
        this.ehFormado = false;
    }

    public Aluno(){
        
    }
    
    ///////////////////////////// Equals e toString ////////////////////////////
    @Override
    public boolean equals(Object obj) {
        if (obj != null
                && getClass() == obj.getClass()) {
            // POR QUE ESSE FINAL???????????????????????????????????????????
            final Aluno other = (Aluno) obj;

            if (this.matricula == other.matricula
                    && this.nome.equals(other.nome)) {
                return true;
            }
        }
        return false;
    }

    @Override
    ///// PRA QUE O HASH CODE ???????????????????????????????????????????
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + (int) (this.matricula ^ (this.matricula >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        String formado = "está formado.";

        if (!ehFormado) {
            formado = "não " + formado;
        }
        String nome_curso = "Nenhum";
        if (curso != null) {
            nome_curso = curso.getNome();
        }
        return "\n nome: " + nome
                + "\n curso: " + nome_curso
                + "\n anoIngresso: " + anoIngresso
                + "\n matricula: " + matricula
                + "\n " + formado;
    }

    @Override
    public int compareTo(Aluno outro) {
        Long mat1 = this.matricula;
        Long mat2 = outro.matricula;

        return this.nome.compareTo(outro.nome) + mat1.compareTo(mat2);
    }

 
}
