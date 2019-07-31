package projeto_academico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Lembretes
// ctrl + shift + minus = fecha todas as função

public class ProgramaPrincipal {
 
//////////////////////// ATRIBUTOS ESTÁTICOS ///////////////////////////////////    
    private static final int TOTAL_ALUNOS = 1000;
    private static final String DIRETOR_ENSINO = "Pâmela Perini";
    private static final String COORDENADOR_ENSINO = "Vitor Valente";

///////////////////////// ESTATICOS MAIN ///////////////////////////////////////
    private static final int OP_ALUNO = 1;
    private static final int OP_PROFESSOR = 2;
    private static final int OP_ENSINO = 3;
    private static final int OP_SAIR = 4;

///////////////////////// ESTATICOS MENU ALUNO /////////////////////////////////
    private static final int OP_ALUNO_VER_CURSOS = 1;
    private static final int OP_ALUNO_VER_NOTAS = 2;
    private static final int OP_ALUNO_VOLTAR = 3;

///////////////////////// ESTATICOS MENU PROFESSOR /////////////////////////////
    private static final int OP_PROFESSOR_DAR_NOTAS = 1;
    private static final int OP_PROFESSOR_ALTERAR_NOTA = 2;
    private static final int OP_PROFESSOR_ADICIONAR_AREA = 3;
    private static final int OP_PROFESSOR_REMOVER_AREA = 4;
    private static final int OP_PROFESSOR_VOLTAR = 5;

//////////////////////// ESTATICOS MENU ENSINO /////////////////////////////////
    private static final int OP_ENSINO_NOVO_ALUNO = 1;
    private static final int OP_ENSINO_NOVO_CURSO = 2;
    private static final int OP_ENSINO_NOVA_DISCIPLINA = 3;
    private static final int OP_ENSINO_NOVO_PROFESSOR = 4;
    private static final int OP_ENSINO_VOLTAR = 5;

    private static final int OP_DISCIPLINA_SAIR = 2;
    private static final int OP_DISCIPLINA_NOVA = 1;

    private static final int POSICAO_INVALIDA = -1;
    
    //////////////////////////////// MENU PRINCIPAL ////////////////////////////
    
    public static void main(String[] args) throws IOException {
        SetorEnsino ensino = Arquivo.obtem_ensino();
        Aluno[] alunos = Arquivo.obtem_alunos();
        int opcao = 4;
        
        do {
            try {
            opcao = menu("MENU 1: \n "
                    + "[" + OP_ALUNO + "] Aluno \n "
                    + "[" + OP_PROFESSOR + "] Professor \n "
                    + "[" + OP_ENSINO + "] Setor de Ensino \n "
                    + "[" + OP_SAIR + "] Sair \n");
            } catch (IOException e){
                Arquivo.escreve_log("Erro ao ler opção do menu 1");
            }
            switch (opcao) {
                case OP_ALUNO:
                    try{
                    menu_alunos("MENU 2: \n "
                            + "[" + OP_ALUNO_VER_CURSOS + "] Ver Cursos \n"
                            + "[" + OP_ALUNO_VER_NOTAS + "] Ver notas \n"
                            + "[" + OP_ALUNO_VOLTAR + "] Voltar",
                            ensino, alunos);
                    } catch (IOException e){
                        Arquivo.escreve_log("Erro ao ler opção do menu do aluno");
                    }
                    break;
                    
                case OP_PROFESSOR:
                    System.out.println("Qual o seu número de siape, professor?");
                    int siape = inputInt();
                    int posicao_professor = ensino.login_professor(siape);
                    
                    if (posicao_professor != POSICAO_INVALIDA) {
                      menu_professor("MENU 2: \n "
                                + "[" + OP_PROFESSOR_DAR_NOTAS + "] Dar Notas de uma disciplina \n "
                                + "[" + OP_PROFESSOR_ALTERAR_NOTA + "] Alterar uma nota \n "
                                + "[" + OP_PROFESSOR_ADICIONAR_AREA + "] Adicionar Área \n "
                                + "[" + OP_PROFESSOR_REMOVER_AREA + "] Remover Área \n"
                                + "[" + OP_PROFESSOR_VOLTAR + "] Voltar",
                                ensino,
                                posicao_professor);
                    } else {
                       System.err.println("SIAPE inválido.");
                    }
                    break;
                    
                case OP_ENSINO:
                    menu_ensino("MENU 2: \n "
                            + "[" + OP_ENSINO_NOVO_ALUNO + "] Cadastrar Aluno \n "
                            + "[" + OP_ENSINO_NOVO_CURSO + "] Cadastrar Curso \n "
                            + "[" + OP_ENSINO_NOVA_DISCIPLINA + "] Adicionar Disciplina ao Curso \n "
                            + "[" + OP_ENSINO_NOVO_PROFESSOR + "] Cadastrar Professor \n"
                            + "[" + OP_ENSINO_VOLTAR + "] Voltar",
                            ensino,
                            alunos);
            }
        } while (opcao != OP_SAIR);
        Arquivo.salva_ensino(ensino);
        Arquivo.salva_alunos(alunos);
    }

