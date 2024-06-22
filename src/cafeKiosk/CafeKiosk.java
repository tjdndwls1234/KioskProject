package cafeKiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CafeKiosk extends JFrame {

    Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 13);
    Font font2 = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 25);

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public CafeKiosk() {

        List<FoodMenu> coffeeMenu = new ArrayList<FoodMenu>();
        coffeeMenu.add(new FoodMenu("아메리카노", "1500", "2500", "./img/아메리카노.png"));
        coffeeMenu.add(new FoodMenu("헤이즐넛아메리카노", "2000", "3000", "./img/헤이즐넛아메리카노.png"));
        coffeeMenu.add(new FoodMenu("바닐라아메리카노", "2000", "3000", "./img/바닐라아메리카노.png"));
        coffeeMenu.add(new FoodMenu("카푸치노", "3000", "4000", "./img/카푸치노.png"));
        coffeeMenu.add(new FoodMenu("카라멜마끼아또", "3500", "4500", "./img/카라멜마끼아또.png"));
        coffeeMenu.add(new FoodMenu("카페모카", "3500", "4500", "./img/카페모카.png"));
        coffeeMenu.add(new FoodMenu("헤이즐넛라떼", "3000", "4000", "./img/헤이즐넛라떼.png"));
        coffeeMenu.add(new FoodMenu("바닐라라떼", "3500", "4500", "./img/바닐라라떼.png"));
        coffeeMenu.add(new FoodMenu("콜드브루라떼", "3000", "4000", "./img/콜드브루라떼.png"));
        coffeeMenu.add(new FoodMenu("콜드브루", "3000", "4000", "./img/콜드브루.png"));
        coffeeMenu.add(new FoodMenu("연유라떼", "3500", "4500", "./img/연유라떼.png"));
        coffeeMenu.add(new FoodMenu("민트카페라떼", "3500", "4500", "./img/민트카페라떼.png"));

        List<FoodMenu> drinkMenu = new ArrayList<FoodMenu>();
        drinkMenu.add(new FoodMenu("레몬에이드", "3500", "4500", "./img/레몬에이드.png"));
        drinkMenu.add(new FoodMenu("블루레몬에이드", "3500", "4500", "./img/블루레몬에이드.png"));
        drinkMenu.add(new FoodMenu("자몽에이드", "3500", "4500", "./img/자몽에이드.png"));
        drinkMenu.add(new FoodMenu("청포도에이드", "3500", "4500", "./img/청포도에이드.png"));
        drinkMenu.add(new FoodMenu("딸기바나나주스", "4000", "5000", "./img/딸기바나나주스.png"));
        drinkMenu.add(new FoodMenu("딸기주스", "4000", "5000", "./img/딸기주스.png"));
        drinkMenu.add(new FoodMenu("초코바나나주스", "4000", "5000", "./img/초코바나나주스.png"));
        drinkMenu.add(new FoodMenu("자몽모히또", "3500", "4500", "./img/자몽모히또.png"));
        drinkMenu.add(new FoodMenu("청포도모히또", "3500", "4500", "./img/청포도모히또.png"));
        drinkMenu.add(new FoodMenu("라임모히또", "3500", "4500", "./img/라임모히또.png"));
        drinkMenu.add(new FoodMenu("초코라떼", "4000", "5000", "./img/초코라떼.png"));
        drinkMenu.add(new FoodMenu("오레오초코라떼", "4500", "5500", "./img/오레오초코라떼.png"));
        drinkMenu.add(new FoodMenu("토피넛라떼", "3500", "4500", "./img/토피넛라떼.png"));
        drinkMenu.add(new FoodMenu("녹차라떼", "3500", "4500", "./img/녹차라떼.png"));
        drinkMenu.add(new FoodMenu("딸기라떼", "3500", "4500", "./img/딸기라떼.png"));

        List<FoodMenu> dessertMenu = new ArrayList<FoodMenu>();
        dessertMenu.add(new FoodMenu("초코무스케익", "4500", "./img/초코무스케익.png"));
        dessertMenu.add(new FoodMenu("티라미수케익", "5000", "./img/티라미수케익.png"));
        dessertMenu.add(new FoodMenu("허니브레드", "3500", "./img/허니브레드.png"));
        dessertMenu.add(new FoodMenu("치즈케익", "5000", "./img/치즈케익.png"));
        dessertMenu.add(new FoodMenu("플레인크로플", "3500", "./img/플레인크로플.png"));
        dessertMenu.add(new FoodMenu("아이스크림크로플", "4000", "./img/아이스크림크로플.png"));
        dessertMenu.add(new FoodMenu("초코스모어쿠키", "2000", "./img/초코스모어쿠키.png"));
        dessertMenu.add(new FoodMenu("말차스모어쿠키", "2000", "./img/말차스모어쿠키.png"));
        dessertMenu.add(new FoodMenu("쿠키프라페마카롱", "2500", "./img/쿠키프라페마카롱.png"));
        dessertMenu.add(new FoodMenu("바닐라마카롱", "2500", "./img/바닐라마카롱.png"));
        dessertMenu.add(new FoodMenu("퐁크러쉬마카롱", "2500", "./img/퐁크러쉬마카롱.png"));
        dessertMenu.add(new FoodMenu("유니콘프라페마카롱", "2500", "./img/유니콘프라페마카롱.png"));

        setTitle("coffece order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFrame frame = new JFrame();
        Container c = frame.getContentPane();

        // 카테고리 배너 버튼
        JPanel typePanel = new JPanel(new BorderLayout());

	     // 타이틀 제목 배너
	    JPanel p1 = new JPanel();
	    p1.setBackground(Color.ORANGE);
	    
	    Label topText = new Label();
	    topText.setText("CAFE KIOSK");
	    topText.setFont(font1);
	    p1.add(topText);
	
	    typePanel.add(p1, BorderLayout.NORTH);
	
	     // 카테고리 배너 버튼
	    JPanel buttonPanel = new JPanel(new FlowLayout()); // FlowLayout으로 버튼들을 왼쪽에서 오른쪽으로 배치
	    buttonPanel.setBackground(Color.WHITE); // 배경색 지정
	
	    JButton coffeeButton = new JButton("커피");
	    JButton drinkButton = new JButton("음료/에이드");
	    JButton dessertButton = new JButton("디저트");
	
	    buttonPanel.add(coffeeButton);
	    buttonPanel.add(drinkButton);
	    buttonPanel.add(dessertButton);
	
	    typePanel.add(buttonPanel, BorderLayout.CENTER);
        
        

        // 카테고리별 커피, 음료, 디저트 패널
        JPanel coffeePanel = new JPanel(new GridLayout(3, 5));

        for (int i = 0; i < coffeeMenu.size(); i++) {
            FoodMenu menu = coffeeMenu.get(i);
            String name = menu.getName();
            String price = menu.getPrice();
            String sizeUpPrice = menu.getSizeUpPrice();
            String img = menu.getImg();

            JPanel itemPanel = createMenuItemPanel(name, price, sizeUpPrice, img);
            coffeePanel.add(itemPanel);
        }
        for (int i = coffeeMenu.size(); i < 15; i++) {
            coffeePanel.add(new JPanel());
        }
        
        
        JPanel drinkPanel = new JPanel(new GridLayout(3, 5));

        for (int i = 0; i < drinkMenu.size(); i++) {
            FoodMenu menu = drinkMenu.get(i);
            String name = menu.getName();
            String price = menu.getPrice();
            String sizeUpPrice = menu.getSizeUpPrice();
            String img = menu.getImg();

            JPanel itemPanel = createMenuItemPanel(name, price, sizeUpPrice, img);
            drinkPanel.add(itemPanel);
        }
        for (int i = drinkMenu.size(); i < 15; i++) {
        	drinkPanel.add(new JPanel());
        }

        JPanel dessertPanel = new JPanel(new GridLayout(3, 5));

        for (int i = 0; i < dessertMenu.size(); i++) {
            FoodMenu menu = dessertMenu.get(i);
            String name = menu.getName();
            String price = menu.getPrice();
            String img = menu.getImg();

            JPanel itemPanel = createMenuItemPanel(name, price, img);
            dessertPanel.add(itemPanel);
        }
        for (int i = dessertMenu.size(); i < 15; i++) {
        	dessertPanel.add(new JPanel());
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(coffeePanel, "커피");
        cardPanel.add(drinkPanel, "음료");
        cardPanel.add(dessertPanel, "디저트");

        // 카테고리별 메뉴 출력 이벤트
        coffeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "커피");
            }
        });

        drinkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "음료");
            }
        });

        dessertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "디저트");
            }
        });


        
        cardPanel.setBackground(Color.LIGHT_GRAY);

        c.setLayout(new BorderLayout());

        c.add(typePanel, BorderLayout.NORTH);
        c.add(cardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setSize(800, 800);
    }

    // 각 메뉴마다 이미지, 버튼, 라벨 생성 메소드
    private static JPanel createMenuItemPanel(String name, String price, String sizeUpPrice, String img) {
        JPanel itemPanel = new JPanel(new BorderLayout());

        // 이미지 아이콘 생성 및 추가
        ImageIcon icon = new ImageIcon(img);
        JButton imgButton = new JButton(icon);
        itemPanel.add(imgButton, BorderLayout.CENTER);

        // 레이블과 버튼이 포함된 패널 생성
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // BoxLayout으로 변경
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // 패널 가운데 정렬 설정

        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 이름 레이블 가운데 정렬
        textPanel.add(nameLabel);

        // 가격과 사이즈업 가격을 표시할 레이블 생성 및 설정
        JLabel priceLabel = new JLabel();
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 가격 레이블 가운데 정렬
        priceLabel.setText(price+"원" + "/" + sizeUpPrice+"원"); // 사이즈업 가격이 있는 경우 함께 표시
        textPanel.add(priceLabel);

        itemPanel.add(textPanel, BorderLayout.SOUTH);

        return itemPanel;
    }


    private static JPanel createMenuItemPanel(String name, String price, String img) {
        JPanel itemPanel = new JPanel(new BorderLayout());

        // 이미지 아이콘 생성 및 추가
        ImageIcon icon = new ImageIcon(img);
        JButton imgButton = new JButton(icon);
        itemPanel.add(imgButton, BorderLayout.CENTER);

        // 레이블과 버튼이 포함된 패널 생성
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // BoxLayout으로 변경
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // 패널 가운데 정렬 설정

        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 이름 레이블 가운데 정렬
        textPanel.add(nameLabel);

        // 가격과 사이즈업 가격을 표시할 레이블 생성 및 설정
        JLabel priceLabel = new JLabel();
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 가격 레이블 가운데 정렬
        priceLabel.setText(price+"원"); 
        textPanel.add(priceLabel);

        itemPanel.add(textPanel, BorderLayout.SOUTH);

        return itemPanel;
    }


    public static void main(String[] args) {
        new CafeKiosk();
    }
}
