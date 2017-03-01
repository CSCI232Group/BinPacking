package binpacking;

/**
 *
 * @author Ian
 */
public class IntegerSorter
{
  //Do we need an integer sorter? Why not use another MaxPQ? It would be logN 
  //time to add elements. log(100,000) = 5. So adding 100,000 elements is >= 5 seconds-ish
  //Considering the MaxPQ handled the particles in Lab1 very well, maybe we can use
  //the MaxPQ to also sort the integers
  
  //!!!!!!!!!!!!!!!!!!
  //Actually, using QuickSort and an ArrayList would be easier to implement than using MaxPQ and
  //and creating an object for each integer. Then when we add a dataFile to a Disk, we can delete
  //it from the ArrayList or:
  //
  //int currentItem = intList.first();
  //**add dataFile to Disk**
  //currentItem = currentItem.next();
  //!!!!!!!!!!!!!!!!!!
}
public static void qsort(int[] a, int si, int ei){

    //base case
    if(ei<=si || si>=ei){}


    else{ 
        int pivot = a[si]; 
        int length = ei - si + 1; 
        int i = si+1; int tmp; 

        //partition array 
        for(int j = si+1; j<length; j++){
            if(pivot > a[j]){
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
        qsort(a, 0, i-2); 
        qsort(a, i, a.length-1); 
    }
}
