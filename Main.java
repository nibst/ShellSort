package com.eu;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        final int SHELL = 1;
        final int KNUTH = 2;
        final int CIURA = 3;
        int[] arr = {8, 2, 10, 11, 3, 9, 9, 15, 4, 14, 9, 6, 13, 3, 4, 8 };
	    ShellSort sortWithShell = new ShellSort(CIURA);
        int[] arr2 = sortWithShell.shellSort(arr,arr.length);

        //Reading File
        Path valuesPath = Paths.get("./" + args[0]);

        try {
            List<String> lines = Files.readAllLines(valuesPath, Charset.defaultCharset());

            for (String line : lines) {     //print lines (or do whatever you need)
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //OPEN FILE--------------------------------------------
        /*if(args.length == 0) {
            System.out.println("File name not specified.");
            System.exit(1);
        }
        try {
            File file1 = new File(args[0]);
            Scanner input1 = new Scanner(file1);
            File file2 = new File(args[1]);
            Scanner input2 = new Scanner(file2);
        } catch (IOException ioException) {
            System.err.println("Cannot open file.");
            System.exit(1);
        }
        //-----------------------------------------------------

        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr2[i] + " ");*/
        }

}
