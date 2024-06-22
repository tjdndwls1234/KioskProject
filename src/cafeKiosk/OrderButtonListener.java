package cafeKiosk;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class OrderButtonListener implements ActionListener {
    public Cart cart;
    public JPanel cartPanel;
    public FoodMenu menu;
    public OrderButtonListener(Cart cart,FoodMenu menu, JPanel cartPanel) {
        this.cart = cart;
        this.cartPanel=cartPanel;
        this.menu=menu;
    }
    @Override
    ////////����&���� ����â make////////
    public void actionPerformed(ActionEvent e) {
        // ���ο� ������ ����
        JFrame orderFrame = new JFrame("�ֹ��ϱ� - " + menu.getName());
        orderFrame.setSize(300, 180); // ������ ũ�� ����
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �ݱ� ��ư ���� ����
        
        // �ֹ� �г� ����
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderFrame.add(orderPanel);
        
        // �޴� �̸� ��
        JLabel menuLabel = new JLabel(menu.getName());
        orderPanel.add(menuLabel);
        
        // ICE/HOT ���� ���� ��ư
        JPanel iceHotPanel = new JPanel();
        JRadioButton iceButton = new JRadioButton("ICE");
        JRadioButton hotButton = new JRadioButton("HOT");
        ButtonGroup stateGroup = new ButtonGroup();
        stateGroup.add(iceButton);
        stateGroup.add(hotButton);
        iceHotPanel.add(iceButton);
        iceHotPanel.add(hotButton);
        orderPanel.add(iceHotPanel);

        // ICE ��ư Ŭ�� ��
        iceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.putState("ICE");
            }
        });
        
        // HOT ��ư Ŭ�� ��
        hotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.putState("HOT");
            }
        });
        // ���� ���� �󺧰� ���ǳ�
        JPanel quantityAndNumPanel= new JPanel();
        JLabel quantityLabel = new JLabel("����:");
        quantityAndNumPanel.add(quantityLabel);
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantityAndNumPanel.add(quantitySpinner);
        orderPanel.add(quantityAndNumPanel);
        // �߰� ��ư
        JButton addToCartButton = new JButton("��ٱ��Ͽ� �߰�");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int quantity = (int) quantitySpinner.getValue();
                //�ֹ��� �޴� ��ü ����
                FoodMenu orderedMenu= new FoodMenu(menu.getName(),menu.getPrice(), null);
                orderedMenu.putState(menu.getState());
                cart.addCart(orderedMenu, quantity);
                updateCartPanel(cart,cartPanel);
                JOptionPane.showMessageDialog(orderFrame, quantity + "���� " + menu.getName() + "�� ��ٱ��Ͽ� �߰��߽��ϴ�.");
                orderFrame.dispose(); // �˾� â �ݱ�
            }
        });
        orderPanel.add(addToCartButton);
        
        // �������� ȭ�� �߾ӿ� ��ġ
        orderFrame.setLocationRelativeTo(null);
        orderFrame.setVisible(true);
    }
    public static void updateCartPanel(Cart cart, JPanel cartPanel) {
        cartPanel.removeAll();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        for (FoodMenu i: cart.getCartItems()) {
            JPanel orderItemBox= new JPanel();
            orderItemBox.setBackground(Color.WHITE);
            JLabel itemLabel = new JLabel(i.getName() + " (" + i.getState() + ") : " + cart.getNum(i) + "��");
            JButton delButton=new JButton("x");
            delButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    cart.deleteCart(i);
                    updateCartPanel(cart, cartPanel);
                }
            });
            orderItemBox.add(itemLabel);
            orderItemBox.add(delButton);
            cartPanel.add(orderItemBox);
        }
        cartPanel.revalidate();
        cartPanel.repaint();
    }
}

class NonStateOrderButtonListener extends OrderButtonListener {

    public NonStateOrderButtonListener(Cart cart, FoodMenu menu, JPanel cartPanel) {
        super(cart, menu, cartPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame orderFrame = new JFrame("�ֹ��ϱ� - " + menu.getName());
        orderFrame.setSize(300, 150);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderFrame.add(orderPanel);

        JLabel menuLabel = new JLabel(menu.getName());
        orderPanel.add(menuLabel);

        JPanel quantityAndNumPanel = new JPanel();
        JLabel quantityLabel = new JLabel("����:");
        quantityAndNumPanel.add(quantityLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantityAndNumPanel.add(quantitySpinner);
        orderPanel.add(quantityAndNumPanel);

        JButton addToCartButton = new JButton("��ٱ��Ͽ� �߰�");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int quantity = (int) quantitySpinner.getValue();
                FoodMenu orderedMenu = new FoodMenu(menu.getName(), menu.getPrice(), null);
                cart.addCart(orderedMenu, quantity);
                updateCartPanel(cart, cartPanel);
                JOptionPane.showMessageDialog(orderFrame, quantity + "���� " + menu.getName() + "�� ��ٱ��Ͽ� �߰��߽��ϴ�.");
                orderFrame.dispose();
            }
        });
        orderPanel.add(addToCartButton);

        orderFrame.setLocationRelativeTo(null);
        orderFrame.setVisible(true);
    }
}
