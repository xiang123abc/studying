package �߳�;
/**
 * ���������� ���� wait ,notify �ȷ��� ǰ  ��ǰ�̱߳���  �ȵõ� �ö���� ��  ����ͨ��synchronized ��Ȼ����synchronized �ڵ���
 *  wait ���ú� ��ʧ��   ���������߳� �������������� notify�� �� ��ִ����synchronized ������ ���� �ͷ������� ʱ ������߳̾Ϳ���ִ��wait()��Ĵ���
 * @author xiang
 *
 */
public class �ȴ�����WaitNotifiy {
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
		T1 t1 = new �ȴ�����WaitNotifiy().new T1();
		T2 t2 = new �ȴ�����WaitNotifiy().new T2();
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