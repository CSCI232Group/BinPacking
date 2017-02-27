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
