import SumApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;
public class SumClient
{
  static Sum sumImpl;

  public static void main(String args[])
    {
      try{
       ORB orb = ORB.init(args, null);
       org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
       NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
       String name = "Sum";
        sumImpl = SumHelper.narrow(ncRef.resolve_str(name));
        System.out.println("Obtained a handle on server object: " + sumImpl);
        Scanner entrada=new Scanner(System.in);
        int res=0;
        while(res!=5){
          System.out.println("Por favor elija una de las siguientes opciones: \n 1)Suma\n2)Resta\n3)multiplicacion\n4)Division\n5)Salir");
         res=entrada.nextInt();
        switch(res){
            case 1:{
                System.out.println("Por favor ingrese un primer numero: ");
        double n1=entrada.nextDouble();
        System.out.println("Por favor ingrese un segundo numero: ");
        double n2= entrada.nextDouble();
         System.out.println("El resultado es: "+(int)sumImpl.sum(n1,n2)); 
                break;
            } 
            case 2:{
                
                break;
            }
            case 3:{
                break;
            }
            case 4:{
                break;
            }
            case 5:{
                break;
            }
            default:{
                break;
            }
      }   
        }
       
        
        
        sumImpl.shutdown();

        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }

}
 