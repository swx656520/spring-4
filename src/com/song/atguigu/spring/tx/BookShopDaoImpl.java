package com.song.atguigu.spring.tx;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public BigDecimal findBookPriceByIsbn(String isbn) {

		String sql = "SELECT price FROM book WHERE isbn = ?";		
		BigDecimal bigDecimal = jdbcTemplate.queryForObject(sql, BigDecimal.class,isbn);
		
		return bigDecimal;
	}


	@Override
	public void updateBookStock(String isbn) {
		String sql2 = "SELECT stock from book_stock where isbn = ?";
		int count = jdbcTemplate.queryForObject(sql2, int.class, isbn);
		if(count == 0){
			throw new BookStockExecption("---->>¿â´æ²»×ã£¡");
		}
		String sql = "UPDATE book_stock SET stock = stock -1 where isbn = ?";
		jdbcTemplate.update(sql, isbn);
	}

	@Override
	public void updateUserAccount(String userName, BigDecimal price) {
		String sql2 = "SELECT balance from account where user_name = ?";
		BigDecimal balance = jdbcTemplate.queryForObject(sql2,BigDecimal.class, userName);
		if(balance.compareTo(price) == -1){
			throw new UserAccountExecption("---->>Óà¶î²»×ã£¡");
		}
		String sql = "UPDATE account SET balance = balance - ? where user_name = ?";
		jdbcTemplate.update(sql, price, userName);
	}

}
