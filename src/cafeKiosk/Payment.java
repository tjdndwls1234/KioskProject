package cafeKiosk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Set;



public class Payment implements ActionListener{
  private Cart cart;
  private JPanel cartPanel;
  
  public Payment(Cart cart,JPanel cartPanel){
    this.cart=cart;
    this.cartPanel=cartPanel;
  }
  @Override
    public void actionPerformed(ActionEvent e) {
        // 새로운 프레임 생성
        JFrame payFrame = new JFrame("결제하기");
        payFrame.setSize(300, 200); // 적절한 크기 설정
        payFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫기 버튼 동작 설정

        JPanel PayCheckPanel = new JPanel();
        PayCheckPanel.setLayout(new BoxLayout(PayCheckPanel, BoxLayout.Y_AXIS));
        
        Set<FoodMenu> items = cart.getCartItems();
        for (FoodMenu menu : items) {
            int quantity = cart.getNum(menu);
            JLabel menuLabel = new JLabel(menu.getName() + " (" + menu.getState() + "): " + quantity + "개");
            PayCheckPanel.add(menuLabel);
        }

        
        JLabel paySum= new JLabel(String.valueOf(cart.sumCart())+" 원");
        JLabel payCheck= new JLabel("결제 하시겠습니까?");
        JButton yesPay= new JButton("Yes");
        JButton NoPay= new JButton("No");
        JPanel buttonPanel = new JPanel();
        //결제 액션
        yesPay.addActionListener(new endPay(cart,cartPanel,payFrame));
        NoPay.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "결제가 취소 되었습니다.", "결제 취소", JOptionPane.INFORMATION_MESSAGE);
            payFrame.dispose();
          }
        });
        ///
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        buttonPanel.add(yesPay);
        buttonPanel.add(NoPay);
        ///추가하기
        PayCheckPanel.add(paySum);
        PayCheckPanel.add(payCheck);
        PayCheckPanel.add(buttonPanel);
        payFrame.add(PayCheckPanel);
        //가운데 정렬
        payFrame.setLocationRelativeTo(null);
        payFrame.setVisible(true);
    }

  
  
}
class endPay implements ActionListener{
  private Cart cart;
  private JPanel cartPanel;
  private JFrame payFrame;
  endPay(Cart cart, JPanel cartPanel, JFrame payFrame){
    this.cart=cart;
    this.cartPanel=cartPanel;
    this.payFrame=payFrame;
  }
  @Override
  public void actionPerformed(ActionEvent e){
    cart.clearCart(); // cartPanel을 초기화
    //장바구니도 초기화
    OrderButtonListener.updateCartPanel(cart, cartPanel);
    payFrame.dispose();
    JOptionPane.showMessageDialog(null, "결제 되었습니다.", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
  }


}
