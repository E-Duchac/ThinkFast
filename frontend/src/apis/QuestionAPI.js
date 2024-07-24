const questionUrl = process.env.REACT_APP_QUESTION_URL;

export const getQuestionsByCategory = async (category) => {
    try {
        const response = await fetch(questionUrl + "/getQuestionsByCategory/" + category);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log('Parsed JSON: ', data);
        return data;
    } catch (error) {
        console.error('Error fetching questions: ', error);
        throw error;
    }
    
}