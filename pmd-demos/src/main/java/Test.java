
public class Test {

	@SuppressWarnings("unused")
	private int unusedProperties = 0;
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		int unusedLocalVariable = 0;
		
		for (int i = 1; i < 101; i ++) {
			System.out.println(i);
		}
		
		System.out.println("============================================");

		for (int i = 1; i < 101; i ++) {
			System.out.println(i);
		}
		
		System.out.println("============================================");
		
		float oldPrice = 1.1f;
		float newPrice = 1.2f - 0.01f;
		
		if (oldPrice == newPrice) {
			System.out.println("the same");
		}
	}
}
