package com.spotifriends.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class NewUser {

    public final String accessCode;
    public final String username;
    public final String password;
    public final String token;
    public TrackArray trackArray;
    public ArtistArray artistArray;

    public NewUser (@JsonProperty("accessCode") String accessCode,
                    @JsonProperty("username") String username,
                    @JsonProperty("password") String password)
    {
        this.accessCode = accessCode;
        this.username = username;
        this.password = password;

//        this.getToken();
        this.token = "BQDmJvNxWFNnoJnGSDFnf5pK_ceFZxoo4Dn-1R5fsX6NtMD6ZPbzLzBSY98Vu3kFm_v85KSZ5I1EukeWuVapz1oY81aCrhfvz7ZpT-ebi3sHMHES5GXxrxlMA1nGOhu3Uk-G4S3VtHFz-SoQy0ZSJSnYm-PCqjB8fLbOsF2vl7WTwamQHnOySEzHPRLz77qQTKA-L8g3fn8WOqyife5a1M0rgMJ6lKk2Qq_mOqqrmkthyIcujUUcUEM7AiO56F28QNp37_aORUB8KyAQIg4tpQ";
        this.getTopData("tracks");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        this.getTopData("artists");

    }

    public String getUsername() {
        return username;
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

    public void getTopData(String tracks_or_artists) {
        try {
            Map<String, String> env = System.getenv();
            String client_secret = env.get("CLIENT_SECRET");
            String client_id = env.get("CLIENT_ID");

            URL url = new URL("https://api.spotify.com/v1/me/top/" + tracks_or_artists + "?time_range=medium_term&limit=10&offset=0");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + this.token);
            int responseCode = con.getResponseCode();

            String js = "";

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    js += line;
                }
                in.close();
                con.disconnect();
                Gson gson = new Gson();

                if (tracks_or_artists == "tracks") {
                    this.trackArray = gson.fromJson(js, TrackArray.class);
                    System.out.println(this.trackArray);
                } else {
                    this.artistArray = gson.fromJson(js, ArtistArray.class);
                    System.out.println(this.artistArray);
                }
            } else {
                System.out.println(responseCode);
                System.out.println("GET request failed in Server when getting top data.");
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
