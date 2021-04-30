import React from 'react';
import {Redirect,useHistory} from 'react-router-dom';
import Cookies from 'js-cookie';

export default function Logout(){
    const history = useHistory();
    return(
        <div className="container">
            <script>
            {Cookies.remove('sessionID')}
            {history.go(0)}
            {history.push('/dashboard')}
            </script>
        </div>
    )
}

/* console.log("here");
                    
                    */