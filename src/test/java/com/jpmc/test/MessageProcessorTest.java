package com.jpmc.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import com.jpmc.service.MessageProcessor;


public class MessageProcessorTest {

	@Test
	public void testProcessMessage() {
		MessageProcessor processer=new MessageProcessor();
		//MessageFormat1
		String msg="apple at 10p";
		assertEquals(true,processer.processMessage(msg));
        assertEquals("apples", processer.getProductType());
        assertEquals(0.10000000149011612, processer.getProductPrice());
        assertEquals(1, processer.getProductQuantiy());
        
      //MessageFormat2
        msg="20 sales of apples at 10p each";
        MessageProcessor processer1=new MessageProcessor();
        assertEquals(true,processer1.processMessage(msg));
        assertEquals("apples", processer1.getProductType());
        assertEquals(0.10000000149011612, processer1.getProductPrice());
        assertEquals(20, processer1.getProductQuantiy());
        
      //MessageFormat3
        msg="Add 20p apples";
        MessageProcessor processer2=new MessageProcessor();
        assertEquals(true,processer2.processMessage(msg));
        assertEquals("apples", processer2.getProductType());
        assertEquals(0.20000000298023224, processer2.getProductPrice());
        assertEquals("Add", processer2.getOperationType());
        
      //MessageFormat4
        msg="Divide 20p apples";
        MessageProcessor processer3=new MessageProcessor();
        assertEquals(false, processer3.processMessage(msg));
        
	}

}
