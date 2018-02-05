
package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mKajian {
    private List<Content> content = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
