package com.eu;

import jdk.swing.interop.SwingInterOpUtils;

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
        Path path1 = null;
        Path path2 = null;
        List<String> lines1 = null;
        List<String> lines2 = null;
        List<Integer> arr = new ArrayList<>();

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
            e.printStackTrace();
        }

        String entrada1 = String.join(" ", lines1);
        String entrada2 = String.join(" ", lines2);
        //Tokeniza tirando espaçoes em branco e quebra de linha
        String[] tokens1 = entrada1.split("\\s+");
        String[] tokens2 = entrada2.split("\\s+");


        //lines1.size() são quantos arrays tem que fazer sorting na entrada1
        for (i = 0; i < lines1.size(); i++) {

            arrSize = Integer.parseInt(tokens1[indexOfArrSize]);

            for (j = indexOfArrSize + 1; j < indexOfArrSize + arrSize+1; j++) {
                arr.add(Integer.parseInt(tokens1[j]));
            }
            indexOfArrSize = indexOfArrSize + arrSize + 1;
            arr = sortWithShell.shellSort(arr, arrSize);

            for (k = 0; k < arrSize; k++) {
                System.out.print(arr.get(k) + " ");
            }
            arr.clear();
            System.out.print("\n");
        }

    }
}

