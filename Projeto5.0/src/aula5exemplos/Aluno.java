
package aula5exemplos;

public class Aluno {
    private String nome;
    private String curso;
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
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
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
            String curso, 
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

}
