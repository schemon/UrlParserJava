import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by simarv on 2016-08-17.
 */
public class UrlParserTest {
    @Test
    public void full() throws Exception {
        Map<String, String> result = new UrlParser().parse("http://username:password@hostname:8080/path?arg=value#anchor");

        assertEquals("http", result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals("username", result.get("user"));
        assertEquals("password", result.get("pass"));
        assertEquals("8080", result.get("port"));
        assertEquals("/path", result.get("path"));
        assertEquals("arg=value", result.get("query"));
        assertEquals("anchor", result.get("fragment"));
    }

    @Test
    public void minimal() throws Exception {
        Map<String, String> result = new UrlParser().parse("hostname");

        assertEquals(null, result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals(null, result.get("user"));
        assertEquals(null, result.get("pass"));
        assertEquals(null, result.get("port"));
        assertEquals(null, result.get("path"));
        assertEquals(null, result.get("query"));
        assertEquals(null, result.get("fragment"));
    }

    @Test
    public void onlyScheme() throws Exception {
        Map<String, String> result = new UrlParser().parse("https://hostname");

        assertEquals("https", result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals(null, result.get("user"));
        assertEquals(null, result.get("pass"));
        assertEquals(null, result.get("port"));
        assertEquals(null, result.get("path"));
        assertEquals(null, result.get("query"));
        assertEquals(null, result.get("fragment"));
    }

    @Test
    public void onlyUserPass() throws Exception {
        Map<String, String> result = new UrlParser().parse("username:password@hostname");

        assertEquals(null, result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals("username", result.get("user"));
        assertEquals("password", result.get("pass"));
        assertEquals(null, result.get("port"));
        assertEquals(null, result.get("path"));
        assertEquals(null, result.get("query"));
        assertEquals(null, result.get("fragment"));
    }

    @Test
    public void onlyPort() throws Exception {
        Map<String, String> result = new UrlParser().parse("hostname:8080");

        assertEquals(null, result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals(null, result.get("user"));
        assertEquals(null, result.get("pass"));
        assertEquals("8080", result.get("port"));
        assertEquals(null, result.get("path"));
        assertEquals(null, result.get("query"));
        assertEquals(null, result.get("fragment"));
    }

    @Test
    public void onlyPath() throws Exception {
        Map<String, String> result = new UrlParser().parse("hostname/path");

        assertEquals(null, result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals(null, result.get("user"));
        assertEquals(null, result.get("pass"));
        assertEquals(null, result.get("port"));
        assertEquals("/path", result.get("path"));
        assertEquals(null, result.get("query"));
        assertEquals(null, result.get("fragment"));
    }

    @Test
    public void onlyQuery() throws Exception {
        Map<String, String> result = new UrlParser().parse("hostname?arg=value");

        assertEquals(null, result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals(null, result.get("user"));
        assertEquals(null, result.get("pass"));
        assertEquals(null, result.get("port"));
        assertEquals(null, result.get("path"));
        assertEquals("arg=value", result.get("query"));
        assertEquals(null, result.get("fragment"));
    }


    @Test
    public void onlyFragment() throws Exception {
        Map<String, String> result = new UrlParser().parse("hostname#anchor");

        assertEquals(null, result.get("scheme"));
        assertEquals("hostname", result.get("host"));
        assertEquals(null, result.get("user"));
        assertEquals(null, result.get("pass"));
        assertEquals(null, result.get("port"));
        assertEquals(null, result.get("path"));
        assertEquals(null, result.get("query"));
        assertEquals("anchor", result.get("fragment"));
    }

    @Test
    public void noUserPass() throws Exception {
        Map<String, String> result = new UrlParser().parse("http://hostname:8080/path?arg=value#anchor");

        assertEquals("http", result.get("scheme"));
        assertEquals("hostname", result.get("host"));

        assertEquals(null, result.get("user"));
        assertEquals(null, result.get("pass"));

        assertEquals("8080", result.get("port"));
        assertEquals("/path", result.get("path"));
        assertEquals("arg=value", result.get("query"));
        assertEquals("anchor", result.get("fragment"));
    }



    @Test
    public void parseQueryOneParam() {
        Map<String, String> result = new UrlParser().parseQuery("key1=value1");

        assertEquals(1, result.size());
        assertEquals("value1", result.get("key1"));
    }

    @Test
    public void parseQueryTwoParams() {
        Map<String, String> result = new UrlParser().parseQuery("key1=value1&key2=value2");

        assertEquals(2, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
    }

}