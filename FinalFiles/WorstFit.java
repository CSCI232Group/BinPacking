package binpacking;

import java.io.File;                                                //Directories that we will be using in this file
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author Ian Hecker, Michael Hewitt, Jake Yakowich
 * CSCI 232
 * LAB 2
 */
public class WorstFit 
{   
    private int d=0;
    private double[] data;
    private int size;
    private ArrayList<Disk> disks=new ArrayList<Disk>();                    //arraylist of disks
    
    public WorstFit()
    {                    
    	
    	disks.add(new Disk());                                              //initialize disks and add first disk
    	disks.get(d).setID(d);
       
    }
    
    public void worstFitSort(double a)
    {                                                                       //adding next file to the next available disk
    	if(!disks.get(d).tryToFill(a)){
    		d++;
    		disks.add(new Disk());
    		disks.get(d).tryToFill(a);
    		disks.get(d).setID(d);
    	}
        
    }
    public void print(){                                                    //printing disks in correct order
    	System.out.println("total disks = "+disks.size());
    	    while(disks.size()>0){
    		Disk greatest=disks.get(0);
    		for(int i=0;i<disks.size();i++){
    			if(greatest.storage()<disks.get(i).storage()){
    				greatest=disks.get(i);
    			}
    		}
    		System.out.print(greatest.getID());
    		double t=(double)greatest.storage()/1000000.0;
    		int space=0;
    		while(t<1){                                                    //printing the correct number of spaces for the length of int
    			t=t*10;
    			space++;
    		}
    		for(int j=0;j<space;j++){
    			System.out.print(" ");
    		}
    		System.out.print(greatest.storage()+": ");
    		greatest.printStorage();
    		for(int k=0;k<disks.size(); k++){
    			if(greatest.getID()==disks.get(k).getID()){
    				disks.remove(k);                                        //remove the greatest storage space disk from list after printing it
    			}
    		}
    		disks.trimToSize();
    	}
    }
    public static void main(String[] args) throws IOException
    {                    
       WorstFit wf = new WorstFit();
        ArrayList<Integer> input=new ArrayList<Integer>();
        int total=0;
        if (args.length > 0) {
            FileInputStream is = null;
            try {
                is = new FileInputStream(new File(args[0]));
            } catch (Exception ex) {
                System.err.println(ex);
            }
            System.setIn(is);
        
            Scanner scan=new Scanner(is);
            while(scan.hasNext()){                                              //reading in values from text file
            input.add(scan.nextInt());
            }
            double[] list=new double[input.size()];
            for (int i=0;i<input.size();i++){
            	total=total+input.get(i);
            	list[i]=(double)input.get(i)/1000000;
            }
            
            for (int i=0;i<input.size();i++){
            	wf.worstFitSort(list[i]);
            }
            wf.data=list;
           wf.size=input.size();
           System.out.println("WORST FIT");                                     //DEBUGGING
           System.out.println("file sizes sum = "+total/1000000+" GB");
           wf.print();
           
        }                
    } 
    public class Disk implements Comparable<Disk>                               //????May be "extends" vs implements????
    {
        private final double totalStorage;
        private double freeStorage;
        private Node firstFile;
        private Node currentFile;
        private int ID = 0;

        public Disk()
        {        
            setID(getID() + 1);                                                 //Enumerates Disks as they are created; Int ID's from 0 up
            totalStorage = 1.0;                                                 //Total storage in Disk. Must convert ints accordingly!!!
            freeStorage = totalStorage;
            firstFile = null;
        }
        public boolean tryToFill(double dataInt)                                //Need to convert int to double
        {
            double dataFile = dataInt;                                          //Conversion of int to double

            if(freeStorage - dataFile >= 0)
            {
                freeStorage -= dataFile;                                         //Subtract dataFile from free space

                if(firstFile == null)                                           //If this is the first dataFile:
                {                
                    firstFile = new Node(dataFile, null);                    //Creates first Node
                    currentFile = firstFile;                                     //Sets currentFile to first and only Node
                }
                else
                {                
                    currentFile.next = new Node(dataFile, null);                //Set next                
                    currentFile = currentFile.next;                             //Move up currentFile
                }
                return true;
            }
            return false;
        }
        public double convertToInt(int dataFile)                                 //Converts int to double
        {
            return (double)dataFile / 1000000;
        }
        public int storage(){
            Node iter = firstFile;
            int total=0;
            while(iter != null)
            {

                total=total+((int)(iter.file*1000000));

                                                                                //Move iter Node up. If next == null, iter will become null
                iter = iter.next;
            }
            return 1000000-total;
        }
        public void printStorage()
        {
            Node iter = firstFile;
            while(iter != null)
            {

                System.out.print((int)(iter.file*1000000)+", ");

                                                                              //Move iter Node up. If next == null, iter will become null
                iter = iter.next;
            }
            System.out.println();
        }
        class Node
        {
            double file;
            Node next;

            public Node(double file, Node next)
            {
                this.file = file;
                this.next = next;
            }
        }
        @Override                                                           //This is how the Max Queue compares Disks!! Crucial to function
        public int compareTo(Disk that)                                     //returns comparison integer
        {
            return Double.compare(this.freeStorage, that.freeStorage);
        }
            public int getID() {
                    return ID;
            }
            public void setID(int iD) {
                    ID = iD;
            }

    }
}
