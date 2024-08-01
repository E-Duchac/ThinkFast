import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';

const TakeQuiz = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const questionList = location.state?.questionList;
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [userResponse, setUserResponse] = useState("");
    const [answerMatches, setAnswerMatches] = useState(null);
    const [score, setScore] = useState(0);

    const Italic = ({children}) => <i>{children}</i>;
    const Bold = ({children}) => <b>{children}</b>;
    const Sup = ({children}) => <sup>{children}</sup>;
    const Sub = ({children}) => <sub>{children}</sub>;

    const needsFormatting = /<i>|<b>|<sup>|<sub>/.test(questionList[currentQuestionIndex].questionText);

    useEffect(() => {
        if (answerMatches !== null) {
            if (answerMatches) {
                alert("Correct!");
            } else {
                alert("Incorrect. Correct answer(s): " + questionList[currentQuestionIndex].answerText);
            }
            setAnswerMatches(null);
        }
    }, [answerMatches, currentQuestionIndex, questionList]);
    
    const formatText = (text) => {
        const parts = text.split(/(<i>.*?<\/i>|<b>.*?<\/b>|<sup>.*?<\/sup>|<sub>.*?<\/sub>)/g);
        return parts.map((part, index) => {
            if (part.startsWith('<i>')) {
                return <Italic key={index}>{part.slice(3, -4)}</Italic>;
            } else if (part.startsWith('<b>')) {
                return <Bold key={index}>{part.slice(3, -4)}</Bold>;
            } else if (part.startsWith('<sup>')) {
                return <Sup key={index}>{part.slice(5, -6)}</Sup>;
            } else if (part.startsWith('<sub>')) {
                return <Sub key={index}>{part.slice(5, -6)}</Sub>;
            }
            return <React.Fragment key={index}>{part}</React.Fragment>
        });
    };

    const getNextQuestion = () => {
        console.log("Index: " + currentQuestionIndex);
        console.log("questionList.length-1: " + (questionList.length - 1));
        console.log("Index = questionList.length-1: " +
            currentQuestionIndex === (questionList.length - 1)
        );
        if (currentQuestionIndex === questionList.length - 1) {
            console.log("Quiz finished! Your score: " + score);
            setUserResponse("");
            setScore(0);
            //navigate away
        } else if (currentQuestionIndex < questionList.length - 1) {
            setCurrentQuestionIndex(prevIndex => {
                return prevIndex + 1;
            });
            setUserResponse("");
        }
    }

    const submitAnswer = () => {
        let currentAnswers = questionList[currentQuestionIndex].answerText;
        const isMatch = currentAnswers.some(item => item === userResponse.toUpperCase());
        if (isMatch) {
            setScore(prevScore =>{
                const newScore = prevScore + 1;
                console.log("Score updated in submitAnswer: " + newScore);
                return newScore;
            });
            setAnswerMatches(true);
        } else {
            setAnswerMatches(false);
        }
        getNextQuestion(currentQuestionIndex);
    }

    return (
        <div className="App">
            <Header />
            <p>### Stubbed TakeQuiz. <a href="http://localhost:3000/">Go back.</a> ###</p>
            <div className = "QuestionCard">
                <p>
                    {needsFormatting ? 
                        formatText(questionList[currentQuestionIndex].questionText) :
                        questionList[currentQuestionIndex].questionText
                    }
                </p>
            </div>
            <input type="text" value={userResponse} onChange={(e) => setUserResponse(e.target.value)} />
            <button onClick={submitAnswer}>Submit Answer</button>
            <Footer />
        </div>
    );
};

export default TakeQuiz;