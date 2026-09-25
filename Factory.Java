// Online Java Compiler (Editor)
// Write and run Java online using this editor.
interface Vehicle{
    void start();
    void stop();
}
class Car implements Vehicle{
    public void start(){
        System.out.println("Car is Started");
    }
    public void stop(){
        System.out.println("Car is stoped");
    }
}
class Bike implements Vehicle{
    public void start(){
        System.out.println("Bike is Started");
    }
    public void stop(){
        System.out.println("Bike is stoped");
    }
    public void Break(){
        System.out.println("Breaked!");
    }
}
class VehicleFactory{
    static Vehicle vehicleType(String type){
        if(type.equals("car")) return new Car();
        else if(type.equals("bike")) return new Bike();
        else throw new IllegalArgumentException("Unkown vehicle type");
    }
}
class Factory {
    
    public static void main(String[] args) {
        // System.out.println("Try clicking the Run button.");
        Vehicle car = VehicleFactory.vehicleType("bike");
        car.start();
        car.stop();
        ((Bike) car).Break();
    }
}