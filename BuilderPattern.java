public class BuilderPattern {
    /**
     * Car
    */
    private static class Car {
        private String color;
        private String engine; 
        private int speed; 
        private boolean roof; 
        private boolean autoMode;
        private boolean safetyBag;
        private int cost;
        private String brand;
        private Car(CarBuilder builder){
            this.color = builder.color;
            this.engine = builder.engine;
            this.speed = builder.speed;
            this.roof = builder.roof;
            this.brand = builder.brand;
            this.autoMode = builder.autoMode;
            this.cost = builder.cost;
            this.safetyBag = builder.safetyBag;
        }
        public void carFeaturs(){
            System.out.println("Color: " + color);
            System.out.println("Engine: " + engine);
            System.out.println("Speed: " + speed);
            System.out.println("Roof: " + roof);
            System.out.println("Auto mode: " + autoMode);
            System.out.println("Safety bag: " + safetyBag);
            System.out.println("Cost: " + cost);
            System.out.println("Brand: " + brand);
        }
    }

    static class CarBuilder {
        private String color = "red"; // default
        private String engine = "H1type";
        private int speed = 200;
        private boolean roof = false;
        private boolean autoMode = false;
        private boolean safetyBag = true;
        private int cost;
        private String brand;
        
        CarBuilder color(String s){
            this.color = s; return  this;
        }
        CarBuilder engine(String s){
            this.engine = s; return  this;
        }
        CarBuilder speed(int s){
            this.speed = s; return  this;
        }
        CarBuilder roof(boolean s){
            this.roof = s; return  this;
        }
        CarBuilder autoMode(boolean s){
            this.autoMode = s; return  this;
        }
        CarBuilder safetyBad(boolean s){
            this.safetyBag = s; return  this;
        }
        CarBuilder cost(int s){
            this.cost = s; return  this;
        }
        CarBuilder brand(String s){
            this.brand = s; return  this;
        }

        Car build(){
            return new Car(this);
        }
    }
    
    public static void main(String[] args) {
        CarBuilder builder = new CarBuilder();
        Car car = builder.autoMode(true).build();
        car.carFeaturs();

    }    

}



