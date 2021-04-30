import React, {Component} from "react";
import Switch from "react-switch";
import Cookies from 'js-cookie';
import {Redirect} from 'react-router-dom';
import axios from 'axios';
import { AxiosProvider, Request, Get, Delete, Head, Post, Put, Patch, withAxios } from 'react-axios';
class Profile extends Component {
    constructor(){
        super();
        this.state = {
            checked:false,
            friends:[],
            whiteListReady:false,
            //whiteList:<div className="friend"> friendfriend</div>,
            addFriendVal:"",
            dataReady:false,
            invalid:false
        };
        this.handleChecked = this.handleChecked.bind(this);
        this.setWhitelist = this.setWhitelist.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleClick = this.handleClick.bind(this);
        
    }
    componentDidMount(){
        axios.get("http://localhost:8080/profile-api",{
            headers:{
                'username':Cookies.get('uname'),
                'session': Cookies.get('sessionID')
            }
        }).then((response)=>{
            console.log(response.data);
            console.log(Cookies.get('uname'));
            console.log(Cookies.get('sessionID'));
            /* this.state.checked = response.data.priv;
            this.state.friends = response.data.friends; */
            if(response.data.priv =="INVALID"){
                this.setState({ 
                    invalid:true
                })
            }
            else{
                this.setState({
                
                    checked: response.data.priv,
                    friends: response.data.friends,
                    dataReady:true
                })
            }
            
        })
    }
    handleChecked = (checked)=>{
        console.log("CHECKED: " + this.state.checked);
        this.setState({checked});
        axios.put("http://localhost:8080/profile-api",{
            username: Cookies.get('uname'),
            session: Cookies.get('sessionID'),
            priv: checked
        }).then((response)=>{
            console.log(response);
            alert("Profile set to: " + (checked ? "PRIVATE" : "PUBLIC") );
            
        })
    }
    setWhitelist = (wl)=>{
        console.log(wl);
        if(wl !== undefined){
            this.setState({friends:wl,
                whiteListReady:true,
                whiteList:

                    <div className="friend">{wl}</div>

            });
        }
    }
    handleChange = (event) => {
        
        const {value} = event.target;       
        this.setState({ addFriendVal: value },()=>{
            /* console.log(this.state.addFriendVal); */
        });
    };
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
    handleClick = ()=>{
        console.log("CLICK!");
        axios.post("http://localhost:8080/profile-api",{
            username: Cookies.get('uname'),
            session: Cookies.get('sessionID'),
            friend_name: this.state.addFriendVal
        }).then((response)=>{
            console.log(response);
            if(response.data != undefined && response.data == "SUCCESS"){
                alert("Added successfully");
                var newFriendsArr = this.state.friends;
                newFriendsArr.push(this.state.addFriendVal);
                console.log(newFriendsArr);
                this.setState({
                    friends:newFriendsArr
                })
            }
        })
    }
    render(){
        if(Cookies.get('sessionID') == undefined){
            return <Redirect push to="/login"/>
        }
        else if(!this.state.dataReady){
            return null;
        }
        else{
        return (
            <div className="container">
                <div className="ProfileCard">
                {/* profile card is outer box and column */}
                    <div className="ProfileTitle">Profile</div>
                    <label className="SettingLabel">
                        <span>Private</span>
                             <Switch onChange={this.handleChecked} checked={this.state.checked}
                            onColor="#1bd31b" uncheckedIcon={false} height={24} width={50}/>
                            {/* <input type="checkbox" name="PrivateToggle" id="PrivateToggle"/> */}
                    </label>
                    {/* WHITELISTED USERS */}
                    <h1 className = "WhitelistedUsersTitle">Whitelisted Users</h1>
                    {/* DISPLAY WHITELISTED USERS */}
                    <ul className="WhitelistDisplay">
                        {this.state.invalid?<div></div>:<Whitelist friends={this.state.friends}/>}
                    </ul>
                    
                    {/* {this.state.whiteList} */}
                    {/* ADD USER */}
                    <div className="AddToWhitelist">
                        <input
                            type="text"
                            value={this.state.addFriendVal}   
                            type="text"
                            name="EnterUser"
                            placeholder="Username"
                            onChange={e=> this.handleChange(e)}
                            required/>
                        <button onClick={this.handleClick}>Add User</button> 
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
                        font-size:21px;
                        color:rgb(236, 236, 236);
                        text-align:start;
                        padding-left:20px;
            
                    }
                    .WhitelistDisplay{
                        text-align:left;
                        padding-left:40px;
                    }
                    .AddToWhitelist {
                        display:flex;
                        padding-top:10px;
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

function Whitelist(props){
    var friends = [];
    try{
        friends = Array.from(props.friends);
    }
    catch(e){
        console.log(e);
    }
    

    return(
            (friends).map(function(friend){
                return <li className="Friend">
                    {friend}
                </li>
            })
        
    )
}

export default Profile;