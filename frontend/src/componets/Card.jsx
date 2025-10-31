import { useState } from "react";

function Card({ title, descripcion, done, onDelete, onEdit, onToggle }) {

  return (
    <div
      className={`shadow-md rounded-xl p-4 w-full max-w-sm border transition
      ${done ? "bg-green-50 border-green-300" : "bg-white border-gray-200 hover:shadow-lg"}`}
    >
      <h3
        className={`text-lg font-semibold 
        ${done ? "line-through text-gray-400" : "text-gray-800"}`}
      >
        {title}
      </h3>

      <p
        className={`text-sm mt-1 
        ${done ? "line-through text-gray-400" : "text-gray-600"}`}
      >
        {descripcion}
      </p>

      <div className="flex items-center justify-between mt-3">
        <button
          onClick={onToggle}
          className={`text-xs py-1 px-3 rounded-full transition font-medium
          ${done ? "bg-gray-400 text-white hover:bg-gray-500" : "bg-green-500 text-white hover:bg-green-600"}`}
        >
          {done ? "Marcar pendiente" : "Marcar hecha"}
        </button>

        <div className="flex gap-2">
          <button onClick={onDelete} className="text-xs bg-red-500 text-white py-1 px-2 rounded-lg hover:bg-red-600 transition">
            Eliminar
          </button>
          <button onClick={onEdit} className="text-xs bg-blue-500 text-white py-1 px-2 rounded-lg hover:bg-blue-600 transition">
            Editar
          </button>
        </div>
      </div>
    </div>
  );
}

export default Card;
