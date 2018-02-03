package Model;

import java.util.HashMap;
import java.util.Map;

public class mKalender {

    private Integer code;
    private String hijriyah;
    private String masehi;
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getHijriyah() {
        return hijriyah;
    }

    public void setHijriyah(String hijriyah) {
        this.hijriyah = hijriyah;
    }

    public String getMasehi() {
        return masehi;
    }

    public void setMasehi(String masehi) {
        this.masehi = masehi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
