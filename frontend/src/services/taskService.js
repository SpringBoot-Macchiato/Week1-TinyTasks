// src/services/taskService.js
const URL_BASE = "http://localhost:8080/api/task";

export async function getTasks() {
  const res = await fetch(URL_BASE);
  if (!res.ok) throw new Error("Error al obtener las tareas");
  return res.json();
}

export async function createTask(task) {
  const res = await fetch(URL_BASE, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(task),
  });
  if (!res.ok) throw new Error("Error al crear la tarea");
  return res.json();
}

export async function deleteTask(id) {
  const res = await fetch(`${URL_BASE}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Error al eliminar la tarea");
}

export async function updateTask(id, task) {
  const res = await fetch(`${URL_BASE}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(task),
  });
  if (!res.ok) throw new Error("Error al actualizar la tarea");
  return res.json();
}

export async function patchTask(id, patch) {
  const res = await fetch(`${URL_BASE}/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(patch),
  });
  if (!res.ok) throw new Error("Error al actualizar la tarea");
  return res.json();
}

export async function toggleDone(id, active) {
  return patchTask(id, {active});
}