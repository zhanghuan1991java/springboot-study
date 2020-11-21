package util;

import cn.hutool.core.util.IdUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class CommonTest {

    @Test
    public void testGeneId(){
        System.out.println("randomUUID:"+IdUtil.randomUUID());
        System.out.println("fastSimpleUUID:"+IdUtil.fastSimpleUUID());
        System.out.println("simpleUUID:"+IdUtil.simpleUUID());
        System.out.println("fastUUID:"+IdUtil.fastUUID());
    }

    @Test
    public void testMock(){
        ArrayList list = Mockito.mock(ArrayList.class);
        list.add("123");
        Mockito.doReturn("234").when(list).get(0);
        Assert.assertEquals("234",list.get(0));
    }
}
