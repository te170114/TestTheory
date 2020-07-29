package com.example.demo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class UserTest {
	static User user = null;
	@BeforeAll
	static void テスト処理前() {
		user = new User("1001");
	}
	@Test
	void 正常系_ユーザー管理コード登録参照(){
		user.setCode("2002");
		assertThat(user.getCode()).isEqualTo("2002");
	}
	@Test
	void 正常系_名前登録参照() {
		user.setName("たけし");
		assertThat(user.getName()).isEqualTo("たけし");
	}
	@Test
	void 正常系_年齢登録参照() {
		user.setAge(20);
		assertEquals(user.getAge(),20);
	}
	@Test
	void 異常系_範囲外年齢登録() {
		user.setAge(-1);
		assertThat(user.getAge()).isEqualTo(-1);
	}
	@Test
	void 異常系_範囲外年齢登録2() {
		user.setAge(1000000);
		assertThat(user.getAge()).isLessThan(100);
	}
	@AfterAll
	static void テスト後処理() {
		user = null;
	}
}
