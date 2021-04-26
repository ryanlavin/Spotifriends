import React, {Component, useEffect, useState } from "react";
import '../css/matching.css';
import axios from "axios";
import { AxiosProvider, Request, Get, Delete, Head, Post, Put, Patch, withAxios } from 'react-axios';
import Cookies from "js-cookie";
import {Redirect,useHistory} from "react-router-dom";
import FormInput from "./input-component";

const realButton = <button id="RealButton"
                
>
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
            uName: "",
            uName2:"",
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
        if(this.state.showButton){
            this.setState({readyToMatch:true});
        }
        else{
            alert("Please enter a valid username");
        }
    }
    handleChange = (event) => {
        const {value} = event.target;
        this.setState({ uName: value },()=>{
            if(this.state.uName.length>5){
                this.setState({showButton:true})

            }
            else{
                this.setState({showButton:false})
            }
        });
    };
    setResults = (res) => {
        this.setState({results:res});
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
                                value={this.state.uName}   
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
                                        method='get'
                                        onSuccess={(response)=>{
                                            this.setResults(
                                                <div className="ResultContainer">
                                                    Matching Score: {response.data.score}
                                                    <style jsx>{`
                                                        .ResultContainer {
                                                            font-size:35;
                                                            text-align: center;
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


