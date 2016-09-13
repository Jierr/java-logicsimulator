public class FF extends Gate
{
    protected boolean state;
    protected boolean prevClk;
    public FF(int delay)
    {
        super(2, delay);
        state = false;
        prevClk = false;
    }
    
    public void process()
    {
        if (super.in != null && super.out != null )
        {
            if (prevClk && super.in[0].getValue()) //state is set with rising!!!! edge
                state = super.in[1].getValue();
            prevClk = super.in[0].getValue();
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
            if (!prevClk && super.in[0].getValue())//state is set with rising!!!! edge
                state = super.in[1].getValue();//set state = data
            prevClk = super.in[0].getValue();
            
            //checking if that Event is necessary will be done in Event::propagate()
            new Event(out, delay+curTime, state);
        }
        else
        {
            System.out.println("Error: Invalid input or output");
        }       
    }
}