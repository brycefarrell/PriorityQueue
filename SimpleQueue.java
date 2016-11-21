/**
 * @author Bryce Farrell
 * 
 * @version Program 3
 * @version SimpleQueue Interface
 */


public interface SimpleQueue<T>
{   
    public T dequeue();
    public void enqueue(T element);
    public T peek();
    public int size();

}
