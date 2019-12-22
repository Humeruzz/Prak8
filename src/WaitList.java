import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WaitList <E> implements IWaitList <E> {

    protected ConcurrentLinkedQueue<E> content;

    public WaitList() {
        content = new ConcurrentLinkedQueue<>();
    }

    public WaitList(ConcurrentLinkedQueue<E> c) {
        content = new ConcurrentLinkedQueue<>();
        this.content = c;
    }


    @Override
    public void add(E element) {
        this.content.offer(element);
    }

    @Override
    public E remove() {
        return content.poll();
    }

    @Override
    public boolean contains(E element) {
        ConcurrentLinkedQueue<E> temp = new ConcurrentLinkedQueue<>();
        while (!content.isEmpty()) {
            if (content.peek() == element) {
                while (!content.isEmpty()) {
                    temp.offer(content.poll());
                }
                while (!temp.isEmpty()) {
                    content.offer(temp.poll());
                }
                return true;
            } else {
                if (content.peek() == null) {
                    while (!temp.isEmpty()) {
                        content.offer(temp.poll());
                    }
                    return false;
                }
                temp.offer(content.poll());
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<E> c) {
        return c.containsAll(content);
    }

    @Override
    public boolean isEmpty() {
        if(content.peek() == null){
            return true;
        } else {
            return false;
        }
    }
}
