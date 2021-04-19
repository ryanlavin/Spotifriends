import Head from 'next/head'

export default function dashboard() {
    return(
    <div className="container">
        <Head>
            <title>Dashboard</title>
            <link rel="icon" href="/favicon.ico" />
        </Head>
        <div className="topBar">
            <div className="logo">
                <text id="Spoti">Spoti</text>
                <text id="friends">friends</text>
            </div>
            <div className="NavContainer">
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
        </div>
        <div className="StatsContainer">
                <div className="StatsDisplay" id="TopSongs">
                    {/* <div className="StatsTitle" id="TopSongs">
                        Top Songs
                    </div>
                    <div className="RankingChart" id="TopSongs">
                        Ranking Chart
                        <div className = "ChartEntry">
                        </div>
                    </div> */}
                </div>
                <div className="StatsDisplay" id="TopArtists">
                TopArtists
                </div>
                <div className="StatsDisplay" id="TopGenres">
                TopGenres
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
                padding-top:14px;
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
            .StatsContainer{
                display:flex;
                flex-direction: row;
                align-items:stretch;
                background:blue;
                align-self: stretch;
                padding:10px;
                padding-left:5px;
                padding-right:5px;
            }
            .StatsDisplay{
                margin-right:5px;
                margin-left:5px;
                flex-grow:1;
                align-self: stretch;
                align-items:stretch;
                background:#810000;
            }
            .RankingChart{

                background:green;

            }
            
        `}
        </style>

        <style jsx global>{`
        html,
        body {
            background:#212121;
            padding: 0;
            margin: 0;
            font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto,
            Oxygen, Ubuntu, Cantarell, Fira Sans, Droid Sans, Helvetica Neue,
            sans-serif;
        }

        * {
          box-sizing: border-box;
        }
      `}</style>
    </div>
    )
}