import React from 'react';
import { Route, Routes } from "react-router-dom";
import Login from '../components/login/Login';
import { PostFeed } from '../components/post-feed/PostFeed';
import Register from '../components/register/Register';

export const AppRoutes: React.FC<unknown> = () => (
  <Routes>
    <Route path="/" element={<PostFeed />} />
    <Route path="/login" element={<Login />} />
    <Route path="/register" element={<Register />} />
  </Routes>
)