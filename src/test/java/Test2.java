import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author Dongguo
 * @date 2021/8/23 0023-16:35
 * @description:
 */
@Slf4j(topic = "c.Test1")
public class Test2 {
    @Test
    public void testLog(){
        log.debug("debug");
        log.error("error");
    }
}