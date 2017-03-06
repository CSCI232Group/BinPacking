package binpacking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class WorstFit
{   
	private int d=0;
    private double[] data;
    private int size;
    private ArrayList<Disk> disks=new ArrayList<Disk>();
    
    public WorstFit()
    {                    
    	
    	disks.add(new Disk());
    	disks.get(d).setID(d);
       
    }
    
    public void worstFitSort(double a)
    {        
    	if(!disks.get(d).tryToFill(a)){
    		d++;
    		disks.add(new Disk());
    		disks.get(d).tryToFill(a);
    		disks.get(d).setID(d);
    	}
        

    }
    public void print(){
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
    		while(t<1){
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
    				disks.remove(k);
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
            while(scan.hasNext()){
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
           System.out.println("file sizes sum = "+total/1000000+" GB");
           wf.print();
           
        }
        
        
    }
}
