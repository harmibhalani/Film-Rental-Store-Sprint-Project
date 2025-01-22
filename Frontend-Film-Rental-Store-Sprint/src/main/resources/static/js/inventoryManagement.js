const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const storeSelect = document.getElementById('storeSelect');
const inventoryList = document.getElementById('inventoryList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const inventoryTable = document.getElementById('inventoryTable');
const inventoryModal = document.getElementById('inventoryModal');
const inventoryForm = document.getElementById('inventoryForm');
 
// Show/Hide Error Functions
function showError(message) {
    const errorDiv = document.getElementById('errorMessage');
    if (errorDiv) {
        errorDiv.textContent = message;
        errorDiv.classList.remove('hidden');
        setTimeout(() => {
            errorDiv.classList.add('hidden');
        }, 5000);
    }
}
function hideError() {
    errorMessage.classList.add('hidden');
}
 
// Success Message Function
function showSuccess(message) {
    const successDiv = document.createElement('div');
    successDiv.className = 'success-message';
    successDiv.textContent = message;
    document.body.appendChild(successDiv);
    
    setTimeout(() => {
        successDiv.remove();
    }, 3000);
}
 
// Load Stores Function
async function loadStores() {
    try {
        const response = await fetch('/homePage/dashboard/inventoryManagement/stores');
        if (!response.ok) throw new Error('Failed to fetch stores');
        
        const stores = await response.json();
        stores.forEach(store => {
            const option = document.createElement('option');
            option.value = store.storeId;
            option.textContent = `Store ${store.storeId}`;
            storeSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading stores:', error);
        showError('Failed to load stores');
    }
}
 
// Search Inventory Function
async function searchInventory(event) {
    event.preventDefault();
 
    const title = searchInput.value.trim();
    const storeId = storeSelect.value;
 
    hideError();
    loadingState.classList.remove('hidden');
    inventoryTable.classList.add('hidden');
 
 
	    try {
	        let url = '/homePage/dashboard/inventoryManagement/films';
	        
	        if (title && storeId) {
	            // If both title and store are provided
	            url = `/homePage/dashboard/inventoryManagement/film/${encodeURIComponent(title)}/store/${storeId}`;
	        } else if (title) {
	            // If only title is provided
	            url = `/homePage/dashboard/inventoryManagement/films/${encodeURIComponent(title)}`;
	        } else if (storeId) {
	            // If only store is provided
	            url = `/homePage/dashboard/inventoryManagement/store/${storeId}/films`;
	        }
 
	        const response = await fetch(url);
	        if (!response.ok) throw new Error('Failed to fetch inventory');
 
	        const inventories = await response.json();
	        displayInventory(inventories);
	        updateStats(inventories);
	        
	    } catch (error) {
	        console.error('Error:', error);
	        showError('Error fetching inventory. Please try again.');
	    } finally {
	        loadingState.classList.add('hidden');
	        inventoryTable.classList.remove('hidden');
	    }
	}
 
// Display Inventory Function
function displayInventory(inventories) {
    if (!inventories || inventories.length === 0) {
        inventoryList.innerHTML = `
            <tr>
                <td colspan="6" class="text-center py-4">No inventory items found</td>
            </tr>`;
        return;
    }
 
    inventoryList.innerHTML = inventories.map(inventory => `
        <tr id="inventory-row-${inventory.inventoryId}">
            <td>${escapeHtml(inventory.inventoryId)}</td>
            <td>${escapeHtml(inventory.filmTitle)}</td>
            <td>${escapeHtml(inventory.storeId)}</td>
            <td>
                <span class="status-tag ${inventory.isRented ? 'status-rented' : 'status-available'}">
                    ${inventory.isRented ? 'Rented' : 'Available'}
                </span>
            </td>
            <td>${formatDate(inventory.lastUpdate)}</td>
           
        </tr>
    `).join('');
}
 
// Update Stats Function
function updateStats(inventories) {
    const totalInventory = inventories.length;
    const availableItems = inventories.filter(i => !i.isRented).length;
    const rentedItems = inventories.filter(i => i.isRented).length;
    const uniqueStores = new Set(inventories.map(i => i.storeId)).size;
 
    document.getElementById('totalInventory').textContent = totalInventory;
    document.getElementById('availableItems').textContent = availableItems;
    document.getElementById('rentedItems').textContent = rentedItems;
    document.getElementById('storeCount').textContent = uniqueStores;
}
 
function clearForm() {
    if (inventoryForm) {
        inventoryForm.reset();
        document.getElementById('inventoryId').value = '';
        document.getElementById('filmId').value = '';
        document.getElementById('storeId').value = '';
    }
}
 
// Modal Functions
function openEditModal(inventoryId = null) {
    const modalTitle = document.querySelector('#inventoryModal h2');
    modalTitle.textContent = inventoryId ? 'Edit Inventory' : 'Add New Inventory';
    
    if (inventoryId) {
        // Fetch inventory details and populate form
        fetchInventoryDetails(inventoryId);
    } 		else {
		        // Clear form for new inventory
		        clearForm();
		    }
    
    inventoryModal.classList.remove('hidden');
}
 
function closeModal() {
    inventoryModal.classList.add('hidden');
    inventoryForm.reset();
}
 
// Fetch Inventory Details Function
async function fetchInventoryDetails(inventoryId) {
    try {
        const response = await fetch(`/homePage/dashboard/inventoryManagement/inventory/${inventoryId}`);
        if (!response.ok) throw new Error('Failed to fetch inventory details');
 
        const inventory = await response.json();
        document.getElementById('inventoryId').value = inventory.inventoryId;
        document.getElementById('filmId').value = inventory.filmId;
        document.getElementById('storeId').value = inventory.storeId;
 
    } catch (error) {
        console.error('Error:', error);
        showError('Failed to fetch inventory details');
        closeModal();
    }
}	async function saveInventory(event) {
	    event.preventDefault();
 
	    const filmTitle = document.getElementById('filmTitle').value;
	    const filmId = document.getElementById('filmId').value;
	    const storeId = document.getElementById('storeId').value;
 
	    const inventoryData = {
	        filmTitle: filmTitle,
	        filmId: parseInt(filmId),
	        storeId: parseInt(storeId),
	        lastUpdate: new Date().toISOString()
	    };
 
	    try {
	        const response = await fetch('/homePage/dashboard/inventoryManagement/add', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify(inventoryData)
	        });
 
	        if (!response.ok) {
	            throw new Error('Failed to save inventory');
	        }
 
	        const savedInventory = await response.json();
	        showSuccess('Inventory added successfully!');
	        closeModal();
	        
	        // Refresh the inventory list
	        searchForm.dispatchEvent(new Event('submit'));
	    } catch (error) {
	        console.error('Error:', error);
	        showError('Failed to save inventory: ' + error.message);
	    }
	}
 
 
	function openModal() {
	    const modal = document.getElementById('inventoryModal');
	    modal.classList.remove('hidden');
	    modal.classList.add('show');
	}
 
	function closeModal() {
	    const modal = document.getElementById('inventoryModal');
	    modal.classList.remove('show');
	    modal.classList.add('hidden');
	    document.getElementById('inventoryForm').reset();
	}
	
// Utility Functions
function formatDate(dateString) {
    return new Date(dateString).toLocaleString();
}
 
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
    successDiv.className = 'success-message';
    successDiv.textContent = message;
    document.body.appendChild(successDiv);
 
    setTimeout(() => {
        successDiv.remove();
    }, 3000);
}
 
 
// Event Listenersdocument.addEventListener('DOMContentLoaded', () => {
	document.addEventListener('DOMContentLoaded', () => {
		
		const addInventoryBtn = document.querySelector('.add-inventory-btn');
		   const inventoryForm = document.getElementById('inventoryForm');
		   const modal = document.getElementById('inventoryModal');
		   
		   addInventoryBtn.addEventListener('click', openModal);
 
		    // Close modal when clicking outside
		    modal.addEventListener('click', (e) => {
		        if (e.target === modal) {
		            closeModal();
		        }
		    });
 
			inventoryForm.addEventListener('submit', saveInventory);
	    // Load initial data
	    try {
	        loadStores();
	        
	        // Form submit handlers
	        if (searchForm) {
	            searchForm.addEventListener('submit', searchInventory);
	        }
	        if (inventoryForm) {
	            inventoryForm.addEventListener('submit', saveInventory);
	        }
	        
	        // Modal close handlers
	        if (inventoryModal) {
	            inventoryModal.addEventListener('click', (e) => {
	                if (e.target === inventoryModal) {
	                    closeModal();
	                }
	            });
	        }
	        
	        // Add inventory button handler
	        const addInventoryBtn = document.querySelector('.add-inventory-btn');
	        if (addInventoryBtn) {
	            addInventoryBtn.addEventListener('click', () => openEditModal());
	        }
	    } catch (error) {
	        console.error('Initialization error:', error);
	        showError('Failed to initialize the page. Please refresh.');
	    }
	});