package pl.agh.wfiis;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Managed bean used for text operations.
 */
@Named(value = "textFetcher")
@SessionScoped
public class TextFetcher implements Serializable {
    
    /**
     * Gets text from given url and returns as string
     * @param urlString
     * @return string of text
     */
    public static String getTextFromUrl(String urlString) {
        if (urlString == "") return "";
        try {
            String webPage = urlString;
            URL url = new URL(webPage);
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                    sb.append(charArray, 0, numCharsRead);
            }
            String result = sb.toString();

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }            
    }
}
