package binpacking;

/**
 *
 * @author Ian
 */
public class Disk implements Comparable<Disk> //May be "extends" vs implements
{
    int totalStorage;
    int usedStorage;
    //int[] fileArray;
    
    public Disk()
    {
        totalStorage = 1000000;
        usedStorage = totalStorage;
    }
    public void fill(int size)
    {
        if(totalStorage - usedStorage - size >= 0)
        {usedStorage += size;}
        //add file into disk record - array???
    }
    public void printStorage()
    {
        //for(file f : fileArray)
        //{
            //print files
        //}
    }

    @Override
    public int compareTo(Disk t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
