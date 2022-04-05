import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The purpose of this class should be to illustrate a scenario where philosophers and equally
 * as many chopsticks are placed around a round table.
 */
public class DiningPhilosophers {

    /**
     * initializes a amount of Philosophers and equally as many chopsticks "numberOfPhilosophers" as Threads.
     * Philosophers is assigned both a left and right chopstick.
     *
     * @param numberOfPhilosophers the amount of philosophers and the same amount of chopsticks.
     */
    public static Philosopher[] createPhilosopherWithChopsticks(int numberOfPhilosophers) {

        // Creates an amount of chopsticks as object in a fixed size array.
        Chopstick[] chopsticks = new Chopstick[numberOfPhilosophers];

        for (int i = 0; i < numberOfPhilosophers; i++){
            chopsticks[i] = new Chopstick(i);

        }

        // Creates an amount of philosophers as object in a fixed size array.
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];

        for (int i = 0; i < numberOfPhilosophers; i++){

            Chopstick leftChopstick = chopsticks[i];
            Chopstick rightChopstick = chopsticks[(i + 1) % numberOfPhilosophers];

            philosophers[i] = new Philosopher(i, leftChopstick, rightChopstick);
        }

        return philosophers;
    }

    /**
     * Starts the sequence and run the created threads.
     */
    public static void main(String[] args) {

        // ExecutorService start of the threads and allows a number of 6 threads to run in its pool.
        // The amount of threads can be altered, this is to stop the application from launching unnecessary amounts.
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        //creates 5 philosophers.
        Philosopher[] philosophers = createPhilosopherWithChopsticks(5);

        //for every philosopher is put in a pool of 6 threads.
        for (Philosopher philosopher : philosophers) {
            executorService.execute(philosopher);
        }
    }
}
