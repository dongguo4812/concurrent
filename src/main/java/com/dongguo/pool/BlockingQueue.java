package com.dongguo.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-8:17
 * @description: 自定义阻塞队列
 */
public class BlockingQueue<T> {
    // 1. 任务队列  双向队列
    private Deque<T> queue = new ArrayDeque<>();
    // 2. 锁
    private ReentrantLock lock = new ReentrantLock();
    // 3. 生产者条件变量
    private Condition fullWaitSet = lock.newCondition();
    // 4. 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();
    // 5. 容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    /**
     * 阻塞获取
     * @return
     */
    public T take(){
        lock.lock();
        try {
            //当队列为空，无法获取，等待非空
            while (queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //不为空获取队首元素
            T t = queue.removeFirst();
            //唤醒生产者
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }
    /**
     * 带超时时间阻塞获取
     * @return
     */
    public T poll(long timeout, TimeUnit unit){
        lock.lock();
        try {
            //统一时间单位纳秒
            long nanos = unit.toNanos(timeout);
            //当队列为空，无法获取，等待非空
            while (queue.isEmpty()){
                try {
                    // 返回值是剩余时间
                    if (nanos <= 0) {
                        return null;
                    }
                    //为了防止虚假唤醒导致超时时间不变
                    /**API示例
                     *  <pre> {@code
                     * boolean aMethod(long timeout, TimeUnit unit) {
                     *   long nanos = unit.toNanos(timeout);
                     *   lock.lock();
                     *   try {
                     *     while (!conditionBeingWaitedFor()) {
                     *       if (nanos <= 0L)
                     *         return false;
                     *       nanos = theCondition.awaitNanos(nanos);
                     *     }
                     *     // ...
                     *   } finally {
                     *     lock.unlock();
                     *   }
                     * }}</pre>
                     *@return an estimate of the {@code nanosTimeout} value minus
                      *         the time spent waiting upon return from this method.
                      *         A positive value may be used as the argument to a
                      *         subsequent call to this method to finish waiting out
                      *         the desired time.  A value less than or equal to zero
                      *         indicates that no time remains.
                     *
                     * 返回的nanos的值就等于传入的nanos的值减去awaitNanos方法执行的时间
                     */
                     nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //不为空获取队首元素
            T t = queue.removeFirst();
            //唤醒生产者
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }
    /**
     *  阻塞添加
     */
    public void put(T task) {
        lock.lock();
        try {
            //当队列满了，等待队列可用
            while (queue.size() == capcity) {
                try {
                    System.out.println(Thread.currentThread().getName()+" 等待加入任务队列："+task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" 加入任务队列："+task);
            //从队列尾部添加元素
            queue.addLast(task);
            //唤醒消费者
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 带超时时间阻塞添加
     * @param task
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capcity) {
                try {
                    if(nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }
    /**
     * 获得当前队列的大小
     * @return
     */
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     *拒绝策略添加
     * @param rejectPolicy
     * @param task
     */
    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            // 判断队列是否满
            if(queue.size() == capcity) {
                System.out.println(Thread.currentThread().getName()+" 任务队列满了执行策略："+task);
                rejectPolicy.reject(this, task);
            } else { // 有空闲
                System.out.println(Thread.currentThread().getName()+" 加入任务队列："+task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}