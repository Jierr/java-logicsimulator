public class Node<T>
{
    T entry;
    Node<T> next;
    
    public Node(T entry)
    {
        this.entry = entry;
        next = null;
    }
    
    public Node()
    {
        entry = null;
        next = null;
    }
    
    public void set(T entry)
    {
        this.entry = entry;
    }
}