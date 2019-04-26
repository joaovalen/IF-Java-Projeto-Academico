package aula5exemplos;

import aula5exemplos.Aluno;
import aula5exemplos.Professor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;



public class ProgramaPrincipal {
    
    
    public static void main(String[] args) throws IOException {
        SetorEnsino ensino = new SetorEnsino("Pâmela Perini", "Vitor Valente");
        Professor professor = new Professor("Coelho",1);
        int opcao = 4;
        
        do {
            opcao = menu("MENU 1: \n [1] Aluno \n [2] Professor \n [3] Setor de Ensino \n [4] Sair");
            switch (opcao) {
                case 1:
                    menu_alunos("MENU 2: \n [1] Ver Cursos [2] Ver notas",ensino);
                    break;
                case 2:
                    menu_professor("MENU 2: \n [1] Dar Notas de uma disciplina [2] Alterar uma nota [3] Adicionar Área [4] Remover Área",ensino);
                    break;
                case 3:
                    menu_ensino("MENU 2: \n [1] Cadastrar Aluno [2] Cadastrar Curso [3] Adicionar Disciplina ao Curso [4] Cadastrar Professor", ensino);
            }
        } while (opcao != 4);
    }

    private static int menu(String opcoes) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(opcoes);
        String texto = br.readLine();
        int opcao = Integer.parseInt(texto);
        return opcao;
    }

    private static void menu_alunos(String opcoes,SetorEnsino ensino) throws IOException {
        int opcao = menu(opcoes);
        switch (opcao) {
            case 1:
                for (Curso c: ensino.getCursos()){
                    if (c != null){
                        String nome = c.getNome();
                        System.out.println(nome);
                    }else{
                        System.out.println("Ainda não há cursos");
                    }
                }
            
        }
    }

    private static void menu_professor(String opcoes, SetorEnsino ensino) throws IOException {
        int opcao = menu(opcoes);
        
        switch(opcao){
            
            case 1:
                System.out.println("Informe a disciplina");
                String disciplina = inputString();
                System.out.println("Informe o curso");
                String curso = inputString();
                ensino.darNotas(disciplina,curso);
        }

    }

    private static void menu_ensino(String opcoes, SetorEnsino ensino) throws IOException {
        int opcao = menu(opcoes);
        long siape = 0;
        switch(opcao){
            
            case 1:
                System.out.println("Nome Aluno: ");
                String nomea = inputString();
                System.out.println("Matrícula: ");
                long matricula = inputLong();
                System.out.println("Ano ingresso: ");
                long anoingresso = inputLong();
                ensino.novoAluno(nomea,matricula,anoingresso);
                break;
                
            case 2:
                cadastra_curso(ensino);
                break;
             
            case 3: 
                System.out.println("Curso a ser adicionada");
                String nome_curso = inputString();
                Curso c = ensino.encontraCurso(nome_curso);
                
                if (c == null) {
                    System.err.println("Curso não encontrado. Cadastre-o.");
                    cadastra_curso(ensino);
                }
                
                System.out.println("Qual o Siape do professor?");
                String siape_professor = inputString();
                Professor p = ensino.encontraProfessor(siape);
                
                if (p == null) {
                    System.err.println("Professor não encontrado. Cadastre-o.");
                    cadastra_professor(ensino);
                }
                
                System.out.println("Nome da nova disciplina: ");
                String nomed = inputString();
                System.out.println("Ano da disciplina: ");
                int ano = inputInt();
        
                ensino.criaDisciplina(nomed, ano, siape, nome_curso);
                break;
                
            case 4:
                cadastra_professor(ensino);
                 
        }
        

    }
    
    private static void cadastra_curso(SetorEnsino ensino){
        System.out.println("Nome do curso: ");
        String nomec = inputString();
        System.out.println("PPC: ");
        String ppc = inputString();
        ensino.novoCurso(nomec, ppc);
    }
    
    private static void cadastra_professor(SetorEnsino ensino){
        System.out.println("Nome do Professor ");
        String nomep = inputString();
        System.out.println("Siape: ");
        long siape = inputLong();
        System.out.println("Quantas áreas? ");
        int qtd = inputInt();
        System.out.println("Informe as áreas:");
        String[] areas = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            String a = inputString();
            areas[i] = a;
        }
        ensino.novoProfessor(nomep, siape, areas);  
    }
    
    private static String inputString(){
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        return x;
    }
    private static long inputLong(){
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        return x;
    }
     private static int inputInt(){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        return x;
    }
}
    

