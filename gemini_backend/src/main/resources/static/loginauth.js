document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const errorMessage = document.getElementById('errorMessage');

    // API endpoint for login
    const API_URL = 'http://localhost:8080/api/auth/login';

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        // Clear previous error messages
        errorMessage.textContent = '';

        // Create login request payload
        const loginData = {
            email: email,
            password: password
        };

        // Show loading state
        const loginButton = document.getElementById('loginButton');
        loginButton.disabled = true;
        loginButton.textContent = 'Logging in...';

        // Send login request
        fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Save user data to session storage
                    sessionStorage.setItem('user', JSON.stringify({
                        userId: data.userId,
                        name: data.name,
                        role: data.role,
                        token: data.token
                    }));

                    window.location.href = 'landingPage.html'

//                    // Redirect based on user role
//                    const role = data.role;
//                    if (role === 'Astronomer') {
//                        window.location.href = 'la.html';
//                    } else if (role === 'Science Observer') {
//                        window.location.href = 'observer-dashboard.html';
//                    } else if (role === 'System Admin') {
//                        window.location.href = 'admin-dashboard.html';
//                    } else {
//                        window.location.href = 'dashboard.html';
//                    }
                } else {
                    // Show error message
                    errorMessage.textContent = data.message || 'Login failed. Please check your credentials.';
                    // Reset login button
                    loginButton.disabled = false;
                    loginButton.textContent = 'Login';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                errorMessage.textContent = 'An error occurred. Please try again later.';
                // Reset login button
                loginButton.disabled = false;
                loginButton.textContent = 'Login';
            });
    });
});