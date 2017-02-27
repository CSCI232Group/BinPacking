package binpacking;

/**
 *
 * @author Ian
 */
public class Disk implements Comparable<Disk>
{
    int totalStorage;
    int usedStorage;
    
    public Disk()
    {
        totalStorage = 1000000;
        usedStorage = totalStorage;
    }
    public void fill(int size)
    {
        if(totalStorage - usedStorage - size >= 0)
        {usedStorage += size;}
    }

    @Override
    public int compareTo(Disk t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
