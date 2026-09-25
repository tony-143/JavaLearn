/**
 * Vehicle
 */
interface Vehicle {
    void start();
    void stop();
}

/**
 * BMW
 */
class BMW implements Vehicle{
    public void start(){
        System.out.println("BMW car is started");
    }
    
    public void stop(){
        System.out.println("BMW car is stoped");
    }
}
/**
 * Honda
 */
class Honda implements Vehicle{
    public void start(){
        System.out.println("BMW car is started");
    }
    
    public void stop(){
        System.out.println("BMW car is stoped");
    }
}



public class AbsFactory {
    
}
