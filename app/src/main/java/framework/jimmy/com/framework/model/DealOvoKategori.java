package framework.jimmy.com.framework.model;

public class DealOvoKategori {
    public DealOvoKategori(String kategori_name, String kategori_icon) {
        this.kategori_name = kategori_name;
        this.kategori_icon = kategori_icon;
    }

    public String getKategori_name() {
        return kategori_name;
    }

    public String getKategori_icon() {
        return kategori_icon;
    }

    private String kategori_name;
    private String kategori_icon;
}
