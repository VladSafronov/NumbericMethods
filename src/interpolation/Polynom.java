package interpolation;

public class Polynom {
    public final static Polynom ZERO = new Polynom(0.0, 0);

    private Double[] coef;   // coefficients
    private int deg;              // degree of polynomial (0 for the zero polynomial)

    // a * x^b
    public Polynom(Double a, int b) {
        coef = new Double[b+1];
        for (int i = 0; i < b; i++)
            coef[i] = 0.0;
        coef[b] = a;
        deg = degree();
    }


    // return the degree of this polynomial (0 for the zero polynomial)
    public int degree() {
        int d = 0;
        for (int i = 0; i < coef.length; i++)
            if   (coef[i].compareTo(0.0) != 0) d = i;
        return d;
    }

    // return c = a + b
    public Polynom plus(Polynom b) {
        Polynom a = this;
        Polynom c = new Polynom(0.0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] = c.coef[i]+a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] = c.coef[i]+b.coef[i];
        c.deg = c.degree();
        return c;
    }

    // return c = a - b
    public Polynom minus(Polynom b) {
        Polynom a = this;
        Polynom c = new Polynom(0.0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] = c.coef[i] + (a.coef[i]);
        for (int i = 0; i <= b.deg; i++) c.coef[i] = c.coef[i] -(b.coef[i]);
        c.deg = c.degree();
        return c;
    }

    // return (a * b)
    public Polynom times(Polynom b) {
        Polynom a = this;
        Polynom c = new Polynom(0.0, a.deg + b.deg);
        for (int i = 0; i <= a.deg; i++)
            for (int j = 0; j <= b.deg; j++)
                c.coef[i+j] = c.coef[i+j]+(a.coef[i]*b.coef[j]);
        c.deg = c.degree();
        return c;
    }

    // return (a / b)
    public Polynom divides(Polynom b) {
        Polynom a = this;
        if ((b.deg == 0) && (b.coef[0].compareTo(0.0) == 0))
            throw new RuntimeException("Divide by zero polynomial");

        if (a.deg < b.deg) return ZERO;

        Double coefficient = a.coef[a.deg]/b.coef[b.deg];
        int exponent = a.deg - b.deg;
        Polynom c = new Polynom(coefficient, exponent);
        return c.plus( (a.minus(b.times(c)).divides(b)) );
    }

    // truncate to degree d
    public Polynom truncate(int d) {
        Polynom p = new Polynom(0.0, d);
        for (int i = 0; i <= d; i++)
            p.coef[i] = coef[i];
        p.deg = p.degree();
        return p;
    }


    // convert to string representation
    public String toString() {
        if (deg ==  0) return "" + coef[0];
        if (deg ==  1) return coef[1] + " x + " + coef[0];
        String s = coef[deg] + " x^" + deg;
        for (int i = deg-1; i >= 0; i--) {
            int cmp = coef[i].compareTo(0.0);
            if      (cmp == 0) continue;
            else if (cmp  > 0) s = s + " + " + ( coef[i]);
            else if (cmp  < 0) s = s + " - " + (Math.abs(coef[i]));
            if      (i == 1) s = s + " x";
            else if (i >  1) s = s + " x^" + i;
        }
        return s;
    }

    public void multiply(double d){
        for (int i = 0; i < coef.length; i++) {
            coef[i] *= d;
        }
    }

    // test client
    public static void main(String[] args) {
        Double half  = 1.0/2.0;
        Double three = 3.0;

        Polynom p = new Polynom(half,  1);
        Polynom q = new Polynom(three, 2);

        Polynom v = q.divides(p);
        //interpolation.Polynom w = v.times(q);

        System.out.println(p);
        System.out.println(q);
        System.out.println(v);

    }

}