// DOM Elements
const actorCardsContainer = document.getElementById('actorCardsContainer');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const backButton = document.getElementById('backToManagement');
 
// Show Error Function
function showError(message) {
    const errorMessage = document.getElementById('errorMessage');
    const errorText = document.getElementById('errorText');
    if (errorText) {
        errorText.textContent = message;
    }
    errorMessage.classList.remove('hidden');
}
 
// Hide Error Function
function hideError() {
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.classList.add('hidden');
}
 
// Create Actor Card Function
function createActorCard(actor) {
    const card = document.createElement('div');
    card.className = 'actor-card';
    card.style.animationDelay = `${Math.random() * 0.5}s`;
 
    card.innerHTML = `
        <div class="actor-card-header">
            <h3>${escapeHtml(actor.firstName)} ${escapeHtml(actor.lastName)}</h3>
            <span class="actor-id-badge">ID: ${escapeHtml(actor.actorId)}</span>
        </div>
        <div class="actor-card-body">
            <div class="actor-info-item">
                <div class="info-label">First Name</div>
                <div class="info-value">${escapeHtml(actor.firstName)}</div>
            </div>
            <div class="actor-info-item">
                <div class="info-label">Last Name</div>
                <div class="info-value">${escapeHtml(actor.lastName)}</div>
            </div>
        </div>
        <div class="actor-card-footer">
            <div class="card-actions">
                <button onclick="editActor(${actor.actorId})" class="card-btn edit-btn">
                    <i class="fas fa-edit"></i>
                    Films
                </button>
            </div>
        </div>
    `;
 
    return card;
}
 
// Fetch and Display Actors
async function fetchAndDisplayActors() {
    try {
        loadingState.classList.remove('hidden');
        hideError();
 
        const response = await fetch('/homePage/dashboard/actorManagement/all-actors');
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
 
        const actors = await response.json();
        
        // Clear existing cards
        actorCardsContainer.innerHTML = '';
 
        // Add new cards
        actors.forEach(actor => {
            const card = createActorCard(actor);
            actorCardsContainer.appendChild(card);
        });
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to fetch actors. Please try again.');
    } finally {
        loadingState.classList.add('hidden');
    }
}
 
// Edit Actor Function
function editActor(actorId) {
    // Redirect to management page with the actor ID
    window.location.href = `/homePage/dashboard/actorManagement?edit=${actorId}`;
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
    // Fetch actors when page loads
    fetchAndDisplayActors();
 
    // Back button handler
    if (backButton) {
        backButton.addEventListener('click', () => {
            window.location.href = '/homePage/dashboard/actorManagement';
        });
    }
});