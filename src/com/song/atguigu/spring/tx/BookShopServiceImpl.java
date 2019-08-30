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
	
	//1.������Ϊ��������� Ĭ�ϣ�propagation=Propagation.REQUIRED ʹ�������������
	//propagation=Propagation.REQUIRES_NEW ʹ���Լ����������
	//2.������ƣ�ʹ��isolation �ƶ�����ĸ��뼶�𣺳��� Isolation.READ_COMMITTED
	//3.�ع���Ĭ�������spring����������ʱ�쳣���лع���
	//��noRollbackForָ����Щ�쳣�����лع���
	//4.ֻ����ʹ��readOnly ָ������Ϊֻ�����ԡ����԰������ݿ������Ż�����
	//5.��ʱ��ʹ��timeout ָ��ǿ�ƻع�֮ǰ������ռ�õ�ʱ�䡣
	@Transactional(propagation=Propagation.REQUIRED
//			,propagation=Propagation.REQUIRES_NEW,
//			isolation=Isolation.READ_COMMITTED,
//			noRollbackFor={UserAccountExecption.class},
//			readOnly=false,
//			timeout=3
			)
	@Override
	public void purchase(String userName, String isbn) {
		//��ȡ��ĵ���
		BigDecimal price = bookShopDao.findBookPriceByIsbn(isbn);
		//���¿��
		bookShopDao.updateBookStock(isbn);
		//�������
		bookShopDao.updateUserAccount(userName, price);
	}

}
