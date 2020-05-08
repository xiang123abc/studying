package cn.ecut.设计模式.Iterator;

//具体聚合（ConcreteAggregate）.. 如  ArrayList, LinkedList
public class ConcreteAggregate implements Aggregate {

	@Override
	public Iterator iterator() {
		return new ConcreteIterator();
	}
	//具体迭代器（ConcreteIterator）
	private class ConcreteIterator implements Iterator {
        @Override
        public Object next() {
            System.out.println("ConcreteIterator next ...");
            return null;
        }
        @Override
        public boolean hasNext() {
            System.out.println("ConcreteIterator hasNext ....");
            return true;
        }
    }
}
