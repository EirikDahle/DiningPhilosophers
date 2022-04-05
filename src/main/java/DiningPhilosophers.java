import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The purpose of this class should be to illustrate a scenario where philosophers and equally
 * as many chopsticks are placed around a round table.
 */
public class DiningPhilosophers {

    /**
     * createPhilosopher() initializes a amount of Philosophers and equally as many chopsticks "n" as Threads.
     * Philosophers is assigned both a left and right chopstick.
     * @param n
     * @return
     */
    public static Philosopher[] createPhilosopher(int n) {

        Chopstick[] chopsticks = new Chopstick[n];

        for (int i = 0; i < n; i++){
            chopsticks[i] = new Chopstick(i);

        }

        Philosopher[] philosophers = new Philosopher[n];

        for (int i = 0; i < n; i++){

            Chopstick leftChopstick = chopsticks[i];
            Chopstick rightChopstick = chopsticks[(i + 1) % n];

            philosophers[i] = new Philosopher(i, leftChopstick, rightChopstick);
        }

        return philosophers;
    }

    /**
     * Starts the sequence and run the created threads.
     * @param args
     */
    public static void main(String[] args) {

        // ExecutorService start of the threads and allows a number of 6 threads to run in its pool.
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        //creates 5 philosophers.
        Philosopher[] philosophers = createPhilosopher(5);

        //for every philosopher is put in a pool of 6 threads.
        for (Philosopher philosopher : philosophers) {
            executorService.execute(philosopher);
        }
    }
}
