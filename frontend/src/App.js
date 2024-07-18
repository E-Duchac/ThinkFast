// import logo from './logo.svg';
import React from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './pages/home';
import Login from './pages/login';
import Profile from './pages/profile';
import SetupQuiz from './pages/setupQuiz';
import TakeQuiz from './pages/takeQuiz';
import QuizStatistics from './pages/quizStatistics';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/setupQuiz" element={<SetupQuiz />} />
        <Route path="/takeQuiz" element={<TakeQuiz />} />
        <Route path="/quizStatistics" element={<QuizStatistics />} />
      </Routes>
    </Router>
  );
}

export default App;
