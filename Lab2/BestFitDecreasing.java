/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;


public class BestFitDecreasing extends RedBlackBST {
    public static RedBlackBST disks = new RedBlackBST<>();
    private static int numDisks;
    
    public static void main(String[] args) {
//        insertFile(300000);
//        insertFile(400000);
//        insertFile(700000);
//        insertFile(1000000);
//        insertFile(200000);
//        insertFile(500000);
//        insertFile(600000);
//        insertFile(300000);

        int[] in = StdIn.readAllInts();
        double fileSum = 0;
        
        for(int i = 0; i < in.length; i++){
            insertFile(in[i]);
            fileSum += in[i];
        }
        
        System.out.println("Files sizes sum = " + fileSum/1000000.0 + " GB");
        System.out.println("Total Disks     = " + numDisks);
        
        if(numDisks <= 100){
            while(!disks.isEmpty()) {
                Disk printing = (Disk) disks.max();
                printing.print();
                disks.deleteMax();
            }
        }
    }
    
    public static void insertFile(int file) {
        int tempFile = 1000000 - file;
        Disk temp = new Disk(disks.size());
        Disk rebalanceDisk;
        temp.addFile(tempFile);
        if(disks.isEmpty()) {
            disks.put(temp, temp.getSpaceTaken());
            numDisks++;
        } else {
            Comparable ceiling = disks.ceiling(temp);
            Disk ceilDisk = (Disk) ceiling;
            if(ceilDisk == null) {
                //System.out.println("New Disk Created");
                Disk newDisk = new Disk(disks.size());
                numDisks++;
                newDisk.addFile(file);
                disks.put(newDisk, newDisk.getSpaceTaken());
            } else {
                rebalanceDisk = ceilDisk;
                //System.out.println("Rebalancing...");
                if(!rebalanceDisk.addFile(file)) System.out.println("Insert Failed. Fuck You.");
                disks = rebalance(disks, ceilDisk, rebalanceDisk);
            }
        }
    }
    
    public static RedBlackBST rebalance(RedBlackBST tree, Disk skip, Disk replace) {
        RedBlackBST temp = new RedBlackBST();
        while(!tree.isEmpty()) {
            if(tree.max() != skip) {
                Disk D = (Disk) tree.max();
                temp.put(D, D.getSpaceRemaining());
            } else {
                temp.put(replace, replace.getSpaceRemaining());
            }
            tree.deleteMax();
        }
        return temp;
    }
}
