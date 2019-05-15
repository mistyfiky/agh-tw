import java.util.concurrent.Semaphore;

class ReadersWritersProblemSemaphores extends ReadersWritersProblem
{
    private int readCount = 0;
    private Semaphore resourceAccess = new Semaphore(1);
    private Semaphore readCountAccess = new Semaphore(1);
    private Semaphore serviceQueue = new Semaphore(1);

    @Override
    Reader createReader(int repeat, int sleep)
    {
        return new ReaderSemaphores(repeat, sleep);
    }

    @Override
    Writer createWriter(int repeat, int sleep)
    {
        return new WriterSemaphores(repeat, sleep);
    }

    class ReaderSemaphores extends Reader
    {
        ReaderSemaphores(int repeat, int sleep)
        {
            super(repeat, sleep);
        }

        void before() throws InterruptedException
        {
            serviceQueue.acquire();
            readCountAccess.acquire();
            if (0 == readCount) {
                resourceAccess.acquire();
            }
            readCount++;
            readCountAccess.release();
            serviceQueue.release();
        }

        void after() throws InterruptedException
        {
            readCountAccess.acquire();
            readCount--;
            if (0 == readCount) {
                resourceAccess.release();
            }
            readCountAccess.release();
        }
    }

    class WriterSemaphores extends Writer
    {
        WriterSemaphores(int repeat, int sleep)
        {
            super(repeat, sleep);
        }

        void before() throws InterruptedException
        {
            serviceQueue.acquire();
            resourceAccess.acquire();
            serviceQueue.release();
        }

        void after() throws InterruptedException
        {
            resourceAccess.release();
        }
    }
}
