package solvemethods.simpleiteration;

/**
 * Created by vlad on 23.2.17.
 */
public class SimpleIteration {
    private static double f(double x){
        //System.out.println(x);
        return (5-Math.pow(x,7))/6;
    }
    private static double function(double x){
        return x*x*x+3*x-1;
    }

    public static void main(String[] args) {
        double approximation = 0.2;
        double next = (5-Math.pow(approximation,7))/6;
      //  System.out.println(next);
       // while(Math.abs(approximation)-Math.abs(next)>1e-10){
        for (int i = 0; Math.abs(approximation-next)>1e-10; i++) {
            System.out.println(next);
            approximation = next;
            next =  (5-Math.pow(approximation,7))/6;
            System.out.println(i);
        }


        //}
    }


}
