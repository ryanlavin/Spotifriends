package com.spotifriends.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class NewUser {

    private final String accessCode;
    private final String email;
    private final String password;
    private final String token;

    public NewUser (@JsonProperty("accessCode") String accessCode,
                    @JsonProperty("email") String email,
                    @JsonProperty("password") String password)
    {
        this.accessCode = accessCode;
        this.email = email;
        this.password = password;

//        this.getToken();
        this.token = "BQDyVBsL-7cYmDRZE-cYka4sz_ZprQ7nmj7msYNWl2oqTim2dT6Kbs7ER8LL9PhLs3rLp9UbrlYeuGok4N_G7uDcCvbpPvC7VwLaCfbXD4dfznpz1wBR_v8Ge0bsOOLOFudTx_QJgIbnFzKAg0N8-WYtX_AumrQ3_baJGAKnqrBv3CNElQWOzPUubj34ijYIsqBbF5EwROlbvmySW5CX2PavY1G79RTrujOqVQu0xBWJVPsHmN1QqJRu9LOFQpyv86dSsUhsqHgiOXdh17MYrg";
        this.getTopData();

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void getToken() {
        try {
            Map<String, String> env = System.getenv();
            String client_secret = env.get("CLIENT_SECRET");
            String client_id = env.get("CLIENT_ID");

            URL url = new URL("https://accounts.spotify.com/api/token");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("grant_type", "authorization_code");
            con.setRequestProperty("code", "accessCode");
            con.setRequestProperty("redirect_uri", "http://localhost:8080/register");
            con.setRequestProperty("client_id", client_id);
            con.setRequestProperty("client_secret", client_secret);
            int responseCode = con.getResponseCode();
    //		System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
    //			String responseString = "";
    //			StringBuffer response = new StringBuffer();
                float closePrice = -1;
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
    //            String empty = in.readLine();
    //            String inputLine = in.readLine();
    //			System.out.println(inputLine);
    //            String[] fields = inputLine.split(",");
    //            closePrice = Float.parseFloat(fields[1]);
                in.close();
                con.disconnect();
    //            return closePrice;
            } else {
                System.out.println("GET request failed in Server. Either an invalid date or ticker was present.");
    //            return -1;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTopData() {
        try {
            Map<String, String> env = System.getenv();
            String client_secret = env.get("CLIENT_SECRET");
            String client_id = env.get("CLIENT_ID");

            URL url = new URL("https://api.spotify.com/v1/me/top/tracks?time_range=medium_term&limit=50&offset=0");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + this.token);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                //            String empty = in.readLine();
                //            String inputLine = in.readLine();
                //			System.out.println(inputLine);
                //            String[] fields = inputLine.split(",");
                //            closePrice = Float.parseFloat(fields[1]);
                in.close();
                con.disconnect();
                //            return closePrice;
            } else {
                System.out.println(responseCode);
                System.out.println("GET request failed in Server when getting top data.");
                //            return -1;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
