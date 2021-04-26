import React, {Component} from "react";
import Switch from "react-switch";
import Cookies from 'js-cookie';
import {Redirect} from 'react-router-dom';
import axios from 'axios';
import { AxiosProvider, Request, Get, Delete, Head, Post, Put, Patch, withAxios } from 'react-axios';
class Profile extends Component {
    constructor(){
        super();
        this.state = {checked:false};
        this.handleChange=this.handleChange.bind(this);
    }
    handleChange(checked){
        this.setState({checked});
    }
    /* handleClick(){
        axios.post("http://localhost:8080/profile-api",{
            username: Cookies.get('uname'),
            session: Cookies.get('sessionID')
        }).then((response)=>{
            if(response.data.code != undefined && response.data.code == "SUCCESS"){
                alert("Added successfully");
            }
        })
    } */
    render(){
        if(Cookies.get('sessionID') == undefined){
            return <Redirect push to="/login"/>
        }
        else{
        return (
            <div className="container">
                <div className="ProfileCard">
                {/* profile card is outer box and column */}
                    <div className="ProfileTitle">Profile</div>
                    <label class="SettingLabel">
                        <span>Private</span>
                             <Switch onChange={this.handleChange} checked={this.state.checked}
                            onColor="#1bd31b" uncheckedIcon={false} height={24} width={50}/>
                            {/* <input type="checkbox" name="PrivateToggle" id="PrivateToggle"/> */}
                    </label>
                    {/* WHITELISTED USERS */}
                    <h1 className = "WhitelistedUsersTitle">Whitelisted Users</h1>
                    {/* DISPLAY WHITELISTED USERS */}
                    <Request
                        instance={axios.create({
                            baseURL:'http://localhost:8080/',
                            timeout:5000,
                        })}
                        url="/profile-api"
                        method='get'
                        data={
                            {
                                username:Cookies.get('uname'),
                                session:Cookies.get('sessionID')
                            }
                        }
                        onSuccess={(response)=>{
                            console.log(response);
                            this.setResults(
                                <ul className="WhitlistedUsersList">
                                    
                                </ul>
                            );
                        }}
                        onError={(error)=>{
                            console.log(Cookies.get('uname'));
                            console.log(Cookies.get('sessionID'));
                            console.log(error);
                        }}
                    />
                    {/* ADD USER */}
                    <div className="AddToWhitelist">
                        <input type="text" name="EnterUser" placeholder="Username"/>
                        <button onClick={ ()=>{
                            axios.post("http://localhost:8080/profile-api",{
                                username: Cookies.get('uname'),
                                session: Cookies.get('sessionID')
                            }).then((response)=>{
                                if(response.data.code != undefined && response.data.code == "SUCCESS"){
                                    alert("Added successfully");
                                }
                            })
                        }

                        }
                            
                            
                        
                        >Add User</button>
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
                    .ProfileCard{
                        display:flex;
                        flex-direction:column;
                        height:95%;
                        width:97.6%;
                        background:#535353;
                        border-radius:15px;
                    }
                    .ProfileTitle{
                        font-size:35px;
                        text-align:start;
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
                        padding-left: 20px;
                    }
                    .AddToWhitelist input{
                        border-radius:6px;
                        border:none;
                        height: 25px;
                        width:200px;
                        margin-right:15px;
                    }
                    .AddToWhitelist button{
                        border-radius:6px;
                        border:none;
                        height:28px;
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

export default Profile;