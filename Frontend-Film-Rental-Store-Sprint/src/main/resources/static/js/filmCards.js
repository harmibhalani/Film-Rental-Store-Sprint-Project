// DOM Elements
const filmCardsContainer = document.getElementById('filmCardsContainer');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const backButton = document.getElementById('backToManagement');
 
// Show Error Function
function showError(message) {
    const errorText = document.getElementById('errorText');
    if (errorText) {
        errorText.textContent = message;
    }
    errorMessage.classList.remove('hidden');
}
 
// Hide Error Function
function hideError() {
    errorMessage.classList.add('hidden');
}
 
// Create Film Card Function
function createFilmCard(film) {
    const card = document.createElement('div');
    card.className = 'film-card';
    card.style.animationDelay = `${Math.random() * 0.5}s`;
 
    card.innerHTML = `
        <div class="film-card-header">
            <h3>${escapeHtml(film.title)}</h3>
            <span class="film-id-badge">ID: ${escapeHtml(film.filmId)}</span>
        </div>
        <div class="film-card-body">
            <div class="film-info-item">
                <div class="info-label">Description</div>
                <div class="info-value">${escapeHtml(film.description)}</div>
            </div>
            <div class="film-info-item">
                <div class="info-label">Release Year</div>
                <div class="info-value">${escapeHtml(film.releaseYear)}</div>
            </div>
            <div class="film-info-item">
                <div class="info-label">Rental Rate</div>
                <div class="info-value">${escapeHtml(film.rentalRate)}</div>
            </div>
            <div class="film-info-item">
                <div class="info-label">Rating</div>
                <div class="info-value">${escapeHtml(film.rating)}</div>
            </div>
        </div>
    `;
 
    return card;
}
 
// Fetch and Display Films
async function fetchAndDisplayFilms() {
    try {
        loadingState.classList.remove('hidden');
        hideError();
 
        const response = await fetch('/homePage/dashboard/filmManagement/all-films');
 
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
 
        const films = await response.json();
 
        // Clear existing cards
        filmCardsContainer.innerHTML = '';
 
        // Add new cards
        films.forEach(film => {
            const card = createFilmCard(film);
            filmCardsContainer.appendChild(card);
        });
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to fetch films. Please try again.');
    } finally {
        loadingState.classList.add('hidden');
    }
}
 
// Utility function to escape HTML
function escapeHtml(value) {
    if (value === null || value === undefined) return '';
    return String(value)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}
 
// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    // Fetch films when page loads
    fetchAndDisplayFilms();
 
    // Back button handler
    if (backButton) {
        backButton.addEventListener('click', () => {
            window.location.href = '/homePage/dashboard/filmManagement';
        });
    }
});
 