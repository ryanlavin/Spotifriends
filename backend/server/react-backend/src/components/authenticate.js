import React from "react";
import {SpotifyAuth,SpotifyAuthListener, Scopes} from 'react-spotify-auth';
import 'react-spotify-auth/dist/index.css';
import {useHistory} from "react-router-dom";

export default function Authenticate(){
    let history = useHistory();
    function handleRedirect(token){
        window.accessToken = token;
        //console.log("ACCESS TOKEN @ AUTHENTICATE: " + window.accessToken);
        history.push("/dashboard");
    }
    return(
        <div className="container">
            <SpotifyAuthListener
                onAccessToken={handleRedirect}  
            />
        </div>
    )
}