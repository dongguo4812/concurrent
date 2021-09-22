package com.dongguo.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Dongguo
 * @date 2021/9/13 0013-16:49
 * @description:
 */
@Slf4j(topic = "d.CountDownLatchDemo4")
public class CountDownLatchDemo4 {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ExecutorService service = Executors.newCachedThreadPool();
        log.debug("begin");
        Future<Map<String,Object>> f1 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/order/{1}", Map.class, 1);
            return r;
        });
        Future<Map<String, Object>> f2 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/product/{1}", Map.class, 1);
            return r;
        });
        Future<Map<String, Object>> f3 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/product/{1}", Map.class, 2);
            return r;
        });
        Future<Map<String, Object>> f4 = service.submit(() -> {
            Map<String, Object> r =
                    restTemplate.getForObject("http://localhost:8080/logistics/{1}", Map.class, 1);
            return r;
        });

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
            System.out.println(f4.get());
            log.debug("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            service.shutdown();
        }
    }
}