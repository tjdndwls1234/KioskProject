package cafeKiosk;

public class FoodMenu {
    
    private String name;
    private String price;
    private String sizeUpPrice;
    private String img;

    
    public FoodMenu(String name, String price, String img) {
        this.price = price;
        this.name = name;
        this.img = img;
       
    }
    
    public FoodMenu(String name, String price, String sizeUpPrice, String img) {
        this.price = price;
        this.name = name;
        this.sizeUpPrice = sizeUpPrice;
        this.img = img;
        
    }
    
    public String getName() {
        return name;
    }
    
    public String getPrice() {
        return price;
    }
    
    public String getSizeUpPrice() {
        return sizeUpPrice;
    }
    
    public String getImg() {
        return img;
    }
    
}
