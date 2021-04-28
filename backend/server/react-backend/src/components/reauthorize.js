import React from 'react';
import {SpotifyAuth,SpotifyAuthListener, Scopes} from 'react-spotify-auth';
import {Redirect,useHistory} from 'react-router-dom';
import Cookies from 'js-cookie';
export default function Reauthorize(){

    return( 
        <div className="container">
            <SpotifyAuthListener
                onAccessToken={(token)=>{
                    console.log(token);
                    Cookies.set('spotifyAuthToken',token);
                }}  
            />
            {redirect()}
            <SpotifyAuth 
                id="login-button"
                redirectUri='http://localhost:3000/reauthorize'
                clientID='35a2f5b326314bf381975f4e5d4dcc1e'
                scopes={[Scopes.userReadPrivate, Scopes.userReadEmail, Scopes.userTopRead]}
                onAccessToken={(token)=>{
                    Cookies.set('spotifyAuthToken',token);
                }}
            />
        </div>
    )
}

function redirect(){

    if(Cookies.get('spotifyAuthToken')!== undefined){
        return <Redirect to='/dashboard'/>
    }
}