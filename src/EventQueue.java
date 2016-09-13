public class EventQueue
{
    protected Node<Event> first;
    
    public EventQueue()
    {
        first = null;
    }
    
    public boolean hasMore()
    {
        if(first == null)
            return false;
        else
            return true;
    }
    
    public Event getFirst()
    {
        if(first == null)
            return null;
        else 
        {
            Event result = first.entry;
            first = first.next;
            return result;
        }
    }
    
    //insert sorted by time
    public void add(Event event)
    {
        if(first == null)
        {
            first = new Node<Event>(event);
        }
        else
        {
            Node<Event> curr = first;
            Node<Event> prev = null;
            while ((curr != null) && (curr.entry.getTime() <= event.getTime()))
            {
                prev = curr;
                curr = curr.next;
            }
            if(prev != null)
            {
                prev.next = new Node<Event>(event);
                prev.next.next = curr;
            }
            else
            {
                Node<Event> tmp = new Node<Event>(event);
                tmp.next = first;
                first = tmp;
            }
        }
    }
    /*
    public Event find(Signal s)
    {
        Event result = null;
        Node<Event> curr = first;
        while (curr != null)
        {
            if (curr.entry.getSignal() == s)
                result = curr.entry;
            curr = curr.next;
        }
        
        return result;
    }
    */
}