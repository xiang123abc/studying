package �߳�;

public class �̻߳������� {
	
	public static void main(String[] args) throws Exception {
		test7();
	}
	/**
	 * ʵ���߳�  ����1   �̳� Thread��
	 */
	public void test1() {
		Thread t1 = new Thread() {
			@Override
			public void run () {
				while(true) {
					int i = 0;
					System.out.println(i++);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
	}
	class ThreadEx extends Thread{
		@Override
		public void run() {
			while(true) {
				System.out.println("a");
			}
		}
	}
	class RunnableImp implements Runnable{
		@Override
		public void run() {
			while(true) {
				int i = 0;
				System.out.println(i++);
				if(i==3) {
					
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	};
	/**
	 * ʵ���߳�  ����2 ʵ��Runnable�ӿ�,��дrun����
	 */
	public static void test2() {
		new �̻߳�������().new RunnableImp().run();
	}
	/**
	 * �߳���ֹ�� stop , ���Ƽ�
	 */
	public static void test3() {
		new �̻߳�������().new ThreadEx().stop();
		while(true) {
			System.out.println("a");
		}
	}
	class ThreadEx2 extends Thread{
		@Override
		public void run() {
			int i = 0;
			while(true) {
				if(Thread.currentThread().isInterrupted()) {
					break;
				}
				System.out.println(i++);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.out.println("interrupt when sleep");
					//�����ж�״̬���׳��쳣�������жϱ��λ
					Thread.currentThread().interrupt();
				}
			}
			
		}
	}
	/**
	 * �߳��ж�
	 */
	public static void test4()  {
		ThreadEx2 t = new �̻߳�������().new ThreadEx2();
		t.start();
		try {
			Thread.sleep(100);
			t.interrupt();
			boolean flag = t.isInterrupted();
			System.out.println("isInterrupt:" +flag);
			System.out.println("end");
			//t.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	Object obj = new Object();
	class ThreadEx3 extends Thread{
		ThreadEx3(String name){
			super.setName(name);
		}
		@Override
		public void run() {
			synchronized(obj) {
				System.out.println("in "+getName());
				Thread.currentThread().suspend();
			}
		}
	}
	/**
	 * jps ,jstack 14472
	 * suspend() ����    resume()  ����ִ��  ���Ѿ�����
	 *  suspend() �����ͷ������������������resume()֮ǰ������������
	 *  
	 * @throws InterruptedException
	 */
	public static void test5() throws InterruptedException {
		ThreadEx3 t1 = new �̻߳�������().new ThreadEx3("t1");
		ThreadEx3 t2 = new �̻߳�������().new ThreadEx3("t2");
		t1.start();
		Thread.sleep(100);//��֤t1 �Ѿ�����
		t2.start();
		t1.resume();
		t2.resume();//����t2��ִ��resume,Ȼ��ִ��run��  ���� suspend ��t2�߳� δ����
		t1.join();//�ȴ�t1����
		t2.join();//�ȴ�t2����
	}
	/**
	 * ǫ�� static  yield
	 * @throws InterruptedException 
	 */
	public static void test6() throws InterruptedException {
		ThreadEx3 t1 = new �̻߳�������().new ThreadEx3("t1");
		t1.start();
		t1.join();//�ȴ�t1����
		Thread.yield();
	}
	class ThreadEx4 extends Thread{
		int i = 0;
		@Override
		public void run() {
			while(true) {
				i++;
				if(i>10000) {
					System.out.println("min pri");
					break;
				}
			}
		}
	}
	class ThreadEx5 extends Thread{
		int i = 0;
		@Override
		public void run() {
			while(true) {
				i++;
				if(i>1000) {
					System.out.println("max pri");
					break;
				}
			}
		}
	}
	/**
	 * �߳����ȼ� 
	 * Thread.MIN_PRIORITY  1
	 * Thread.MAX_PRIORITY  10
	 */
	public static void test7() {
		ThreadEx4 t1 = new �̻߳�������().new ThreadEx4();
		ThreadEx5 t2 = new �̻߳�������().new ThreadEx5();
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		
	}
	
}
