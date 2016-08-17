import java.util.HashMap;
import java.util.Map;

/**
 * Created by simarv on 2016-08-17.
 */
public class UrlParser {


    /**
     *
     * Handles urls of this format taken from wikipedia:
     * scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
     *
     * Input url: http://username:password@hostname:port/path?arg=value#anchor'
     *
     * Will return
     *
     * (
     *    [scheme] => http
     *    [host] => hostname
     *    [user] => username
     *    [pass] => password
     *    [port] => port
     *    [path] => /path
     *    [query] => arg=value
     *    [fragment] => anchor
     * )
     *
     * @param url
     * @return
     */
    public Map<String, String> parse(String url) {

        String[] tmp;

        Map<String, String> result = new HashMap<>();


        /*
        * Start eating from the beginning
        *
        * Any of these:
        *
        * scheme://username:password@end
        * username:password@end
        * end
        */

        if(url.contains("://")) {
            tmp = url.split("://");
            result.put("scheme", tmp[0]);
            url = tmp[1];
        }

        if(url.contains("@")) {
            tmp = url.split("@");
            String[] userAndPass = tmp[0].split(":");
            result.put("user", userAndPass[0]);
            result.put("pass", userAndPass[1]);
            url = tmp[1];
        }


        /*
        * Start eating from the end
        *
        * Any of these:
        *
        * hostname
        * hostname#anchor
        * hostname?arg=value#anchor
        * hostname/path?arg=value#anchor
        * hostname:port/path?arg=value#anchor
        */

        if(url.contains("#")) {
            tmp = url.split("#");
            result.put("fragment", tmp[1]);
            url = tmp[0];
        }

        if(url.contains("?")) {
            tmp = url.split("\\?");
            result.put("query", tmp[1]);
            url = tmp[0];
        }

        if(url.contains("/")) {
            tmp = url.split("/");
            result.put("path", "/" +tmp[1]);
            url = tmp[0];
        }

        if(url.contains(":")) {
            tmp = url.split(":");
            result.put("port", tmp[1]);
            url = tmp[0];
        }

        result.put("host", url);


        return result;
    }


    /**
     *  query input "key1=value1&key2=value2";
     *
     *  Should give:
     *  (
     *       key1 => value1
     *       key2 => value2
     *  )
     *
     * @param query
     * @return
     */
    public Map<String, String> parseQuery(String query) {
        Map<String,String> result = new HashMap<>();

        for(String pair : query.split("&")) {
            String[] parts = pair.split("=");
            result.put(parts[0], parts[1]);
        }

        return result;
    }
}
