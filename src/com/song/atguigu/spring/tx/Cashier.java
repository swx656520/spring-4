package com.song.atguigu.spring.tx;

import java.util.List;

public interface Cashier {

	void checkout(String userName, List<String> isbns);
}
