import React from "react";

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

export default function RankingChart(props) {
    const elements = props.rankElements.map(function(element){
        return (<div className="RankElement">
                    <div className ="RankNumber">{element.rank}</div>
                    <img src={element.image} alt="ALBUM COVER"/>
                    <div className="RankElementInfo">
                        <div className="RankElementTitle">{element.title}</div>
                        <div className="RankElementArtist">{element.artist}</div>
                    </div>
                </div>
        )
    })
    return (
        <div className="RankingChart">
            {elements}
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
    )
}