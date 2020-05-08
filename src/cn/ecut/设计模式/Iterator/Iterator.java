package cn.ecut.设计模式.Iterator;

//定义：提供一种方法访问一个容器对象中各个元素，而又不暴露该对象的内部细节。
/**
 * 
 * @author xiang
 *
 */
//创建抽象迭代器接口
public interface Iterator {
	Object next();
    boolean hasNext();
}
