/**
 * Main application module - Orchestrates the application
 */

/**
 * Loads and displays all tasks from the backend
 */
async function loadTasks() {
    showLoading();

    try {
        const tasks = await fetchTodos();
        renderTasks(tasks);
    } catch (error) {
        console.error('Error loading tasks:', error);
        alert('Could not load tasks. Please check if the backend is running on http://localhost:8080');
        renderTasks([]);
    } finally {
        hideLoading();
    }
}

/**
 * Handles form submission to add a new task
 * @param {Event} event - Form submit event
 */
async function handleAddTask(event) {
    event.preventDefault();

    const title = elements.taskInput.value.trim();

    if (!title) {
        displayError('Please enter a task');
        return;
    }

    try {
        await createTodo(title);
        clearInput();
        clearErrorMessage();
        await loadTasks();
    } catch (error) {
        displayError(error.message);
    }
}

/**
 * Handles button clicks on task items (toggle/delete)
 * @param {Event} event - Click event
 */
async function handleTaskAction(event) {
    const button = event.target.closest('button[data-action]');
    if (!button) return;

    const action = button.dataset.action;
    const taskId = parseInt(button.dataset.taskId);

    if (action === 'toggle') {
        try {
            await toggleTodo(taskId);
            await loadTasks();
        } catch (error) {
            alert('Error: ' + error.message);
        }
    } else if (action === 'delete') {
        if (confirm('Are you sure you want to delete this task?')) {
            try {
                await deleteTodo(taskId);
                await loadTasks();
            } catch (error) {
                alert('Error: ' + error.message);
            }
        }
    }
}

/**
 * Sets up event listeners
 */
function setupEventListeners() {
    const addForm = getElement('addForm');
    const tasksList = getElement('tasksList');

    if (addForm) {
        addForm.addEventListener('submit', handleAddTask);
    }

    if (tasksList) {
        tasksList.addEventListener('click', handleTaskAction);
    }
}

/**
 * Initializes the application
 */
function init() {
    initializeElements();
    setupEventListeners();
    loadTasks();
}

// Start the application when DOM is ready
document.addEventListener('DOMContentLoaded', init);
