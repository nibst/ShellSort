package com.eu;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        final int SHELL = 0;
        final int KNUTH = 1;
        final int CIURA = 2;


        ShellSort sortWithShell = new ShellSort(SHELL);
        ShellSort sortWithKnuth = new ShellSort(KNUTH);
        ShellSort sortWithCiura = new ShellSort(CIURA);
        Path path1 = null;
        Path path2 = null;
        List<String> lines1 = null;
        List<String> lines2 = null;
        List<Integer> arr = new ArrayList<>();
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        File saida1 = null;
        File saida2 = null;
        int print_ok;//flag para printar durante shell sort ou nao

        try {
            saida1 = new File("./saida1.txt");
            saida2 = new File("./saida2.txt");
            if (!saida1.exists()) {
                saida1.createNewFile();
                System.out.println("File " + "criada :" + saida1.getName());
            }
            if (!(saida2.exists())) {
                saida2.createNewFile();
                System.out.println("File " + "criada: " + saida2.getName());
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Erro em criar file");
            System.exit(1);
        }

        FileWriter fileWriter1 = null;
        FileWriter fileWriter2 = null;
        try {
            fileWriter1 = new FileWriter(saida1.getName());
            fileWriter2 = new FileWriter(saida2.getName());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        PrintWriter printWriter1 = new PrintWriter(fileWriter1);
        PrintWriter printWriter2 = new PrintWriter(fileWriter2);


        //pega os paths
        try {
            //path da primeira entrada
            path1 = Paths.get("./" + args[0]);
            //path da segunda entrada
            path2 = Paths.get("./" + args[1]);
        } catch (FileSystemNotFoundException e) {
            LOG.log(Level.SEVERE, "Coloque duas entradas");
            System.exit(1);
        }
        try {
            lines1 = Files.readAllLines(path1, Charset.defaultCharset());
            lines2 = Files.readAllLines(path2, Charset.defaultCharset());
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Erro na leitura/abertura dos arquivos");
            System.exit(1);
        }

        String entrada1 = String.join(" ", lines1);
        String entrada2 = String.join(" ", lines2);
        //Tokeniza tirando espaçoes em branco e quebra de linha
        String[] tokens1 = entrada1.split("\\s+");
        String[] tokens2 = entrada2.split("\\s+");


        //PARTE 1 DO EXERCICIO-FAZ O SORT E CRIA FILE saida1.txt (cada linha tem um passo do sorting)

        print_ok = 1;//flag ativada para printar as etapas do shell sort
        extracted(sortWithShell, sortWithKnuth, sortWithCiura, lines1, arr, arr1, arr2, print_ok, printWriter1, tokens1);
        printWriter1.close();

        //PARTE 2 DO EXERCICIO - MOSTRA O TEMPO QUE PASSOU EM CADA SHELL SORT USANDO DIFERENTES SEQUENCIAS
        print_ok = 0;
        extracted(sortWithShell, sortWithKnuth, sortWithCiura, lines2, arr, arr1, arr2, print_ok, printWriter2, tokens2);
        printWriter2.close();

    }

    private static void extracted(ShellSort sortWithShell, ShellSort sortWithKnuth, ShellSort sortWithCiura, List<String> lines, List<Integer> arr, List<Integer> arr1, List<Integer> arr2, int print_ok, PrintWriter printWriter, String[] tokens) {
        int arrSize;
        int j;
        int i;
        int indexOfArrSize = 0;
        //lines1.size() são quantos arrays tem que fazer sorting na entrada
        for (i = 0; i < lines.size(); i++) {
            //indexOfArrSize é 0 no inicio, inicializado na declaracao
            arrSize = Integer.parseInt(tokens[indexOfArrSize]);
            //preenche as listas com os numeros do arquivo de entrada
            for (j = indexOfArrSize + 1; j < indexOfArrSize + arrSize + 1; j++) {
                arr.add(Integer.parseInt(tokens[j]));
                arr1.add(Integer.parseInt(tokens[j]));
                arr2.add(Integer.parseInt(tokens[j]));
            }
            indexOfArrSize = indexOfArrSize + arrSize + 1;

            sortWithShell.shellSort(arr, arrSize, printWriter, print_ok);
            sortWithKnuth.shellSort(arr1, arrSize, printWriter, print_ok);
            sortWithCiura.shellSort(arr2, arrSize, printWriter, print_ok);

            arr.clear();
            arr1.clear();
            arr2.clear();
            System.out.print("\n");
        }
    }
}
