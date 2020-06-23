package econmikael;

import java.lang.Math;

public class Supply {
    
    private int c;
    private int d;
    
    public Supply()
    {
        this(0,0);
    }
    
    public Supply(int c, int d)
    {
        this.c = c;
        this.d = d;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setD(int d) {
        this.d = d;
    }
    
    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }
    
    //overloaded
    public int calculateQuantity(int price)
    {
        return price - this.c / this.d;
    }
    
    public int calculatePrice(int quantity)
    {
        return (c + quantity *d);
    }
    
    public String eqnString()
    {
        return "P=" + c + "-" + c + "*Qs";
    }
}
