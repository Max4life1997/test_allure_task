import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class getPosts {
    private static HttpURLConnection connection;

    public void getPostList(String targetUrl, int connectionTimeOut, int readTimeOut) {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(connectionTimeOut);
            connection.setReadTimeout(readTimeOut);

            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }

            parse(responseContent.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

    }



    private static String parse(String responseBody){
        JSONArray albums = new JSONArray(responseBody);
        for (int i =0; i < albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            String body = album.getString("body");
            System.out.println("Id:"+ id + "\nTitle:" + title + "\nUserId:" + userId + "\nBody:" + body);
            System.out.println("--------------------------------------------");
        }
        return null;

    }

}


//http://jsonplaceholder.typicode.com/albums

//    private static HttpURLConnection connection;
//
//    public void getPostList(String targetUrl, int connectionTimeOut, int readTimeOut) {
//
//        BufferedReader reader;
//        String line;
//        StringBuffer responseContent = new StringBuffer();
//        try {
//            URL url = new URL(targetUrl);
//            connection = (HttpURLConnection) url.openConnection();
//
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(connectionTimeOut);
//            connection.setReadTimeout(readTimeOut);
//
//            int status = connection.getResponseCode();
//            if (status > 299) {
//                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                while ((line = reader.readLine()) != null){
//                    responseContent.append(line);
//                }
//                reader.close();
//            }
//            else {
//                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                while ((line = reader.readLine()) != null){
//                    responseContent.append(line);
//                }
//                reader.close();
//            }
//            System.out.println("This " + responseContent);
//            parse(responseContent.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            connection.disconnect();
//        }
//
//    }
//
//    public static String parse(String responseBody){
//        JSONArray albums = new JSONArray(responseBody);
//        for (int i =0; i < albums.length(); i++){
//            JSONObject album = albums.getJSONObject(i);
//            int id = album.getInt("id");
//            int userId = album.getInt("userId");
//            String title = album.getString("title");
//            String body = album.getString("body");
//            System.out.println("Id:"+ id + "\nTitle:" + title + "\nUserId:" + userId + "\nBody:" + body);
//            System.out.println("--------------------------------------------");
//        }
//        return null;
//
//    }