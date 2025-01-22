// DOM Elements
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const searchType = document.getElementById('searchType');
const staffList = document.getElementById('staffList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const staffTable = document.getElementById('staffTable');
 
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
 
// Search Staffs Function
async function searchStaffs(event) {
	event.preventDefault();
 
	const searchTerm = searchInput.value.trim();
	const currentSearchType = searchType.value;
 
	if (!searchTerm) {
		showError('Please enter a search term');
		return;
	}
 
	hideError();
	loadingState.classList.remove('hidden');
	staffList.innerHTML = '';
 
	try {
		// Try this alternate format
		const response = await fetch(`/homePage/dashboard/staffManagement/search-staff?searchType=${currentSearchType}&searchTerm=${encodeURIComponent(searchTerm)}`);
 
		if (!response.ok) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}
 
		const staffs = await response.json();
		console.log('Received staff:', staffs);
		displayStaffs(staffs, currentSearchType);
 
	} catch (error) {
		console.error('Error:', error);
		showError('Error fetching staffs. Please try again.');
		staffList.innerHTML = `
            <tr>
                <td colspan="4" class="text-center text-red-500">
                    Failed to load staffs. Please try again.
                </td>
            </tr>
        `;
	} finally {
		loadingState.classList.add('hidden');
		staffTable.classList.remove('hidden');
	}
}
 
// Display Staffs Function
function displayStaffs(staffs, searchType) {
	if (!staffs || staffs.length === 0) {
		staffList.innerHTML = `
            <tr>
                <td colspan="4" class="text-center py-4">No staffs found</td>
            </tr>`;
		return;
	}
 
	staffList.innerHTML = staffs.map(staff => `
        <tr id="staff-row-${staff.staffId}">
            <td class="staff-id">${escapeHtml(staff.staffId)}</td>
            <td class="staff-first-name">${escapeHtml(staff.firstName)}</td>
            <td class="staff-last-name">${escapeHtml(staff.lastName)}</td>
			<td class="staff-email">${escapeHtml(staff.email)}</td>
			<td class="staff-user-name">${escapeHtml(staff.username)}</td>
            <td>
                ${searchType === 'firstname' ? `
                    <button
                        onclick="openEditModal('${staff.staffId}', '${escapeHtml(staff.firstName)}', 'firstname')"
                        class="edit-btn">
                        Edit First Name
                    </button>` : ''}
                ${searchType === 'lastname' ? `
                    <button
                        onclick="openEditModal('${staff.staffId}', '${escapeHtml(staff.lastName)}', 'lastname')"
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
function openEditModal(staffId, currentValue, fieldType) {
	const modal = document.getElementById('editModal');
	const firstNameContainer = document.getElementById('editFirstNameContainer');
	const lastNameContainer = document.getElementById('editLastNameContainer');
	const firstNameInput = document.getElementById('editFirstName');
	const lastNameInput = document.getElementById('editLastName');
	const staffIdInput = document.getElementById('editStaffId');
 
	if (!modal || !staffIdInput) {
		console.error('Required modal elements not found');
		return;
	}
 
	staffIdInput.value = staffId;
 
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
 
// Update Staff Function
async function updateStaff(event) {
	event.preventDefault();
 
	const staffId = document.getElementById('editStaffId').value;
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
			? `/homePage/dashboard/staffManagement/update/fn/${staffId}`
			: `/homePage/dashboard/staffManagement/update/ln/${staffId}`;
 
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
 
		const updatedStaff = await response.json();
 
		// Update the table row
		const row = document.getElementById(`staff-row-${staffId}`);
		if (row) {
			if (isFirstNameUpdate) {
				row.querySelector('.staff-first-name').textContent = updatedStaff.firstName;
			} else if (isLastNameUpdate) {
				row.querySelector('.staff-last-name').textContent = updatedStaff.lastName;
			}
		}
 
		// Close modal and show success message
		closeModal();
		showSuccessMessage('Staff updated successfully!');
	} catch (error) {
		console.error('Error:', error);
		showError('Failed to update staff');
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
		searchForm.addEventListener('submit', searchStaffs);
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

 
	// Initialize update form
	const updateForm = document.getElementById('updateForm');
	if (updateForm) {
		updateForm.addEventListener('submit', updateStaff);
	}
});