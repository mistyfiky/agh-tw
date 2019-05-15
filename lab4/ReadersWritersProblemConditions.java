import java.util.concurrent.locks.*;

class ReadersWritersProblemConditions extends ReadersWritersProblem
{
    private final Lock m = new ReentrantLock();
    private final Condition turn = m.newCondition();
    private int writers = 0;
    private int writing = 0;
    private int reading = 0;

    @Override
    Reader createReader(int repeat, int sleep)
    {
        return new ReaderConditions(repeat, sleep);
    }

    @Override
    Writer createWriter(int repeat, int sleep)
    {
        return new WriterConditions(repeat, sleep);
    }

    class ReaderConditions extends Reader
    {
        ReaderConditions(int repeat, int sleep)
        {
            super(repeat, sleep);
        }

        void before() throws InterruptedException
        {
            m.lock();
            while (0 < writers) {
                turn.await();
            }
            reading++;
            m.unlock();
        }

        void after() throws InterruptedException
        {
            m.lock();
            reading--;
            turn.signalAll();
            m.unlock();
        }
    }

    class WriterConditions extends Writer
    {
        WriterConditions(int repeat, int sleep)
        {
            super(repeat, sleep);
        }

        void before() throws InterruptedException
        {
            m.lock();
            writers++;
            while (0 < reading || 0 < writing) {
                turn.await();
            }
            writing++;
            m.unlock();
        }

        void after() throws InterruptedException
        {
            m.lock();
            writing--;
            writers--;
            turn.signalAll();
            m.unlock();
        }
    }
}
