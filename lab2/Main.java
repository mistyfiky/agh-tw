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
        Semafor s = new Semafor();

        Thread one = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                s.P();
                number.increment();
                s.V();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                s.P();
                number.decrement();
                s.V();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.printf("%d\n", number.getValue());
    }
}

