
package assignment1;

public class Airport {

    private int xcoord;
    private int ycoord;
    private int fees;
    public Airport(int xcoord, int ycoord, int fees){
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.fees = fees;
    }
    public int getFees(){
        return fees;
    }
    public static int getDistance(Airport i, Airport j){
        int deltax = Math.abs(i.xcoord - j.xcoord);
        int deltay = Math.abs(i.ycoord - j.ycoord);
        double dist = Math.sqrt(Math.pow(deltax, 2) + Math.pow(deltay, 2));
        int dist_rounded = (int)dist;
        if(dist != dist_rounded)
            dist_rounded++;
        return dist_rounded;
    }
}

