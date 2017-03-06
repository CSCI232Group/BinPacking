package binpacking;                                                 //Project folder we are working on
                                                                    //We did get help from others to get this class working
import edu.princeton.cs.algs4.RedBlackBST;                          //Directories where we are getting information from for later on in the Project
import edu.princeton.cs.algs4.StdIn;

public class BestFitDecreasing extends RedBlackBST {                //Class BestFitDecreasing that gets the Least amount of disks and packs the disks for optimal space 
    public static RedBlackBST disks = new RedBlackBST<>();          //Creating a new object 
    private static int numDisks;                                    //Declaring numDisk a variable
    
    public static void main(String[] args) {                        

        int[] in = StdIn.readAllInts();                             
        double fileSum = 0;
        
        for(int i = 0; i < in.length; i++){
            insertFile(in[i]);
            fileSum += in[i];
        }
        
        System.out.println("BEST FIT");                                         //DEBUGGING for the Command Prompt
        System.out.println("Files sizes sum = " + fileSum/1000000.0 + " GB");   //Prints out the File size 
        System.out.println("Total Disks     = " + numDisks);                    //Prints our the number of Disks
        
        if(numDisks <= 100){                                                    //
            while(!disks.isEmpty()) {
                Disk printing = (Disk) disks.max();
                printing.print();
                disks.deleteMax();
            }
        }
    }    
    public static void insertFile(int file) {                      //The section is the ceiling. The ceiling allows the code to find the best fit for the variable that it is given.
        int tempFile = 1000000 - file;                             // In our case the variable will be put into a disk that is the best fit. If it can't be but into a disk then
        Disk temp = new Disk(disks.size());                        //It will be put into null, where we will create a new disk for that varible to be put into.
        Disk rebalanceDisk;
        temp.addFile(tempFile);
        if(disks.isEmpty()) {
            disks.put(temp, temp.getSpaceTaken());
            numDisks++;
        } else {
            Comparable ceiling = disks.ceiling(temp);
            Disk ceilDisk = (Disk) ceiling;
            if(ceilDisk == null) {                
                Disk newDisk = new Disk(disks.size());
                numDisks++;
                newDisk.addFile(file);
                disks.put(newDisk, newDisk.getSpaceTaken());
            } else {
                rebalanceDisk = ceilDisk;                
                if(!rebalanceDisk.addFile(file));
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
