import React from "react";
import {SpotifyAuth,SpotifyAuthListener, Scopes} from 'react-spotify-auth';
import axios from 'axios';
import 'react-spotify-auth/dist/index.css';
import {useHistory} from "react-router-dom";
import Cookies from 'js-cookie';

export default function Authenticate(){
    let history = useHistory();
    function handleRedirect(token){
        window.accessToken = token;
        axios.post("http://localhost:8080/register-api",
        {
            accessCode: token,
            username: Cookies.get('uname'),
            password: Cookies.get('pw'),
            
        }
        ).then((response) => {
            console.log(response);
            axios.post("http://localhost:8080/login-api",{
                username: Cookies.get('uname'),
                password: Cookies.get('pw'),
            }).then((response)=>{
                var in30Minutes = 1/48;
                Cookies.set('sessionID',response.data.session,{
                    expires: in30Minutes
                });
                //console.log(Cookies.get('sessionID'));
                //console.log(response.data.session);
                history.push("/dashboard");
            })
        });

        //console.log("ACCESS TOKEN @ AUTHENTICATE: " + window.accessToken);
        //history.push("/dashboard");
    }
    /* function handleRegistration(){
        axios.post("https:localhost:8080/register-api",
        {
            username: Cookies.get('uname'),
            password: Cookies.get('pw'),
            accessToken: Cookies.get('spotifyAuthToken')
        }
        );
    } */
    return(
        <div className="container">
            <SpotifyAuthListener
                onAccessToken={handleRedirect}  
            />
        </div>
    )
}