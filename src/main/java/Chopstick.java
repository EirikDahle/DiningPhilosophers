public class Chopstick {

    public final int id;
    private boolean notOccupiedChopstick = true;
    private int occupiedChopstick;

    public Chopstick(int id) {
        this.id = id;

    }

    /**
     * Chopsticks are resources shared between philosophers during mutual exclusion.
     * The chopsticks acts as objects that can be used by all philosophers,
     * but not at the same time. By using a synchronized method a singular chopstick
     * can only be attained by one philosopher at a time.
     * @param philosopher
     */
    public synchronized void takeChopstick(int philosopher) {

        while (!notOccupiedChopstick) {
            try {
                // Wait method is added so that a philosopher can wait until chopsticks are available.
                wait();
            } catch (InterruptedException ignored) {}
        }

        occupiedChopstick = philosopher;

        notOccupiedChopstick = false;

        //Thread.sleep(((int) (Math.random() * 2000)));


    }


    public synchronized void putDownChopstick(int philosopher) {

        if (!notOccupiedChopstick && occupiedChopstick == philosopher) {
            notOccupiedChopstick = true;
            // Notify method should notify a "waiting" philosopher if the chopstick is available.
            notify();

            //Thread.sleep(((int) (Math.random() * 2000)));

        }
    }
}
