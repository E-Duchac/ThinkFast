import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';

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
    })
    
    const handleToggle = ({target}) =>
        setCategories(s => ({ ...s, [target.name]: !s[target.name]}));

    const composeQuiz = () => {
        alert("Form submitted! You clicked: " +
            "\n\tMath: " + categories.MATH +
            "\n\tScience: " + categories.SCIENCE +
            "\n\tLanguage Arts: " + categories.LANGUAGE_ARTS +
            "\n\tSocial Studies: " + categories.SOCIAL_STUDIES +
            "\n\tArts & Humanities: " + categories.ARTS_HUMANITIES
        );
        navigate('/takeQuiz');
    }

    return (
        <div className='App'>
            <Header />
            <p>### Stubbed SetupQuiz. <a href="http://localhost:3000/">Go back.</a> ###</p>

            {userType === 'student' ? 
                (<div>
                    <h3>Custom Quiz Setup -- Academic Team Practice</h3>
                    <form action={composeQuiz}>
                        <p>Choose your Subject(s): </p>
                        {Object.keys(categories).map(key => (
                            <label key={key} id="category-checkbox">
                                <input 
                                type="checkbox"
                                onChange={handleToggle}
                                name={key}
                                checked={categories[key]}/>
                            {key}
                            </label>
                        ))}
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