package cn.ecut.设计模式.Iterator;

/**
 定义（目的）：
  	提供一种方法来访问聚合对象，而不用暴露这个对象的内部表示
  优点
  	支持以不同方式遍历一个聚合对象
	迭代器简化了聚合类的接口.
	增加新的聚合类和迭代器无需修改原有代码，满足“开闭原则”的要求
 缺点
	增加新的聚合类需要增加新的具体迭代器类，类的个数成对增加，增加了系统的复杂性
应用场景：
	 java中的集合有用到
抽象迭代器接口（Iterator）.
具体迭代器（ConcreteIterator）.
抽象聚合接口（Aggregate）.
具体聚合（ConcreteAggregate）.
 * @author xiang
 *
 */
public class Main {

	public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator iterator = aggregate.iterator();
        if(iterator.hasNext()){
            iterator.next();
        }
    }
}
