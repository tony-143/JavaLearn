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

/**
 * VehicleFactory
 */
interface VehicleFactory {
    Vehicle createVehicle();
}

class HondaFactory implements VehicleFactory{
    public Vehicle createVehicle(){
        return new Honda();
    }
}

/**
 * BMWFactory
 */
class BMWFactory implements VehicleFactory{
    public Vehicle createVehicle(){
        return new BMW();
    }
}

public class AbsFactory {
    
    public static void main(String[] args) {
    HondaFactory hd = new HondaFactory();
    Vehicle honda = hd.createVehicle();    
    honda.start();
    honda.stop();
}
}