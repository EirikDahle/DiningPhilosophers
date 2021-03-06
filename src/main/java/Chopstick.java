/**
 * The Chopstick class represent the individual chopsticks as objects.
 * A chopstick is identified and should be acquired by philosophers as soon as it is no longer occupied.
 */
public class Chopstick {

    public final int id;
    private boolean notOccupiedChopstick = true;
    private int occupiedChopstick;

    /**
     * giving each chopstick its unique identification.
     *
     * @param id a chopsticks unique identification
     */
    public Chopstick(int id) {
        this.id = id;

    }

    /**
     * Chopsticks are resources shared between philosophers during mutual exclusion.
     * The chopsticks acts as objects that can be used by all philosophers,
     * but not at the same time. By using a synchronized method a singular chopstick
     * can only be attained by one philosopher at a time.
     *
     * @param philosopher which philosopher occupies a chopstick.
     */
    public synchronized void takeChopstick(int philosopher) {

        while (!notOccupiedChopstick) {
            try {
                // Wait method is added so that a philosopher can wait until chopsticks are available.
                wait();
            } catch (InterruptedException ignored) {}
        }

        // Chopstick is currently occupied by philosopher.
        occupiedChopstick = philosopher;

        notOccupiedChopstick = false;


    }

    /**
     * Philosophers receive a notification that the chopstick is no longer occupied and available to pick up.
     *
     * @param philosopher which philosopher puts down his chopstick.
     */
    public synchronized void putDownChopstick(int philosopher) {

        if (!notOccupiedChopstick && occupiedChopstick == philosopher) {
            notOccupiedChopstick = true;
            // Notify method should notify a "waiting" philosopher if the chopstick is available.
            notify();

        }
    }
}
