package rehome.in.rehome;

/**
 * Created by tarun on 28/12/14.
 */
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {
    private JSONParser jsonParser;
    private static String loginURL = "http://rehome.in/getter.php";
    private static String update_tag = "update";
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
    public JSONObject postUpdate(String email, String switchid){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", update_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", switchid));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
}


