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
        payFrame.setSize(400, 300); // ������ ũ�� ����
        payFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ݱ� ��ư ���� ����

        JPanel PayCheckPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        // ��� �� �߰�
        gbc.gridx = 0;
        gbc.gridy = 0;
        PayCheckPanel.add(new JLabel("�޴���"), gbc);
        gbc.gridx++;
        PayCheckPanel.add(new JLabel("�ܰ�"), gbc);
        gbc.gridx++;
        PayCheckPanel.add(new JLabel("����"), gbc);
        gbc.gridx++;
        PayCheckPanel.add(new JLabel("�հ�"), gbc);
    
        // �� �׸� �߰�
        Set<FoodMenu> items = cart.getCartItems();
        for (FoodMenu menu : items) {
            gbc.gridx = 0;
            gbc.gridy++;
            int quantity = cart.getNum(menu);
    
            PayCheckPanel.add(new JLabel(menu.getState()==null?menu.getName():menu.getName()+menu.Size()+"("+menu.getState()+")" ), gbc);
            gbc.gridx++;
            PayCheckPanel.add(new JLabel(menu.getPrice() + "��"), gbc);
            gbc.gridx++;
            PayCheckPanel.add(new JLabel(quantity + "��"), gbc);
            gbc.gridx++;
            PayCheckPanel.add(new JLabel((quantity * Integer.valueOf(menu.getPrice())) + "��"), gbc);
        }
    
        // �Ѿ� �� �߰�
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 4;
        JLabel paySum = new JLabel("�� " + cart.sumCart() + " ��");
        PayCheckPanel.add(paySum, gbc);
    
        // ���� Ȯ�� �� �߰�
        gbc.gridy++;
        JLabel payCheck = new JLabel("���� �Ͻðڽ��ϱ�?");
        PayCheckPanel.add(payCheck, gbc);
    
        // ��ư �г� �߰�
        gbc.gridy++;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton yesPay = new JButton("Yes");
        JButton noPay = new JButton("No");
        buttonPanel.add(yesPay);
        buttonPanel.add(noPay);
        PayCheckPanel.add(buttonPanel, gbc);
    
        // ���� �׼� �߰�
        yesPay.addActionListener(new endPay(cart, cartPanel, payFrame));
        noPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "������ ��� �Ǿ����ϴ�.", "���� ���", JOptionPane.INFORMATION_MESSAGE);
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
    cart.clearCart(); // cartPanel�� �ʱ�ȭ
    //��ٱ��ϵ� �ʱ�ȭ
    OrderButtonListener.updateCartPanel(cart, cartPanel);
    payFrame.dispose();
    JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
  }


}
