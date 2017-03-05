package binpacking;

import java.util.Iterator;

/**
 *
 * @author Ian
 */
public class WorstFit
{       
    private int[] dataArray;
    private MaxPQ<Disk> maxPQ;
    
    public WorstFit()
    {                    
        int[] tempArray = {300000, 400000, 100000, 199999};        
        this.dataArray = tempArray;
    }
    
    public void worstFitSort(int[] array)
    {        
        maxPQ = new MaxPQ<Disk>();//Initialize maxPQ
        
        maxPQ.insert(new Disk());//Add First Disk
                
        /* For all dataFiles we have, try to add to disk */
        for(int dataFile : array)
        {
            /* Try to add dataFile. If dataFile does not fit: */
            if(maxPQ.max().tryToFill(dataFile) == false)
            {                
                Iterator heapIter = maxPQ.iterator();
                //For every disk in maxPQ
                {
                    if(maxPQ[d] == null)//If out of Disks in maxPQ
                    {
                        Disk newDisk = new Disk(); //Create new Disk
                        newDisk.tryToFill(dataFile); //Put dataFile on new Disk
                        
                        maxPQ.insert(newDisk);//Insert new Disk into maxPQ
                    }
                    
                    if(d.tryToFill(dataFile) == true)//Attempt to put dataFile onto disk d. If placed:
                    break;
                }
            }
        }
        
        //disk.printStorage();        
               
        
        /* Create initial Disk
         * Place Disk into PQ
         * Place data in disk
         * 
         * IF (next dataFile fits onto Disk)  {add}
         * ELSE IF (dataFile fits on any other Disk in PQ)  {add}
         * ELSE {create new disk, add dataFile onto Disk_2}
         * End loop once dataFile inputs have all been placed
         * 
         * //Maybe we can put integers into a Priortiy Queue as well? 
         * //Then we dont need an IntegerSorter.java and can remove an int from the intPQ
         * //when it has been placed
         */
    }
    public static void main(String[] args) throws IOException
    {                    
       //WorstFit wf = new WorstFit();
       // wf.worstFitSort(wf.dataArray);
        ArrayList<Integer> input=new ArrayList<Integer>();
        if (args.length > 0) {
            FileInputStream is = null;
            try {
                is = new FileInputStream(new File(args[0]));
            } catch (Exception ex) {
                System.err.println(ex);
            }
            System.setIn(is);
        
            Scanner scan=new Scanner(is);
            while(scan.hasNext()){
            input.add(scan.nextInt());
            }
            double[] list=new double[input.size()];
            for (int i=0;i<input.size();i++){
            	list[i]=(double)input.get(i)/1000000;
            }
            IntegerSorter sort=new IntegerSorter();
            list=sort.qsort(list, 0, input.size()-1);
            for (int i=0;i<input.size();i++){
            	System.out.println(list[i]);
            }
            
           
        }
        
        
    }
}
