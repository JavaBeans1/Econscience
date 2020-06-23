package econmikael;

import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to calculating equilibrium of a supply and demand curve.");
        
        System.out.println("\n");
        
        System.out.println("Enter the values for variables(a,b,c,d) in the supply and demand equations.\n");
        System.out.println("Demand curve --> P=a-b*Qd\nSupply curve --> P=c+d*Qs\n");
                
        System.out.print("Enter a: "); int a = input.nextInt();
        System.out.print("Enter b: "); int b = input.nextInt();
        Demand demand = new Demand(a,b);
                
        System.out.print("Enter c: "); int c = input.nextInt();
        System.out.print("Enter d: "); int d = input.nextInt();
        Supply supply = new Supply(c,d);
        
        System.out.println();
        
        Equilibrium equilibrium = new Equilibrium(demand, supply);
        
        System.out.println(equilibrium);        
        System.out.println();
        
        /*
        System.out.print("Enter exchange price for the good: "); double imexPr = input.nextDouble();
        System.out.println();
                    
        equilibrium.calculateGainsToo(imexPr);
        
        System.out.println();
        */
        
        System.out.println("Choose production control: (1) Price regulation (3) Tax (4) Subsidy (5) Production Quota");
        System.out.print("Enter your choice: "); int choice = input.nextInt();
            
        System.out.println();
        
        System.out.println("Enter ");
        double control = 0.0;
        switch(choice)
        {
            case 1:
                System.out.println("Enter regulated price: ");
                control = input.nextDouble();
                
                equilibrium.control( control, control < equilibrium.equilibriumPr() );
                break;
               
            case 2:
                System.out.println("tax amount:");
                control = input.nextDouble();
                
                equilibrium.control(true, control);
                break;
                
            case 3:
                System.out.println("subsidy amount: "); 
                control = input.nextDouble();
                
                equilibrium.control(false, control);
                break;
                
            case 4:
                System.out.println("import quota limit: ");
                break;
                
            default:
                System.out.println("Sorry! There is an error in your choice of input. ");
        }
    }
}
