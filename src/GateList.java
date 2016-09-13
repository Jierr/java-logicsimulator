public class GateList
{
    Node<Gate> head;
    Node<Gate> last;
    
    public GateList()
    {
        head = null;
        last = null;
    }
    
    public void addGate (Gate gate)
    {
        if (head == null)
        {
            head = new Node<Gate>(gate);
            last = head;
        }
        else
        {
            last.next = new Node<Gate>(gate);
            last = last.next;
        }
    }
    
    public void update()
    {
        Node<Gate> curr = head;
        while (Signal.stable && curr != null)
        {
            curr.entry.process();
            curr = curr.next;
        }
    }
    
    public void update(int curTime)
    {
        Node<Gate> curr = head;
        while (curr != null)
        {
            curr.entry.process(curTime);
            curr = curr.next;
        }
    }
}
