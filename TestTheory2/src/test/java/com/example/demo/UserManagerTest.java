package com.example.demo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserManagerTest {
	static UserManager um1 = null;
	static UserManager um2 = null;
	static User user1 = null;
	static User user2 = null;
	@BeforeAll
	static void テスト処理前() {
		um1 = UserManager.getInstance();
		um2 = UserManager.getInstance();
		user1 = new User("1111");
		user2 = new User("2222");
	}
	
	@Test
	void 正常系_UserManagerインスタンス同一() {
		assertThat(um1)
		.isSameAs(um2);
	}
	@Test
	void 正常系_userList登録参照() {
		um1.setUserToList(user1);
		um1.setUserToList(user2);
		assertThat(um1.getUserList()).contains(user1,user2);
	}
	@Test
	void 正常系_userMap登録参照() {
		um1.setUserToMap(user1);
		um1.setUserToMap(user2);
		assertThat(um1.getUserMap()).containsKeys("1111","2222");
	}
	@Test
	void 正常系_user全削除() {
		um1.setUserToList(user1);
		um1.setUserToMap(user1);
		um1.deleteAllUser();
		assertThat(um1.getUserList()).isEmpty();
		assertThat(um1.getUserMap()).isEmpty();
	}
	@Test
	void 正常系_code指定user削除() {
		um1.deleteUser("1111");
		assertThat(um1).isNotIn("1111");
	}
	@Test
	void 異常系_ユーザー削除されないバグ() {
		um1.setUserToList(user1);
		um1.setUserToList(user1);
		
		um1.deleteUser(user1.getCode());
		assertThat(um1.getUserList()).isNotIn(user1.getCode());
	}
	@Test
	@Order(1)
	void 正常系_MapList初期生成() {
		assertThat(UserManager.getInstance()).isNotNull();
		assertThat(um1.getUserList()).isNotNull().isEmpty();
		assertThat(um1.getUserMap()).isNotNull().isEmpty();
	}
	@Test
	void 正常系_List登録順序保持() {
		um1.setUserToList(user1);
		um1.setUserToList(user2);
		assertThat(um1.getUserList()).contains(user1,user2);
	}
	@Test
	void 正常系_Mapキー確認() {
		um1.setUserToMap(user1);
		um1.setUserToMap(user2);
		assertThat(um1.getUserMap()).containsKeys("1111","2222");
	}
	@AfterAll
	static void テスト処理後() {
		um1 = null;
		user1 = null;
	}
}
