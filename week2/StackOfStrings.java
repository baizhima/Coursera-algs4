public class StackOfStrings {
    private Stack<String> stack;
    public StackOfStrings()
    {
        stack = new Stack<String>();
    }
    public void push(String item)
    {
        stack.push(item);
    }
    public String pop()
    {
        String s = stack.pop();
        return s+" ";
    }
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    
    public static void main(String[] args)
    {
        StackOfStrings stack = new StackOfStrings();
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else stack.push(s);
            
        }
        StdOut.printf("\n");
    }
}
