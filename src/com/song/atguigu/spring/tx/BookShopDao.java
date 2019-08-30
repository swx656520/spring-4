package com.song.atguigu.spring.tx;

import java.math.BigDecimal;

public interface BookShopDao {
	
	BigDecimal findBookPriceByIsbn(String isbn);
	
	void updateBookStock(String isbn);
	
	void updateUserAccount(String userName, BigDecimal price);
}
