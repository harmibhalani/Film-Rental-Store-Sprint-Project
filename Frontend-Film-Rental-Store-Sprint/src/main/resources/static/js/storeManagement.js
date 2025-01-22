// DOM Elements
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const storeList = document.getElementById('storeList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const storeTable = document.getElementById('storeTable');
 
// Show Error Function
function showError(message) {
    errorMessage.textContent = message;
    errorMessage.classList.remove('hidden');
}
 
// Hide Error Function
function hideError() {
    errorMessage.classList.add('hidden');
}
 
// Search Store Function
async function searchStore(event) {
    event.preventDefault();
 
    const searchTerm = searchInput.value.trim();
    if (!searchTerm) {
        showError('Please enter a search term');
        return;
    }
 
    hideError();
    loadingState.classList.remove('hidden');
    storeList.innerHTML = '';
 
    try {
        const response = await fetch(`/homePage/dashboard/storeManagement/search-city?city=${encodeURIComponent(searchTerm)}`);
 
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
 
        const store = await response.json();
        displayStore(store);
 
    } catch (error) {
        console.error('Error:', error);
        showError('Error fetching store. Please try again.');
        storeList.innerHTML = `
            <tr>
                <td colspan="3" class="text-center text-red-500">Failed to load store. Please try again.</td>
            </tr>
        `;
    } finally {
        loadingState.classList.add('hidden');
        storeTable.classList.remove('hidden');
    }
}
 
// Display Stores Function
function displayStore(store) {
    if (!store || store.length === 0) {
        storeList.innerHTML = `
            <tr>
                <td colspan="3" class="text-center py-4">No store found</td>
            </tr>`;
        return;
    }
 
    storeList.innerHTML = store.map(store => `
        <tr id="store-row-${store.storeId}">
            <td class="store-id">${escapeHtml(store.storeId)}</td>
            <td class="store-first-name">${escapeHtml(store.managerName)}</td>
            <td class="store-address">${escapeHtml(store.address)}</td>
			<td class="store-city">${escapeHtml(store.city)}</td>
			<td class="store-country">${escapeHtml(store.country)}</td>
			<td class="store-phone">${escapeHtml(store.phone)}</td>
			
            <td>
                <button
                    onclick="openEditModal('${store.storeId}', '${escapeHtml(store.phone)}')"
                    class="edit-btn bg-blue-500 text-white px-3 py-1 rounded">
                    Edit
                </button>
            </td>
        </tr>
    `).join('');
}
 
// Open Edit Modal Function
function openEditModal(storeId, currentPhone) {
    const modal = document.getElementById('editModal');
    const PhoneInput = document.getElementById('editPhone');
    const storeIdInput = document.getElementById('editStoreId');
 
    if (!modal || !PhoneInput || !storeIdInput) {
        console.error('Required modal elements not found');
        return;
    }
 
    storeIdInput.value = storeId;
    PhoneInput.value = currentPhone;
    modal.classList.remove('hidden');
}
 
// Close Modal Function
function closeModal() {
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.classList.add('hidden');
    }
}
 
// Update Store Function
async function updateStore(event) {
    event.preventDefault();
 
    const storeId = document.getElementById('editStoreId').value;
    const newPhone = document.getElementById('editPhone').value;
 
    if (!newPhone.trim()) {
        showError('phone number cannot be empty');
        return;
    }
 
    try {
        const response = await fetch(`/homePage/dashboard/storeManagement/update/${storeId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                phone: newPhone,
            })
        });
 
        if (!response.ok) {
            throw new Error('Update failed');
        }
 
        const updatedStore = await response.json();
 
        // Update the table row
        const row = document.getElementById(`store-row-${storeId}`);
        if (row) {
            row.querySelector('.store-phone').textContent = updatedStore.phone;
        }
 
        // Close modal and show success message
        closeModal();
        showSuccessMessage('Store updated successfully!');
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to update ');
    }
}
 
// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    searchForm?.addEventListener('submit', searchStore);
 
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
        updateForm.addEventListener('submit', updateStore);
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
    }, 1000);
}
 