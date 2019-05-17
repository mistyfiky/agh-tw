// PKmon.java

class Producer extends Thread {
	private Buffer _buf;

	public void run() {
	  for (int i = 0; i < 100; ++i) {
		_buf.put(i);
	  }
	}
}
		
class Consumer extends Thread {
	private Buffer _buf;

	public void run() {
	  for (int i = 0; i < 100; ++i) {
		System.out.println(_buf.get());
	  }
	}
}
		
class Buffer {
  public void put(int i) {

  }

  public int get() {

  }
}

public class PKmon {
  public static void main(String[] args) {

  }
}
