package demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by thibautvirolle on 30/10/15.
 */
public class Utils {

    public static String uriDecoded(String param) throws UnsupportedEncodingException {
        return URLDecoder.decode(param.replace("+", "%2B"), "UTF-8").replace("%2B", "+");
    }

}
