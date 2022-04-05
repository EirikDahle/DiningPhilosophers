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

    private void threadSleep() {
        try {
            Thread.sleep(getRandomNumber(500, 4000));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
    private void think() {
        System.out.println("\nPhilosopher " + getPhilisopherId() + " : THINKING..");
    }

    private void pickUpLeftThenRightChopstick() {
        leftChopstick.takeChopstick(id);
        System.out.println("\nPhilosopher " + getPhilisopherId() + " HUNGRY, PICKS UP LEFT CHOPSTICK " + leftChopstick.id);

        rightChopstick.takeChopstick(id);
        System.out.println("Philosopher " + getPhilisopherId() + " PICKS UP RIGHT CHOPSTICK " + rightChopstick.id);

        threadSleep();
    }

    private void pickUpRightThenLeftChopstick() {
        rightChopstick.takeChopstick(id);
        System.out.println("\nPhilosopher " + getPhilisopherId() + " HUNGRY, PICKS UP RIGHT CHOPSTICK " + rightChopstick.id);

        leftChopstick.takeChopstick(id);
        System.out.println("Philosopher " + getPhilisopherId() + " PICKS UP LEFT CHOPSTICK " + leftChopstick.id);

        threadSleep();
    }

    private void eat() {
        System.out.println("\nPhilosopher " + getPhilisopherId() + " EATING..");

        leftChopstick.putDownChopstick(id);
        System.out.println("Philosopher " + getPhilisopherId() + " DONE EATING, PUT DOWN LEFT CHOPSTICK " + leftChopstick.id);

        rightChopstick.putDownChopstick(id);
        System.out.println("Philosopher " + getPhilisopherId() + " PUT DOWN RIGHT CHOPSTICK, BACK TO THINKING " + rightChopstick.id);

        threadSleep();
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getPhilisopherId() {
        return this.id + 1;
    }
}
