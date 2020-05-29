package hcmute.edu.vn.food_22;

public class InfoQuan {
    private String tenQuan;
    private String diaChi;
    private double khoangCach;
    private String loaiHinh;
    private int thumbnail;

    public InfoQuan(String tenQuan, String diaChi, double khoangCach, String loaiHinh) {
        this.tenQuan = tenQuan;
        this.diaChi = diaChi;
        this.khoangCach = khoangCach;
        this.loaiHinh = loaiHinh;
    }

    public InfoQuan(String tenQuan, String diaChi, double khoangCach, String loaiHinh, int thumbnail) {
        this.tenQuan = tenQuan;
        this.diaChi = diaChi;
        this.khoangCach = khoangCach;
        this.loaiHinh = loaiHinh;
        this.thumbnail = thumbnail;
    }

    public InfoQuan() {
    }

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(double khoangCach) {
        this.khoangCach = khoangCach;
    }

    public String getLoaiHinh() {
        return loaiHinh;
    }

    public void setLoaiHinh(String loaiHinh) {
        this.loaiHinh = loaiHinh;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
