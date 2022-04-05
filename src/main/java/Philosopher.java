/**
 * Class Philosopher represent a runnable object as a person assigned with chopsticks around a round table.
 */
public class Philosopher extends Thread {

    private final int id;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    /**
     * Giving each Philosopher an unique identification and assigning them two chopsticks,
     * one on the left and right hand side.
     *
     * @param id a philosophers unique identification.
     * @param leftChopstick this philosophers left chopstick identification.
     * @param rightChopstick this philosophers right chopstick identification.
     */
    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {

        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;

    }


    /**
     * Every philosopher is represented as threads performing the different actions.
     * while (true) acts as a infinite loop so that the actions can be repeated over and over.
     * Modulus if-statement prevents deadlocks having one of the philosophers
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
     * ThreadSleep is implemented to slow down the infinite cycle of actions.
     * By having its own method its easier to alter the duration it takes for philosophers
     * to eat, think etc. by having min and max time period instead of a set time it varies
     * how long time each philosopher spends between actions.
     *
     * @param minTime minimum time for threads to sleep.
     * @param maxTime maximum time for threads to sleep.
     */

    private void threadSleep(int minTime, int maxTime) {

        try {

            Thread.sleep(getRandomNumber(minTime, maxTime));

        } catch (InterruptedException e) {

            System.out.println(e.getMessage());

        }

    }

    /**
     * Method used in run() to initialize thinking action and print according information.
     */
    private void think() {

        System.out.println("\nPhilosopher " + getPhilosopherId() + " : THINKING..");
    }

    /**
     * Method is used in run() to make philosopher take chopsticks,
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
     * Method is used in run() to make philosopher take chopsticks,
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
     * Method used in run() and is common for every scenario so that when philosophers have gathered
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
     * used to generate a random number.
     *
     * @param min sets a minimum integer value.
     * @param max sets a maximum integer value.
     */
    private int getRandomNumber(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);

    }

    /**
     * is used to display which philosopher performs which act in the printed sequence.
     *
     * @return philosopher identification.
     */
    public int getPhilosopherId() {

        // return identification (+1) to get correct values when printing from array.
        return this.id + 1;

    }

    /**
     * returns the id of a philosophers chopstick on the left side.
     *
     * @return left hand chopstick identification.
     */
    public int getLeftChopstickId() {

        return leftChopstick.id + 1;

    }

    /**
     * returns the id of a philosophers chopstick on the right side.
     *
     * @return right hand chopstick identification.
     */
    public int getRightChopstickId() {

        return rightChopstick.id + 1;

    }
}
