import java.util.concurrent.locks.*;

class BoundedBufferProblemConditions extends BoundedBufferProblem
{
    Buffer createBuffer(int capacity)
    {
        return new BufferConditions(capacity);
    }

    class BufferConditions extends Buffer
    {
        private final Lock lock = new ReentrantLock();
        private final Condition canPut = lock.newCondition();
        private final Condition canGet = lock.newCondition();

        BufferConditions(int capacity)
        {
            super(capacity);
        }

        void put(int i)
        {
            this.lock.lock();
            try {
                while (this.getCapacity() == this.getSize()) {
                    this.canPut.await();
                }
                super.put(i);
                this.canGet.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
        }

        int get()
        {
            int tmp = -1;
            this.lock.lock();
            try {
                while (0 == this.getSize()) {
                    this.canGet.await();
                }
                tmp = super.get();
                this.canPut.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
            return tmp;
        }
    }
}
