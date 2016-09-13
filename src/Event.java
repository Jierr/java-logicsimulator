public class Event
{
    private static EventQueue queue = null;
    
    protected EType type;
    protected int time;
    protected Signal modS;
    protected boolean toSet;
    public Event()
    {
        type = EType.NONE;
        time = 0;
        modS = null;
        toSet = false;        
    }
    
    public Event(Signal in, int time, boolean val)
    {
        if(Event.queue == null)
            System.out.println("Error: Fehlende Eventqueue!");
        else
        {
            set(in, time, val);
            Event.queue.add(this);
        }
    }
    
    public void set(Signal in, int time, boolean val)
    {
        type = EType.SET;
        modS = in;
        this.time = time;
        toSet = val;
    }
    
    public static void setEventQueue(EventQueue queue)
    {
        Event.queue = queue;
    }
    
    public void propagate()
    {
        Event first;
        if (type == EType.SET)
        {
            //look if the latest change to the corresponding signal made, differs from current change
            //avoid senseless propagating
            if (modS.getValue() != toSet)
            {
                System.out.format("%1$-20s %2$-40s %3$-20s\r\n", ">Event(" + time + ")", "SET >>" + modS.getName() + "<<", toSet);
                modS.setValue(toSet, time);
            }
        }  
        else
        {
            System.out.println("\nError: A none SET-Event has been queued!\n");
        }            
    }
    
    public int getTime()
    {
        return time;
    }
    
    public Signal getSignal()
    {
        return modS;
    }
}