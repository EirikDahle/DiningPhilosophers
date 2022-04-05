import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophers {

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

    public static void main(String[] args) {

        // ExecutorService start of the threads and allows a number of 6 threads to run in its pool.
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        //creates 5 philosophers.
        Philosopher[] philosophers = createPhilosopher(5);

        //for every philosopher a thread pool is assigned.
        for (Philosopher philosopher : philosophers) {
            executorService.execute(philosopher);
        }
    }
}
