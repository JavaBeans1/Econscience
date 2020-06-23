package econmikael;

import java.lang.Math;

public class Equilibrium {
    
    private int a;
    private int b;
    private int c;
    private int d;
    
    Demand demand;
    Supply supply;
    
    private int equilibriumQty;
    private double equilibriumPr;
    
    private double efficiency = 100.0;
    private double imexPr;
    private double gainsQty;
    private double gains; 
    
    public Equilibrium()
    {   
        this(0,0,0,0);
    }
    
    public Equilibrium(Demand demand, Supply supply)
    {
        this.a = demand.getA();
        this.b = demand.getB();
        this.c = supply.getC();
        this.d = supply.getD();
        
        demand = new Demand(a,b);
        supply = new Supply(c,d);
    }
    
    public Equilibrium(int a, int b, int c, int d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int equilibriumQty() 
    {
        return (a-c)/(b+d);
    }

    public double equilibriumPr() 
    {
        return (double)(a*d + b*c)/(b+d);
    }
    
    public double consumerSurplus()  //use demand eqn: C.surplus = (a-p)*q/2
    {
        return (this.a - equilibriumPr()) * equilibriumQty() / 2.0;
    }
    
    public double consumerSurplus(double price)
    {
        return 0.0;
    }
    
    public double producerSurplus()  //use supply eqn: P.surplus = (p-c)*q/2
    {
        return (equilibriumPr() - this.c) * equilibriumQty() / 2.0;  
    }
    
    public double producerSurplus(double price)
    {
        return 0.0;
    }
    
    public double totalSurplus()    //use sum of S. and D. eqns: T.surplus = (a-c)*q/2
    {
        return (this.a - this.c) * equilibriumQty() / 2.0;   
    }
    
    public double totalSurplus(double price)
    {
        return 0.0;
    }
    
    public double inequalityRate()  //(C.surplus - P.surplus) / T.surplus
    {
        return Math.abs(this.a + this.c - 2.0 * equilibriumPr()) / (this.a - this.c) * 100;
    }
    
    public void control(double price, boolean regulator)
    {
        System.out.println("You have set a price " + (regulator ? "floor (low": "ceiling (upp") + "er limit).");
        System.out.println((regulator ? "Excess": "Shortage") + " of production: " + inefficientRemainder(price) );
        System.out.println("Deadweight loss: " + dwLoss(price));
    }
    
    public double dwLoss(double price)
    {
        return 0.0;
    }
    
    public void control(boolean regulator, double price)
    {
        
    }
    
    public double inefficientRemainder(double price)        //excess/shortage determined: Qs - Qd
    {
        return Math.abs(this.a * this.d + (this.d - this.b) * price + this.b * this.c) / (this.b * this.d);
    }
    
    public String toString() 
    {
        return "Equilibrium: (" + equilibriumQty() + ", " + equilibriumPr() + ")" + "\nConsumer surplus: " + consumerSurplus() + "\nProducer surplus: " + producerSurplus() + "\nTotal surplus: " + totalSurplus() + "\nEquality rate: " + inequalityRate() + "%\n";
    }
     
    public void calculateGainsToo(double imexPr)
    {
        this.imexPr = imexPr;
        
        if(imexPr == equilibriumPr)
            System.out.println("The domestic price is equal to the national price.\nTrade would only harm the well-being of society.");
        else
            System.out.println("Eligibility: " + ( (imexPr < equilibriumPr) ? "Import" : "Export") );
        
        double imexDemandQty = (a - imexPr)/b;
        double imexSupplyQty = (imexPr - c)/d;
        
        double deltaCS = (double)(imexDemandQty + equilibriumQty) * (equilibriumPr - imexPr) / 2.0;
        double deltaPS = (double)(imexSupplyQty + equilibriumQty) * (equilibriumPr - imexPr) / 2.0;
        double deltaTS = (double)(imexDemandQty - imexSupplyQty)*(equilibriumPr-imexPr) / 2.0;
        
        double imexCS = consumerSurplus() + deltaCS;
        double imexPS = producerSurplus() + deltaPS;
        double imexTS = totalSurplus() + deltaTS;
        
        double fluctuationCS = deltaCS / consumerSurplus();
        double fluctuationPS = deltaPS / producerSurplus();
        double fluctuationTS = deltaTS / totalSurplus();
        
        double imexEq = (double) (imexCS - imexPS) / imexTS * 100.0;
        double deltaEq = (double)(imexEq - inequalityRate()) / inequalityRate() * 100.0;
        
        double imexEf = (double)(imexTS) / totalSurplus() * 100.0;
        double deltaEf = (double)(imexEf - efficiency) / efficiency * 100.0;
        
        System.out.println("Consumer Surplus -->\tCurrent: " + imexCS);
        System.out.println("\t\t\tPrevious: " + consumerSurplus());
        System.out.println("\t\t\t" + ((deltaCS > 0) ? "In" : "De") + "crement: " + Math.abs(deltaCS) );
        System.out.println("\t\t\tFluctuation: " + fluctuationCS);
        System.out.println();
        
        System.out.println("Producer Surplus -->\tCurrent: " + imexPS);
        System.out.println("\t\t\tPrevious: " + producerSurplus());
        System.out.println("\t\t\t" + ((deltaPS > 0) ? "In" : "De") + "crement: " + Math.abs(deltaPS) );
        System.out.println("\t\t\tFluctuation: " + fluctuationPS);
        System.out.println();
        
        System.out.println("Total Surplus -->\tCurrent: " + imexTS);
        System.out.println("\t\t\tPrevious: " + totalSurplus());
        System.out.println("\t\t\t" + ((deltaTS > 0) ? "In" : "De") + "crement: " + Math.abs(deltaTS) );
        System.out.println("\t\t\tFluctuation: " + fluctuationTS);
        System.out.println();
        
        System.out.println("Equality -->\t\tCurrent: " + imexEq);
        System.out.println("\t\t\tPrevious: " + inequalityRate());
        System.out.println("\t\t\t" + ((deltaEq > 0) ? "In" : "De") + "crement: " + Math.abs(deltaEq) );
        System.out.println();
        
        System.out.println("Efficiency -->\t\tCurrent: " + imexEf);
        System.out.println("\t\t\tPrevious: " + efficiency);
        System.out.println("\t\t\t" + ((deltaEf > 0) ? "In" : "De") + "crement: " + Math.abs(deltaEf) );
        System.out.println();
    }
    
    public void regulate(boolean regulator)
    {
        
    }
    
    public void subsidize(double subsidy)
    {
        
    }
    
    public void priceLimit(double price)
    {
        if(this.priceRegulator)
        {
            if(price < equilibriumPr)
                System.out.println("everythigns good");
            else
                System.out.println("A price floor set below equilibrium price has no effect on the forces of market demand and supply. ");
        }
        else
        {
            if((-1)*price < (-1*equilibriumPr))
                System.out.println("Everything good");
            else
                System.out.println("A price floor set below equilibrium price has no effect on the forces of market demand and supply. ");
        }
    }
    
    public void quotaLimit(int quantity)
    {
        
    }
    
    public void taxation(double taxation)
    {
        
    }
    
    public void taxation(double incidence, boolean forcedOn)
    {
        
    }
    
    public void calculateGains(double imexPr)
    {
        this.imexPr = imexPr;
        
        if(imexPr == equilibriumPr)
            System.out.println("The domestic price is equal to the national price.\nTrade would only harm the well-being of society. ");
        else
            System.out.println("Eligibility: " + ( (imexPr < equilibriumPr) ? "Import" : "Export") );
        
        double imexDemandQty = (a - imexPr)/b;
        double imexSupplyQty = (imexPr - c)/d;
        
        double imexCS = imexDemandQty * (a-imexPr) / 2;
        double imexPS = imexSupplyQty * (imexPr-c) / 2;
        
        if(imexDemandQty > imexSupplyQty)
            this.gainsQty = imexDemandQty - imexSupplyQty;
        else
            this.gainsQty = imexSupplyQty - imexDemandQty;
        this.gains = gainsQty * (equilibriumPr - imexPr);
        
        double imexTS = imexCS + imexPS;
        
        double deltaCS = imexCS - consumerSurplus;
        double deltaPS = imexPS - producerSurplus;
        double deltaTS = imexTS - totalSurplus;
        
        System.out.println("Quantity: " + Math.abs(gainsQty) );
        System.out.println("Gains from trade: " + gains);
        
        System.out.println("Efficiency increase: " + (gains/totalSurplus * 100)+ "%");
        System.out.println("Consumer Surplus: " + imexCS + "\n\tA" + ( (deltaCS > 0) ? "n increase" : " decrease" ) + " of " + Math.abs(deltaCS) );
        System.out.println("Producer Surplus: " + imexPS + "\n\tA" + ( (deltaPS > 0) ? "n increase" : " decrease" ) + " of " + Math.abs(deltaPS) );
        System.out.println("Total Surplus: "   + imexTS + "\n\tA" + ( (deltaTS > 0) ? "n increase" : " decrease" ) + " of " + Math.abs(deltaTS) );
        
        System.out.println("");
    }
}
