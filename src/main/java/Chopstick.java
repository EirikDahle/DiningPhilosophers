public class Chopstick {

    public final int id;
    private boolean notOccupiedChopstick = true;
    private int occupiedChopstick;

    public Chopstick(int id) {
        this.id = id;

    }

    public synchronized void takeChopstick(int philosopher) {

        while (!notOccupiedChopstick) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }

        occupiedChopstick = philosopher;

        notOccupiedChopstick = false;

    }

    public synchronized void putDownChopstick(int philosopher) {

        if (!notOccupiedChopstick && occupiedChopstick == philosopher) {
            notOccupiedChopstick = true;
            notify();
        }
    }
}
