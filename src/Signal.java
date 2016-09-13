public class Signal
{
    public static int count = 0;
    public static boolean stable = true;
    public static SList all = null;
    String name;
    Gate outG;
    GateList inG;
    boolean value;
    int changes;
    

    public Signal(String name)
    {
        this.name = name;
        this.value = false;
        this.outG = null;
        this.inG = null;
        changes = 0;
        if (Signal.all == null)
        {
            Signal.all = new SList();
        }
        Signal.all.add(this);
    }
    
    public static void reset()
    {
        if (Signal.all != null)
            Signal.all.reset();
    }
    
    public void setChanges(int val)
    {
        changes = val;
    }
  
    public void addIn(Gate input)
    {
        /*temp.next = inG;
        inG = temp;*/        
        Signal.count++;
        if (inG == null)
        {
            inG = new GateList();
            inG.addGate(input);
        }
        else
        {
            inG.addGate(input);
        }
    }
    
    public void setOut(Gate out)
    {
        this.outG = out;
    }
      
    //No propagating when no change occured
    //With each change of the Signal value increase [changes]
    //reset [changes] when an instable state has been detected
    public void setValue(boolean val)
    {
        Signal.stable = true;
        System.out.format("%1$-30s %2$-30s %3$-20s %4$-10s\r\n",
                          "Valide Gatterdurchlaeufe: " + Signal.count*Signal.count,
                          " Gatterdurchlaeufe: " + this.changes + "--->",
                          name,
                          " = " + val);
        
        if (changes < (Signal.count * Signal.count))
        {
            if ((changes == 0) || (this.value != val))
            {
                changes++;
                this.value = val;
                propagate(inG);
            }
        }
        else
        {
            Signal.stable = false;
            System.out.println("Die Schaltung ist mit den angelegten Initialsignalen mit hoher Wahrscheinlichkeit instabil!");
        }
        
        //recursive ascension to inputsignal
        if (outG == null)
        {
            if (Signal.stable)
                System.out.println("Schaltung ist stabil!");
            Signal.reset();
        }
    }
    
    public void setValue(boolean val, int curTime)
    {
        value = val;
        if (inG != null)
            inG.update(curTime);
    }
    
    public boolean getValue()
    {
        return value;
    }
    
    public String getName()
    {
        return name;
    }
    
    private void propagate(GateList inputs)
    {
        if (inputs != null)
            inputs.update();
    }
}