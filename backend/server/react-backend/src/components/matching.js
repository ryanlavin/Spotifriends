import React, { useEffect, useState } from "react";

import '../css/matching.css';
import axios from "axios";

export const Matching = () => {
    const [songData, setSongData] = useState([{name: "Song Name", artist: "Drake"}, {name: "Song 2", artist: "G-Eazy"}]);

    useEffect(() => {
        axios.get("https:localhost:8080/songs")
            .then(response => {
                console.log(response)
                if (response && response.data) {
                    setSongData(response.data)
                }
            })
            .catch(e => {
                console.log("Some error happened while fetching song/artist objects from db:", e)
            })
    });
/*
    handleFormLoad = (event) => {
        console.log("Gotta handle form loading");
    };*/

    return (
        <div className="songData">
            {songData.map(oneSong => (
                <div>
                    Title: {oneSong.name}
                    <br />
                    Artist: {oneSong.artist}
                </div>
            ))}
        </div>
    );
}

/*<form id="matching-form" onLoad={this.handleFormLoad} />
{this.state.listitems.map(listitem => (
    <li className="list-group-item list-group-item-primary">
        {listitem}
    </li>
))}*/

export default Matching;
