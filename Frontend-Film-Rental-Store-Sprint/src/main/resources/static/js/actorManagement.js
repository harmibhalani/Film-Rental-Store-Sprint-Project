// DOM Elements
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const searchType = document.getElementById('searchType');
const actorList = document.getElementById('actorList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const actorTable = document.getElementById('actorTable');

// Constants
const BACKEND_URL = 'http://localhost:4311';
const FRONTEND_URL = 'http://localhost:4322';

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

// Search Actors Function
async function searchActors(event) {
	event.preventDefault();

	const searchTerm = searchInput.value.trim();
	const currentSearchType = searchType.value;

	if (!searchTerm) {
		showError('Please enter a search term');
		return;
	}

	hideError();
	loadingState.classList.remove('hidden');
	actorList.innerHTML = '';

	try {
		// Try this alternate format
		const response = await fetch(`/homePage/dashboard/actorManagement/search-actor?searchType=${currentSearchType}&searchTerm=${encodeURIComponent(searchTerm)}`);

		if (!response.ok) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		const actors = await response.json();
		console.log('Received actors:', actors);
		displayActors(actors, currentSearchType);

	} catch (error) {
		console.error('Error:', error);
		showError('Error fetching actors. Please try again.');
		actorList.innerHTML = `
            <tr>
                <td colspan="4" class="text-center text-red-500">
                    Failed to load actors. Please try again.
                </td>
            </tr>
        `;
	} finally {
		loadingState.classList.add('hidden');
		actorTable.classList.remove('hidden');
	}
}

// Display Actors Function
function displayActors(actors, searchType) {
	if (!actors || actors.length === 0) {
		actorList.innerHTML = `
            <tr>
                <td colspan="4" class="text-center py-4">No actors found</td>
            </tr>`;
		return;
	}

	actorList.innerHTML = actors.map(actor => `
        <tr id="actor-row-${actor.actorId}">
            <td class="actor-id">${escapeHtml(actor.actorId)}</td>
            <td class="actor-first-name">${escapeHtml(actor.firstName)}</td>
            <td class="actor-last-name">${escapeHtml(actor.lastName)}</td>
            <td>
                ${searchType === 'firstname' ? `
                    <button
                        onclick="openEditModal('${actor.actorId}', '${escapeHtml(actor.firstName)}', 'firstname')"
                        class="edit-btn">
                        Edit First Name
                    </button>` : ''}
                ${searchType === 'lastname' ? `
                    <button
                        onclick="openEditModal('${actor.actorId}', '${escapeHtml(actor.lastName)}', 'lastname')"
                        class="edit-btn">
                        Edit Last Name
                    </button>` : ''}
            </td>
        </tr>
    `).join('');
}

// Update placeholder based on search type
function updatePlaceholder() {
	switch (searchType.value) {
		case 'firstname':
			searchInput.placeholder = 'Enter first name...';
			break;
		case 'lastname':
			searchInput.placeholder = 'Enter last name...';
			break;
	}
}

// Open Edit Modal Function
function openEditModal(actorId, currentValue, fieldType) {
	const modal = document.getElementById('editModal');
	const firstNameContainer = document.getElementById('editFirstNameContainer');
	const lastNameContainer = document.getElementById('editLastNameContainer');
	const firstNameInput = document.getElementById('editFirstName');
	const lastNameInput = document.getElementById('editLastName');
	const actorIdInput = document.getElementById('editActorId');

	if (!modal || !actorIdInput) {
		console.error('Required modal elements not found');
		return;
	}

	actorIdInput.value = actorId;

	// Show and populate the correct input field based on the fieldType
	if (fieldType === 'firstname') {
		firstNameInput.value = currentValue;
		firstNameContainer.classList.remove('hidden');
		lastNameContainer.classList.add('hidden');
	} else if (fieldType === 'lastname') {
		lastNameInput.value = currentValue;
		lastNameContainer.classList.remove('hidden');
		firstNameContainer.classList.add('hidden');
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

// Update Actor Function
async function updateActor(event) {
	event.preventDefault();

	const actorId = document.getElementById('editActorId').value;
	const firstNameInput = document.getElementById('editFirstName');
	const lastNameInput = document.getElementById('editLastName');

	const newFirstName = firstNameInput.value.trim();
	const newLastName = lastNameInput.value.trim();

	// Determine which field is being updated
	const isFirstNameUpdate = !document.getElementById('editFirstNameContainer').classList.contains('hidden');
	const isLastNameUpdate = !document.getElementById('editLastNameContainer').classList.contains('hidden');

	if (isFirstNameUpdate && !newFirstName) {
		showError('First name cannot be empty');
		return;
	}

	if (isLastNameUpdate && !newLastName) {
		showError('Last name cannot be empty');
		return;
	}

	try {
		const endpoint = isFirstNameUpdate
			? `/homePage/dashboard/actorManagement/update/firstname/${actorId}`
			: `/homePage/dashboard/actorManagement/update/lastname/${actorId}`;

		const body = isFirstNameUpdate ? { firstName: newFirstName } : { lastName: newLastName };

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

		const updatedActor = await response.json();

		// Update the table row
		const row = document.getElementById(`actor-row-${actorId}`);
		if (row) {
			if (isFirstNameUpdate) {
				row.querySelector('.actor-first-name').textContent = updatedActor.firstName;
			} else if (isLastNameUpdate) {
				row.querySelector('.actor-last-name').textContent = updatedActor.lastName;
			}
		}

		// Close modal and show success message
		closeModal();
		showSuccessMessage('Actor updated successfully!');
	} catch (error) {
		console.error('Error:', error);
		showError('Failed to update actor');
	}
}

// Show Success Message Function
function showSuccessMessage(message) {
	const successDiv = document.createElement('div');
	successDiv.className = 'success-message';
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
	if (searchForm) {
		searchForm.addEventListener('submit', searchActors);
	}

	// Initialize search type change handler
	if (searchType) {
		searchType.addEventListener('change', updatePlaceholder);
	}

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

	// Modal Handling
	const createActorButton = document.getElementById('createActorButton');
	const createActorModal = document.getElementById('createActorModal');

	document.getElementById('createActorButton').addEventListener('click', function() {
	    document.getElementById('createActorModal').classList.remove('hidden');
	});

	function closeActorModal() {
		createActorModal.classList.add('hidden');
	}

	// Handle Form Submission
	document.getElementById('createActorForm').addEventListener('submit', async (event) => {
		event.preventDefault();
		const firstName = document.getElementById('createFirstName').value.trim();
		const lastName = document.getElementById('createLastName').value.trim();

		if (!firstName || !lastName) {
			showError('Both fields are required.');
			return;
		}

		try {
			const response = await fetch('/homePage/dashboard/actorManagement/post', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ firstName, lastName })
			});

			if (!response.ok) throw new Error('Failed to create actor');

			closeActorModal();
			searchActors(new Event('submit'));
			document.getElementById('createActorForm').reset();
			showSuccessMessage('Actor created successfully!');
		} catch (error) {
			showError('Failed to create actor.');
		}
	});

	// Initialize update form
	const updateForm = document.getElementById('updateForm');
	if (updateForm) {
		updateForm.addEventListener('submit', updateActor);
	}
});