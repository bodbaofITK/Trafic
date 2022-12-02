package threads;

import java.util.Random;

public class Car extends Thread{
    
    private int ID;
    private boolean crossed=false;
    private static int car_count=0; //for DEBUG_MODE
    private Random random = new Random(); 

    //debugging (is easier with simple numbers)
    Car(){
        if(Test.DEBUG_MODE){
            Car.car_count+=1;
            this.ID=Car.car_count;
        }else{
            this.ID=random.nextInt(899999)+100000;
        }
        
    }
    @Override
    public void run(){
        //car goes until intersection
        System.out.format("Car %d SPAWNED\n",this.ID);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        System.out.format("Car %d WAITS\n",this.ID);
        //trying "until" car did not cross the intersection
        while(!this.crossed){
            //check for entry
            if(Intersection.green & !Intersection.occupied){
                //if entry: lock->wait->release
                synchronized(Intersection.sync){
                    Intersection.occupied=true;
                    System.out.format("Car %d ENTERED\n",this.ID);
                    try {
                        Intersection.sync.wait(1000);
                    } catch (InterruptedException e) {}
                    System.out.format("Car %d EXITED\n",this.ID);
                    Intersection.occupied=false;
                    this.crossed=true;
                    Intersection.sync.notifyAll();
                }
            }else{
                //if no entry: wait for unlock
                synchronized(Intersection.sync){
                    try {
                        Intersection.sync.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        System.out.format("Car %d DESPAWNED\n",this.ID);
    }
    
}