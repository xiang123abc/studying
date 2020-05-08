package cn.ecut.设计模式.Adapter;

/**
 * 
 * 定义(目的)
	将一个类的接口转换成客户希望的另一个接口             (实现接口里的方法中 改造， 调用自己的 方法， 认为现有接口的方法不好，要改造)
	
	
 * 优点
	目标类和适配者类解耦，增加了类的透明性和复用性，同时系统的灵活性和扩展性都非常好，
	更换适配器或者增加新的适配器都非常方便，符合“开闭原则”
     缺点
	过多的使用适配器，会让系统非常零乱，不易整体进行把握。
	比如，明明看到调用的是A接口，其实内部被适配成了B接口的实现
	
应用案例
	连接工具JDBC，JDBC给出一个客户端通用的抽象接口
	每一个具体数据库引擎（如SQL Server、Oracle、MySQL等）的JDBC驱动软件都是一个
	介于JDBC接口和数据库引擎接口之间的适配器软件
应用场景
	后期维护，由于不同的厂家不同的产品以及不同的开发人员
	调用第三方组件
用途
	想要复用一些现有的类，但是接口与复用环境要求不一致
 * @author xiang
 *
 */
//	适配器
public class Adapter implements Target{

	//持有源接口对象
    private Adaptee adaptee;
    
    /**
     * 构造方法，传入需要被适配的对象
     * @param adaptee
     */
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * 重写目标接口的方法，以适应客户端的需求
     */
	@Override
	public void request() {
		//调用源接口的方法
        System.out.println("适配器包装源接口对象，调用源接口的方法");
        adaptee.specifiRequest();
	}

}
