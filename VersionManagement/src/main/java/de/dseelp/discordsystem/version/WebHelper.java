package de.dseelp.discordsystem.version;

import com.google.gson.JsonParser;
import de.dseelp.discordsystem.utils.JsonDocument;
import lombok.Getter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class WebHelper {
    @Getter
    private final String baseUrl;
    private HttpClient httpClient;

    public WebHelper(String baseUrl) throws Exception {
        httpClient = HttpClients.createMinimal();
        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        if (!uriBuilder.getPath().endsWith("/")) uriBuilder.setPath(uriBuilder.getPath()+"/");
        this.baseUrl = uriBuilder.toString();
    }


    public JsonDocument getFromFile(String fileName) {
        try {
            return getFromURL(setFileName(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String setFileName(String fileName) {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return builder.setPath(builder.getPath()+fileName).toString();
    }

    public JsonDocument getFromURL(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        System.out.println(url);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                return new JsonDocument(JsonParser.parseReader(new InputStreamReader(response.getEntity().getContent())).getAsJsonObject());
            }catch (IllegalStateException e) {}
        }
        return null;
    }

    private URL toURL(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
