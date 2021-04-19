import React from 'react';
import {css, jsx} from '@emotion/react';

function Navbar(){
    return (

        <div className="NavBar" css={css`
            font-size:60px;
        `}>
             Text
           {/*  <NavMenuItem>
                <NavLink to='/profile'>
                   Profile 
                </NavLink>
            </NavMenuItem> */}
        </div>
    
    )
}
export default Navbar;


