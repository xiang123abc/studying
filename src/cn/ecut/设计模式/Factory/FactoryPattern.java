package cn.ecut.设计模式.Factory;

/**
 * 1. 简单工厂模式(Simple Factory)

  2. 工厂方法模式(Factory Method)

  3. 抽象工厂模式(Abstract Factory)
 */
/**
 * 定义
	（工厂方法模式，又称工厂模式、多态工厂模式和虚拟构造器模式），
	---通过工厂类创建对象的公共接口，而子类则负责生成具体的对象
   优点：
   	一个调用者想创建一个对象，只要知道其名称就可以了。
   	屏蔽产品的具体实现，调用者只关心产品的接口。
 缺点：
 每次增加一个产品时，都需要增加一个具体类，使得系统中类的个数成倍增加，增加了系统的复杂度
主要作用
	将类的实例化（具体产品的创建）延迟到工厂类的子类（具体工厂）中完成，即由子类来决定应该实例化（创建）哪一个类。
 * @author xiang
 *
 */

//步骤5：外界通过调用具体工厂类的方法，从而创建不同具体产品类的实例
 class FactoryPattern {
	public static void main(String[] args){
        //客户要产品A
        FactoryA mFactoryA = new FactoryA();
        mFactoryA.Manufacture().Show();
 
        //客户要产品B
        FactoryB mFactoryB = new FactoryB();
        mFactoryB.Manufacture().Show();
    }

}
