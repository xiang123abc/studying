package cn.ecut.设计模式.Singleton;

/**
 * 描述介绍
 * 类负责创建自己的对象，同时确保只有一个对象被创建
 * 
 * 特点（如何实现）：
 * 类构造器私有，(不能new)
 * 对外提供获取实例的静态方法  (可以调用静态方法）
 * 持有自己类型的属性  （方法返回）
 * @author xiang
 *
 */
public class Singleton {
	//懒汉模式(延迟初始化)       线程不安全，延迟初始化，严格意义上不是不是单例模式
	private static Singleton instance;  
    private Singleton (){}  
  
    public static Singleton getInstance() {  
    if (instance == null) {  
        instance = new Singleton();  
    }  
    return instance;  
    }  
    
  //  饿汉模式  (一开始就初始化)
    //线程安全，比较常用，但容易产生垃圾，因为一开始就初始化
//    private static Singleton instance = new Singleton();  
//    privatez Singleton (){}  
//    public static Singleton getInstance() {  
//    return instance;  
//    }
    
   // 双重锁模式
    //线程安全，延迟初始化。这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
    //进行了两次的判断，第一次是为了避免不要的实例，第二次是为了进行同步，避免多线程问题
    // 重排序 new Singleton() 故用 volatile
//    private volatile static Singleton singleton;  
//    private Singleton (){}  
//    public static Singleton getSingleton() {  
//    if (singleton == null) {  
//        synchronized (Singleton.class) {  
//        if (singleton == null) {  
//            singleton = new Singleton();  
//        }  
//        }  
//    }  
//    return singleton;  
//    }  
    
   
}
