// DOM Elements
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const searchType = document.getElementById('searchType');
const filmList = document.getElementById('filmList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const filmTable = document.getElementById('filmTable');
 
// Constants
const BACKEND_URL = 'http://localhost:4311';
const FRONTEND_URL = 'http://localhost:4322';
 
// Show Error Function
function showError(message) {
    errorMessage.textContent = message;
    errorMessage.classList.remove('hidden');
}
 
// Hide Error Function
function hideError() {
    errorMessage.classList.add('hidden');
}
 
// Search Films Function
async function searchFilms(event) {
    event.preventDefault();
    
    const searchTerm = searchInput.value.trim();
    const currentSearchType = searchType.value;
 
    if (!searchTerm) {
        showError('Please enter a search term');
        return;
    }
 
    // Validate year format if searching by year
    if (currentSearchType === 'year' && !/^\d{4}$/.test(searchTerm)) {
        showError('Please enter a valid 4-digit year');
        return;
    }
 
    hideError();
    loadingState.classList.remove('hidden');
    filmList.innerHTML = '';
 
    try {
        const response = await fetch(`/homePage/dashboard/filmManagement/search-film?searchType=${currentSearchType}&searchTerm=${encodeURIComponent(searchTerm)}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const films = await response.json();
        console.log('Received films:', films);
        displayFilms(films, currentSearchType);
 
    } catch (error) {
        console.error('Error:', error);
        showError('Error fetching films. Please try again.');
        filmList.innerHTML = `
            <tr>
                <td colspan="9" class="text-center text-red-500">
                    Failed to load films. Please try again.
                </td>
            </tr>
        `;
    } finally {
        loadingState.classList.add('hidden');
        filmTable.classList.remove('hidden');
    }
}
 
// Display Films Function
function displayFilms(films, searchType) {
    if (!films || films.length === 0) {
        filmList.innerHTML = `
            <tr>
                <td colspan="9" class="text-center py-4">No films found</td>
            </tr>`;
        return;
    }
 
    filmList.innerHTML = films.map(film => `
        <tr id="film-row-${film.filmId}">
            <td class="film-id">${escapeHtml(film.filmId)}</td>
            <td class="film-title">${escapeHtml(film.title)}</td>
            <td class="film-description">${escapeHtml(film.description)}</td>
            <td class="film-release-year">${escapeHtml(film.releaseYear)}</td>
            <td class="film-rental-rate">${escapeHtml(film.rentalRate)}</td>
            <td class="film-rating">${escapeHtml(film.rating)}</td>
            <td class="film-rental-duration">${escapeHtml(film.rentalDuration)}</td>
            <td class="film-length">${escapeHtml(film.length)}</td>
			<td>
			               ${searchType === 'title' ? `
			                   <button
			                       onclick="openEditModal('${film.filmId}', '${escapeHtml(film.title)}', 'title')"
			                       class="edit-btn bg-blue-500 text-white px-3 py-1 rounded">
			                       Edit Title
			                   </button>` : ''}
			               ${searchType === 'year' ? `
			                   <button
			                       onclick="openEditModal('${film.filmId}', '${escapeHtml(film.releaseYear)}', 'releaseYear')"
			                       class="edit-btn bg-green-500 text-white px-3 py-1 rounded">
			                       Edit Release Year
			                   </button>` : ''}
			           </td>
        </tr>
    `).join('');
}
 
 
 
 
// Update placeholder based on search type
function updatePlaceholder() {
    switch(searchType.value) {
        case 'title':
            searchInput.placeholder = 'Enter film title...';
            break;
        case 'year':
            searchInput.placeholder = 'Enter release year...';
            break;
    }
}
 
// Open Edit Modal Function
function openEditModal(filmId, currentValue, fieldType) {
    const modal = document.getElementById('editModal');
    const titleInput = document.getElementById('editTitle');
    const releaseYearInput = document.getElementById('editReleaseYear');
    const filmIdInput = document.getElementById('editFilmId');
    
    if (!modal || !filmIdInput) {
        console.error('Required modal elements not found');
        return;
    }
 
    filmIdInput.value = filmId;
 
    // Show and populate the correct input field based on the fieldType
    if (fieldType === 'title') {
        titleInput.value = currentValue;
        titleInput.parentElement.classList.remove('hidden');
        releaseYearInput.parentElement.classList.add('hidden');
    } else if (fieldType === 'releaseYear') {
        releaseYearInput.value = currentValue;
        releaseYearInput.parentElement.classList.remove('hidden');
        titleInput.parentElement.classList.add('hidden');
    }
 
    modal.classList.remove('hidden');
}
 
 
 
// Close Modal Function
function closeModal() {
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.classList.add('hidden');
    }
}
 
// Update Film Function
async function updateFilm(event) {
    event.preventDefault();
 
    const filmId = document.getElementById('editFilmId').value;
    const titleInput = document.getElementById('editTitle');
    const releaseYearInput = document.getElementById('editReleaseYear');
 
    const newTitle = titleInput.value.trim();
    const newReleaseYear = releaseYearInput.value.trim();
 
    // Determine which field is being updated
    const isTitleUpdate = !titleInput.parentElement.classList.contains('hidden');
    const isReleaseYearUpdate = !releaseYearInput.parentElement.classList.contains('hidden');
 
    if (isTitleUpdate && !newTitle) {
        showError('Title cannot be empty');
        return;
    }
 
    if (isReleaseYearUpdate && (!/^\d{4}$/.test(newReleaseYear) || !newReleaseYear)) {
        showError('Please enter a valid 4-digit year');
        return;
    }
 
    try {
        const endpoint = isTitleUpdate
            ? `/homePage/dashboard/filmManagement/update/title/${filmId}`
            : `/homePage/dashboard/filmManagement/update/releaseyear/${filmId}`;
 
        const body = isTitleUpdate ? { title: newTitle } : { releaseYear: newReleaseYear };
 
        const response = await fetch(endpoint, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body),
        });
 
        if (!response.ok) {
            throw new Error('Update failed');
        }
 
        const updatedFilm = await response.json();
 
        // Update the table row
        const row = document.getElementById(`film-row-${filmId}`);
        if (row) {
            if (isTitleUpdate) {
                row.querySelector('.film-title').textContent = updatedFilm.title;
            } else if (isReleaseYearUpdate) {
                row.querySelector('.film-release-year').textContent = updatedFilm.releaseYear;
            }
        }
 
        // Close modal and show success message
        closeModal();
        showSuccessMessage('Film updated successfully!');
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to update film');
    }	
}
 
// Show Success Message Function
function showSuccessMessage(message) {
    const successDiv = document.createElement('div');
    successDiv.className = 'fixed top-4 right-4 bg-green-500 text-white px-6 py-3 rounded shadow-lg';
    successDiv.textContent = message;
    document.body.appendChild(successDiv);
    
    setTimeout(() => {
        successDiv.remove();
    }, 3000);
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
    // Initialize search form
    searchForm?.addEventListener('submit', searchFilms);
    
    // Initialize search type change handler
    searchType?.addEventListener('change', updatePlaceholder);
    
    // Set initial placeholder
    updatePlaceholder();
    
    // Close modal when clicking outside
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.addEventListener('click', (e) => {
            if (e.target === modal) {
                closeModal();
            }
        });
    }
 
    // Initialize update form
    const updateForm = document.getElementById('updateForm');
    if (updateForm) {
        updateForm.addEventListener('submit', updateFilm);
    }
});