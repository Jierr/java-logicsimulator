public class Latch extends Gate
{
    protected boolean state;
    public Latch(int delay)
    {
        super(2, delay);
        state = false;
    }

    public void process()
    {
        if (super.in != null && super.out != null )
        {
            if (super.in[0].getValue())
                state = super.in[1].getValue();
            super.out.setValue(state);
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
            if (super.in[0].getValue())//enable == true
                state = super.in[1].getValue();//set state = data
            
            //checking if that Event is necessary will be done in Event::propagate()
            new Event(out, delay+curTime, state);
        }
        else
        {
            System.out.println("Error: Invalid input or output");
        }       
    }    
}