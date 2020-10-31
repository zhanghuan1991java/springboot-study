package util;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;

public class CommonTest {

    @Test
    public void testGeneId(){
        System.out.println("randomUUID:"+IdUtil.randomUUID());
        System.out.println("fastSimpleUUID:"+IdUtil.fastSimpleUUID());
        System.out.println("simpleUUID:"+IdUtil.simpleUUID());
        System.out.println("fastUUID:"+IdUtil.fastUUID());
    }
}
