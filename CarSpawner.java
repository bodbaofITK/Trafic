package threads;

import java.util.Random;

public class CarSpawner extends Thread{
    @Override
    public void run(){
        Random random = new Random();

        while(Test.ENDLESS_CARS){
            try {
                Thread.sleep(3000+random.nextInt(12000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            (new Car()).start();
        }
    }
}
