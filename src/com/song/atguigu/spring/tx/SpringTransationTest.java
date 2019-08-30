package com.song.atguigu.spring.tx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTransationTest {
	
	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao = null;
	
	private BookShopService bookShopService = null;
	private Cashier cashier = null;
	

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = (BookShopDao) ctx.getBean("bookShopDao");
		bookShopService = ctx.getBean(BookShopService.class);
		cashier = ctx.getBean(Cashier.class);
	}
	@Test
	public void testTransactionlPropagation(){
		
//		List<String> isbns = new ArrayList<String>();
//		isbns.add("1");
//		isbns.add("2");
		cashier.checkout("Tom", Arrays.asList("1", "2"));
	}
	@Test
	public void testBookShopService(){
		bookShopService.purchase("Tom", "1");
	}
	
	@Test
	public void testFindBookPriceByIsbn() {
		BigDecimal price = bookShopDao.findBookPriceByIsbn("1");
		System.out.println(price);
		//assertEquals(price, 100);
	}
	@Test
	public void testUpdateBookStock() {
		bookShopDao.updateBookStock("1");		
	}
	
	@Test
	public void testUpdateUserAccount() {
		bookShopDao.updateUserAccount("Tom", BigDecimal.valueOf(200));		
	}
	
	
}
