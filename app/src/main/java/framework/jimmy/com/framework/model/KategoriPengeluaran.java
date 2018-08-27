package framework.jimmy.com.framework.model;

public class KategoriPengeluaran {
    private int resourceKategori;
    private String namaKategori;

    public KategoriPengeluaran(int resourceKategori, String namaKategori) {
        this.resourceKategori = resourceKategori;
        this.namaKategori = namaKategori;
    }

    public int getResourceKategori() {
        return resourceKategori;
    }

    public void setResourceKategori(int resourceKategori) {
        this.resourceKategori = resourceKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
}
