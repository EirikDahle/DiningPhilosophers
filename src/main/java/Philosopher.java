public class Philosopher extends Thread {

    private final int id;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
        for (;;) {

            System.out.println("Philosopher " + id + " : THINKING..");

            rightChopstick.takeChopstick(id);
            System.out.println("Philosopher " + id + " HUNGRY, PICKS UP RIGHT CHOPSTICK" + rightChopstick.id);

            leftChopstick.takeChopstick(id);
            System.out.println("Philosopher " + id + " PICKS UP LEFT CHOPSTICK" + leftChopstick.id);
            System.out.println("Philosopher " + id + " EATING..");

            leftChopstick.putDownChopstick(id);
            System.out.println("Philosopher " + id + " DONE EATING, PUT DOWN LEFT CHOPSTICK" + leftChopstick.id);

            rightChopstick.putDownChopstick(id);
            System.out.println("Philosopher " + id + " PUT DOWN RIGHT CHOPSTICK, BACK TO THINKING" + rightChopstick.id);

        }
    }
}