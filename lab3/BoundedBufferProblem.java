import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract class BoundedBufferProblem
{
    void solve(
        int producentsAll,
        int consumentsAll,
        int capacity,
        List<Integer> producentsDelays,
        List<Integer> consumentsDelays
    )
    {
        Buffer buffer = createBuffer(capacity);
        if (producentsAll != producentsDelays.size()) {
            throw new IllegalArgumentException("Not sufficient producentsDelays argument length");
        }
        if (consumentsAll != consumentsDelays.size()) {
            throw new IllegalArgumentException("Not sufficient consumentsDelays argument length");
        }

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < producentsAll; i++) {
            Thread t = new Producer(buffer, producentsDelays.get(i));
            t.setName("producer#" + (i + 1));
            threads.add(t);
        }
        for (int i = 0; i < consumentsAll; i++) {
            Thread t = new Consumer(buffer, consumentsDelays.get(i));
            t.setName("consumer#" + (i + 1));
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }
    }

    abstract Buffer createBuffer(int capacity);

    abstract class Buffer
    {
        private int capacity;
        protected LinkedList<Integer> buffer;

        Buffer(int capacity)
        {
            this.capacity = capacity;
            this.buffer = new LinkedList<>();
        }

        int getCapacity()
        {
            return this.capacity;
        }

        int getSize()
        {
            return this.buffer.size();
        }

        void put(int i)
        {
            this.buffer.addLast(i);
        }

        int get()
        {
            return this.buffer.removeFirst();
        }
    }

    class Producer extends Thread
    {
        private Buffer _buf;
        private int sleep;

        Producer(Buffer _buf, int sleep)
        {
            this._buf = _buf;
            this.sleep = sleep;
        }

        public void run()
        {
            for (int i = 0; i < 100; ++i) {
                try {
                    Thread.sleep(this.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this._buf.put(i);
                System.out.printf("%s put %d\n",
                    Thread.currentThread().getName(), i);
            }
        }
    }

    class Consumer extends Thread
    {
        private Buffer _buf;
        private int sleep;

        Consumer(Buffer _buf, int sleep)
        {
            this._buf = _buf;
            this.sleep = sleep;
        }

        public void run()
        {
            for (int i = 0; i < 100; ++i) {
                try {
                    Thread.sleep(this.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s get %d\n",
                    Thread.currentThread().getName(), this._buf.get());
            }
        }
    }
}
