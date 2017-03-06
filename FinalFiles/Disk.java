package binpacking;

public class Disk implements Comparable<Disk>{
    private int spaceRemaining = 1000000;
    private int spaceTaken = 0;
    private int files[] = new int[1];
    int id;
    
    Disk(int id) {
        this.id = id;
    }
    
    public int getSpaceTaken() {
        return spaceTaken;
    }
    
    public int getSpaceRemaining() {
        return spaceRemaining;
    }
    
    private boolean hasSpace(int insertedFile) {
        return spaceRemaining >= insertedFile;
    }
    
    public boolean addFile(int insertedFile) {
        if(hasSpace(insertedFile)) {            
            int i = 0;
            while(i < files.length) {
                if(files[i] == 0) {
                    files[i] = insertedFile;
                    break;
                }
                i++;
            }
            if(i == files.length) {
                int biggerFiles[] = new int[files.length * 2];
                int j;
                for(j = 0; j < files.length; j++) {
                    biggerFiles[j] = files[j];
                }         
                files = biggerFiles;
                files[j] = insertedFile;
            }            
            spaceRemaining -= insertedFile;
            spaceTaken += insertedFile;
            return true;
            } else {
        return false;
        }
    }
    
    public void print() {
        if(spaceTaken != 0) {
            System.out.print(id + " " + spaceRemaining + ": ");
            for(int i = 0; i < files.length; i++) {
                if(files[i] != 0) {
                    System.out.print(files[i] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public int compareTo(Disk that) {
        if (this.spaceRemaining < that.spaceRemaining){
            return -1;
        } else if (this.spaceRemaining > that.spaceRemaining) {
            return 1;
        } else {
            return 0;
        }
    }
}