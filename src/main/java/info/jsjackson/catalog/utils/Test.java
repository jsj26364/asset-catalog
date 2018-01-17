package info.jsjackson.catalog.utils;

public class Test {

	/*
	 * Demonstrating call back functions 
	 * https://stackoverflow.com/questions/19405421/what-is-a-callback-method-in-java-term-seems-to-be-used-loosely 
	 * 
	 */
	public static void main(String[] args) throws  Exception {
        new Test().doWork(new Callback() { // implementing class            
            @Override
            public void call() {
                System.out.println("callback called");
            }
        });
    }

    public void doWork(Callback callback) {
        System.out.println("doing work");
        callback.call();
    }

    public interface Callback {
        void call();
    }
    
}
