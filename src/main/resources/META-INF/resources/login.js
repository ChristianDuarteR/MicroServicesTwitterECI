const switchers = [...document.querySelectorAll('.switcher')];
switchers.forEach(item => {
    item.addEventListener('click', function() {
        switchers.forEach(item => item.parentElement.classList.remove('is-active'));
        this.parentElement.classList.add('is-active');
    });
});

async function getAccessToken(event) {
    event.preventDefault(); // Evita que el formulario recargue la página

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    const url = 'http://localhost:8180/realms/quarkus/protocol/openid-connect/token';
    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa('backend-service:secret') // Convierte credenciales en Base64
    };

    const body = new URLSearchParams({
        username: username,
        password: password,
        grant_type: 'password',
        scope: 'openid'
    });

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: headers,
            body: body
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const data = await response.json();
        console.log('Access Token:', data.access_token);
        return data.access_token;
    } catch (error) {
        console.error('Error fetching access token:', error);
        return null;
    }
}

