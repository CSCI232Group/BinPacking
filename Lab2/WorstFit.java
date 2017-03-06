/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Quick;

public class WorstFit {
    public static Disk[] disks = new Disk[1];
    
    public static void main(String[] args) {
        disks[0] = new Disk(1);
        
//        insertFile(200000);
//        insertFile(1000000);
//        insertFile(300000);
//        insertFile(700000);
//        insertFile(300000);
//        insertFile(500000);
//        insertFile(300000);
//        insertFile(400000);
//        insertFile(1000000);

        int[] in = StdIn.readAllInts();
        
        for(int file : in){
            insertFile(file);
        }
        
        double sum = 0;
        int numDisks = 0;
        for(int i = 0; i < disks.length; i++) {
            sum += disks[i].getSpaceTaken();
            if(disks[i].getSpaceTaken() != 0) numDisks++;
        }
        System.out.println("Files sizes sum = " + sum/1000000.0 + " GB");
        System.out.println("Total Disks     = " + numDisks);
        
        
        Quick.sort(disks);
        
        disks = (Disk[]) reverse(disks);
        
        for(int i = 0; i < disks.length; i++) {
            disks[i].print();
        }
    }
    
    public static Disk[] reverse(Disk[] a){
        Disk[] temp = new Disk[a.length];
        for(int i = 0; i < a.length; i++){
            temp[i] = a[a.length - 1 - i];
        }
        return temp;
    }
    
    public static void insertFile(int file) {
        int i = 0;
        while (i < disks.length) {
            if (disks[i].addFile(file)) break;
            i++;
        }
        if (i == disks.length) {
            Disk[] newDisks = new Disk[disks.length * 2];
            int j;
            for (j = 0; j < disks.length; j++) {
                newDisks[j] = disks[j];
            }
            int temp = j;
            while(j < newDisks.length) {
                newDisks[j] = new Disk(j + 1);
                j++;
            }
            newDisks[temp].addFile(file);
            disks = newDisks;
        }
    }
}
