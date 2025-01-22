// DOM Elements
const loadPaymentBtn = document.getElementById('loadPaymentBtn');
const paymentList = document.getElementById('paymentList');
const loadingState = document.getElementById('loadingState');
const errorMessage = document.getElementById('errorMessage');
const paymentTable = document.getElementById('paymentTable');
 
// Format currency
function formatCurrency(amount) {
    return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD'
    }).format(amount);
}
 
// Format date
function formatDate(dateString) {
    return new Date(dateString).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    });
}
 
// Show Error Function
function showError(message) {
    const errorText = document.getElementById('errorText');
    if (errorText) {
        errorText.textContent = message;
    }
    errorMessage.classList.remove('hidden');
}
 
// Hide Error Function
function hideError() {
    errorMessage.classList.add('hidden');
}
 
// Load Payments Function
async function loadPayments() {
    hideError();
    loadingState.classList.remove('hidden');
    paymentList.innerHTML = '';
    paymentTable.classList.add('hidden');
 
    try {
        const response = await fetch(`/homePage/dashboard/paymentManagement/all-revenue`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
 
        const payments = await response.json();
        displayPayments(payments);
 
    } catch (error) {
        console.error('Error:', error);
        showError('Error fetching payments. Please try again.');
       
    } finally {
        loadingState.classList.add('hidden');
        paymentTable.classList.remove('hidden');
    }
}
 
// Display Payments Function
function displayPayments(payments) {
    if (!payments || payments.length === 0) {
        paymentList.innerHTML = `
            <tr>
                <td colspan="2" class="text-center py-4">No payments found</td>
            </tr>`;
        return;
    }
 
    paymentList.innerHTML = payments.map(payment => `
        <tr>
            <td>${formatDate(payment.paymentDate)}</td>
            <td>${formatCurrency(payment.amount)}</td>
        </tr>
    `).join('');
}
 
// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    // Initialize button listener
    loadPaymentBtn?.addEventListener('click', loadPayments);
});