public class SnoarExample {

	static class innerClass {
		
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		innerClass InnerClass = new innerClass();
		
		@SuppressWarnings("unused")
		Integer num = null;
//		if (num.intValue() == 0) {
//			
//		}
		
		// performance
		Integer count = 10;
		// 
		// some other code
		//
		if (count == 10) {
			// TODO handle this case
		}
		
		new Integer(100).toString();
		Integer.toString(100);
		
		
		try {
			throw new RuntimeException();
		} catch (Exception e) {
			
		}
	}
}
