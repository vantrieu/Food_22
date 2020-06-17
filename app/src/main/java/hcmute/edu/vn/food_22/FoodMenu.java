package hcmute.edu.vn.food_22;

public class FoodMenu {
    private  String tenMon;
    private String giaCa;

    public FoodMenu(String tenMon, String giaCa) {
        this.tenMon = tenMon;
        this.giaCa = giaCa;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getGiaCa() {
        return giaCa;
    }

    public void setGiaCa(String giaCa) {
        this.giaCa = giaCa;
    }
}
