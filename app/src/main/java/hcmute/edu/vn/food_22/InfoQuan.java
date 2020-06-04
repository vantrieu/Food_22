package hcmute.edu.vn.food_22;

public class InfoQuan {
    private String tenQuan;
    private String diaChi;
    private double khoangCach;
    private String loaiHinh;
    private String thumbnail;

    public InfoQuan(String tenQuan, String diaChi, double khoangCach, String loaiHinh) {
        this.tenQuan = tenQuan;
        this.diaChi = diaChi;
        this.khoangCach = khoangCach;
        this.loaiHinh = loaiHinh;
    }

    public InfoQuan(String tenQuan, String diaChi, double khoangCach, String loaiHinh, String thumbnail) {
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
