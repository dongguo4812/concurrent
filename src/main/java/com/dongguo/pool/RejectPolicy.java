package com.dongguo.pool;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-9:03
 * @description:
 */
@FunctionalInterface // 拒绝策略
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}