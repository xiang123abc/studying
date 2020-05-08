package cn.ecut.设计模式.Factory;

//步骤3： 创建具体产品类（继承抽象产品类）， 定义生产的具体产品；
 class ProductA extends Product{
	@Override
    public void Show() {
        System.out.println("生产出了产品A");
    }
}


//具体产品B类
class  ProductB extends  Product{

  @Override
  public void Show() {
      System.out.println("生产出了产品B");
  }
}