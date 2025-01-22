// DOM Elements
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const staffList = document.getElementById('staffList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const staffTable = document.getElementById('staffTable');

// Show Error Function
function showError(message) {
    errorMessage.textContent = message;
    errorMessage.classList.remove('hidden');
}

// Hide Error Function
function hideError() {
    errorMessage.classList.add('hidden');
}

// Search Staff Function
async function searchStaff(event) {
    event.preventDefault();

    const searchTerm = searchInput.value.trim();
    if (!searchTerm) {
        showError('Please enter a search term');
        return;
    }

    hideError();
    loadingState.classList.remove('hidden');
    staffList.innerHTML = '';

    try {
        const response = await fetch(`/homePage/dashboard/staffManagement/search-staff?firstName=${encodeURIComponent(searchTerm)}`);

        if (!response.ok) {
			const errorText = await response.text();
            throw new Error(`HTTP error! status: ${response.status} Error: ${errorText}`);
        }

        const staffs = await response.json();
        displayStaff(staffs);

    } catch (error) {
        console.error('Error:', error);
        showError('Error fetching staff. Please try again.');
        staffList.innerHTML = `
            <tr>
                <td colspan="3" class="text-center text-red-500">Failed to load saff. Please try again.</td>
            </tr>
        `;
    } finally {
        loadingState.classList.add('hidden');
        staffTable.classList.remove('hidden');
    }
}

// Display Staff Function
function displayStaff(staffs) {
    if (!staffs || staffs.length === 0) {
        staffList.innerHTML = `
            <tr>
                <td colspan="5" class="text-center py-4">No staff found</td>
            </tr>`;
        return;
    }

    staffList.innerHTML = staffs.map(staff => `
        <tr id="staff-row-${staff.staffId}">
            <td class="staff-id">${escapeHtml(staff.staffId)}</td>
            <td class="staff-first-name">${escapeHtml(staff.firstName)}</td>
            <td class="staff-last-name">${escapeHtml(staff.lastName)}</td>
            <td class="staff-email">${escapeHtml(staff.email)}</td>
            <td class="staff-username">${escapeHtml(staff.username)}</td>
            <td>
                <button 
                    onclick="openEditModal('${staff.staffId}', '${escapeHtml(staff.firstName)}')"
                    class="edit-btn bg-blue-500 text-white px-3 py-1 rounded">
                    Edit
                </button>
            </td>
        </tr>
    `).join('');
}

// Open Edit Modal Function
function openEditModal(staffId, currentFirstName) {
    const modal = document.getElementById('editModal');
    const firstNameInput = document.getElementById('editFirstName');
    const staffIdInput = document.getElementById('editStaffId');

    if (!modal || !firstNameInput || !staffIdInput) {
        console.error('Required modal elements not found');
        return;
    }

    staffIdInput.value = staffId;
    firstNameInput.value = currentFirstName;
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
    const newFirstName = document.getElementById('editFirstName').value.trim();

	if (!newFirstName.trim()) {
	        showError('First name cannot be empty');
	        return;
	    }

    // Get existing staff data from the row
/*    const row = document.getElementById(`staff-row-${staffId}`);
    const staffData = {
        staffId: parseInt(staffId),
        firstName: newFirstName,
        lastName: row.querySelector('.staff-last-name').textContent,
        email: row.querySelector('.staff-email').textContent,
        username: row.querySelector('.staff-username').textContent
    };*/

    try {
        const response = await fetch(`/homePage/dashboard/staffManagement/update/fn/${staffId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(			{
			                firstName: newFirstName,
			            })  
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.log('Error response:', errorText);
            throw new Error(`Update failed. Status: ${response.status}`);
        }

        const updatedStaff = await response.json();

        // Update the table row
		const row = document.getElementById(`staff-row-${staffId}`);
        if (row) {
            row.querySelector('.staff-first-name').textContent = escapeHtml(updatedStaff.firstName);
            row.querySelector('.staff-last-name').textContent = escapeHtml(updatedStaff.lastName);
            row.querySelector('.staff-email').textContent = escapeHtml(updatedStaff.email);
            row.querySelector('.staff-username').textContent = escapeHtml(updatedStaff.username);
        }

        closeModal();
        showSuccessMessage('Staff updated successfully!');

    } catch (error) {
        console.error('Error details:', error);
        showError('Failed to update staff. Please try again.');
    }
}

// Update the event listeners to ensure they're properly attached
document.addEventListener('DOMContentLoaded', () => {
    const updateForm = document.getElementById('updateForm');
    if (updateForm) {
        updateForm.addEventListener('submit', updateStaff);
        console.log('Update form listener attached');  // Debug log
    } else {
        console.error('Update form not found');  // Debug log
    }
});

// Updated close modal function
function closeModal() {
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.classList.add('hidden');
        // Reset the form if it exists
        const updateForm = document.getElementById('updateForm');
        if (updateForm) {
            updateForm.reset();
        }
    }
}

// Updated openEditModal function
function openEditModal(staffId, currentFirstName) {
    const modal = document.getElementById('editModal');
    const firstNameInput = document.getElementById('editFirstName');
    const staffIdInput = document.getElementById('editStaffId');

	if (modal && firstNameInput && staffIdInput) {
	        staffIdInput.value = staffId;
	        firstNameInput.value = currentFirstName;
	        modal.classList.remove('hidden');
	    } else {
	        console.error('Required modal elements not found');
	    }

    staffIdInput.value = staffId;
    firstNameInput.value = currentFirstName;
    modal.classList.remove('hidden');
}

// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    searchForm?.addEventListener('submit', searchStaff);

    // Close modal when clicking outside
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.addEventListener('click', (e) => {
            if (e.target === modal) {
                closeModal();
            }
        });
    }

    // Add form submit handler
    const updateForm = document.getElementById('updateForm');
    if (updateForm) {
        updateForm.addEventListener('submit', updateStaff);
    }
});

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
