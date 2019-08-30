package com.song.atguigu.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier {
	
	@Autowired
	private BookShopService bookShopService;
	
	@Transactional
	@Override
	public void checkout(String userName, List<String> isbns) {
		
		//¹ºÂò¶à±¾Êé
		for(String isbn : isbns){
			bookShopService.purchase(userName, isbn);
		}
	}

}
