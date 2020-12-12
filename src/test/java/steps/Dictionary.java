package steps;

import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    static Map<String, Object> data = new HashMap<>();
    static Object elementData;

    public void setData(String key, String value) {
        this.data.put(key, removeQuotes(value));
        System.out.println("save the " + key + ": " + value);

    }

    public String getData(String key) {
        return (String) this.data.get(key);
    }


    protected void setData(Object object) {
        this.elementData = object;
    }

    public String getElement(String elementKey) {
        String value = JsonPath.read(this.elementData, "$." + elementKey);
        return removeQuotes(value);
    }

    private String replaceTheValue(String text) {
        if (text.contains("{the")) {
            String key = text.split("\\{the")[1].split("}")[0];
            String keyValue = getData(key);
            text = text.replaceAll("\\{the" + key + "}", keyValue);
        }
        return text;
    }

    public String removeQuotes(String text) {
        text = replaceTheValue(text).replace("\"\"", "\"");
        return text;
    }
}
