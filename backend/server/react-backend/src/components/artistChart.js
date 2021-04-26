import React, {Component} from "react";
import axios from "axios";
import {Request} from 'react-axios';
import {getData, getPreview} from 'spotify-url-info';
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

export default class ArtistChart extends Component{
 
    constructor(){
        super();
        this.state = {
            tracks: [],
            artists:[],
        };
    }
    setTracks(t){
        this.setState({tracks:t});
    };
    render(){return (
        <div className="RankingChart">
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
                
                tracks = response.data.artists;
                
                //console.log(tracks);
                tracks.sort((a,b) => a.popularity < b.popularity ? 1 : -1);
                console.log(tracks);
                var index = 0;
                    var t = tracks.map(function(element){
                        index = index + 1;
                        var artSrc = element.images[1];
                        console.log(artSrc);
                        var artistSrc = element.name;
                        return (<div className="RankElement">
                                    <div className ="RankNumber">{index}</div>
                                    <img src={artSrc} alt="ALBUM COVER"/>
                                    <div className="RankElementInfo">
                                        <div className="RankElementTitle">{artistSrc}</div>
                                        <div className="RankElementArtist">{""}</div>
                                    </div>
                                </div>
                                
                        )
                    })
                    this.setTracks(t);

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