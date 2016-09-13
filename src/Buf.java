public class Buf extends Gate
{
    public Buf(int delay)
    {
        super(1, delay);
    }
    
    public Buf(int inputs, int delay)
    {
        super(1, delay);
    }

    public void process()
    {
        boolean result;
        if (in != null && out != null )
        {
            result = in[0].getValue();
            out.setValue(result);
        }
        else
        {
            System.out.println("Error: Invalid input or output");
        }
    }
    
    public void process(int curTime)
    {
        if (in != null && out != null )
        {            
            //checking if that Event is necessary will be done in Event::propagate()
            new Event(out, delay+curTime, in[0].getValue());
        }
        else
        {
            System.out.println("Error: Invalid input or output");
        }       
    }
    
}