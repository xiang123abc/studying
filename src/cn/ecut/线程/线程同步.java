package cn.ecut.线程;

public class 线程同步 implements Runnable{
	
	static 线程同步 instance  =  new 线程同步();
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
		Thread t2 = new Thread (instance);// 必须是同一个当前对象的实例，不然不能用 synchronized 修饰方法
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}

}

