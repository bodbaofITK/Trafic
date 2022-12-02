package threads;

public class Intersection extends Thread{

    public static boolean green = false;
    public static boolean occupied = false;

    public static Object sync = new Object(); //non-primitive type object for syncing

    public void run(){
        //finite state machine: go->stop; stop->go; sleeps after every state change
        //on "go" sends notify to all threads
        //state is displayed on every state change
        while(true){
            try{
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intersection.green=!Intersection.green;
            if(green){
                System.out.println("Lamp: GO!");
                synchronized(Intersection.sync){
                    Intersection.sync.notifyAll();
                }
            }else{
                System.out.println("Lamp: STOP!");
            }
        }
    }
}
