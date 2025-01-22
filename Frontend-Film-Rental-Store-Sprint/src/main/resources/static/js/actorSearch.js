// actorSearch.js
document.addEventListener('DOMContentLoaded', function() {
    const searchForm = document.querySelector('.search-form');
    const searchInput = document.querySelector('.search-input');
    const searchType = document.querySelector('.search-type');
 
    // Update placeholder text based on search type
    function updatePlaceholder() {
        switch(searchType.value) {
            case 'firstName':
                searchInput.placeholder = 'Enter first name...';
                break;
            case 'lastName':
                searchInput.placeholder = 'Enter last name...';
                break;
            case 'id':
                searchInput.placeholder = 'Enter actor ID...';
                break;
        }
    }
 
    // Initial placeholder setup
    updatePlaceholder();
 
    // Update placeholder when search type changes
    searchType.addEventListener('change', updatePlaceholder);
 
    // Form validation
    searchForm.addEventListener('submit', function(e) {
        const searchTerm = searchInput.value.trim();
        
        if (!searchTerm) {
            e.preventDefault();
            alert('Please enter a search term');
            return;
        }
 
        if (searchType.value === 'id') {
            if (!/^\d+$/.test(searchTerm)) {
                e.preventDefault();
                alert('Please enter a valid numeric ID');
                return;
            }
        }
    });
 
    // Add loading state
    searchForm.addEventListener('submit', function() {
        const button = this.querySelector('.search-button');
        button.disabled = true;
        button.textContent = 'Searching...';
    });
});