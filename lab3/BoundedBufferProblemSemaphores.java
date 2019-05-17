import java.util.concurrent.Semaphore;

class BoundedBufferProblemSemaphores extends BoundedBufferProblem
{
    Buffer createBuffer(int capacity)
    {
        return new BufferSemaphore(capacity);
    }

    class BufferSemaphore extends Buffer
    {
        Semaphore mutex;
        Semaphore empty;
        Semaphore full;

        BufferSemaphore(int capacity)
        {
            super(capacity);
            this.mutex = new Semaphore(1);
            this.empty = new Semaphore(getCapacity());
            this.full = new Semaphore(0);
        }

        void put(int i)
        {
            try {
                this.empty.acquire();
                this.mutex.acquire();
                super.put(i);
                this.mutex.release();
                this.full.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int get()
        {
            int tmp = -1;
            try {
                this.full.acquire();
                this.mutex.acquire();
                tmp = super.get();
                this.mutex.release();
                this.empty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return tmp;
        }
    }
}
