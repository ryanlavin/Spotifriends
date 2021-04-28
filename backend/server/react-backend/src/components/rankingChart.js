import React, {Component} from "react";
import axios from "axios";
import { AxiosProvider, Request, Get, Delete, Head, Post, Put, Patch, withAxios } from 'react-axios';
import {getData, getPreview} from 'spotify-url-info';
import Cookies from 'js-cookie';
import {Redirect} from 'react-router-dom';
//import {spotify} from 'spotify-cover-art-api';
//rankElements -> 
/* 
    [
        { 
            rank: the ranking (number) of the song/artist/genre
            image: path to image,
            title: song title/genre title/artist name
            artist: if song, then this will have the artist title
        }
    ]
*/

export default class RankingChart extends Component{
    /* const elements = props.rankElements.map(function(element){
        return (<div className="RankElement">
                    <div className ="RankNumber">{element.rank}</div>
                    <img src={element.image} alt="ALBUM COVER"/>
                    <div className="RankElementInfo">
                        <div className="RankElementTitle">{element.title}</div>
                        <div className="RankElementArtist">{element.artist}</div>
                    </div>
                </div>
        )
    }) */
    constructor(){
        super();
        this.artistList = false;
        this.state = {
            tracks: [],
            artists:[],
        };
    }
    setTracks(t){
        this.setState({tracks:t});
    };
    checkAccessToken(){
        if(Cookies.get('spotifyAuthToken') === undefined 
        && Cookies.get('sessionID') !== undefined){
            return <Redirect to="/reauthorize"/>
        }
    }
    render(){return (
        <div className="RankingChart">
            {this.checkAccessToken()}
            <Request
            instance = {axios.create({
                baseURL:'http://localhost:8080/',
                timeout:5000,
            })}
            url='/dashboard-api'
            method='get'
            onSuccess= {(response)=>{
                //console.log(response.data.artists);
                var tracks = [];
                
                tracks = response.data.tracks;
                
                
                //console.log(tracks);
                tracks.sort((a,b) => a.popularity < b.popularity ? 1 : -1);
                //console.log(tracks);
                var index = 0;

                    
                var idArray = [];
                for(var i=0;i<tracks.length;i++){
                    idArray.push(tracks[i].id);
                }
                var idstring = idArray.join(',');
                var cookieToken = Cookies.get('spotifyAuthToken');
                if(!(cookieToken === undefined)){
                    axios.get("https://api.spotify.com/v1/tracks",{
                        headers:{
                            Authorization: `Bearer ${cookieToken}`
                        },
                        params:{
                            ids: idstring
                        }
                        
                }).then((response)=>{
                    
                    console.log(response.data);
                    var t = tracks.map(function(element){
                        index = index + 1;
                        var match = response.data.tracks.find(track=>
                            track.id===element.id
                        );
                        var artSrc = match.album.images[1].url;
                        console.log(artSrc);
                        var artistSrc = match.artists[0].name;
                        return (<div className="RankElement">
                                    <div className ="RankNumber">{index}</div>
                                    <img src={artSrc} alt="ALBUM COVER"/>
                                    <div className="RankElementInfo">
                                        <div className="RankElementTitle">{element.name}</div>
                                        <div className="RankElementArtist">{artistSrc}</div>
                                    </div>
                                </div>
                                
                        )
                    })
                    this.setTracks(t);
                }).then((error)=>{
                    console.log(error);
                })}
                else{
                    var t = tracks.map(function(element){
                        index = index + 1;
                        var artistSrc = "";
                        return (<div className="RankElement">
                                    <div className ="RankNumber">{index}</div>
                                    <img src={""} alt="ALBUM COVER"/>
                                    <div className="RankElementInfo">
                                        <div className="RankElementTitle">{element.name}</div>
                                        <div className="RankElementArtist">{artistSrc}</div>
                                    </div>
                                </div>
                                
                        )
                    })
                    this.setTracks(t);
                }
                
                
            }}
            onError={function(error){
                console.log("error");
            }}
            />
            {this.state.tracks}
            <style jsx>{`
                .RankingChart{
                    flex-grow:1;
                    display:flex;
                    flex-direction:column;
                    max-width:100%;
                    
                }
                .RankElement{
                    display:flex;
                    flex-grow:0;
                    flex-direction:row;

                    height:100px;
                    width:100%;
                    margin-top:10px;
                    margin-bottom:10px;
                }
                .RankElement img{
                    height:100px;
                    width:100px;
                    border-radius:5px;
                }
                .RankElement .RankElementInfo{
           
                    padding:8px;
                }
                .RankElement .RankElementTitle{
                    font-weight:500;
                    font-size:17px;
                }   
                
                .RankElement .RankNumber{
                    align-self:center;
                    font-size:20px;
                    padding-right:10px;
                }
                
            `}
            </style>
        </div>
    )}
}