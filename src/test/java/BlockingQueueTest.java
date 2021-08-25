import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Dongguo
 * @date 2021/8/24 0024-20:36
 * @description:
 */
public class BlockingQueueTest {
    @Test
    public void test1() {
        //创建容量为3阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //插入
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //检查
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue);
    }

    @Test
    public void test2() {
        //创建容量为3阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //插入
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //检查
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue);
        //插入第四个元素
        System.out.println(blockingQueue.add("d"));
    }

    @Test
    public void test3() {
        //创建容量为3阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //插入
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue);

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    @Test
    public void test4() {
        //创建容量为3阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //插入
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    @Test
    public void test5() {
        //创建容量为3阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //插入
        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test6() {
        //创建容量为3阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //插入
        try {
            System.out.println(blockingQueue.offer("a"));
            System.out.println(blockingQueue.offer("b"));
            System.out.println(blockingQueue.offer("c"));
            System.out.println(blockingQueue.offer("d", 3, TimeUnit.SECONDS));//最多阻塞3秒后退出
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}