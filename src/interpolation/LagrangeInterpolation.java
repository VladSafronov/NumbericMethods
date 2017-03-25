package interpolation;


public class LagrangeInterpolation {

    static double table[][] = {
            {-1.1,-0.2,1.3},
            {3.2,4.1,-2.2}
    };
    public static void main(String[] args) {

        Polynom result = new Polynom(0d,0);
        for (int j = 0; j <table[0].length ; j++) {

            Polynom up = new Polynom(1d,0);
            double down = 1;
            for (int i = 0; i < table[0].length; i++) {
                if(i==j) continue;
                up = up.times(getUpperPolynom(i));
                down *= getDownValue(i,j);
            }
            up.multiply(table[1][j]/down);
            result = result.plus(up);
        }
        System.out.println(result);
  //      System.out.println(result);
    }

    public static Polynom getUpperPolynom( int j){
        Polynom one = new Polynom(1d,1);
        Polynom two = new Polynom(-table[0][j],0);
        return one.plus(two);
    }
    public static Double getDownValue(int j,int i){
        if (i == j) return 1d;
        return table[0][i]-table[0][j];
    }

}
