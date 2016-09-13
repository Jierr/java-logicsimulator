public class And extends Gate
{
    public And(int inputs)
    {
        super(inputs);
    }
    
    public And(int inputs, int delay)
    {
        super(inputs, delay);
    }

    public void process()
    {
        boolean result;
        if (in != null && out != null )
        {
            result = in[0].getValue();
            for (int i = 1; i < in.length; i++)
            {
                result = result && in[i].getValue();
            }           
            out.setValue(result);
        }
        else
        {
            System.out.println("Error: Invalid input or output");
        }
    }
    
    public void process(int curTime)
    {
        boolean result;
        if (in != null && out != null )
        {
            result = in[0].getValue();
            for (int i = 1; i < in.length; i++)
            {
                result = result && in[i].getValue();
            }           
            //checking if that Event is necessary will be done in Event::propagate()
            new Event(out, delay+curTime, result);
            //System.out.println("       |new Event(" + (curTime+delay) + "): SET [Signal " + out.getName() + "] to [" + result + "]");
            
        }
        else
        {
            System.out.println("Error: Invalid input or output");
        }       
    }
    
}