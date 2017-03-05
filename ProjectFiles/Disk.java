package binpacking;

/**
 *
 * @author Ian
 */
public class Disk implements Comparable<Disk>  //????May be "extends" vs implements????
{
    private final double totalStorage;
    private double freeStorage;
    private Node firstFile;
    private Node currentFile;
    private static int ID = 0;
    
    public Disk()
    {        
        ID++; //Enumerates Disks as they are created; Int ID's from 0 up
        totalStorage = 1.0;//Total storage in Disk. Must convert ints accordingly!!!
        freeStorage = totalStorage;
        firstFile = null;
    }
    public boolean tryToFill(int dataInt)//Need to convert int to double
    {
        double dataFile = convertToInt(dataInt);//Conversion of int to double
        
        if(freeStorage - dataFile >= 0)
        {
            freeStorage -= dataFile;//Subtract dataFile from free space
        
            if(firstFile == null)//If this is the first dataFile:
            {                
                firstFile = new Node(dataFile, null); //Creates first Node
                currentFile = firstFile; //Sets currentFile to first and only Node
            }
            else
            {                
                currentFile.next = new Node(dataFile, null); //Set next                
                currentFile = currentFile.next; //Move up currentFile
            }
            return true;
        }
        return false;
    }
    public double convertToInt(int dataFile)//Converts int to double
    {
        return (double)dataFile / 1000000;
    }
    public void printStorage()
    {
        Node iter = firstFile;
        
        while(iter != null)
        {
            System.out.printf("%d\n", iter.file);
            
            //Move iter Node up. If next == null, iter will become null
            iter = iter.next;
        }
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
    @Override //This is how the Max Queue compares Disks!! Crucial to function
    public int compareTo(Disk that)//returns comparison integer
    {
        return Double.compare(this.freeStorage, that.freeStorage);
    }

}