    private static int menu(String opcoes) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(opcoes);
        String texto = br.readLine();
        int opcao = Integer.parseInt(texto);
        return opcao;
    }

    ////////////////////////////// MENU ALUNOS /////////////////////////////////
    private static void menu_alunos(String opcoes,SetorEnsino ensino, Aluno alunos[]) throws IOException {
        int opcao = menu(opcoes);
        switch (opcao) {
            case OP_ALUNO_VER_CURSOS:
                ensino.ver_cursos();
                break;
            case OP_ALUNO_VER_NOTAS:
                System.out.println("Qual a sua matrícula, caro discente?");
                long matricula = inputInt();
                
                ensino.ver_notas(matricula);
                break;
        }
    }

    //////////////////////////// MENU PROFESSOR ////////////////////////////////
    
    private static void menu_professor(String opcoes,SetorEnsino ensino,int posicao_professor) throws IOException {
        int opcao = menu(opcoes);
        
        switch(opcao){
            
            case OP_PROFESSOR_DAR_NOTAS:
                System.out.println("Informe a disciplina");
                String disciplina = inputString();
                System.out.println("Informe o curso");
                String curso = inputString();
                ensino.darNotas(disciplina,curso);
                break;
                
            case OP_PROFESSOR_ALTERAR_NOTA://alterar uma nota
                cadastra_alteracao_nota(ensino);
                break;
                
            case OP_PROFESSOR_ADICIONAR_AREA://adicionar área
                cadastra_nova_area(ensino,posicao_professor);
                break;
                
            case OP_PROFESSOR_REMOVER_AREA://remover área
                System.out.println("Informe a área a ser removida: ");
                String area = inputString();
                
                if (remover_area(ensino, posicao_professor, area)) {
                    System.out.println("Área " + area + " foi removido com sucesso para o professor " + ensino.getProfessores()[posicao_professor].toString());
                } else {
                    System.err.println("A área " + area + " não estava cadastrada para o professor com siape " + ensino.getProfessores()[posicao_professor].getSiape());
                }
                break;
        }

    }

    ///////////////////////// MENU ENSINO //////////////////////////////////////
    
    private static void menu_ensino(String opcoes, SetorEnsino ensino, Aluno[] alunos) throws IOException {
        int opcao = menu(opcoes);
        long siape = 0;
        switch(opcao){
            
            case OP_ENSINO_NOVO_ALUNO:
                cadastra_aluno(ensino,alunos);
                break;
                
            case OP_ENSINO_NOVO_CURSO:
                cadastra_curso(ensino);
                break;
             
            case OP_ENSINO_NOVA_DISCIPLINA: 
                System.out.println("Curso a ser adicionada");
                String nome_curso = inputString();
                Curso c = ensino.encontraCurso(nome_curso);
                
                if (c == null) {
                    System.err.println("Curso não encontrado. Cadastre-o.");
                    c = cadastra_curso(ensino);
                }
                
                System.out.println("Qual o Siape do professor da disciplina?");
                int siape_professor = inputInt();
                Professor p = ensino.encontraProfessor(siape_professor);
                
                if (p == null) {
                    System.err.println("Professor não encontrado. Cadastre-o.");
                    p = cadastra_professor(ensino);
                }           
                       
                if (cadastra_disciplina(ensino,c,p)) {
                    System.out.println("Disciplina cadastrada com sucesso.");
                } else {
                    System.err.println("O limite de disciplinas foi excedido.");
                }
                break;
            case OP_ENSINO_NOVO_PROFESSOR:
                cadastra_professor(ensino);
                 
        }
        

    }
    
    ///////////////////////////// FUNÇÕES ALUNO ////////////////////////////////
    
    private static Aluno cadastra_aluno(SetorEnsino ensino,Aluno[] alunos) throws IOException {
        Aluno a;
        a = cria_aluno(ensino);
        
        
        if (ensino.novoAluno(a) && ensino.matricularAluno(a)){
            System.out.println("Aluno " + a.getNome() + " cadastrado com sucesso");
        }else {
            System.out.println("Número máximo de alunos alcançado");
        }
       
        return a;
    }

    private static Aluno cria_aluno(SetorEnsino ensino) throws IOException {
        Aluno a = new Aluno();

        System.out.println("Nome:");
        a.setNome(inputString());
        System.out.println("Curso:");
        String nome_curso = inputString();
        Curso c;
        if (ensino.encontraCurso(nome_curso) == null){
            System.out.println("Curso não encontrado");
            c = cadastra_curso(ensino);
            a.setCurso(c);
        } else {
            a.setCurso(ensino.encontraCurso(nome_curso));
        }
        System.out.println("Matricula:");
        a.setMatricula(inputLong());
        System.out.println("Ingresso:");
        a.setAnoIngresso(inputInt());
        a.setEhFormado(false);
        return a;
    }
    
    private static void cadastra_disciplinas_aluno(SetorEnsino ensino, Aluno a) throws IOException {
        if (ensino.matricularAluno(a)) {
            System.out.println("Aluno matriculado nas disciplinas do curso.");
        } else {
            System.err.println("Curso não encontrado. Cadastre-o.");
            Curso c = cadastra_curso(ensino);

            a.setCurso(c);
        }
    }
    
    
    ///////////////////////////// FUNÇÕES CURSO ////////////////////////////////
    
    private static Curso cadastra_curso(SetorEnsino ensino) throws IOException {
        Curso c;

        c = cria_curso(ensino);
        if (ensino.novoCurso(c)) {
            System.out.println("Curso " + c.toString() + " cadastrado com sucesso");
        } else {
            System.out.println("O limite de cursos foi alcançado.");
        }
        return c;
    }
    
    private static Curso cria_curso(SetorEnsino ensino) throws IOException {
        Curso a = new Curso();

        System.out.println("Nome do curso");
        a.setNome(inputString());
        System.out.println("PPC:");
        a.setPpc(inputString());
        ArrayList<Disciplina> disciplinas = recebe_disciplinas(ensino);

        a.setDisciplinas(disciplinas);
        return a;
    }
    
    private static ArrayList<Disciplina> recebe_disciplinas(SetorEnsino ensino) throws IOException {
        ArrayList<Disciplina> disciplinas = new ArrayList();

        System.out.println("Digite [" + OP_DISCIPLINA_NOVA + "] para cadastrar disciplina "
                           + "e [" + OP_DISCIPLINA_SAIR + "] para terminar");
        
        int opcao = inputInt();
        for (int i = 0; opcao != OP_DISCIPLINA_SAIR; i++) {
            System.out.println("Qual o SIAPE do professor da disciplina?");
            int siape = inputInt();
            Professor professor = ensino.encontraProfessor(siape);

            if (professor == null) {
                System.err.println("O professor ainda não foi cadastrado. Informe seus dados.");
                professor = cria_professor();
                ensino.novoProfessor(professor);
                System.out.println("Professor " + professor.getNome() + " Cadastrado com sucesso");
            }
            disciplinas.add(cria_disciplina(professor));
            System.out.println("\n Digite [1] para cadastrar disciplina e [2] para terminar");
            opcao = inputInt();
        }
        return disciplinas;
    }
    
    ///////////////////////////// FUNÇÕES PROFESSOR ////////////////////////////
    
    private static Professor cadastra_professor(SetorEnsino ensino) throws IOException {
        Professor p;
        
        p = cria_professor();     
        if (ensino.novoProfessor(p)){
            System.out.println("Professor " + p.toString() + " Cadastrado com sucesso");
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
        p.setAreas(new String[10]);
        System.out.println("Informe as áreas:");
        for (int i = 0; i < quantAreas; i++) {
            if (p.getAreas() != null && p.getAreas()[i] == null) {
                p.getAreas()[i] = inputString();
            }
        }
        return p;
    }
    
    private static void cadastra_nova_area(SetorEnsino ensino,int posicao_professor) throws IOException{
        System.out.println("Qual a nova área, professor?");
        String area = inputString();
        if (ensino.novaArea(posicao_professor,area)) {
            System.out.println("Área " + area + " cadastrada para o professor " + ensino.getProfessores()[posicao_professor].toString());
        } else {
            System.err.println("O limite de áreas foi atingido para o professor com siape " + ensino.getProfessores()[posicao_professor].getSiape());
        }
    }
    
    private static void cadastra_alteracao_nota(SetorEnsino ensino){
        String disciplina = "";
        String curso = "";
        String nome_aluno = "";
        float nova_nota = 0;
        try{
            System.out.println("Qual a disciplina?");
            disciplina = inputString();
            System.out.println("Qual o curso?");
            curso = inputString();
            System.out.println("Qual o nome do aluno?");
            nome_aluno = inputString();
            System.out.println("Qual a sua nova nota?");
            nova_nota = inputFloat();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
       

                    if (ensino.alterarNota(disciplina, curso, nome_aluno, nova_nota)) {
                        System.out.println("Nota "
                                + nova_nota
                                + " alterada para o aluno "
                                + nome_aluno
                                + " na disciplina "
                                + disciplina
                                + " do curso "
                                + curso
                                + ".");
                    } else {
                        System.err.println("Aluno "
                                + nome_aluno
                                + " do curso "
                                + curso
                                + " não foi encontrado. Ele não está matriculado na disciplina "
                                + disciplina);
                    }
    }
    
    private static boolean remover_area(SetorEnsino ensino, int pos_professor, String area) throws IOException{
         if (ensino.getProfessores() != null) {
            String areas[] = ensino.getProfessores()[pos_professor].getAreas();

            for (int i = 0; areas != null && i < areas.length; i++) {
                if (areas[i] != null && areas[i].equals(area)) {
                    areas[i] = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    
    ///////////////////////////// FUNÇÕES DISCIPLINA ///////////////////////////
    
    private static boolean cadastra_disciplina(SetorEnsino ensino, Curso c, Professor p) throws IOException {
        Disciplina d = cria_disciplina(p);

        return ensino.novaDisciplina(d, c);
    }
    
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
    
    private static String inputString() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        return input;
    }
    private static long inputLong() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Long input = Long.parseLong(reader.readLine());
        return input; 
    }
    private static int inputInt() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());
        return input; 
    }
    private static float inputFloat() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        float input = Float.parseFloat(reader.readLine());
        return input; 
    }
}
    

