
package aula5exemplos;

import java.io.Serializable;
import java.util.Objects;

public class Professor implements Serializable {
    private String nome;
    private String areas[] = new String[10];
    private int siape;

    public boolean novaArea(String area) {
        for (int i = 0; i < areas.length; i++) {
            if (areas[i] == null) {
                areas[i] = area;
                return true;
            }
        }
        return false;
    }
    
    public boolean removerArea(String area) {
        for (int i = 0; i < areas.length; i++) {
            if (areas[i].equals(area)) {
                areas[i] = null;
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
    public String[] getAreas() {
        return areas;
    }
    public void setAreas(String[] areas) {
        this.areas = areas;
    }
    public long getSiape() {
        return siape;
    }
    public void setSiape(int siape) {
        this.siape = siape;
    }
    public void alterarNome(String novoNome) {
        this.nome = novoNome;
    }
    
    
     public Professor(String nome, int siape){
        this.nome = nome;
        this.siape = siape;
    }
    
    public Professor(String nome,int siape,String[] areas){
        this.nome = nome;
        this.siape = siape;
        this.areas = areas;
    }
    
    public Professor(){
        
    }
    ///////////////////////// Equals E toString ////////////////////////////////
     
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + (int) (this.siape ^ (this.siape >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        if (this.siape != other.siape) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public String toString() {
        String nomes_areas = "";

        if (areas != null) {
            for (String d : areas) {
                if (d != null) {
                    nomes_areas += d + "\n";
                }
            }
        }
        return "\n nome: "
                + nome
                + "\n siape: "
                + siape
                + "\n areas: "
                + nomes_areas;
    }
    
}
