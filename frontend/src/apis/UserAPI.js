const userUrl = process.env.REACT_APP_USER_URL;

export const login = async (username, password) => {
    const loginRequestBody = {username: username, password: password};
    try {
        const response = await fetch(userUrl + "/login", {
            method: 'POST',
            headers: {'Content-Type' : 'application/json'},
            body: JSON.stringify(loginRequestBody)
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log('Parsed JSON: ', data);
        return data;
    } catch (error) {
        console.error('Error fetching user: ', error);
        throw error;
    }
}