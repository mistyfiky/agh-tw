class Semafor
{
    private boolean _stan = true;
    private int _czeka = 0;

    public Semafor()
    {
    }

    synchronized void P()
    {
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

    synchronized void V()
    {
        _stan = true;
        if (0 < _czeka) {
            notify();
        }
    }
}   
