import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private static final Semaphore semaphore = new Semaphore(5);

    private final int id;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }



    /**
     * Every philosopher is represented as threads performing these different actions.
     *
     */
    @Override
    public void run() {

            while (true) {

            think();

                if (id % 2 == 0) {

                pickUpLeftThenRightChopstick();

                }
                else {

                pickUpRightThenLeftChopstick();

                }
                eat();
        }

    }

    private void threadSleep(int minTime, int maxTime) {
        try {
            Thread.sleep(getRandomNumber(minTime, maxTime));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
    private void think() {
        System.out.println("\nPhilosopher " + getPhilosopherId() + " : THINKING..");
    }

    private void pickUpLeftThenRightChopstick() {
        leftChopstick.takeChopstick(id);
        System.out.println("\nPhilosopher " + getPhilosopherId() + " HUNGRY, PICKS UP LEFT CHOPSTICK " + getChopstickId());

        rightChopstick.takeChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " PICKS UP RIGHT CHOPSTICK " + getChopstickId());

        threadSleep(500, 4000);
    }

    private void pickUpRightThenLeftChopstick() {
        rightChopstick.takeChopstick(id);
        System.out.println("\nPhilosopher " + getPhilosopherId() + " HUNGRY, PICKS UP RIGHT CHOPSTICK " + getChopstickId());

        leftChopstick.takeChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " PICKS UP LEFT CHOPSTICK " + getChopstickId());

        threadSleep(500, 4000);
    }

    private void eat() {
        System.out.println("\nPhilosopher " + getPhilosopherId() + " EATING..");

        leftChopstick.putDownChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " DONE EATING, PUT DOWN LEFT CHOPSTICK " + getChopstickId());

        rightChopstick.putDownChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " PUT DOWN RIGHT CHOPSTICK, BACK TO THINKING " + getChopstickId());

        threadSleep(500, 4000);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getPhilosopherId() {
        return this.id + 1;
    }

    public int getChopstickId() {
        return this.id + 1;
    }
}
