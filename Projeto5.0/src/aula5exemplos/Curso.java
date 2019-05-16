
package aula5exemplos;

import java.util.Objects;

public class Curso {
    
    public static final int MAX_DISCIPLINAS = 40;
    private String nome;
    private String ppc;
    private Disciplina disciplinas[];
     
    public boolean novaDisciplina(String nome, int ano, Professor professor) {
        for ( int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                disciplinas[i] = new Disciplina(professor,nome,ano);
                return true;
            }
        }
    return false;
    }
    
    public boolean removerDisciplina(String nome) {
        for ( int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null &&
            disciplinas[i].getNome().equals(nome)) {
            disciplinas[i] = null;
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
    public String getPpc() {
        return ppc;
    } 
    public void setPpc(String ppc) {
        this.ppc = ppc;
    }
    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }
    
     public Curso(String nome,String ppc,Disciplina[] disciplinas){
        this.nome = nome;
        this.ppc = ppc;
        this.disciplinas = new Disciplina[MAX_DISCIPLINAS];
    }
    
    public Curso(String nome,String ppc){
        this.nome = nome;
        this.ppc = ppc;
    }
    
    public Curso(){   
    }
    
    ///////////////////////////// equals e toString ////////////////////////////
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null
                && getClass() == obj.getClass()) {
            final Curso other = (Curso) obj;

            if (this.nome.equals(other.nome)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String disciplina = "";

        for (Disciplina d : disciplinas) {
            if (d != null) {
                disciplina += d.toString() + "\n";
            }
        }
        return "\n nome: "
                + nome
                + "\n ppc: "
                + ppc
                + "\n disciplinas: \n"
                + disciplina;
    }
    
}

