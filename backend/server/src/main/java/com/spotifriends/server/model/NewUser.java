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
        this.token = "BQBXg2PQhqCd-NeZcmm-2xNZoyvUETlYGdTXzNUqOmjBr8u4ctiUxcetHz2qLfdwqda6F6z8DMvNsTo57MIIuZbX4P2d_P5X-lk8tkucGOtm9bw-d6IVHjja13flNAugNore5sgdSx5k4vG_GnPBHYOugD7MTAXhuZ5KSM-L5jy3UNvNLamuSxMEYylif6ZwMH69tY1vMGVZbStFgzMVHy1WTJtIVbkq5EPQI3n3vhjW9WcSM-u1a2yeZ89lSiCtSPu2mJvUooF4dk-AgJIlGw";



    }

    public String validate() {
//        this.getToken();
//        if (this.token.equals("COULD NOT GET TOKEN")) {
//            return "COULD NOT GET TOKEN";
//        }
        this.getTopData("tracks");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        this.getTopData("artists");
        return "GOT TOKEN";
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

    public String[] getTracks() {
        String[] trackIds = new String[this.trackArray.size()];
        for (int i=0; i < this.trackArray.size(); i++) {
            trackIds[i] = this.trackArray.items.get(i).id;
        }
        return trackIds;
    }

    public String[] getArtists() {
        String[] artistIds = new String[this.artistArray.size()];
        for (int i=0; i < this.artistArray.size(); i++) {
            artistIds[i] = this.artistArray.items.get(i).id;
        }
        return artistIds;
    }

    public String getToken() {
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
                return "GOT TOKEN";
            } else {
                System.out.println("GET request failed in Server. Either an invalid date or ticker was present.");
                return "COULD NOT GET TOKEN";
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "COULD NOT GET TOKEN";
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

    @Override
    public String toString() {
        return "NewUser{" +
                "accessCode='" + accessCode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", trackArray=" + trackArray +
                ", artistArray=" + artistArray +
                '}';
    }
}
