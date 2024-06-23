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
    ////////상태&갯수 선택창 make////////
    public void actionPerformed(ActionEvent e) {
        // 새로운 프레임 생성
        JFrame orderFrame = new JFrame("주문하기 - " + menu.getName());
        orderFrame.setSize(300, 180); // 적절한 크기 설정
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫기 버튼 동작 설정
        
        // 주문 패널 생성
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderFrame.add(orderPanel);
        
        // 메뉴 이름 라벨
        JLabel menuLabel = new JLabel(menu.getName());
        orderPanel.add(menuLabel);
        
        // ICE/HOT 선택 라디오 버튼
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
        JRadioButton sizeUpButton = new JRadioButton("Size Up ( " + menu.getSizeUpPrice() + "원)");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(regularButton);
        sizeGroup.add(sizeUpButton);
        sizePanel.add(regularButton);
        sizePanel.add(sizeUpButton);
        orderPanel.add(sizePanel);
        
        regularButton.setSelected(true); // 기본적으로 Regular 선택
        price = menu.getPrice();
        
        sizeUpButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
              price = menu.getSizeUpPrice();
                size=true;
           }
        });
        // ICE 버튼 클릭 시
        iceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.putState("ICE");
            }
        });
        
        // HOT 버튼 클릭 시
        hotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.putState("HOT");
            }
        });
        // 수량 조절 라벨과 스피너
        JPanel quantityAndNumPanel= new JPanel();
        JLabel quantityLabel = new JLabel("수량:");
        quantityAndNumPanel.add(quantityLabel);
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantityAndNumPanel.add(quantitySpinner);
        orderPanel.add(quantityAndNumPanel);
        // 추가 버튼
        JButton addToCartButton = new JButton("장바구니에 추가");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int quantity = (int) quantitySpinner.getValue();
                //주문된 메뉴 객체 생성
                FoodMenu orderedMenu= new FoodMenu(menu.getName(),price ,  null,size);
                orderedMenu.putState(menu.getState());
                cart.addCart(orderedMenu, quantity);
                updateCartPanel(cart,cartPanel);
                JOptionPane.showMessageDialog(orderFrame, quantity + "개의 " + menu.getName() + "를 장바구니에 추가했습니다.");
                orderFrame.dispose(); // 팝업 창 닫기
            }
        });
        orderPanel.add(addToCartButton);
        
        // 프레임을 화면 중앙에 배치
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
        JLabel priceLabel = new JLabel(i.getPrice() + "원");
        orderItemBox.add(priceLabel, gbc);

        gbc.gridx = 2;
        JLabel quantityLabel = new JLabel(cart.getNum(i) + "개");
        orderItemBox.add(quantityLabel, gbc);

        gbc.gridx = 3;
        JLabel totalLabel = new JLabel((cart.getNum(i) * Integer.valueOf(i.getPrice())) + "원");
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
        JFrame orderFrame = new JFrame("주문하기 - " + menu.getName());
        orderFrame.setSize(400, 500);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderFrame.add(orderPanel);

        JLabel menuLabel = new JLabel(menu.getName());
        orderPanel.add(menuLabel);
        
        JPanel sizePanel = new JPanel();
        JRadioButton regularButton = new JRadioButton("Regular");
        JRadioButton sizeUpButton = new JRadioButton("Size Up ( " + menu.getSizeUpPrice() + "원)");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(regularButton);
        sizeGroup.add(sizeUpButton);
        sizePanel.add(regularButton);
        sizePanel.add(sizeUpButton);
        orderPanel.add(sizePanel);
        
        regularButton.setSelected(true); // 기본적으로 Regular 선택
        price = menu.getPrice();
        
        sizeUpButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e) {
              price = menu.getSizeUpPrice();
           }
        });

        JPanel quantityAndNumPanel = new JPanel();
        JLabel quantityLabel = new JLabel("수량:");
        quantityAndNumPanel.add(quantityLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantityAndNumPanel.add(quantitySpinner);
        orderPanel.add(quantityAndNumPanel);

        JButton addToCartButton = new JButton("장바구니에 추가");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int quantity = (int) quantitySpinner.getValue();
                FoodMenu orderedMenu = new FoodMenu(menu.getName(), price, null,size);
                cart.addCart(orderedMenu, quantity);
                updateCartPanel(cart, cartPanel);
                JOptionPane.showMessageDialog(orderFrame, quantity + "개의 " + menu.getName() + "를 장바구니에 추가했습니다.");
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
        JFrame orderFrame = new JFrame("주문하기 - " + menu.getName());
        orderFrame.setSize(300, 150);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderFrame.add(orderPanel);

        JLabel menuLabel = new JLabel(menu.getName());
        orderPanel.add(menuLabel);
        
     

        JPanel quantityAndNumPanel = new JPanel();
        JLabel quantityLabel = new JLabel("수량:");
        quantityAndNumPanel.add(quantityLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantityAndNumPanel.add(quantitySpinner);
        orderPanel.add(quantityAndNumPanel);

        JButton addToCartButton = new JButton("장바구니에 추가");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int quantity = (int) quantitySpinner.getValue();
                FoodMenu orderedMenu = new FoodMenu(menu.getName(), menu.getPrice(), null);
                cart.addCart(orderedMenu, quantity);
                updateCartPanel(cart, cartPanel);
                JOptionPane.showMessageDialog(orderFrame, quantity + "개의 " + menu.getName() + "를 장바구니에 추가했습니다.");
                orderFrame.dispose();
            }
        });
        orderPanel.add(addToCartButton);

        orderFrame.setLocationRelativeTo(null);
        orderFrame.setVisible(true);
    }
}