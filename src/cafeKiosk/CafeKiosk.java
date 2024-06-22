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
        coffeeMenu.add(new FoodMenu("�Ƹ޸�ī��", "1500", "2500", "./img/�Ƹ޸�ī��.png"));
        coffeeMenu.add(new FoodMenu("������ӾƸ޸�ī��", "2000", "3000", "./img/������ӾƸ޸�ī��.png"));
        coffeeMenu.add(new FoodMenu("�ٴҶ�Ƹ޸�ī��", "2000", "3000", "./img/�ٴҶ�Ƹ޸�ī��.png"));
        coffeeMenu.add(new FoodMenu("īǪġ��", "3000", "4000", "./img/īǪġ��.png"));
        coffeeMenu.add(new FoodMenu("ī��Ḷ���ƶ�", "3500", "4500", "./img/ī��Ḷ���ƶ�.png"));
        coffeeMenu.add(new FoodMenu("ī���ī", "3500", "4500", "./img/ī���ī.png"));
        coffeeMenu.add(new FoodMenu("������Ӷ�", "3000", "4000", "./img/������Ӷ�.png"));
        coffeeMenu.add(new FoodMenu("�ٴҶ��", "3500", "4500", "./img/�ٴҶ��.png"));
        coffeeMenu.add(new FoodMenu("�ݵ����", "3000", "4000", "./img/�ݵ����.png"));
        coffeeMenu.add(new FoodMenu("�ݵ���", "3000", "4000", "./img/�ݵ���.png"));
        coffeeMenu.add(new FoodMenu("������", "3500", "4500", "./img/������.png"));
        coffeeMenu.add(new FoodMenu("��Ʈī���", "3500", "4500", "./img/��Ʈī���.png"));

        List<FoodMenu> drinkMenu = new ArrayList<FoodMenu>();
        drinkMenu.add(new FoodMenu("�����̵�", "3500", "4500", "./img/�����̵�.png"));
        drinkMenu.add(new FoodMenu("��緹���̵�", "3500", "4500", "./img/��緹���̵�.png"));
        drinkMenu.add(new FoodMenu("�ڸ����̵�", "3500", "4500", "./img/�ڸ����̵�.png"));
        drinkMenu.add(new FoodMenu("û�������̵�", "3500", "4500", "./img/û�������̵�.png"));
        drinkMenu.add(new FoodMenu("����ٳ����ֽ�", "4000", "5000", "./img/����ٳ����ֽ�.png"));
        drinkMenu.add(new FoodMenu("�����ֽ�", "4000", "5000", "./img/�����ֽ�.png"));
        drinkMenu.add(new FoodMenu("���ڹٳ����ֽ�", "4000", "5000", "./img/���ڹٳ����ֽ�.png"));
        drinkMenu.add(new FoodMenu("�ڸ�������", "3500", "4500", "./img/�ڸ�������.png"));
        drinkMenu.add(new FoodMenu("û����������", "3500", "4500", "./img/û����������.png"));
        drinkMenu.add(new FoodMenu("���Ӹ�����", "3500", "4500", "./img/���Ӹ�����.png"));
        drinkMenu.add(new FoodMenu("���ڶ�", "4000", "5000", "./img/���ڶ�.png"));
        drinkMenu.add(new FoodMenu("���������ڶ�", "4500", "5500", "./img/���������ڶ�.png"));
        drinkMenu.add(new FoodMenu("���ǳӶ�", "3500", "4500", "./img/���ǳӶ�.png"));
        drinkMenu.add(new FoodMenu("������", "3500", "4500", "./img/������.png"));
        drinkMenu.add(new FoodMenu("�����", "3500", "4500", "./img/�����.png"));

        List<FoodMenu> dessertMenu = new ArrayList<FoodMenu>();
        dessertMenu.add(new FoodMenu("���ڹ�������", "4500", "./img/���ڹ�������.png"));
        dessertMenu.add(new FoodMenu("Ƽ��̼�����", "5000", "./img/Ƽ��̼�����.png"));
        dessertMenu.add(new FoodMenu("��Ϻ극��", "3500", "./img/��Ϻ극��.png"));
        dessertMenu.add(new FoodMenu("ġ������", "5000", "./img/ġ������.png"));
        dessertMenu.add(new FoodMenu("�÷���ũ����", "3500", "./img/�÷���ũ����.png"));
        dessertMenu.add(new FoodMenu("���̽�ũ��ũ����", "4000", "./img/���̽�ũ��ũ����.png"));
        dessertMenu.add(new FoodMenu("���ڽ������Ű", "2000", "./img/���ڽ������Ű.png"));
        dessertMenu.add(new FoodMenu("�����������Ű", "2000", "./img/�����������Ű.png"));
        dessertMenu.add(new FoodMenu("��Ű�����丶ī��", "2500", "./img/��Ű�����丶ī��.png"));
        dessertMenu.add(new FoodMenu("�ٴҶ�ī��", "2500", "./img/�ٴҶ�ī��.png"));
        dessertMenu.add(new FoodMenu("��ũ������ī��", "2500", "./img/��ũ������ī��.png"));
        dessertMenu.add(new FoodMenu("�����������丶ī��", "2500", "./img/�����������丶ī��.png"));

        setTitle("coffece order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFrame frame = new JFrame();
        Container c = frame.getContentPane();

        // ī�װ� ��� ��ư
        JPanel typePanel = new JPanel(new BorderLayout());

	     // Ÿ��Ʋ ���� ���
	    JPanel p1 = new JPanel();
	    p1.setBackground(Color.ORANGE);
	    
	    Label topText = new Label();
	    topText.setText("CAFE KIOSK");
	    topText.setFont(font1);
	    p1.add(topText);
	
	    typePanel.add(p1, BorderLayout.NORTH);
	
	     // ī�װ� ��� ��ư
	    JPanel buttonPanel = new JPanel(new FlowLayout()); // FlowLayout���� ��ư���� ���ʿ��� ���������� ��ġ
	    buttonPanel.setBackground(Color.WHITE); // ���� ����
	
	    JButton coffeeButton = new JButton("Ŀ��");
	    JButton drinkButton = new JButton("����/���̵�");
	    JButton dessertButton = new JButton("����Ʈ");
	
	    buttonPanel.add(coffeeButton);
	    buttonPanel.add(drinkButton);
	    buttonPanel.add(dessertButton);
	
	    typePanel.add(buttonPanel, BorderLayout.CENTER);
        
        

        // ī�װ��� Ŀ��, ����, ����Ʈ �г�
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

        cardPanel.add(coffeePanel, "Ŀ��");
        cardPanel.add(drinkPanel, "����");
        cardPanel.add(dessertPanel, "����Ʈ");

        // ī�װ��� �޴� ��� �̺�Ʈ
        coffeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Ŀ��");
            }
        });

        drinkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "����");
            }
        });

        dessertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "����Ʈ");
            }
        });


        
        cardPanel.setBackground(Color.LIGHT_GRAY);

        c.setLayout(new BorderLayout());

        c.add(typePanel, BorderLayout.NORTH);
        c.add(cardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setSize(800, 800);
    }

    // �� �޴����� �̹���, ��ư, �� ���� �޼ҵ�
    private static JPanel createMenuItemPanel(String name, String price, String sizeUpPrice, String img) {
        JPanel itemPanel = new JPanel(new BorderLayout());

        // �̹��� ������ ���� �� �߰�
        ImageIcon icon = new ImageIcon(img);
        JButton imgButton = new JButton(icon);
        itemPanel.add(imgButton, BorderLayout.CENTER);

        // ���̺�� ��ư�� ���Ե� �г� ����
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // BoxLayout���� ����
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // �г� ��� ���� ����

        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // �̸� ���̺� ��� ����
        textPanel.add(nameLabel);

        // ���ݰ� ������� ������ ǥ���� ���̺� ���� �� ����
        JLabel priceLabel = new JLabel();
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // ���� ���̺� ��� ����
        priceLabel.setText(price+"��" + "/" + sizeUpPrice+"��"); // ������� ������ �ִ� ��� �Բ� ǥ��
        textPanel.add(priceLabel);

        itemPanel.add(textPanel, BorderLayout.SOUTH);

        return itemPanel;
    }


    private static JPanel createMenuItemPanel(String name, String price, String img) {
        JPanel itemPanel = new JPanel(new BorderLayout());

        // �̹��� ������ ���� �� �߰�
        ImageIcon icon = new ImageIcon(img);
        JButton imgButton = new JButton(icon);
        itemPanel.add(imgButton, BorderLayout.CENTER);

        // ���̺�� ��ư�� ���Ե� �г� ����
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // BoxLayout���� ����
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // �г� ��� ���� ����

        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // �̸� ���̺� ��� ����
        textPanel.add(nameLabel);

        // ���ݰ� ������� ������ ǥ���� ���̺� ���� �� ����
        JLabel priceLabel = new JLabel();
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // ���� ���̺� ��� ����
        priceLabel.setText(price+"��"); 
        textPanel.add(priceLabel);

        itemPanel.add(textPanel, BorderLayout.SOUTH);

        return itemPanel;
    }


    public static void main(String[] args) {
        new CafeKiosk();
    }
}
