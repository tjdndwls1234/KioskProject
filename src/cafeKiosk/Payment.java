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
        // ���ο� ������ ����
        JFrame payFrame = new JFrame("�����ϱ�");
        payFrame.setSize(300, 200); // ������ ũ�� ����
        payFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ݱ� ��ư ���� ����

        JPanel PayCheckPanel = new JPanel();
        PayCheckPanel.setLayout(new BoxLayout(PayCheckPanel, BoxLayout.Y_AXIS));
        
        Set<FoodMenu> items = cart.getCartItems();
        for (FoodMenu menu : items) {
            int quantity = cart.getNum(menu);
            JLabel menuLabel = new JLabel(menu.getName() + " (" + menu.getState() + "): " + quantity + "��");
            PayCheckPanel.add(menuLabel);
        }

        
        JLabel paySum= new JLabel(String.valueOf(cart.sumCart())+" ��");
        JLabel payCheck= new JLabel("���� �Ͻðڽ��ϱ�?");
        JButton yesPay= new JButton("Yes");
        JButton NoPay= new JButton("No");
        JPanel buttonPanel = new JPanel();
        //���� �׼�
        yesPay.addActionListener(new endPay(cart,cartPanel,payFrame));
        NoPay.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "������ ��� �Ǿ����ϴ�.", "���� ���", JOptionPane.INFORMATION_MESSAGE);
            payFrame.dispose();
          }
        });
        ///
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        buttonPanel.add(yesPay);
        buttonPanel.add(NoPay);
        ///�߰��ϱ�
        PayCheckPanel.add(paySum);
        PayCheckPanel.add(payCheck);
        PayCheckPanel.add(buttonPanel);
        payFrame.add(PayCheckPanel);
        //��� ����
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
    cart.clearCart(); // cartPanel�� �ʱ�ȭ
    //��ٱ��ϵ� �ʱ�ȭ
    OrderButtonListener.updateCartPanel(cart, cartPanel);
    payFrame.dispose();
    JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
  }


}
