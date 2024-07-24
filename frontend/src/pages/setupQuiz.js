import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import { getQuestionsByCategory } from '../apis/QuestionAPI';

const SetupQuiz = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const userType = location.state?.userType;
    const [categories, setCategories] = useState({
        MATH: false,
        SCIENCE: false,
        LANGUAGE_ARTS: false,
        SOCIAL_STUDIES: false,
        ARTS_HUMANITIES: false,
    });
    const [quizLength, setQuizLength] = useState(20);
    
    const handleToggle = ({target}) =>
        setCategories(s => ({ ...s, [target.name]: !s[target.name]}));

    const composeQuiz = async (event) => {
        event.preventDefault();
        const categoriesArray = Object.entries(categories);
        const fetchPromises = categoriesArray.map(async ([categoryName, isSelected]) => {
            if (isSelected) {
                try {
                    const response = await getQuestionsByCategory(categoryName);
                    console.log("Questions for ${categoryName}: ", response);
                    return response;
                } catch (error) {
                    console.error("Error fetching questions for ${categoryName}: ", error);
                    throw error;
                }
            }
        });
        try {
            const results = await Promise.all(fetchPromises);
            console.log("All questions fetched: ", results);
        } catch (error) {
            console.error("Error fetching one or more categories: ", error);
        }
        //navigate('/takeQuiz');
    }

    return (
        <div className='App'>
            <Header />
            <p>### Stubbed SetupQuiz. <a href="http://localhost:3000/">Go back.</a> ###</p>

            {userType === 'student' ? 
                (<div>
                    <h3>Custom Quiz Setup -- Academic Team Practice</h3>
                    <form className="custom-quiz-form" /*action={composeQuiz}*/>
                        

                        <div className="quiz-categories">
                            <p>Choose your Subject(s): </p>
                            {Object.keys(categories).map(key => (
                                <label key={key}>
                                    <input 
                                    type="checkbox"
                                    onChange={handleToggle}
                                    name={key}
                                    checked={categories[key]}/>
                                {key}
                                </label>
                            ))}
                        </div>

                        <div className="quiz-length">
                            <p>Length of Quiz: </p>
                            <select
                                value={quizLength}
                                onChange={e => setQuizLength(e.target.value)}>
                                <option value={20}>20</option>
                                <option value={50}>50</option>
                                <option value={100}>100</option>
                            </select>
                        </div>

                        <button onClick={composeQuiz}>Compose Quiz!</button>
                    </form>
                </div>) 
                : 
                (<div>
                    <h3>Custom Quiz Setup -- Adults' Trivia</h3>
                    <form>
                        <p>Choose your Subject(s): </p>
                        <button onClick={composeQuiz}>Compose Quiz!</button>
                    </form>
                </div>)}
            <Footer />
        </div>
    );
};

export default SetupQuiz;