import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private final int id;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    /**
     * Giving each Philosopher an unique identification and assigning them two chopsticks,
     * one on the left and right hand side.
     * @param id
     * @param leftChopstick
     * @param rightChopstick
     */
    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {

        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;

    }


    /**
     * Every philosopher is represented as threads performing the different actions.
     * while (true) acts as a infinite loop so that the actions can be repeated over and over
     * without any deadlocks. Modulus if-statement prevents deadlocks having one of the philosophers
     * pick up the left chopstick first instead of the right.
     */
    @Override
    public void run() {

            while (true) {

                // All philosophers start the initial cycle thinking.
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

    /**
     * threadSleep is implemented to slow down the infinite cycle of actions.
     * By having its own method its easier to alter the duration it takes for philosophers
     * to eat, think etc. by having min and max time period instead of a set time it varies
     * how long time each philosopher spends between actions.
     * @param minTime
     * @param maxTime
     */

    private void threadSleep(int minTime, int maxTime) {

        try {

            Thread.sleep(getRandomNumber(minTime, maxTime));

        } catch (InterruptedException e) {

            System.out.println(e.getMessage());

        }

    }

    /**
     * Think() method used in run() to initialize thinking action and print according information.
     */
    private void think() {

        System.out.println("\nPhilosopher " + getPhilosopherId() + " : THINKING..");
    }

    /**
     * pickUpLeftThenRightChopstick() method is used in run() to make philosopher take chopsticks,
     * first the one on the left side then followed by the right to break deadlock.
     */
    private void pickUpLeftThenRightChopstick() {

        leftChopstick.takeChopstick(id);
        System.out.println("\nPhilosopher " + getPhilosopherId() + " HUNGRY, PICKS UP CHOPSTICK " + getLeftChopstickId());

        rightChopstick.takeChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " PICKS UP CHOPSTICK " + getRightChopstickId());

        // threadsleep implemented to alter duration of action.
        threadSleep(500, 4000);

    }

    /**
     * pickUpRightThenLeftChopstick() method is used in run() to make philosopher take chopsticks,
     * first the one on the right side then followed by the left.
     */
    private void pickUpRightThenLeftChopstick() {

        rightChopstick.takeChopstick(id);
        System.out.println("\nPhilosopher " + getPhilosopherId() + " HUNGRY, PICKS UP CHOPSTICK " + getRightChopstickId());

        leftChopstick.takeChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " PICKS UP CHOPSTICK " + getLeftChopstickId());

        // threadsleep implemented to alter duration of action.
        threadSleep(500, 4000);

    }

    /**
     * eat() method used in run() and is common for every scenario so that when philosophers have gathered
     * both a right and left chopstick they will proceed to eat, before putting them down again and go back to thinking.
     */
    private void eat() {

        System.out.println("\nPhilosopher " + getPhilosopherId() + " EATING..");

        leftChopstick.putDownChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " DONE EATING, PUT DOWN CHOPSTICK " + getLeftChopstickId());

        rightChopstick.putDownChopstick(id);
        System.out.println("Philosopher " + getPhilosopherId() + " PUT DOWN CHOPSTICK " + getRightChopstickId() + ", BACK TO THINKING ");

        // threadsleep implemented to alter duration of action.
        threadSleep(500, 4000);

    }

    /**
     * getRandomNumber() is used to generates a random time period within a given interval for threads
     * to stop up between actions.
     * @param min
     * @param max
     * @return
     */
    private int getRandomNumber(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);

    }

    /**
     * getPhilosopherId() is used to display which philosopher performs which act in the printed sequence.
     * @return
     */
    public int getPhilosopherId() {

        return this.id + 1;

    }

    /**
     * getLeftChopstickId() returns the id of a philosophers chopstick on the left side.
     * @return
     */
    public int getLeftChopstickId() {

        return leftChopstick.id + 1;

    }

    /**
     * getRightChopstickId() returns the id of a philosophers chopstick on the right side.
     * @return
     */
    public int getRightChopstickId() {

        return rightChopstick.id + 1;

    }
}
