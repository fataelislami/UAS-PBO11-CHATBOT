package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mCariMasjid {
    private List<Result> result = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
