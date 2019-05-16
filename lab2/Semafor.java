class Semafor
{
    private boolean _stan = true;
    private int _czeka = 0;

    public Semafor()
    {
    }

    public synchronized void P()
    {
        synchronized (this) {
            _czeka++;
            while (!_stan) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            _stan = false;
            _czeka--;
        }
    }

    public synchronized void V()
    {
        synchronized (this) {
            _stan = true;
            if (0 < _czeka) {
                notify();
            }
        }
    }
}   
