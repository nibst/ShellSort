package com.eu;

public class ShellSort {

    //qual das sequencias fazer o shellsort
    private int sequence;
    final int SHELL = 1;
    final int KNUTH = 2;
    final int CIURA = 3;
    int[] SHELLsequence = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576};
    int[] KNUTHsequence = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    int[] CIURAsequence = {1, 4, 10, 23, 57, 132, 301, 701, 1577, 3548, 7983, 17961, 40412, 90927, 204585, 460316, 1035711};

    public ShellSort(int seq){
        //sequencia que sera usada para o shellsort
        this.sequence = seq;
    }
    public int[] getSequence (){
        //nao vai sair desse switch pois eu que vou dar os valores de sequence, nao o usuario.
        //nao tem problema nao ter return no fim, mas vou por return SHELLsequence no fim so pra nao ficar warning
        switch(sequence) {
            case SHELL:
                return SHELLsequence;
            case KNUTH:
                return KNUTHsequence;
            case CIURA:
                return CIURAsequence;
        }
        return SHELLsequence;
    }
    //escolhe a posição da sequencia dada que deve ser o primeiro divisor do array que será ordenado no sort()
    //Ex: arrSize 16 , na SHELLsequence. O resultado deve ser a posição em que está o 8, ou seja, posicao 3.
    public int chooseArrayFirstDivider(int arrSize){
        int i = 0;
        //nao tem caso fora desses 3 pq eu que vou dar pra essa funcao o valor de sequence nao o usuario.
        switch (sequence) {
            case SHELL:
                //a sequencia nao vai acabar antes de achar um numero, pois ja sei o tamanho maximo que vai ter um array
                //e o numero maximo de cada sequencia ultrapassa o numero maximo que vai ter em um array
                while (arrSize > SHELLsequence[i]) {
                    i++;
                }
                break;
            case KNUTH:
                //a sequencia nao vai acabar antes de achar um numero, pois ja sei o tamanho maximo que vai ter um array
                //e o numero maximo de cada sequencia ultrapassa o numero maximo que vai ter em um array
                while (arrSize > KNUTHsequence[i]) {
                    i++;
                }
                break;
            case CIURA:
                //a sequencia nao vai acabar antes de achar um numero, pois ja sei o tamanho maximo que vai ter um array
                //e o numero maximo de cada sequencia ultrapassa o numero maximo que vai ter em um array
                while (arrSize > CIURAsequence[i]) {
                    i++;
                }
                break;
            //i-1 pq nao quero o primeiro elemento da sequencia q seja maior q arrSize
            //quero o ultimo elemento da sequencia q seja menor que arrSize

        }
            return i - 1;
    }

    public void insDiretaShellsort(int arr[],int arrSize,int gap,int startingCell){
        int i,j;
        int chave;

        for (i = (startingCell+gap); i<arrSize; i += gap){
            chave = arr[i];
            j = (i-gap);
            while((j>=0) && (arr[j]>chave)){
                arr[j+gap] = arr[j];
                j = j-gap;
            }
            arr[j+gap] = chave;
        }
    }

    public int[] shellSort(int arr[], int arrSize){
        int gapIndex,gap,i;
        int seq[]= getSequence();
        gapIndex = chooseArrayFirstDivider(arrSize);
        for(;gapIndex>=0;gapIndex--){
            gap = seq[gapIndex];
            for (i = 0; i < gap ; i++) {
                insDiretaShellsort(arr,arrSize,gap,i);
            }
        }
        return arr;
    }

}