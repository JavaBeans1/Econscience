package econmikael;

import java.lang.Math;

public class Demand {

    private int a;
    private int b;
    private int elasticity; 
    
    public Demand()
    {
        this(0,0);
    }
    
    public Demand(int a, int b)
    {
        this.a = a;
        this.b = b;
    }
    
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public void setA(int a) {
        this.a = a;
    }
    public void setB(int b) {
        this.b = b;
    }
    //overloaded
    public double getQuantity(double price)
    {
        return this.a - price / this.b;
    }
    //overloaded
    public double getPrice(int quantity)
    {
        return (double)(this.a - this.b * quantity);
    }
    
    public static int calculateQuantity(int a, int b, int price)
    {
        return (a - price)/b;
    }
    
    public static double calculatePrice(int a, int b, int quantity)
    {
        return a - b * quantity;
    }
    
    public String eqnString()
    {
        return "P=" + a + "-" + b + "*Qd";
    }
}
