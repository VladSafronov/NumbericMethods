package solvemethods.newton;

/**
 * Created by vlad on 24.2.17.
 */
public class NewtonsMethod {
    private static double f(double x){
        return x*x*Math.log10(x)-1;
    }

    private static double df(double x){
        return 2*x*Math.log10(x)+x/Math.log(10);
    }


    public static void main(String[] args) {
        double approximation = 2;
        double next = approximation - f(approximation)/df(approximation);
        while(Math.abs(approximation-next)>1e-10){
            System.out.println(next);
            approximation = next;
            next = approximation - f(approximation)/df(approximation);
        }
        System.out.println("f("+next+") = " + f(next));
    }
}
