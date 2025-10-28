/**
 * Utility functions for TinyTasks application
 */

/**
 * Escapes HTML to prevent XSS attacks
 * @param {string} text - Text to escape
 * @returns {string} Escaped HTML string
 */
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

/**
 * Shows an element by changing its display style
 * @param {HTMLElement} element - Element to show
 */
function show(element) {
    if (element) {
        element.style.display = 'block';
    }
}

/**
 * Hides an element by changing its display style
 * @param {HTMLElement} element - Element to hide
 */
function hide(element) {
    if (element) {
        element.style.display = 'none';
    }
}

/**
 * Gets DOM elements by ID
 * @param {string} id - Element ID
 * @returns {HTMLElement|null} DOM element
 */
function getElement(id) {
    return document.getElementById(id);
}

/**
 * Displays an error message
 * @param {string} message - Error message to display
 * @param {HTMLElement} errorElement - Error container element
 */
function showError(message, errorElement) {
    if (errorElement) {
        errorElement.textContent = message;
        show(errorElement);
    }
}

/**
 * Clears error message
 * @param {HTMLElement} errorElement - Error container element
 */
function clearError(errorElement) {
    if (errorElement) {
        errorElement.textContent = '';
        hide(errorElement);
    }
}
