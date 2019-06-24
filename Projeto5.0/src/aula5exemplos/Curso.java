
package aula5exemplos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Curso implements Serializable, Comparable {
    
    public static final int MAX_DISCIPLINAS = 40;
    private String nome;
    private String ppc;
    private ArrayList<Disciplina> disciplinas;
     
    public boolean novaDisciplina(String nome, int ano, Professor professor) {
        return disciplinas.add(new Disciplina(professor,nome,ano));
    }
    
    public boolean novaDisciplina(Disciplina disciplina){
        return disciplinas.add(disciplina);
    }
        
    public boolean removerDisciplina(String nome) {
        for (Iterator<Disciplina> iterator = disciplinas.iterator(); iterator.hasNext();) {
            Disciplina d = iterator.next();
        
            if (d != null && d.getNome().equals(nome)){
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
    public String getPpc() {
        return ppc;
    } 
    public void setPpc(String ppc) {
        this.ppc = ppc;
    }
    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
     public Curso(String nome,String ppc,ArrayList<Disciplina> disciplinas){
        this.nome = nome;   
        this.ppc = ppc;
        this.disciplinas = disciplinas;
    }
    
    public Curso(String nome,String ppc){
        this.nome = nome;
        this.ppc = ppc;
        this.disciplinas = new ArrayList();
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

    @Override
    public int compareTo(Object o) {
        return this.nome.compareTo(((Curso) o).nome);
    }
}