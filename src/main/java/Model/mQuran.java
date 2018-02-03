package Model;

import java.util.HashMap;
import java.util.Map;

public class mQuran {

    private String nomor;
    private String url;
    private String namasurat;
    private Integer totalayat;
    private String ayat;
    private String terjemah;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNamasurat() {
        return namasurat;
    }

    public void setNamasurat(String namasurat) {
        this.namasurat = namasurat;
    }

    public Integer getTotalayat() {
        return totalayat;
    }

    public void setTotalayat(Integer totalayat) {
        this.totalayat = totalayat;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getTerjemah() {
        return terjemah;
    }

    public void setTerjemah(String terjemah) {
        this.terjemah = terjemah;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}