package �߳�;

public class �߳�ͬ�� implements Runnable{
	
	static �߳�ͬ�� instance  =  new �߳�ͬ��();
	static int i = 0;
	@Override
	public void run() {
		for(int  j = 0;j<100000;j++) {
//			synchronized(instance) {
//				i++;
//			}
			increase();
		}
	}
	public synchronized void increase() {
		i++;
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread (instance);//
		Thread t2 = new Thread (instance);// ������ͬһ����ǰ�����ʵ������Ȼ������ synchronized ���η���
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}

}

