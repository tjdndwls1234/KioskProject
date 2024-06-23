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
        payFrame.setSize(400, 300); // 적절한 크기 설정
        payFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫기 버튼 동작 설정

        JPanel PayCheckPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        // 헤더 라벨 추가
        gbc.gridx = 0;
        gbc.gridy = 0;
        PayCheckPanel.add(new JLabel("메뉴명"), gbc);
        gbc.gridx++;
        PayCheckPanel.add(new JLabel("단가"), gbc);
        gbc.gridx++;
        PayCheckPanel.add(new JLabel("수량"), gbc);
        gbc.gridx++;
        PayCheckPanel.add(new JLabel("합계"), gbc);
    
        // 각 항목 추가
        Set<FoodMenu> items = cart.getCartItems();
        for (FoodMenu menu : items) {
            gbc.gridx = 0;
            gbc.gridy++;
            int quantity = cart.getNum(menu);
    
            PayCheckPanel.add(new JLabel(menu.getState()==null?menu.getName():menu.getName()+menu.Size()+"("+menu.getState()+")" ), gbc);
            gbc.gridx++;
            PayCheckPanel.add(new JLabel(menu.getPrice() + "원"), gbc);
            gbc.gridx++;
            PayCheckPanel.add(new JLabel(quantity + "개"), gbc);
            gbc.gridx++;
            PayCheckPanel.add(new JLabel((quantity * Integer.valueOf(menu.getPrice())) + "원"), gbc);
        }
    
        // 총액 라벨 추가
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 4;
        JLabel paySum = new JLabel("총 " + cart.sumCart() + " 원");
        PayCheckPanel.add(paySum, gbc);
    
        // 결제 확인 라벨 추가
        gbc.gridy++;
        JLabel payCheck = new JLabel("결제 하시겠습니까?");
        PayCheckPanel.add(payCheck, gbc);
    
        // 버튼 패널 추가
        gbc.gridy++;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton yesPay = new JButton("Yes");
        JButton noPay = new JButton("No");
        buttonPanel.add(yesPay);
        buttonPanel.add(noPay);
        PayCheckPanel.add(buttonPanel, gbc);
    
        // 결제 액션 추가
        yesPay.addActionListener(new endPay(cart, cartPanel, payFrame));
        noPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "결제가 취소 되었습니다.", "결제 취소", JOptionPane.INFORMATION_MESSAGE);
                payFrame.dispose();
            }
        });
    
        payFrame.add(PayCheckPanel);
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
