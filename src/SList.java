public class SList
{
    private Node<Signal> first, last;
    
    public SList()
    {
        first = last = null;
    }
    
    public void add(Signal entry)
    {
        if (first == null)
        {
            first = new Node<Signal>(entry);
            last = first;
        }
        else
        {
            last.next = new Node<Signal>(entry);
            last = last.next;
        }
    }
    
    public void reset()
    {
        Node<Signal> curr = first;
        while (curr != null)
        {
            curr.entry.setChanges(0);
            curr = curr.next;
        }
    }
}