package cn.ecut.线程;
/**
 * 给加锁对象 调用 wait ,notify 等方法 前  当前线程必须  先得到 该对象的 锁  比如通过synchronized ，然后下synchronized 内调用
 *  wait 调用后， 丢失锁   ，当其他线程 给这个锁对象调用 notify后 且 在执行完synchronized 代码块后 真正 释放锁对象 时 ，这个线程就可以执行wait()后的代码
 * @author xiang
 *
 */
public class 等待唤醒WaitNotifiy {
	static Object obj = new Object();
	public class T1 extends Thread{
		@Override
		public void run() {
			//obj.wait();   error
			synchronized(obj) {
				System.out.println("t1 -start");
				try {
					System.out.println("t1 -wait");
					Thread.sleep(1000);
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t1 -end");
			}
			System.out.println("t2 run -end ");
		}
	}
	public class T2 extends Thread{
		@Override
		public void run() {
			synchronized(obj) {
				System.out.println("t2 -start");
				try {
					System.out.println("t2 -notify");
					obj.notify();
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("t2 -end");
			}
			System.out.println("t2 run -end ");
		}
	}
	public static void main(String[] args) {
		T1 t1 = new 等待唤醒WaitNotifiy().new T1();
		T2 t2 = new 等待唤醒WaitNotifiy().new T2();
		t1.start();
		t2.start();
	}
}
/**
t1 -start
t1 -wait
t2 -start
t2 -notify
t2 -end
t2 run -end 
t1 -end
t2 run -end 
*/