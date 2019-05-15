class Main
{
    static class Number
    {
        private int value;

        Number(int value)
        {
            this.value = value;
        }

        void increment()
        {
            this.value++;
        }

        void decrement()
        {
            this.value--;
        }

        int getValue()
        {
            return value;
        }
    }

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

        System.out.printf("%d\n", number.getValue());
    }
}

