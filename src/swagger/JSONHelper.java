package swagger;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.Base;

public class JSONHelper extends Base {

    public static List<String> stringArray(JSONArray jsonArray) {
        List<String> stringArray = new ArrayList<String>();
        if (jsonArray != null) {
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                stringArray.add(jsonArray.optString(i));
            }
        }
        return stringArray;
    }
}
