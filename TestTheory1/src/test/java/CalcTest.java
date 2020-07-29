import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.Calc;

public class CalcTest {
	static Calc calc = null;
	@BeforeAll
	static void テスト前処理() {
	    calc = new Calc();
	}
	@Test
	void addテスト_正常() {
	    assertEquals(calc.add(1, 3), 4);
	    assertThat(calc.add(1, 3)).isEqualTo(4);
	    calc.sub(5, 3);
	    calc.mult(8, 2);
	    /*assertThatThrownBy(() -> {
	    	calc.div(4,0);
	    })
	    //発生したExceptionのクラス
	    .isInstanceOf(ArithmeticException.class)
	    //エラーメッセージに特定の文字列が含まれるか
	    .hasMessageContaining("/ by zero");*/
	    
	    //when
	    Throwable thrown = catchThrowable(() -> {
	    	calc.div(4,0);
	    });
	    //then
	    assertThat(thrown)
	    .isInstanceOf(ArithmeticException.class)
	    .hasMessageContaining("/ by zero");
	}
	@AfterAll
	static void テスト後処理() {
	    calc = null;
	}
	
	

}
