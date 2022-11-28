import * as React from 'react';
import { useEffect, useState } from "react";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import LoginIcon from '@mui/icons-material/Login';
import LogoutIcon from '@mui/icons-material/Logout';
import { apiLogout } from '../../remote/social-media-api/auth.api';
import { useNavigate } from 'react-router-dom';
import Tooltip from '@mui/material/Tooltip';
import { useContext } from 'react';
import { UserContext } from '../../context/user.context';

export default function Navbar() {

    const navigate = useNavigate();

    const { user, setUser } = useContext(UserContext);
    const [loggedIn, setLoggedIn] = useState(<></>);
    const [tipTitle, setTipTitle] = useState('');
    
    
    useEffect(() => {
        if(user) {
            setLoggedIn(<LogoutIcon />);
            setTipTitle('Logout');
        } else {
            setLoggedIn(<LoginIcon />);
            setTipTitle('Login');
        }
    }, [user]);

    function handleAuth() {
        if(user) {
            apiLogout();
            setUser();
        } else {
           navigate('/login'); 
        }
    } 

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" color="transparent">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Revature Social
          </Typography>
            <div>
            <Tooltip disableFocusListener disableTouchListener title={tipTitle}>
            <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={() => handleAuth()}
                color="inherit"
            >
                {loggedIn}
            </IconButton>
            </Tooltip>
            </div>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
