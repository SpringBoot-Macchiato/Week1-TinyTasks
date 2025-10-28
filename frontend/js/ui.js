/**
 * UI module - Handles all DOM manipulation and rendering
 */

// Cache DOM elements
const elements = {
    tasksList: null,
    emptyMessage: null,
    loading: null,
    taskCount: null,
    errorMsg: null,
    taskInput: null
};

/**
 * Initializes DOM element references
 */
function initializeElements() {
    elements.tasksList = getElement('tasksList');
    elements.emptyMessage = getElement('emptyMessage');
    elements.loading = getElement('loading');
    elements.taskCount = getElement('taskCount');
    elements.errorMsg = getElement('errorMsg');
    elements.taskInput = getElement('taskInput');
}

/**
 * Shows loading spinner
 */
function showLoading() {
    show(elements.loading);
    hide(elements.tasksList);
    hide(elements.emptyMessage);
}

/**
 * Hides loading spinner
 */
function hideLoading() {
    hide(elements.loading);
}

/**
 * Renders all tasks in the UI
 * @param {Array} tasks - Array of todo objects
 */
function renderTasks(tasks) {
    elements.tasksList.innerHTML = '';

    if (tasks.length === 0) {
        show(elements.emptyMessage);
        hide(elements.tasksList);
    } else {
        hide(elements.emptyMessage);
        show(elements.tasksList);

        tasks.forEach(task => {
            const taskElement = createTaskElement(task);
            elements.tasksList.appendChild(taskElement);
        });
    }

    updateTaskCount(tasks);
}

/**
 * Creates a DOM element for a single task
 * @param {Object} task - Todo object with id, title, and done properties
 * @returns {HTMLElement} Task DOM element
 */
function createTaskElement(task) {
    const div = document.createElement('div');
    div.className = `task-item ${task.done ? 'completed' : ''}`;
    div.setAttribute('data-task-id', task.id);

    div.innerHTML = `
        <div class="d-flex justify-content-between align-items-center">
            <div class="flex-grow-1">
                <h6 class="task-title">${escapeHtml(task.title)}</h6>
                <small class="task-id">ID: ${task.id}</small>
            </div>
            <div class="btn-group btn-group-sm" role="group">
                <button
                    class="btn ${task.done ? 'btn-warning' : 'btn-success'} btn-toggle"
                    data-action="toggle"
                    data-task-id="${task.id}"
                    title="${task.done ? 'Mark as pending' : 'Mark as done'}">
                    ${task.done ? 'Undo' : 'Done'}
                </button>
                <button
                    class="btn btn-danger"
                    data-action="delete"
                    data-task-id="${task.id}"
                    title="Delete task">
                    Delete
                </button>
            </div>
        </div>
    `;

    return div;
}

/**
 * Updates the task count badge
 * @param {Array} tasks - Array of todo objects
 */
function updateTaskCount(tasks) {
    const completed = tasks.filter(task => task.done).length;
    const total = tasks.length;
    elements.taskCount.textContent = `${completed}/${total} completed`;
}

/**
 * Clears the task input field
 */
function clearInput() {
    if (elements.taskInput) {
        elements.taskInput.value = '';
    }
}

/**
 * Displays an error message
 * @param {string} message - Error message
 */
function displayError(message) {
    showError(message, elements.errorMsg);
}

/**
 * Clears the error message
 */
function clearErrorMessage() {
    clearError(elements.errorMsg);
}

/**
 * Shows an alert message
 * @param {string} message - Message to display
 * @param {string} type - Alert type (success, error, warning)
 */
function showAlert(message, type = 'error') {
    alert(message);
}