public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        int n = 10000;
        Number number = new Number(0);

        Thread one = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                number.increment();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                number.decrement();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.printf("%d", number.getValue());
    }
}

