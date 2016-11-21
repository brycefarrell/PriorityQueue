/**
 * @author Bryce Farrell
 * 
 * @version Program 3
 * @version PriorityQueue Class
 */

import java.util.*;
public class PriorityQueue<T extends Comparable<? super T>> implements SimpleQueue<T>
{   
    
    private ArrayList<T> arr;
    private boolean isMax;

    //contstructors
    public PriorityQueue()
    {
        arr = new ArrayList<T>();
        arr.add(null);
        isMax = false;
    }
    
    public PriorityQueue(boolean isMax)
    {
        arr = new ArrayList<T>();
        arr.add(null);
        this.isMax = isMax;
    }

    public PriorityQueue(T[] arr, int size)
    {
        isMax = false;
        this.arr = new ArrayList<T>();

        
        this.arr.add(null);
        
        for (int i = 0; i < size; i++)
        {
            this.enqueue(arr[i]); 
            
        }

    }
    public PriorityQueue(T[] arr, int size, boolean isMax)
    {
        this.isMax = isMax;
        this.arr = new ArrayList<T>();
   
        this.arr.add(null);
        for (int i = 0; i < size; i++)
        {
            this.enqueue(arr[i]);
        }

    }



    //operator methods
    public T dequeue()
    {
        if (this.size() == 0)
        {
            throw new NoSuchElementException();
        }


        int child;
        int x = 1;
        T rv = this.peek();
        int index = this.size();
        T lastElement = arr.get(index);
        arr.remove(index);
        index = this.size();
 
        if (isMax)
        {
            while (2*x <= index)
            {
                child = 2*x;
                if ((child < index) &&(arr.get(child+1).compareTo(arr.get(child)) > 0))
                {
                    child++;
                }

                if (lastElement.compareTo(arr.get(child)) >= 0)
                {
                    break;
                }
                else
                {
                    arr.set(x, arr.get(child));
                    x = child;
                }
            }

            if (index > 0)
            {
                arr.set(x, lastElement);
            }
            
        }
        else
        {
            while (2*x <= index)
            {
                child = 2*x;
                if ((child < index) &&(arr.get(child+1).compareTo(arr.get(child)) < 0))
                {
                    child++;
                }

                if (lastElement.compareTo(arr.get(child)) <= 0)
                {
                    break;
                }
                else
                {
                    arr.set(x, arr.get(child));
                    x = child;
                }
            }

            if (index > 0)
            {
                arr.set(x, lastElement);
            }
        }
        return rv;
    }
    public void enqueue(T element)
    {
        if (isMax)
        {
            int k = this.size() + 1;
            arr.add(arr.get(k/2));
            while (k > 1 && (element.compareTo(arr.get(k/2)) > 0))
            {
                arr.set(k, arr.get(k/2));
                k = k/2;
            }
            arr.set(k, element);
 
        }
        else
        {
            int k = this.size() + 1;
            arr.add(arr.get(k/2));
            while (k > 1 && (element.compareTo(arr.get(k/2)) < 0))
            {
                arr.set(k, arr.get(k/2));
                k = k/2;
            }
            arr.set(k, element);
        }
     
    }


    public T peek()
    {
        if (this.size() == 0)
        {
            throw new NoSuchElementException();
        }
        return arr.get(1);
    }
    public int size()
    {
        return arr.size() - 1;
    }
    
    //static methods
    public static <E extends Comparable<? super E>> void sort(E[] arr, int size)
    {
        PriorityQueue<E> queue = new PriorityQueue<E>(arr, size);
        for (int x = 0; x < size; x++)
        {
            arr[x] = queue.dequeue();
        }
    }

    public static <E extends Comparable<? super E>> E kth(E[] arr, int size, int k)
    {
        E rv;
        int NK1 = size - k + 1;
        if (k <= NK1)
        {
            PriorityQueue<E> queue = new PriorityQueue<E>(arr, k, false);
            for (int i = k; i < size; i++)
            {
                if (arr[i].compareTo(queue.peek()) > 0)
                {
                    queue.dequeue();
                    queue.enqueue(arr[i]);
                }
            }
            rv = queue.peek();
        }
        else
        {
            PriorityQueue<E> queue = new PriorityQueue<E>(arr, NK1, true);
            for (int i = NK1; i < size; i++)
            {
                if (arr[i].compareTo(queue.peek()) < 0)
                {
                    queue.dequeue();
                    queue.enqueue(arr[i]);
                }
            }
            rv = queue.peek();
        }

        return rv;

    }





}
