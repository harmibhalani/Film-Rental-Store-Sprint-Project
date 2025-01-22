// DOM Elements
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const searchType = document.getElementById('searchType');
const customerList = document.getElementById('customerList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const customerTable = document.getElementById('customerTable');
 
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
 
// Search Customers Function
async function searchCustomers(event) {
	event.preventDefault();
 
	const searchTerm = searchInput.value.trim();
	const currentSearchType = searchType.value;
 
	if (!searchTerm) {
		showError('Please enter a search term');
		return;
	}
 
	hideError();
	loadingState.classList.remove('hidden');
	customerList.innerHTML = '';
 
	try {
		// Try this alternate format
		const response = await fetch(`/homePage/dashboard/customerManagement/search-customer?searchType=${currentSearchType}&searchTerm=${encodeURIComponent(searchTerm)}`);
 
		if (!response.ok) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}
 
		const customers = await response.json();
		console.log('Received customers:', customers);
		displayCustomers(customers, currentSearchType);
 
	} catch (error) {
		console.error('Error:', error);
		showError('Error fetching customers. Please try again.');
		customerList.innerHTML = `
            <tr>
                <td colspan="4" class="text-center text-red-500">
                    Failed to load customers. Please try again.
                </td>
            </tr>
        `;
	} finally {
		loadingState.classList.add('hidden');
		customerTable.classList.remove('hidden');
	}
}
 
// Display Customers Function
function displayCustomers(customers, searchType) {
	if (!customers || customers.length === 0) {
		customerList.innerHTML = `
            <tr>
                <td colspan="4" class="text-center py-4">No customers found</td>
            </tr>`;
		return;
	}
 
	customerList.innerHTML = customers.map(customer => `
        <tr id="customer-row-${customer.customerId}">
            <td class="customer-id">${escapeHtml(customer.customerId)}</td>
            <td class="customer-first-name">${escapeHtml(customer.firstName)}</td>
            <td class="customer-last-name">${escapeHtml(customer.lastName)}</td>
			<td class="customer-email">${escapeHtml(customer.email)}</td>
            <td>
                ${searchType === 'firstname' ? `
                    <button
                        onclick="openEditModal('${customer.customerId}', '${escapeHtml(customer.firstName)}', 'firstname')"
                        class="edit-btn">
                        Edit First Name
                    </button>` : ''}
                ${searchType === 'lastname' ? `
                    <button
                        onclick="openEditModal('${customer.customerId}', '${escapeHtml(customer.lastName)}', 'lastname')"
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
function openEditModal(customerId, currentValue, fieldType) {
	const modal = document.getElementById('editModal');
	const firstNameContainer = document.getElementById('editFirstNameContainer');
	const lastNameContainer = document.getElementById('editLastNameContainer');
	const firstNameInput = document.getElementById('editFirstName');
	const lastNameInput = document.getElementById('editLastName');
	const customerIdInput = document.getElementById('editCustomerId');
 
	if (!modal || !customerIdInput) {
		console.error('Required modal elements not found');
		return;
	}
 
	customerIdInput.value = customerId;
 
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
 
// Update Customer Function
async function updateCustomer(event) {
	event.preventDefault();
 
	const customerId = document.getElementById('editCustomerId').value;
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
			? `/homePage/dashboard/customerManagement/update/fn/${customerId}`
			: `/homePage/dashboard/customerManagement/update/ln/${customerId}`;
 
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
 
		const updatedCustomer = await response.json();
 
		// Update the table row
		const row = document.getElementById(`customer-row-${customerId}`);
		if (row) {
			if (isFirstNameUpdate) {
				row.querySelector('.customer-first-name').textContent = updatedCustomer.firstName;
			} else if (isLastNameUpdate) {
				row.querySelector('.customer-last-name').textContent = updatedCustomer.lastName;
			}
		}
 
		// Close modal and show success message
		closeModal();
		showSuccessMessage('Customer updated successfully!');
	} catch (error) {
		console.error('Error:', error);
		showError('Failed to update customer');
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
		searchForm.addEventListener('submit', searchCustomers);
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
		updateForm.addEventListener('submit', updateCustomer);
	}
});