import React, {Component, useEffect, useState } from "react";
import '../css/matching.css';
import axios from "axios";
import { AxiosProvider, Request, Get, Delete, Head, Post, Put, Patch, withAxios } from 'react-axios';
import Cookies from "js-cookie";
import {Redirect,useHistory} from "react-router-dom";
import FormInput from "./input-component";
import { CircularProgressbar,buildStyles } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';

const realButton = <button id="RealButton">
Match
<style jsx>{`
    #RealButton{
        background-color:#12c012;
    }
    
`}
</style>
</button>

const fakeButton = <button id="FakeButton">
    Match
    <style jsx>{`
        #FakeButton{
            background-color: grey;
        }  
        
    `}
    </style>
    </button>
export default class Matching extends Component{

/*
    handleFormLoad = (event) => {
        console.log("Gotta handle form loading");
    };*/
    constructor(props){
        super(props);
        
        this.state = {
            uName: Cookies.get('uname'),
            uName2: "",
            results:<div></div>,
            readyToMatch:false,
            showButton:false
        };
    }
    /* returnButton = () => {
        if(this.state.showButton){
            return <button id="RealButton"
                
                >
                Match
                <style jsx>{`
                    #RealButton{
                        background-color:#12c012;
                    }
                    
                `}
                </style>
            </button>
        }
        else{
            return(
                <button id="FakeButton">
                    Match
                    <style jsx>{`
                        #FakeButton{
                            background-color: grey;
                        }
                        
                        
                        
                    `}
                    </style>
                    </button>
            )
        }
    } */
    handleButton = () =>{
        this.setState({readyToMatch:true});
    }
    handleChange = (event) => {
        const {value} = event.target;
        this.setState({ uName2: value },()=>{
            if(this.state.uName2.length>5){
                this.setState({showButton:true})

            }
            else{
                this.setState({showButton:false})
            }
        });
    };
    setResults = (res) => {
        this.setState({results:res, readyToMatch:false});
    }
    render(){
        if(Cookies.get('sessionID') == undefined){
            return <Redirect push to="/login"/>
        }
        else{
        return (
            <div className="container">
                <div className="MainCard">
                    <div className="SearchTitle">Search for a user. . .</div>
                    <div className="AddToWhitelist">
                            <input
                                type="text"
                                value={this.state.uName2}   
                                type="text"
                                name="EnterUser"
                                placeholder="Username"
                                onChange={e=> this.handleChange(e)}
                                required/>
                            {/* this.state.showButton ? realButton : fakeButton */}
                            <button onClick={this.handleButton}>Match</button>
                            {this.state.readyToMatch ? 
                                
                                    <Request
                                        instance={axios.create({
                                            baseURL:'http://localhost:8080/',
                                            timeout:5000,
                                        })}
                                        url="/matching-api"
                                        method='post'
                                        data ={
                                            {
                                                username:Cookies.get('uname'),
                                                session:Cookies.get('sessionID'),
                                                friend_name:this.state.uName2
                                            }
                                        }
                                        onSuccess={(response)=>{
                                            console.log(this.state.uName2);
                                            console.log(response.data.score);
                                            var result = "Matching Score: ";
                                            var validResult = false;
                                            var resultVar = 0; 
                                            if(response.data.score == "INVALID QUERY"){
                                                result = "User does not exist";
                                            }
                                            else if(response.data.score === 'INVALID PRIVATE'){
                                                result = "User is private";
                                            }
                                            else{
                                                resultVar = Number(response.data.score);
                                                resultVar = (resultVar.toFixed(2)) * 100;
                                                result = result + resultVar + "%";
                                                validResult = true;
                                            }
                                            this.setResults(
                                                <div className="ResultContainer">
                                                    {validResult ? <span  id="MATCHINGSCORE">Matching Score:</span>:<div></div>}
                                                    {/* {result} */}
                                                    {validResult ?
                                                        <div className="CircleContainer">
                                                            <CircularProgressbar value={resultVar} text={`${resultVar}%`}
                                                            styles={buildStyles({
                                                                pathColor:`#1db954`,
                                                                textColor:`#1db954`,
                                                            })
                                                            
                                                            }
                                                        />
                                                        <style jsx>{`
                                                            .CircleContainer{
                                                                height:20%;
                                                                width:20%;
                                                                font-size:35px;
                                                                color:#1db954;
                                                            }
                                                            #MATCHINGSCORE{
                                                                margin-bottom:15px;
                                                                color:#1db954;
                                                            }  
                                                        `}
                                                        </style>
                                                        </div>
                                                        :
                                                    `${result}`}
                                                    <style jsx>{`
                                                        
                                                        .ResultContainer{
                                                            display:flex;
                                                            flex-direction:column;
                                                            align-items:center;
                                                            font-size:35px;
                                                            text-align: center;
                                                            padding-top:10px;
                                                            
                                                        }
                                                    `}
                                                    </style>
                                                </div>
                                            );
                                        }}
                                        onError={(error)=>{
                                            console.log(error);
                                        }}
                                    />
                                
                                
                            :<div></div>}
                            
                    </div>
                    {this.state.results}
                </div>
                <style jsx>{`
                        .container {
                            display:flex;
                            flex-direction:row;
                            justify-content:center;
                            align-items:center;
                            height:94.5%;
                            vertical-align:center;
                            color:rgb(236, 236, 236);
                        }
                        .MainCard{
                            display:flex;
                            flex-direction:column;
                            height:95%;
                            width:97.6%;
                            background:#535353;
                            border-radius:15px;
                        }
                        .SearchTitle{
                            font-size:45px;
                            text-align:center;
                            padding-left:20px;
                            padding-top:8px;
                            margin-bottom:20px;
                        }  
                        .SettingLabel{
                            display:flex;
                            flex-direction:row;
                            justify-content:space-between;
                            text-align:right;
                            padding-right:16px;
                            padding-left:20px;
                            font-size:22px;
                            align-items:center;
                        }
                        .SettingLabel span{
                            margin-right:20px;
                        }
                        h1{
                            font-weight:normal;
                            font-size:22px;
                            color:rgb(236, 236, 236);
                            text-align:start;
                            padding-left:20px;
                        }
                        .AddToWhitelist {
                            display:flex;
                            flex-direction:row;
                            justify-content:center;
                            padding-left: 20px;
                        }
                        .AddToWhitelist input{
                            border-radius:6px;
                            border:none;
                            height: 35px;
                            width:350px;
                            margin-right:15px;
                        }
                        .AddToWhitelist button{
                            border-radius:6px;
                            border:none;
                            height:38px;
                            width:80px;
                            background-color:#12c012;
                            color:rgb(236, 236, 236);
                            font-size:15px;
                            font-weight:600;
                        }
                        
                    `}
                    </style>
            </div>
        );
        }
    }
}

/*<form id="matching-form" onLoad={this.handleFormLoad} />
{this.state.listitems.map(listitem => (
    <li className="list-group-item list-group-item-primary">
        {listitem}
    </li>
))}*/


