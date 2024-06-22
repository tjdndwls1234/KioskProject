package cafeKiosk;
import java.util.HashMap;
import java.util.Set;

public class Cart{
  private HashMap<FoodMenu,Integer> cart;
  public Cart() {

  cart = new HashMap<>();
}
  public void addCart(FoodMenu menu, int num){
      cart.put(menu,num);
  }
  public int sumCart(){
    int sum=0;
    for (FoodMenu i: cart.keySet()){
      sum+=Integer.parseInt(i.getPrice())*cart.get(i);
    }
    return sum;
  }
  public void deleteCart(FoodMenu menu){
    cart.remove(menu);
  } 
  ///item 주기
  public Set<FoodMenu> getCartItems() {
        return cart.keySet();
    }
  // 갯수 리턴 함수
  public int getNum(FoodMenu menu){
        return cart.get(menu);
  }
  public void clearCart() {
    cart.clear();
}
}
