const questionUrl = process.env.REACT_APP_QUESTION_URL;

export const getQuestionsByCategory = async (category) => {
    const response = await fetch(questionUrl + "/getQuestionsByCategory/" + category);
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
}