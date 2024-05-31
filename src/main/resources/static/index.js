
    async function getAllJokes() {
    const response = await fetch('/jokes');
    const jokes = await response.json();
    if (response.ok) {
    const allJokesList = document.getElementById('allJokesList');
    allJokesList.innerHTML = '';
    jokes.forEach(joke => {
    const li = document.createElement('li');
    li.innerHTML = `
                <p class="joke-info"><strong>ID:</strong> ${joke.id}</p>
                <p class="joke-info"><strong>Шутка:</strong> ${joke.joke}</p>
                <p class="joke-info"><strong>Дата создания:</strong> ${joke.timeCreated}</p>
                <p class="joke-info"><strong>Дата обновления:</strong> ${joke.timeUpdated}</p>
            `;
    allJokesList.appendChild(li);
});

}
}
    async function getJokeById() {
    const jokeId = document.getElementById('jokeId').value;
    if (!jokeId) {
    document.getElementById('jokeById').textContent = 'Пожалуйста, введите ID шутки';
    return;
}
    const response = await fetch(`/jokes/${jokeId}`);
    if (response.ok) {
    const joke = await response.json();
    const jokeById = document.getElementById('jokeById');
    jokeById.innerHTML = `
            <strong>ID</strong>: ${joke.id}<br>
            <strong>Шутка:</strong> ${joke.joke}<br>
            <strong>Дата создания:</strong> ${joke.timeCreated},<br>
            <strong>Дата обновления:</strong> dsfsd${joke.timeUpdated}
        `;
} else {
    document.getElementById('jokeById').textContent = 'Шутка не найдена';
}
}

    async function updateJoke() {
    const updateId = document.getElementById('updateId').value;
    const updateText = document.getElementById('updateText').value;
    if (!updateId || !updateText) {
    alert('Пожалуйста, введите ID шутки и новый текст шутки');
    return;
}
    const response = await fetch(`/jokes/${updateId}`, {
    method: 'PUT',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify({ joke: updateText })
});
    if (response.ok) {
    alert('Шутка обновлена успешно');
} else {
    alert('Ошибка удаления шутки');
}
}

    async function deleteJoke() {
    const deleteId = document.getElementById('deleteId').value;
    if (!deleteId) {
    alert('Пожалуйста, введите ID шутки для удаления');
    return;
}
    const response = await fetch(`/jokes/${deleteId}`, {
    method: 'DELETE'
});
    if (response.ok) {
    alert('Шутка удалена успешно');
} else {
    alert('Ошибка удаления шутки');
}
}

    async function addJoke() {
    const newJokeText = document.getElementById('newJokeText').value;
    if (!newJokeText) {
    document.getElementById('addJokeMessage').textContent = 'Пожалуйста, введите шутку для добавления';
    return;
}
    const response = await fetch('/jokes', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify({ joke: newJokeText })
});
    if (response.ok) {
    alert('Шутка добавлена успешно');
    document.getElementById('addJokeMessage').textContent = '';
    getAllJokes();
} else {
    alert('Ошибка добавления шутки');
}
}