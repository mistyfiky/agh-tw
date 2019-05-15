import java.util.ArrayList;
import java.util.List;

abstract class ReadersWritersProblem
{
    void solve(
        int readersAll,
        int writersAll,
        int readerRepeat,
        int writerRepeat,
        int readerSleep,
        int writerSleep
    )
    {
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= readersAll; i++) {
            Thread t = new Thread(createReader(readerRepeat, readerSleep));
            t.setName(String.format("#%03d", i));
            threads.add(t);
        }
        for (int i = 1; i <= writersAll; i++) {
            Thread t = new Thread(createWriter(writerRepeat, writerSleep));
            t.setName(String.format("#%03d", i));
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }
    }

    abstract Reader createReader(int repeat, int sleep);

    abstract Writer createWriter(int repeat, int sleep);

    abstract class Reader implements Runnable
    {
        int repeat;
        int sleep;

        Reader(int repeat, int sleep)
        {
            this.repeat = repeat;
            this.sleep = sleep;
        }

        @Override
        public void run()
        {
            for (int i = 0; i < repeat; i++) {
                try {
                    log("before");
                    before();
                    log("read");
                    read();
                    log("after");
                    after();
                    log("end");
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        void read() throws InterruptedException
        {
            Thread.sleep(sleep);
        }

        abstract void before() throws InterruptedException;

        abstract void after() throws InterruptedException;

        void log(String action)
        {
            System.err.printf("%s\t%s\n",
                "reader" + Thread.currentThread().getName(),
                action);
        }
    }

    abstract class Writer implements Runnable
    {
        int repeat;
        int sleep;

        Writer(int repeat, int sleep)
        {
            this.repeat = repeat;
            this.sleep = sleep;
        }

        @Override
        public void run()
        {
            for (int i = 0; i < repeat; i++) {
                try {
                    log("before");
                    before();
                    log("write");
                    write();
                    log("after");
                    after();
                    log("end");
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        void write() throws InterruptedException
        {
            Thread.sleep(sleep);
        }

        abstract void before() throws InterruptedException;

        abstract void after() throws InterruptedException;

        void log(String action)
        {
            System.err.printf("%s\t%s\n",
                "writer" + Thread.currentThread().getName(),
                action);
        }
    }
}
