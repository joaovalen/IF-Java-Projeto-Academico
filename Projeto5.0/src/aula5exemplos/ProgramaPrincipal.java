package aula5exemplos;

import aula5exemplos.Aluno;
import aula5exemplos.Professor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;



public class ProgramaPrincipal {
    
    //////////////////////////////// MENU PRINCIPAL ////////////////////////////
    
    public static void main(String[] args) throws IOException {
        SetorEnsino ensino = new SetorEnsino("Pâmela Perini", "Vitor Valente");
        Aluno[] alunos = new Aluno[1000];
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
                    menu_ensino("MENU 2: \n [1] Cadastrar Aluno [2] Cadastrar Curso [3] Adicionar Disciplina ao Curso [4] Cadastrar Professor", ensino,alunos);
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

    ////////////////////////////// MENU ALUNOS /////////////////////////////////
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

    //////////////////////////// MENU PROFESSOR ////////////////////////////////
    
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

    ///////////////////////// MENU ENSINO //////////////////////////////////////
    
    private static void menu_ensino(String opcoes, SetorEnsino ensino, Aluno[] alunos) throws IOException {
        int opcao = menu(opcoes);
        long siape = 0;
        switch(opcao){
            
            case 1:
                cadastra_aluno(ensino);
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
                    c = cadastra_curso(ensino);
                }
                
                System.out.println("Qual o Siape do professor?");
                String siape_professor = inputString();
                Professor p = ensino.encontraProfessor(siape);
                
                if (p == null) {
                    System.err.println("Professor não encontrado. Cadastre-o.");
                    p = cadastra_professor(ensino);
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
    
    ///////////////////////////// FUNÇÕES ALUNO ////////////////////////////////
    
    private static void cadastra_aluno(SetorEnsino ensino) throws IOException {
        System.out.println("Nome Aluno: ");
        String nome = inputString();
        System.out.println("Matrícula: ");
        long matricula = inputLong();
        System.out.println("Ano ingresso: ");
        long anoingresso = inputLong();
        
        if(ensino.novoAluno(nome,matricula,anoingresso)){
            System.out.println("Aluno " + nome + " Cadastrado com sucesso");
        } else {
            System.out.println("Número máximo de alunos alcançado");
        }
    }
    
    ///////////////////////////// FUNÇÕES CURSO ////////////////////////////////
    
    private static Curso cadastra_curso(SetorEnsino ensino) throws IOException {
        Curso c;

        c = cria_curso(ensino);
        if (ensino.novoCurso(c)) {
            System.out.println("Curso " + c.getNome());
        } else {
            System.out.println("O limite de cursos foi alcançado.");
        }
        return c;
    }
    
    private static Curso cria_curso(SetorEnsino ensino) throws IOException {
        Curso a = new Curso();

        System.out.println("Nome da disciplina:");
        a.setNome(inputString());
        System.out.println("PPC:");
        a.setPpc(inputString());
        Disciplina[] disciplinas = recebe_disciplinas(ensino);

        a.setDisciplinas(disciplinas);
        return a;
    }
    
    private static Disciplina[] recebe_disciplinas(SetorEnsino ensino) throws IOException {
        Disciplina[] disciplinas = new Disciplina[40];

        System.out.println("Digite [1] para cadastrar disciplina e [2] para terminar");
        int op = inputInt();

        for (int i = 0; op != 1 && i < disciplinas.length; i++) {
            System.out.println("Qual o SIAPE do professor da disciplina?");
            int siape = inputInt();
            Professor professor = ensino.encontraProfessor(siape);

            if (professor == null) {
                System.err.println("O professor ainda não foi cadastrado. Informe seus dados.");
                professor = cria_professor();
                ensino.novoProfessor(professor);
            }
            disciplinas[i] = cria_disciplina(professor);
            System.out.println("\n Digite [1] para terminar e [2] para cadastrar disciplina");
            op = inputInt();
        }
        return disciplinas;
    }
    
    ///////////////////////////// FUNÇÕES PROFESSOR ////////////////////////////
    
    private static Professor cadastra_professor(SetorEnsino ensino) throws IOException {
        Professor p;
        
        p = cria_professor();       
        if(ensino.novoProfessor(p)){
            System.out.println("Professor " + p.getNome() + " Cadastrado com sucesso");
        } else {
            System.out.println("Número máximo de professores alcançado");
        }
        return p;
    }
    
    private static Professor cria_professor() throws IOException {
        Professor p = new Professor();
        
        System.out.println("Nome do Professor:");
        p.setNome(inputString());
        System.out.println("SIAPE:");
        p.setSiape(inputInt());
        System.out.println("Quantas áreas?");
        int quantAreas = inputInt();
        p.setAreas(new String[quantAreas]);
        System.out.println("Informe as áreas:");
        for (int i = 0; i < quantAreas; i++) {
            if (p.getAreas() != null && p.getAreas()[i] != null) {
                p.getAreas()[i] = inputString();
            }
        }
        return p;
    }
    
    ///////////////////////////// FUNÇÕES DISCIPLINA ///////////////////////////
    
    private static Disciplina cria_disciplina(Professor p) throws IOException, NumberFormatException {
        System.out.println("Quantos alunos tem na turma?");
        int quantAlunos = inputInt();
        System.out.println("Qual o nome da disciplina?");
        String nome_disciplina = inputString();
        System.out.println("Qual o ano/semestre da disciplina?");
        int ano = inputInt();
        Disciplina d = new Disciplina(quantAlunos, p, nome_disciplina, ano);

        return d;
    }
    
    //////////////////////////// LEITORES //////////////////////////////////////
    
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
    

