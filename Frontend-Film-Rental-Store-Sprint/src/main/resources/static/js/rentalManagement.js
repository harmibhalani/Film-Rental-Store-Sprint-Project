const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const rentalList = document.getElementById('rentalList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const rentalTable = document.getElementById('rentalTable');
const rentalModal = document.getElementById('rentalModal');
const rentalForm = document.getElementById('rentalForm');
 
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
 
// Modal Functions
function openModal() {
    rentalModal.classList.remove('hidden');
}
 
function closeModal() {
    rentalModal.classList.add('hidden');
    rentalForm.reset();
    hideError();
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
    hideError();
 
    const rentalData = {
		rentalId: document.getElementById('rentalId').value,
		       rentalDate: document.getElementById('rentalDate').value,
		       returnDate: document.getElementById('returnDate').value || null, // Handle empty return date
		       inventoryId: document.getElementById('inventoryId').value,
		       staffId: document.getElementById('staffId').value,
		       customerId: document.getElementById('customerId').value
		
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
            throw new Error(`HTTP error! status: ${response.status}`);
        }
 
        const newRental = await response.json();
        showSuccessMessage('Rental added successfully!');
        closeModal();
        
        // Refresh the rental list if it's visible
        if (!rentalTable.classList.contains('hidden')) {
            searchForm.dispatchEvent(new Event('submit'));
        }
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to add rental. Please try again.');
    }
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
    // Form submissions
    searchForm?.addEventListener('submit', searchRentals);
    rentalForm?.addEventListener('submit', addNewRental);
    
    // Modal triggers
    document.querySelector('.add-rental-btn')?.addEventListener('click', openModal);
    
    // Close modal when clicking outside
    window.addEventListener('click', (event) => {
        if (event.target === rentalModal) {
            closeModal();
        }
    });
});