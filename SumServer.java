
import SumApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;

class SumImpl extends SumPOA {
  private ORB orb;

  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  
 
  public double sum(double n1,double n2) {
    return (n1+n2);
  }
    
  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
  }
}


public class SumServer {

  public static void main(String args[]) {
    try{
    
      ORB orb = ORB.init(args, null);
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();
      SumImpl sumImpl = new SumImpl();
      sumImpl.setORB(orb); 
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(sumImpl);
      Sum href = SumHelper.narrow(ref);
      org.omg.CORBA.Object objRef =
      orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
      String name = "Sum";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("SumServer listo y esperando ...");

   
      orb.run();
    } 
        
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
          
      System.out.println("SumServer Saliendo ...");
        
  }
}