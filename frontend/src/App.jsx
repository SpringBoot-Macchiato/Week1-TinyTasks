import { useState,useEffect } from 'react'
import Card from './componets/Card';
import { getTasks,createTask,deleteTask,toggleDone } from './services/taskService';

function App() {

  const [tasks, setTask] = useState([])

  const [formTask, setFormTask] = useState({title: "", description: "", active: false});


  useEffect(() => {
    loadTasks();
  }, []);

  async function loadTasks() {
    try {
      const data = await getTasks();
      setTask(data);
    } catch (err) {
      console.error("Error al cargar tareas:", err);
    }
  }


  const addTask = async (e)=>{

    e.preventDefault();

    if (!formTask.title.trim() || formTask.title.length < 4) return alert('Escribe un titulo con mas de 3 caracteres');
    if (!formTask.description.trim()) return alert('Escribe una description');

    try {
      
      const newTask = await createTask(formTask)

      setTask([...tasks, newTask]);
      setFormTask({title: "", description: "", active: false});
    } catch (error) {
      console.error("Error al crear la tarea " ,error);
    }

    
  }


  const removeTask = async (id) => {
    if (!confirm("¿Seguro que deseas eliminar esta tarea?")) return;
    try {
      await deleteTask(id);
      setTask(tasks.filter((t) => t.id !== id));
    } catch (err) {
      console.error("Error al eliminar tarea:", err);
    }
  };


  const toggleTaskDone = async (id, active) => {
    try {
      const updated = await toggleDone(id, active);
      setTask(
        tasks.map((t) => (t.id === id ? { ...t, active: updated.active } : t))
      );
    } catch (err) {
      console.error("Error al actualizar tarea:", err);
    }
  };

  return (
    <>
  <div className="bg-white min-h-screen p-6">
    <h1 className="bg-zinc-200 text-violet-700 text-4xl font-extrabold text-center p-6 rounded-xl mt-4 shadow-lg">
      Mis tareas
    </h1>

    
    <div className="flex flex-col md:flex-row gap-8 mt-10 justify-center items-start">

     
      <div className="bg-white p-6 rounded-2xl shadow-lg w-full md:w-1/3">
        <form
          className="space-y-4"
          onSubmit={addTask}
        >
          <input
            type="text"
            placeholder="Escribe un título"
            className="w-full p-3 rounded-lg border"
            value={formTask.title}
            onChange={(e) =>
              setFormTask({
                ...formTask,
                title: e.target.value,
                active: false,
              })
            }
          />

          <textarea
            name="description"
            placeholder="Escribe una descripción"
            className="w-full p-3 rounded-lg border"
            value={formTask.description}
            onChange={(e) =>
              setFormTask({
                ...formTask,
                description: e.target.value,
              })
            }
          ></textarea>

          <button
            type="submit"
            className="w-full bg-violet-600 text-white p-3 rounded-lg font-semibold hover:bg-violet-700 duration-200"
          >
            Agregar tarea
          </button>
        </form>
      </div>

      {/* SECCIÓN DE CARDS */}
      <div className="w-full md:w-2/3 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        {tasks.map((task) => (
          <Card key={task.id} title={task.title} descripcion={task.description} done={task.active} onDelete={ ()=> removeTask(task.id)} onToggle={ ()=> toggleTaskDone(task.id,!task.active)} />
        ))}
      </div>
    </div>
  </div>
</>

  )
}

export default App
