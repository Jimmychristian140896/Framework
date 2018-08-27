package framework.jimmy.com.framework.model;

public class InstruksiOvo {

    private String instruksi;
    private String urlImage1;
    private String urlImage2;
    private String subInstruksi;
    private boolean showSalin;

    public InstruksiOvo(String instruksi, String urlImage1, String urlImage2, String subInstruksi, boolean showSalin) {
        this.instruksi = instruksi;
        this.urlImage1 = urlImage1;
        this.urlImage2 = urlImage2;
        this.subInstruksi = subInstruksi;
        this.showSalin = showSalin;
    }



    public String getInstruksi() {
        return instruksi;
    }

    public void setInstruksi(String instruksi) {
        this.instruksi = instruksi;
    }

    public String getUrlImage1() {
        return urlImage1;
    }

    public void setUrlImage1(String urlImage1) {
        this.urlImage1 = urlImage1;
    }

    public String getUrlImage2() {
        return urlImage2;
    }

    public void setUrlImage2(String urlImage2) {
        this.urlImage2 = urlImage2;
    }

    public String getSubInstruksi() {
        return subInstruksi;
    }

    public void setSubInstruksi(String subInstruksi) {
        this.subInstruksi = subInstruksi;
    }

    public boolean isShowSalin() {
        return showSalin;
    }

    public void setShowSalin(boolean showSalin) {
        this.showSalin = showSalin;
    }
}
