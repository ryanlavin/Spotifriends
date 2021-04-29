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
            {/* {redirect()} */}
            <div className="reauthcontainer">
                <div id="reauthmessage">
                    Please reauthorize access to your Spotify account, then swtich to matching page and back:
                </div>
                <SpotifyAuth 
                    id="login-button"
                    redirectUri='http://localhost:3000/reauthorize'
                    clientID='35a2f5b326314bf381975f4e5d4dcc1e'
                    scopes={[Scopes.userReadPrivate, Scopes.userReadEmail, Scopes.userTopRead]}
                    onAccessToken={(token)=>{
                        Cookies.set('spotifyAuthToken',token);
                    }}
                />
                <style jsx>{`
                    .container{
                        display:flex;
                        justify-content:center;
                        align-items:center;
                        height:80%;
                    }
                    .reauthcontainer{
                        display:flex;
                        margin-top:20px;
                        flex-direction:column;
                        justify-content:center;
                        align-items:center;
                    }
                    #reauthmessage{
                        font-size:30px;
                        color:white;
                        margin-bottom:20px;
                    }
                    
                `}
                </style>
            </div>
            
        </div>
    )
}

function redirect(){

    if(Cookies.get('spotifyAuthToken')!== undefined){
        return <Redirect to='/dashboard'/>
    }
}