package binpacking;

/**
 *
 * @author Ian
 */
public class Disk implements Comparable<Disk> //????May be "extends" vs implements????
{
    int totalStorage;
    int usedStorage;
    Node firstFile;
    Node currentFile;
    
    public Disk()
    {        
        totalStorage = 1000000;
        usedStorage = 0;
        firstFile = null;        
    }
    public void fill(int dataFile)
    {
        if(usedStorage + dataFile <= totalStorage)
        {
            usedStorage += dataFile;//add dataFile to used space
        
            if(firstFile == null)//If this is the first dataFile
            {
                firstFile = new Node(dataFile, null); //Creates first Node
                currentFile = firstFile; //Sets currentFile to first and only Node
            }
            else
            {
               currentFile.next = new Node(dataFile, null); //Set next
               currentFile = currentFile.next; //Move up currentFile
            }
        }
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
        int file;
        Node next;
        
        public Node(int file, Node next)
        {
            this.file = file;
            this.next = next;
        }
    }

    @Override
    public int compareTo(Disk t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
