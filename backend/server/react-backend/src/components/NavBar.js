import React from 'react';
import { Link } from 'react-router-dom';
import "../css/NavBar.css";

function NavBar() {
    return (
            <div className="topBar">
            <div className="logo">
                <text id="Spoti">Spoti</text>
                <text id="friends">friends</text>
            </div>
            <div className="NavContainer">
                <div className="NavBarItem">
                    <a href="/dashboard">DASHBOARD</a>
                </div>
                <div className="NavBarItem">
                    <a href="/matching">MATCHING</a>
                </div>
                <div className="NavBarItem">
                    <a href="/profile">PROFILE</a>
                </div>
                <div className="NavBarItem">
                    <a href="/login">LOGIN</a>
                </div>
            </div>
            <style jsx>{`
            .topBar{
                display:flex;
                flex-direction:row;
                justify-content:space-between;
                height:51px;
                background:#535353;
            }
            .logo{
                padding-left:10px;
                color:white;
                font-size:33px;
                
            }
            .logo #Spoti{
                color:#1db954;
                font-weight: 600;
            }

            .NavContainer{
                display:flex;
                flex-direction: row;
                justify-content:flex-end;
                padding-right:10px;
                
            }
            .NavBarItem{
                font-size:20px;
                padding-top:10px;
                margin-left:16px;
            }
            .NavBarItem a:link, .NavBarItem a:visited{   
                color:#b3b3b3;
                text-decoration:none;
            }
            .NavBarItem a:hover{
                color:#e0e0e0;
                text-decoration:none;
            }
            
            
        `}
        </style>
        </div>
        
    )
}

export default NavBar;