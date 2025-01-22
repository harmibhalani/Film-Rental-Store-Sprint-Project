const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const rentalList = document.getElementById('rentalList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const rentalTable = document.getElementById('rentalTable');
const addRentalForm = document.getElementById('addRentalForm');
const returnDateForm = document.getElementById('returnDateForm');
 
// Show Error Function
function showError(message) {
    const errorText = document.getElementById('errorText');
    errorText.textContent = message;
    errorMessage.classList.remove('hidden');
}
 
// Hide Error Function
function hideError() {
    errorMessage.classList.add('hidden');
}
 
// Search Rentals Function
async function searchRentals(event) {
    event.preventDefault();
 
    const customerId = searchInput.value.trim();
    if (!customerId) {
        showError('Please enter a customer ID');
        return;
    }
 
    hideError();
    loadingState.classList.remove('hidden');
    rentalList.innerHTML = '';
 
    try {
        const response = await fetch(`/homePage/dashboard/rentalManagement/customer/${customerId}`);
 
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
 
        const rentals = await response.json();
        displayRentals(rentals);
 
    } catch (error) {
        console.error('Error:', error);
        showError('Error fetching rentals. Please try again.');
        rentalList.innerHTML = `
            <tr>
                <td colspan="6" class="text-center text-red-500">Failed to load rentals. Please try again.</td>
            </tr>
        `;
    } finally {
        loadingState.classList.add('hidden');
        rentalTable.classList.remove('hidden');
    }
}
 
// Display Rentals Function
function displayRentals(rentals) {
    if (!rentals || rentals.length === 0) {
        rentalList.innerHTML = `
            <tr>
                <td colspan="6" class="text-center py-4">No rentals found</td>
            </tr>`;
        return;
    }
 
	rentalList.innerHTML = rentals.map(rental => `
	        <tr id="rental-row-${rental.rentalId}">
	            <td>${escapeHtml(rental.rentalId)}</td>
	            <td>${escapeHtml(rental.customerId)}</td>
	            <td>${formatDate(rental.rentalDate)}</td>
	            <td>${rental.returnDate ? formatDate(rental.returnDate) : 'Not returned'}</td>
	            <td>${getRentalStatus(rental)}</td>
	        </tr>
	    `).join('');
}
 
// Add New Rental Function
async function addNewRental(event) {
    event.preventDefault();
 
    const rentalData = {
        customerId: document.getElementById('customerId').value,
        inventoryId: document.getElementById('inventoryId').value,
        staffId: document.getElementById('staffId').value
    };
 
    try {
        const response = await fetch('/homePage/dashboard/rentalManagement/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(rentalData)
        });
 
        if (!response.ok) {
            throw new Error('Failed to add rental');
        }
 
        const newRental = await response.json();
        closeAddModal();
        showSuccessMessage('Rental added successfully!');
        
        // Refresh the search if there are results displayed
        if (!rentalTable.classList.contains('hidden')) {
            searchForm.dispatchEvent(new Event('submit'));
        }
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to add rental');
    }
}
 
// Update Return Date Function
async function updateReturnDate(event) {
    event.preventDefault();
 
    const rentalId = document.getElementById('returnRentalId').value;
    const returnDate = document.getElementById('returnDate').value;
 
    try {
        const response = await fetch(`/homePage/dashboard/rentalManagement/update/returndate/${rentalId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(returnDate)
        });
 
        if (!response.ok) {
            throw new Error('Failed to update return date');
        }
 
        const updatedRental = await response.json();
        closeReturnModal();
        showSuccessMessage('Return date updated successfully!');
        
        // Update the table row
        const row = document.getElementById(`rental-row-${rentalId}`);
        if (row) {
            const cells = row.getElementsByTagName('td');
            cells[3].textContent = formatDate(updatedRental.returnDate);
            cells[4].textContent = getRentalStatus(updatedRental);
            cells[5].innerHTML = ''; // Remove return button
        }
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to update return date');
    }
}
 
// Modal Functions
function openAddModal() {
    document.getElementById('addRentalModal').classList.remove('hidden');
}
 
function closeAddModal() {
    document.getElementById('addRentalModal').classList.add('hidden');
    addRentalForm.reset();
}
 
function openReturnModal(rentalId) {
    document.getElementById('returnRentalId').value = rentalId;
    document.getElementById('returnDateModal').classList.remove('hidden');
}
 
function closeReturnModal() {
    document.getElementById('returnDateModal').classList.add('hidden');
    returnDateForm.reset();
}
 
// Utility Functions
function escapeHtml(value) {
    if (value === null || value === undefined) return '';
    return String(value)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}
 
function formatDate(dateString) {
    return new Date(dateString).toLocaleString();
}
 
function getRentalStatus(rental) {
    if (!rental.returnDate) {
        const rentalDate = new Date(rental.rentalDate);
        const today = new Date();
        const diffTime = Math.abs(today - rentalDate);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
        
        return diffDays > 7 ?
            '<span class="status-badge overdue">Overdue</span>' :
            '<span class="status-badge active">Active</span>';
    }
    return '<span class="status-badge returned">Returned</span>';
}
 
function showSuccessMessage(message) {
    const successDiv = document.createElement('div');
    successDiv.className = 'success-message';
    successDiv.textContent = message;
    document.body.appendChild(successDiv);
 
    setTimeout(() => {
        successDiv.remove();
    }, 3000);
}
 
// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    searchForm?.addEventListener('submit', searchRentals);
    addRentalForm?.addEventListener('submit', addNewRental);
    returnDateForm?.addEventListener('submit', updateReturnDate);
    
    document.querySelector('.add-rental-btn')?.addEventListener('click', openAddModal);
    
    // Close modals when clicking outside
    window.addEventListener('click', (event) => {
        if (event.target.classList.contains('modal')) {
            event.target.classList.add('hidden');
        }
    });
});