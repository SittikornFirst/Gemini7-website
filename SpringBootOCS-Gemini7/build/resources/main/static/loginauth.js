document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const errorMessage = document.getElementById('errorMessage');

    const API_URL = 'http://localhost:8080/api/auth/login';

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        errorMessage.textContent = '';

        const loginData = {
            email: email,
            password: password
        };

        const loginButton = document.getElementById('loginButton');
        loginButton.disabled = true;
        loginButton.textContent = 'Logging in...';

        fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(loginData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    sessionStorage.setItem('user', JSON.stringify({
                        userId: data.userId,
                        name: data.name,
                        role: data.role,
                        token: data.token
                    }));

                    window.location.href = '/homepage';
                } else {
                    errorMessage.textContent = data.message || 'Login failed. Please check your credentials.';
                    loginButton.disabled = false;
                    loginButton.textContent = 'Login';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                errorMessage.textContent = 'An error occurred. Please try again later.';
                loginButton.disabled = false;
                loginButton.textContent = 'Login';
            });
    });
});
