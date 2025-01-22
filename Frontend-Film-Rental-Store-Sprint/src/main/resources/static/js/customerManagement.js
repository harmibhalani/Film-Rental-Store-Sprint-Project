// DOM Elements
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const customerList = document.getElementById('customerList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const customerTable = document.getElementById('customerTable');
 
// Show Error Function
function showError(message) {
    errorMessage.textContent = message;
    errorMessage.classList.remove('hidden');
}
 
// Hide Error Function
function hideError() {
    errorMessage.classList.add('hidden');
}
 
// Search Customer Function
async function searchCustomer(event) {
    event.preventDefault();
 
    const searchTerm = searchInput.value.trim();
    if (!searchTerm) {
        showError('Please enter a search term');
        return;
    }
 
    hideError();
    loadingState.classList.remove('hidden');
    customerList.innerHTML = '';
 
    try {
        const response = await fetch(`/homePage/dashboard/customerManagement/search-customer?firstName=${encodeURIComponent(searchTerm)}`);
 
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
 
        const customer = await response.json();
        displayCustomer(customer);
 
    } catch (error) {
        console.error('Error:', error);
        showError('Error fetching customers. Please try again.');
        customerList.innerHTML = `
            <tr>
                <td colspan="3" class="text-center text-red-500">Failed to load customer. Please try again.</td>
            </tr>
        `;
    } finally {
        loadingState.classList.add('hidden');
        customerTable.classList.remove('hidden');
    }
}
 
// Display Customer Function
function displayCustomer(customer) {
    if (!customer || customer.length === 0) {
        customerList.innerHTML = `
            <tr>
                <td colspan="3" class="text-center py-4">No customer found</td>
            </tr>`;
        return;
    }
 
    customerList.innerHTML = customer.map(customer => `
        <tr id="customer-row-${customer.customerId}">
            <td class="customer-id">${escapeHtml(customer.customerId)}</td>
            <td class="customer-first-name">${escapeHtml(customer.firstName)}</td>
            <td class="customer-last-name">${escapeHtml(customer.lastName)}</td>
			<td class="customer-email">${escapeHtml(customer.email)}</td>
            <td>
                <button
                    onclick="openEditModal('${customer.customerId}', '${escapeHtml(customer.firstName)}')"
                    class="edit-btn bg-blue-500 text-white px-3 py-1 rounded">
                    Edit
                </button>
            </td>
        </tr>
    `).join('');
}
 
// Open Edit Modal Function
function openEditModal(customerId, currentFirstName) {
    const modal = document.getElementById('editModal');
    const firstNameInput = document.getElementById('editFirstName');
    const customerIdInput = document.getElementById('editCustomerId');
 
    if (!modal || !firstNameInput || !customerIdInput) {
        console.error('Required modal elements not found');
        return;
    }
 
    customerIdInput.value = customerId;
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
 
// Update Customer Function
async function updateCustomer(event) {
    event.preventDefault();
 
    const customerId = document.getElementById('editCustomerId').value;
    const newFirstName = document.getElementById('editFirstName').value;
 
    if (!newFirstName.trim()) {
        showError('First name cannot be empty');
        return;
    }
 
    try {
        const response = await fetch(`/homePage/dashboard/customerManagement/update/${customerId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                firstName: newFirstName,
            })
        });
 
        if (!response.ok) {
            throw new Error('Update failed');
        }
 
        const updatedCustomer = await response.json();
 
        // Update the table row
        const row = document.getElementById(`customer-row-${customerId}`);
        if (row) {
            row.querySelector('.customer-first-name').textContent = updatedCustomer.firstName;
        }
 
        // Close modal and show success message
        closeModal();
        showSuccessMessage('Customer updated successfully!');
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to update Customer');
    }
}
 
// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    searchForm?.addEventListener('submit', searchCustomer);
 
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
        updateForm.addEventListener('submit', updateCustomer);
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