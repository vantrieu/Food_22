package hcmute.edu.vn.food_22;

public class Store {
    private int res_id;
    private String res_name;
    private String tes_type;
    private String res_address;
    private String description;
    private String url;
    private String res_open;
    private String res_close;
    private int province_id, has_wifi, has_delivery;

    public Store(int res_id, String res_name, String tes_type, String res_address, String description, String url, String res_open, String res_close, int province_id,
                 int has_wifi, int has_delivery) {
        this.res_id = res_id;
        this.res_name = res_name;
        this.tes_type = tes_type;
        this.res_address = res_address;
        this.description = description;
        this.url = url;
        this.res_open = res_open;
        this.res_close = res_close;
        this.province_id = province_id;
        this.has_wifi = has_wifi;
        this.has_delivery = has_delivery;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getTes_type() {
        return tes_type;
    }

    public void setTes_type(String tes_type) {
        this.tes_type = tes_type;
    }

    public String getRes_address() {
        return res_address;
    }

    public void setRes_address(String res_address) {
        this.res_address = res_address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRes_open() {
        return res_open;
    }

    public void setRes_open(String res_open) {
        this.res_open = res_open;
    }

    public String getRes_close() {
        return res_close;
    }

    public void setRes_close(String res_close) {
        this.res_close = res_close;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getHas_wifi() {
        return has_wifi;
    }

    public void setHas_wifi(int has_wifi) {
        this.has_wifi = has_wifi;
    }

    public int getHas_delivery() {
        return has_delivery;
    }

    public void setHas_delivery(int has_delivery) {
        this.has_delivery = has_delivery;
    }
}
