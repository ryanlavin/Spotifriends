import React from "react";
import RankingChart from "./rankingChart";
import ArtistChart from "./artistChart";
import axios from "axios";
import { AxiosProvider, Request, Get, Delete, Head, Post, Put, Patch, withAxios } from 'react-axios';
import {SpotifyAuthListener} from "react-spotify-auth";
import Cookies from 'js-cookie';
function Dashboard(props) {
    //props.topSongs, props.topArtists
    //array for testing;
    var songs=[
        {
            rank:1,
            image:"https://images.genius.com/9ebf3cd9c3e933279a81c8a250bb70e7.1000x1000x1.png",
            title:"Mood (feat. iann dior)",
            artist:"24kGoldn"  
        },
        {
            rank:2,
            image:"https://upload.wikimedia.org/wikipedia/en/6/6c/Playboi_Carti_-_Whole_Lotta_Red.png",
            title:"Sky",
            artist:"Playboi Carti"  
        }
    ];

    /* function getTest(){
        console.log("HERE!");
        axios({
            method:'get',
            url:'http://localhost:8080/dashboard-api',
            headers:{
                'Access-Control-Allow-Origin': '*',
                
            },
            timeout:5000,
        })
        .then((response) => {
            console.log(response);
        })
        .catch((error)=>{
            console.log(error);
        })
    } */
    
    return (
        
        <div className="container">
        <script>
        {
            //console.log(Cookies.get('sessionID'))
            //console.log(window.accessToken);
            //getTest()
        }
        </script>
        {/* <Request
            instance = {axios.create({
                baseURL:'http://localhost:8080/',
                timeout:5000,
            })}
            url='/dashboard-api'
            method='GET'
            onSuccess= {function(response){
                console.log(response.data)
            }}
            onError={function(error){
                console.log("error");
            }}
        /> */}
        <div className="StatsContainer">
                <div className="StatsDisplay" id="TopSongs">
                    <div className="StatsTitle" id="TopSongs">
                        Top Songs
                    </div>
                    <RankingChart/>
                </div>
                <div className="StatsDisplay" id="TopArtists">
                <div className="StatsTitle" id="TopArtists">
                        Top Artists
                    </div>
                    <ArtistChart/>
                </div>
            </div>
            <style jsx>{`
            .container{
                height:92% 
            }
            .StatsContainer{
                display:flex;
                height:100%;
                flex-direction:row;
                margin:10px;
                margin-top:12px;
                
            }
            .StatsDisplay{
                display:flex;
                flex-basis:0;
                flex-grow:1;
                border-radius: 15px;
                flex-direction:column;
                margin:10px;
                text-align:left;
                padding-left:18px;
                padding-right:18px;
                padding-bottom:18px;
                padding-top:7px;
                color:rgb(236, 236, 236);
                background:#535353;
            }
            .StatsDisplay .StatsTitle{
                font-size: 25px;
                font-weight:400;
                margin-bottom:8px;
            }
   
            `}
            </style>
        </div>
    );
} 

export default Dashboard;