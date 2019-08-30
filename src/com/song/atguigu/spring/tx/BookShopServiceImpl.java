package com.song.atguigu.spring.tx;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;
	
	//1.传播行为：添加事务 默认：propagation=Propagation.REQUIRED 使用外层的事务机制
	//propagation=Propagation.REQUIRES_NEW 使用自己的事务机制
	//2.隔离机制：使用isolation 制定事务的隔离级别：常用 Isolation.READ_COMMITTED
	//3.回滚：默认情况下spring对所有运行时异常进行回滚，
	//用noRollbackFor指定哪些异常不进行回滚。
	//4.只读：使用readOnly 指定事务为只读属性。可以帮助数据库引擎优化事务。
	//5.超时：使用timeout 指定强制回滚之前事务所占用的时间。
	@Transactional(propagation=Propagation.REQUIRED
//			,propagation=Propagation.REQUIRES_NEW,
//			isolation=Isolation.READ_COMMITTED,
//			noRollbackFor={UserAccountExecption.class},
//			readOnly=false,
//			timeout=3
			)
	@Override
	public void purchase(String userName, String isbn) {
		//获取书的单价
		BigDecimal price = bookShopDao.findBookPriceByIsbn(isbn);
		//更新库存
		bookShopDao.updateBookStock(isbn);
		//更新余额
		bookShopDao.updateUserAccount(userName, price);
	}

}
