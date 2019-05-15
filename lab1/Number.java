public class Number
{
    private int value;

    public Number(int value)
    {
        this.value = value;
    }

    public void increment()
    {
        this.value++;
    }

    public void decrement()
    {
        this.value--;
    }

    public int getValue()
    {
        return value;
    }
}
