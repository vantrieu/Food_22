package hcmute.edu.vn.food_22;

public class FoodTest {
    private int food_id;
    private String food_name;
    private int type_id;
    private String descript;
    private String food_img;
    private int price;
    private int res_id;

    public FoodTest(int food_id, String food_name, int type_id, String descript, String food_img, int price, int res_id) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.type_id = type_id;
        this.descript = descript;
        this.food_img = food_img;
        this.price = price;
        this.res_id = res_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }
}
