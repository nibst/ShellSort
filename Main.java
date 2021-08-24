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

        int arrSize = 0, i, j, k;
        int indexOfArrSize = 0;
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
        //lines1.size() são quantos arrays tem que fazer sorting na entrada1
        for (i = 0; i < lines1.size(); i++) {
            //indexOfArrSize é 0 no inicio, inicializado na declaracao
            arrSize = Integer.parseInt(tokens1[indexOfArrSize]);
            //preenche as listas com os numeros do arquivo de entrada
            for (j = indexOfArrSize + 1; j < indexOfArrSize + arrSize + 1; j++) {
                arr.add(Integer.parseInt(tokens1[j]));
                arr1.add(Integer.parseInt(tokens1[j]));
                arr2.add(Integer.parseInt(tokens1[j]));
            }
            indexOfArrSize = indexOfArrSize + arrSize + 1;

            sortWithShell.shellSort(arr, arrSize, printWriter1);
            sortWithKnuth.shellSort(arr1, arrSize, printWriter1);
            sortWithCiura.shellSort(arr2, arrSize, printWriter1);

            arr.clear();
            arr1.clear();
            arr2.clear();
            System.out.print("\n");
        }
        printWriter1.close();
        printWriter2.close();

    }
}
