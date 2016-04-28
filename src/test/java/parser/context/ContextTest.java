package parser.context;

import lombok.Data;

import org.junit.Test;

public class ContextTest {
	private Lala lala;

	@Data
	class Lala {
		private String msg;
		private int a;

		public Lala(String msg) throws IllegalArgumentException {
			if (msg != null) {
				this.msg = msg;
				this.a = msg.length();
			}
			throw new IllegalArgumentException();
		}
	}

	@Test
	public void lala() {
		try {
			lala = new Lala("Hola");
		} catch (Exception e) {
			System.out.println(lala);
		}
//		System.out.println("Int " + lala.getA());
//		System.out.println("Msg " + lala.getMsg());
	}
}
