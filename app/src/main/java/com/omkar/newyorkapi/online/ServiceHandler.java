    package com.omkar.newyorkapi.online;

    import org.apache.http.HttpEntity;
    import org.apache.http.HttpResponse;
    import org.apache.http.NameValuePair;
    import org.apache.http.client.ClientProtocolException;
    import org.apache.http.client.entity.UrlEncodedFormEntity;
    import org.apache.http.client.methods.HttpGet;
    import org.apache.http.client.methods.HttpPost;
    import org.apache.http.client.utils.URLEncodedUtils;
    import org.apache.http.impl.client.DefaultHttpClient;
    import org.apache.http.util.EntityUtils;

    import java.io.IOException;
    import java.io.UnsupportedEncodingException;
    import java.util.List;

    public class ServiceHandler {
        public static final int POST=2;
        public static final int GET=1;


    public String makeConnection(String url, int method,List<NameValuePair> params)
    {   String response = null;
        try
        {
        // http client

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;

        // Checking http request method type
        if (method == POST) {
            HttpPost httpPost = new HttpPost(url);
            // adding post params
            if (params != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(params));
            }

            httpResponse = httpClient.execute(httpPost);

        } else if (method == GET) {
            // appending params to url
            if (params != null) {
                String paramString = URLEncodedUtils
                        .format(params, "utf-8");
                url += "?" + paramString;
            }
            HttpGet httpGet = new HttpGet(url);

            httpResponse = httpClient.execute(httpGet);

        }
        httpEntity = httpResponse.getEntity();
        response = EntityUtils.toString(httpEntity);

    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


    return response;
    }
    }
