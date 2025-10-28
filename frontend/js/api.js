/**
 * API module - Handles all communication with the backend
 */

const API_URL = 'http://localhost:8080/api/todos';

/**
 * Fetches all tasks from the backend
 * @returns {Promise<Array>} Array of todo objects
 * @throws {Error} If the request fails
 */
async function fetchTodos() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error('Error fetching todos:', error);
        throw new Error('Could not load tasks. Please check if the backend is running.');
    }
}

/**
 * Creates a new task
 * @param {string} title - Task title
 * @returns {Promise<Object>} Created todo object
 * @throws {Error} If the request fails or validation fails
 */
async function createTodo(title) {
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ title: title })
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.error || 'Error creating task');
        }

        return data;
    } catch (error) {
        console.error('Error creating todo:', error);
        throw error;
    }
}

/**
 * Toggles the done status of a task
 * @param {number} id - Task ID
 * @returns {Promise<Object>} Updated todo object
 * @throws {Error} If the request fails or task not found
 */
async function toggleTodo(id) {
    try {
        const response = await fetch(`${API_URL}/${id}/toggle`, {
            method: 'PUT'
        });

        if (!response.ok) {
            if (response.status === 404) {
                throw new Error('Task not found');
            }
            throw new Error('Error updating task');
        }

        return await response.json();
    } catch (error) {
        console.error('Error toggling todo:', error);
        throw error;
    }
}

/**
 * Deletes a task
 * @param {number} id - Task ID
 * @returns {Promise<void>}
 * @throws {Error} If the request fails or task not found
 */
async function deleteTodo(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            if (response.status === 404) {
                throw new Error('Task not found');
            }
            throw new Error('Error deleting task');
        }
    } catch (error) {
        console.error('Error deleting todo:', error);
        throw error;
    }
}
