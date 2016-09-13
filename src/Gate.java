abstract public class Gate
{
    protected int delay;
    protected Signal in [];
    protected Signal out;
    
    public Gate(int inputs)
    {
        in = new Signal[inputs];
    }
    
    public Gate(int inputs, int delay)
    {
        in = new Signal[inputs];
        this.delay = delay;
    }
    
    public void setInput(int index, Signal inS)
    {
        if ((in != null) && (index < in.length))
        {
            in[index] = inS;
            in[index].addIn(this);
        }
    }
    
    public void setOutput(Signal outS)
    {
        out = outS;
        out.setOut(this);
    }
    
    abstract public void process();
    abstract public void process(int curTime);
}