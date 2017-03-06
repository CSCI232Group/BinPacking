package binpacking;

public class IntegerSorter
{
	public IntegerSorter(){
		
	}
 public double[] qsort(double[] a, int si, int ei){
    //base case
    if(ei<=si || si>=ei){}

    else{ 
        double pivot = a[si]; 
        int i = si+1; 
        double tmp; 

        //partition array 
        for(int j = si+1; j<= ei; j++){
            if(pivot < a[j]){
                tmp = a[j]; 
                a[j] = a[i]; 
                a[i] = tmp; 

                i++; 
            }
        }

        //put pivot in right position
        a[si] = a[i-1]; 
        a[i-1] = pivot; 

        //call qsort on right and left sides of pivot
        qsort(a, si, i-2); 
        qsort(a, i, ei); 
        return a;
    }
    return a;
 }
 }
