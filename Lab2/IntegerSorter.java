/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Quick;


public class IntegerSorter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        //File input = new File("C:\\Users\\Austin\\Documents\\Spring Junior\\Data Structures\\Labs\\Lab2\\IntegerSorter\\input20.txt");
        //Scanner in = new Scanner(input);
        

        //Integer[] files = new Integer[1000];
        int[] in = StdIn.readAllInts();
        Integer[] files = new Integer[in.length];
        
        // convert int[] to Integer[]
        for(int i = 0; i < in.length; i++){
            files[i] = in[i];            
        }
        
        Quick.sort(files);
        
        files = reverse(files);
        
        
        int i = 0;
        while (i < files.length && files[i] != null) {
            StdOut.println(files[i++]);
        }
    }
    
    public static Integer[] reverse(Integer[] a){
        Integer[] temp = new Integer[a.length];
        for(int i = 0; i < a.length; i++){
            temp[i] = a[a.length - 1 - i];
        }
        return temp;
    }
    
}
