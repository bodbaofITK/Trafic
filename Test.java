package threads;

public class Test extends Thread {
    
    public static boolean DEBUG_MODE   =false;
    public static boolean ENDLESS_CARS =true;
    public static Integer NUM_INIT_CARS=3;

    @Override
    public void run(){
        while(Test.DEBUG_MODE){
            System.out.print(".");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
    public static void main(String[] args) {
        (new Intersection()).start();
        (new Test()).start();        // while debugging (signals every passing 500ms)
        (new CarSpawner()).start();  //spawns cars every 3-15 secs

        for (int i = 0; i < NUM_INIT_CARS; i++) {     //spawning initial cars   
           (new Car()).start();
       }

   }
}
