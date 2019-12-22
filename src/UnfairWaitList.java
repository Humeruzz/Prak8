import java.util.concurrent.ConcurrentLinkedQueue;

public class UnfairWaitList<E> extends WaitList<E> {
    public UnfairWaitList() {
    }

    public void remove(E element){
        ConcurrentLinkedQueue<E> temp = new ConcurrentLinkedQueue<>();
        while (!content.isEmpty()) {
            if (content.peek() == element) {
                content.poll();
                while (!content.isEmpty()) {
                    temp.offer(content.poll());
                }
                while (!temp.isEmpty()) {
                    content.offer(temp.poll());
                }
            } else {
                if (content.peek() == null) {
                    while (!temp.isEmpty()) {
                        content.offer(temp.poll());
                    }
                    break;
                }
                temp.offer(content.poll());
            }
        }
    }

    public void moveToBack(E element){
        ConcurrentLinkedQueue<E> temp = new ConcurrentLinkedQueue<>();
        while (!content.isEmpty()) {
            if (content.peek() == element) {
                content.offer(content.poll());
                while (!content.isEmpty()) {
                    temp.offer(content.poll());
                }
                while (!temp.isEmpty()) {
                    content.offer(temp.poll());
                }
            } else {
                if (content.peek() == null) {
                    while (!temp.isEmpty()) {
                        content.offer(temp.poll());
                    }
                    break;
                }
                temp.offer(content.poll());
            }
        }
    }
}
