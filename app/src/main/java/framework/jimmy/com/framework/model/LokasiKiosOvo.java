package framework.jimmy.com.framework.model;

import java.io.Serializable;

public class LokasiKiosOvo implements Serializable {
    private String namaLokasi;
    private String alamatLokasi;
    private String waktuOperasionalLokasi;
    private String notelpLokasi;
    private double longitudeLokasi;
    private double latitudeLokasi;

    public LokasiKiosOvo(String namaLokasi, String alamatLokasi, String waktuOperasionalLokasi, String notelpLokasi, double longitudeLokasi, double latitudeLokasi) {
        this.namaLokasi = namaLokasi;
        this.alamatLokasi = alamatLokasi;
        this.waktuOperasionalLokasi = waktuOperasionalLokasi;
        this.notelpLokasi = notelpLokasi;
        this.longitudeLokasi = longitudeLokasi;
        this.latitudeLokasi = latitudeLokasi;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }

    public String getAlamatLokasi() {
        return alamatLokasi;
    }

    public void setAlamatLokasi(String alamatLokasi) {
        this.alamatLokasi = alamatLokasi;
    }

    public String getWaktuOperasionalLokasi() {
        return waktuOperasionalLokasi;
    }

    public void setWaktuOperasionalLokasi(String waktuOperasionalLokasi) {
        this.waktuOperasionalLokasi = waktuOperasionalLokasi;
    }

    public String getNotelpLokasi() {
        return notelpLokasi;
    }

    public void setNotelpLokasi(String notelpLokasi) {
        this.notelpLokasi = notelpLokasi;
    }

    public double getLongitudeLokasi() {
        return longitudeLokasi;
    }

    public void setLongitudeLokasi(double longitudeLokasi) {
        this.longitudeLokasi = longitudeLokasi;
    }

    public double getLatitudeLokasi() {
        return latitudeLokasi;
    }

    public void setLatitudeLokasi(double latitudeLokasi) {
        this.latitudeLokasi = latitudeLokasi;
    }
}
