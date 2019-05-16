/*
 * Classe auxiliar com funções para uso de arquivos.
 */
package aula5exemplos;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 *
 * @author coelho
 */
public class Arquivo {

    private static final String ARQUIVO_LOG = "log.txt";
    private static final String ARQUIVO_BINARIO_ALUNOS = "alunos.bin";
    private static final String ARQUIVO_BINARIO_ENSINO = "ensino.bin";

    private static final int TOTAL_ALUNOS = 1000;

    private static final String DIRETOR_ENSINO = "Pâmela Perini";
    private static final String COORDENADOR_ENSINO = "Vitor Valente";

    public static boolean escreve_log(String texto) {
        try {
            File f = new File(ARQUIVO_LOG);
            if (!f.exists()) {
                f.createNewFile();
            }
            try (FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw)) {
                String hora_data_atual = String.valueOf(Calendar.getInstance().getTime());

                pw.print(hora_data_atual + ": ");
                pw.println(texto);
                System.err.println("LOG: " + texto);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo " + ARQUIVO_LOG);
            return false;
        }
        return true;
    }

    public static boolean salva_ensino(SetorEnsino ensino) {
        try {
            if (ensino != null) {
                File f = new File(ARQUIVO_BINARIO_ENSINO);
                if (!f.exists()) {
                    f.createNewFile();
                }
                try (FileOutputStream fos = new FileOutputStream(f);
                        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(ensino);
                    System.out.println("Dados do Setor de Ensino salvos com sucesso: ");
                    System.out.println(ensino.toString());
                } catch (EOFException e) {
                }
            }
        } catch (IOException e) {
            Arquivo.escreve_log("Erro ao criar o arquivo " + ARQUIVO_BINARIO_ENSINO);
            e.printStackTrace();
        }
        return true;
    }

    public static boolean salva_alunos(Aluno[] alunos) {
        try {
            File f = new File(ARQUIVO_BINARIO_ALUNOS);
            if (!f.exists()) {
                f.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(alunos);
            }
            if (alunos != null && !array_vazio(alunos)) {
                System.out.println("Alunos salvos com sucesso: ");
                for (Aluno aluno : alunos) {
                    if (aluno != null) {
                        System.out.println(aluno.toString());
                    }
                }
            }
        } catch (IOException e) {
            Arquivo.escreve_log("Erro ao criar o arquivo " + ARQUIVO_BINARIO_ALUNOS);
        }
        return true;
    }

    public static Aluno[] obtem_alunos() {
        Aluno[] alunos = new Aluno[TOTAL_ALUNOS];
        try {
            File f = new File(ARQUIVO_BINARIO_ALUNOS);
            if (!f.exists()) {
                f.createNewFile();
            }
            try (FileInputStream fis = new FileInputStream(f);
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                alunos = (Aluno[]) ois.readObject();
            } catch (ClassNotFoundException ex) {
                Arquivo.escreve_log("Erro ao ler dados dos alunos salvos no arquivo "
                        + ARQUIVO_BINARIO_ALUNOS);
            } catch (EOFException e) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            Arquivo.escreve_log("Erro ao ler o arquivo " + ARQUIVO_BINARIO_ALUNOS);
        }
        return alunos;
    }

    public static SetorEnsino obtem_ensino() {
        SetorEnsino ensino = new SetorEnsino(DIRETOR_ENSINO, COORDENADOR_ENSINO);
        File f = null;

        try {
            f = new File(ARQUIVO_BINARIO_ENSINO);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Arquivo.escreve_log("Erro ao ler o arquivo " + ARQUIVO_BINARIO_ENSINO);
        }
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            ensino = (SetorEnsino) ois.readObject();

        } catch (ClassNotFoundException ex) {
            Arquivo.escreve_log(
                    "Erro ao ler dados do setor de ensino salvos no arquivo "
                    + ARQUIVO_BINARIO_ENSINO);
        } catch (EOFException e) {
        } catch (IOException ex) {
        }

        return ensino;
    }

    public static boolean array_vazio(Object[] arr) {
        for (Object arr1 : arr) {
            if (arr1 != null) {
                return false;
            }
        }
        return true;
    }
}
