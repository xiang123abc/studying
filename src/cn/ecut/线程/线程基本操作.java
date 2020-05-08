package cn.ecut.线程;

public class 线程基本操作 {
	
	public static void main(String[] args) throws Exception {
		test7();
	}
	/**
	 * 实现线程  方法1   继承 Thread类
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
	 * 实现线程  方法2 实现Runnable接口,重写run方法
	 */
	public static void test2() {
		new 线程基本操作().new RunnableImp().run();
	}
	/**
	 * 线程终止， stop , 不推荐
	 */
	public static void test3() {
		new 线程基本操作().new ThreadEx().stop();
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
					//设置中断状态，抛出异常后会清空中断标记位
					Thread.currentThread().interrupt();
				}
			}
			
		}
	}
	/**
	 * 线程中断
	 */
	public static void test4()  {
		ThreadEx2 t = new 线程基本操作().new ThreadEx2();
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
	 * suspend() 挂起    resume()  继续执行  都已经废弃
	 *  suspend() 不会释放锁，如果加锁发生在resume()之前，则死锁发生
	 *  
	 * @throws InterruptedException
	 */
	public static void test5() throws InterruptedException {
		ThreadEx3 t1 = new 线程基本操作().new ThreadEx3("t1");
		ThreadEx3 t2 = new 线程基本操作().new ThreadEx3("t2");
		t1.start();
		Thread.sleep(100);//保证t1 已经挂起
		t2.start();
		t1.resume();
		t2.resume();//可能t2先执行resume,然后执行run中  挂起 suspend ，t2线程 未结束
		t1.join();//等待t1结束
		t2.join();//等待t2结束
	}
	/**
	 * 谦让 static  yield
	 * @throws InterruptedException 
	 */
	public static void test6() throws InterruptedException {
		ThreadEx3 t1 = new 线程基本操作().new ThreadEx3("t1");
		t1.start();
		t1.join();//等待t1结束
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
	 * 线程优先级 
	 * Thread.MIN_PRIORITY  1
	 * Thread.MAX_PRIORITY  10
	 */
	public static void test7() {
		ThreadEx4 t1 = new 线程基本操作().new ThreadEx4();
		ThreadEx5 t2 = new 线程基本操作().new ThreadEx5();
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		
	}
	
}
