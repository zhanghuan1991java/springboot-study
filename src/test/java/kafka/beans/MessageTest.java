package kafka.beans;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.didispace.kafka.beans.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MessageTest {

    @Test
    public void testToString(){
        Message msg = new Message();
        msg.setId(IdUtil.randomUUID());
        msg.setMsg("Hello Message Test!");
        msg.setSendTime(DateUtil.now());
        log.info(msg.toString());
    }

    @Test
    public void testToStringPretty(){
        Message msg = new Message();
        msg.setId(IdUtil.randomUUID());
        msg.setMsg("Hello Message Test!");
        msg.setSendTime(DateUtil.now());
        log.info(msg.toStringPretty());
    }
}
