async function fetchUsers() {
    try {
        const response = await fetch("http://localhost:8080/api/v1/users/permit-all");
        const users = await response.json();
        displayUsers(users);
    } catch (error) {
        console.error("Error al obtener los usuarios:", error);
    }
}

function displayUsers(users) {
    const userContainer = document.getElementById("userContainer");
    userContainer.innerHTML = ""; // Limpiar cualquier contenido previo

    users.forEach(user => {
        // Crear un contenedor para cada usuario
        const userDiv = document.createElement("div");
        userDiv.className = "user";

        userDiv.innerHTML = `
            <h2> ${user.userName}</h2>
            <h3>  A publicado los siguientes hilos </h3>
        `;

        // Agregar los streams del usuario
        user.streams.forEach(stream => {
            const streamDiv = document.createElement("div");
            streamDiv.className = "stream";
            streamDiv.id = `stream-${stream.streamId}`; // Asignar un ID único al stream

            // Agregar los posts del stream
            stream.posts.forEach(post => {
                const postDiv = document.createElement("div");
                postDiv.className = "post";
                postDiv.innerHTML = `
                    <p><strong>Autor: </strong> ${post.author}</p>
                    <p><strong>Contenido:</strong> ${post.content}</p>
                    <p><strong>Fecha:</strong> ${post.createdAt}</p>
                `;
                streamDiv.appendChild(postDiv);
            });

            // Agregar la sección de comentarios
            const commentSection = document.createElement("div");
            commentSection.className = "comment-section";
            commentSection.innerHTML = `
                <input type="text" placeholder="Escribe un comentario...">
                <button onclick="submitComment('${stream.streamId}', '${user.email}')">Comentar</button>
            `;
            streamDiv.appendChild(commentSection);

            userDiv.appendChild(streamDiv);
        });

        userContainer.appendChild(userDiv);
    });
}

// Función para crear un nuevo stream
function createStream() {
    const streamContent = document.getElementById("streamContent").value;
    if (!streamContent) {
        alert("Por favor, escribe algo en el stream.");
        return;
    }

    const postDTO = {
        content: streamContent
    };

    fetch(`http://localhost:8080/api/v1/users/newPost`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(postDTO)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Error al crear el stream.");
            }
        })
        .then(data => {
            console.log("Stream creado:", data);
            document.getElementById("streamContent").value = ""; // Limpiar el campo después de enviar
            fetchUsers(); // Actualizar la lista de usuarios y streams
        })
        .catch(error => {
            console.error("Hubo un problema al crear el stream:", error);
            alert("No se pudo iniciar el stream. Intenta de nuevo.");
        });
}

// Función para manejar el envío del comentario
function submitComment(streamId, emailOwner) {
    const streamContainer = document.getElementById(`stream-${streamId}`);
    const commentSection = streamContainer.getElementsByClassName('comment-section')[0];
    const commentInput = commentSection.getElementsByTagName('input')[0];

    const commentText = commentInput.value;

    if (commentText) {
        const postDTO = {
            content: commentText
        };

        fetch(`http://localhost:8080/api/v1/users/newPostToStream/${emailOwner}/${streamId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(postDTO)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Error al crear el comentario en el stream.");
                }
            })
            .then(data => {
                console.log(`Comentario creado en el stream ${streamId}:`, data);
                commentInput.value = ""; // Limpiar el campo después de enviar
            })
            .catch(error => {
                console.error("Hubo un problema al crear el comentario:", error);
                alert("No se pudo enviar el comentario. Intenta de nuevo.");
            });
    } else {
        alert("Por favor, escribe un comentario.");
    }
}

fetchUsers();
