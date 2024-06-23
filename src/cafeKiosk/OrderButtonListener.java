package cafeKiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;



class OrderButtonListener implements ActionListener {
    public Cart cart;
    public JPanel cartPanel;
    public FoodMenu menu;
    public String price;
    public boolean size;
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
        
        JPanel sizePanel = new JPanel();
        JRadioButton regularButton = new JRadioButton("Regular");
        JRadioButton sizeUpButton = new JRadioButton("Size Up ( " + menu.getSizeUpPrice() + "��)");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(regularButton);
        sizeGroup.add(sizeUpButton);
        sizePanel.add(regularButton);
        sizePanel.add(sizeUpButton);
        orderPanel.add(sizePanel);
        
        regularButton.setSelected(true); // �⺻������ Regular ����
        price = menu.getPrice();
        
        sizeUpButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
              price = menu.getSizeUpPrice();
                size=true;
           }
        });
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
                FoodMenu orderedMenu= new FoodMenu(menu.getName(),price ,  null,size);
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
        Set<FoodMenu> items = cart.getCartItems();
    for (FoodMenu i : items) {
        JPanel orderItemBox = new JPanel(new GridBagLayout());
        orderItemBox.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        JLabel itemLabel = new JLabel(i.getState()==null?i.getName():i.getName()+i.Size()+"("+i.getState()+")" );
        orderItemBox.add(itemLabel, gbc);

        gbc.gridx = 1;
        JLabel priceLabel = new JLabel(i.getPrice() + "��");
        orderItemBox.add(priceLabel, gbc);

        gbc.gridx = 2;
        JLabel quantityLabel = new JLabel(cart.getNum(i) + "��");
        orderItemBox.add(quantityLabel, gbc);

        gbc.gridx = 3;
        JLabel totalLabel = new JLabel((cart.getNum(i) * Integer.valueOf(i.getPrice())) + "��");
        orderItemBox.add(totalLabel, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        JButton delButton = new JButton("x");
        delButton.setFont(new Font("Arial", Font.PLAIN, 10));
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.deleteCart(i);
                updateCartPanel(cart, cartPanel);
            }
        });
        orderItemBox.add(delButton, gbc);

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
        orderFrame.setSize(400, 500);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderFrame.add(orderPanel);

        JLabel menuLabel = new JLabel(menu.getName());
        orderPanel.add(menuLabel);
        
        JPanel sizePanel = new JPanel();
        JRadioButton regularButton = new JRadioButton("Regular");
        JRadioButton sizeUpButton = new JRadioButton("Size Up ( " + menu.getSizeUpPrice() + "��)");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(regularButton);
        sizeGroup.add(sizeUpButton);
        sizePanel.add(regularButton);
        sizePanel.add(sizeUpButton);
        orderPanel.add(sizePanel);
        
        regularButton.setSelected(true); // �⺻������ Regular ����
        price = menu.getPrice();
        
        sizeUpButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
              price = menu.getSizeUpPrice();
           }
        });

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
                FoodMenu orderedMenu = new FoodMenu(menu.getName(), price, null,size);
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

class DessertOrderButtonListener extends OrderButtonListener {

    public DessertOrderButtonListener(Cart cart, FoodMenu menu, JPanel cartPanel) {
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