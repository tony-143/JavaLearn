public class Singleton {
    
    static class Logger{
        private static Logger logger;
        private Logger(){}; //private constructor is used for prevent external instantiation
        public static Logger getLogger(){
            if(logger==null) { // first check
                synchronized (Logger.class){   // syncronisation for give same instance for all threads
                    if(logger==null) return new Logger(); // second check
                }
            }
            return logger; 
        }
        public void log(String s){
            System.out.println("LOG: "+s);
        }
    }

    
    public static void main(String[] args) {
        Logger logger = Logger.getLogger();
        logger.log("message from tony");
        Logger logger2 = new Logger();
        logger2.log("message from tony2");
    }
}
