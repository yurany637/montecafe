// ----------------------------
// Inicialización global
// ----------------------------
document.addEventListener("DOMContentLoaded", () => {
  aplicarTema();
  inicializarLogin();
  inicializarClientes();
  inicializarConfiguracion();
  inicializarProveedores();
  inicializarMateriaPrima();
  inicializarInventario();
  inicializarVentas();
  inicializarReportes();
});

// ----------------------------
// Validación del Login
// ----------------------------
function inicializarLogin() {
  const formLogin = document.getElementById("formLogin");
  if (formLogin) {
    formLogin.addEventListener("submit", (event) => {
      event.preventDefault();
      const usuario = document.getElementById("usuario").value.trim();
      const contrasena = document.getElementById("contrasena").value.trim();
      if (usuario === "" || contrasena === "") {
        alert("Por favor complete todos los campos.");
      } else {
        window.location.href = "dashboard.html";
      }
    });
  }
}

// ----------------------------
// Cambio de tema global
// ----------------------------
function aplicarTema() {
  const temaGuardado = localStorage.getItem("temaSistema");
  if (temaGuardado === "oscuro") {
    document.body.classList.add("tema-oscuro");
  } else {
    document.body.classList.remove("tema-oscuro");
  }
}

// ----------------------------
// Módulo de Clientes
// ----------------------------
let contadorClientes = 2;

function inicializarClientes() {
  const btnRegistrar = document.getElementById("btnRegistrarCliente");
  const tabla = document.getElementById("tablaClientes");

  if (btnRegistrar && tabla) {
    btnRegistrar.addEventListener("click", () => {
      const nombre = document.getElementById("nombreCliente").value.trim();
      const telefono = document.getElementById("telefonoCliente").value.trim();
      const correo = document.getElementById("correoCliente").value.trim();
      const direccion = document.getElementById("direccionCliente").value.trim();

      if (!nombre || !telefono || !correo || !direccion) {
        alert("Por favor complete todos los campos.");
        return;
      }

      contadorClientes++;
      const idCliente = "CL" + String(contadorClientes).padStart(3, '0');

      const nuevaFila = `
        <tr>
          <td>${idCliente}</td>
          <td>${nombre}</td>
          <td>${telefono}</td>
          <td>${correo}</td>
          <td>${direccion}</td>
          <td class="acciones">
            <button class="editar">Editar</button>
            <button class="eliminar">Eliminar</button>
          </td>
        </tr>
      `;

      tabla.insertAdjacentHTML('beforeend', nuevaFila);

      document.getElementById("nombreCliente").value = "";
      document.getElementById("telefonoCliente").value = "";
      document.getElementById("correoCliente").value = "";
      document.getElementById("direccionCliente").value = "";
    });

    tabla.addEventListener("click", (e) => {
      if (e.target.classList.contains("eliminar")) {
        if (confirm("¿Está seguro que desea eliminar este cliente?")) {
          e.target.closest("tr").remove();
        }
      }

      if (e.target.classList.contains("editar")) {
        const fila = e.target.closest("tr").querySelectorAll("td");
        const nuevoNombre = prompt("Ingrese el nuevo nombre:", fila[1].textContent);
        const nuevoTelefono = prompt("Ingrese el nuevo teléfono:", fila[2].textContent);
        const nuevoCorreo = prompt("Ingrese el nuevo correo:", fila[3].textContent);
        const nuevaDireccion = prompt("Ingrese la nueva dirección:", fila[4].textContent);

        if (nuevoNombre && nuevoTelefono && nuevoCorreo && nuevaDireccion) {
          fila[1].textContent = nuevoNombre;
          fila[2].textContent = nuevoTelefono;
          fila[3].textContent = nuevoCorreo;
          fila[4].textContent = nuevaDireccion;
        }
      }
    });
  }
}

// ----------------------------
// Módulo de Configuración
// ----------------------------
function inicializarConfiguracion() {
  const btnGuardar = document.getElementById("btnGuardar");

  if (btnGuardar) {
    btnGuardar.addEventListener("click", () => {
      const nombre = document.getElementById("nombreUsuario").value.trim();
      const correo = document.getElementById("correoUsuario").value.trim();
      const contrasena = document.getElementById("nuevaContrasena").value.trim();
      const idioma = document.getElementById("idiomaSistema").value;
      const tema = document.getElementById("temaVisual").value;

      if (!nombre || !correo) {
        alert("Por favor complete al menos el nombre y el correo.");
        return;
      }

      alert(`✅ Cambios guardados:\nNombre: ${nombre}\nCorreo: ${correo}\nIdioma: ${idioma === "es" ? "Español" : "Inglés"}\nTema: ${tema === "claro" ? "Claro" : "Oscuro"}\nContraseña: ${contrasena ? "(actualizada)" : "(sin cambios)"}`);

      localStorage.setItem("temaSistema", tema);
      aplicarTema();
    });
  }
}

// ----------------------------
// Módulo de Proveedores
// ----------------------------
let contadorProveedores = 3;

function inicializarProveedores() {
  const btnRegistrar = document.getElementById("btnRegistrarProveedor");
  const tabla = document.getElementById("tablaProveedores");

  if (btnRegistrar && tabla) {
    btnRegistrar.addEventListener("click", () => {
      const nombre = document.getElementById("nombreProveedor").value.trim();
      const telefono = document.getElementById("telefonoProveedor").value.trim();
      const correo = document.getElementById("correoProveedor").value.trim();
      const ciudad = document.getElementById("ciudadProveedor").value.trim();

      if (!nombre || !telefono || !correo || !ciudad) {
        alert("Por favor completa todos los campos.");
        return;
      }

      const id = "PR" + String(contadorProveedores).padStart(3, '0');
      const nuevaFila = `
        <tr>
          <td>${id}</td>
          <td>${nombre}</td>
          <td>${telefono}</td>
          <td>${correo}</td>
          <td>${ciudad}</td>
          <td class="acciones">
            <button class="editar">Editar</button>
            <button class="eliminar">Eliminar</button>
          </td>
        </tr>
      `;

      tabla.insertAdjacentHTML('beforeend', nuevaFila);
      contadorProveedores++;

      document.getElementById("nombreProveedor").value = "";
      document.getElementById("telefonoProveedor").value = "";
      document.getElementById("correoProveedor").value = "";
      document.getElementById("ciudadProveedor").value = "";
    });

    tabla.addEventListener("click", (e) => {
      if (e.target.classList.contains("eliminar")) {
        if (confirm("¿Estás seguro que desea eliminar este proveedor?")) {
          e.target.closest("tr").remove();
        }
      }

      if (e.target.classList.contains("editar")) {
        const fila = e.target.closest("tr").querySelectorAll("td");
        const nuevoNombre = prompt("Editar nombre:", fila[1].textContent);
        const nuevoTelefono = prompt("Editar teléfono:", fila[2].textContent);
        const nuevoCorreo = prompt("Editar correo:", fila[3].textContent);
        const nuevaCiudad = prompt("Editar ciudad:", fila[4].textContent);

        if (nuevoNombre && nuevoTelefono && nuevoCorreo && nuevaCiudad) {
          fila[1].textContent = nuevoNombre;
          fila[2].textContent = nuevoTelefono;
          fila[3].textContent = nuevoCorreo;
          fila[4].textContent = nuevaCiudad;
        }
      }
    });
  }
}

// ----------------------------
// Módulo de Materia Prima
// ----------------------------
let contadorMateriaPrima = 3;

function inicializarMateriaPrima() {
  const btnRegistrar = document.getElementById("btnRegistrarInsumo");
  const tabla = document.getElementById("tablaMateriaPrima");

  if (btnRegistrar && tabla) {
    btnRegistrar.addEventListener("click", () => {
      const nombre = document.getElementById("nombreInsumo").value.trim();
      const unidad = document.getElementById("unidadInsumo").value;
      const stock = document.getElementById("stockInsumo").value.trim();

      if (!nombre || !stock || isNaN(stock) || Number(stock) < 0) {
        alert("Por favor complete todos los campos correctamente.");
        return;
      }

      const id = "MP" + String(contadorMateriaPrima).padStart(3, '0');
      const nuevaFila = `
        <tr>
          <td>${id}</td>
          <td>${nombre}</td>
          <td>${unidad}</td>
          <td>${stock}</td>
          <td class="acciones">
            <button class="editar">Editar</button>
            <button class="eliminar">Eliminar</button>
          </td>
        </tr>
      `;

      tabla.insertAdjacentHTML('beforeend', nuevaFila);
      contadorMateriaPrima++;

      document.getElementById("nombreInsumo").value = "";
      document.getElementById("unidadInsumo").selectedIndex = 0;
      document.getElementById("stockInsumo").value = "";
    });

    tabla.addEventListener("click", (e) => {
      if (e.target.classList.contains("eliminar")) {
        if (confirm("¿Estás seguro que desea eliminar este insumo?")) {
          e.target.closest("tr").remove();
        }
      }

      if (e.target.classList.contains("editar")) {
        const fila = e.target.closest("tr").querySelectorAll("td");
        const nuevoNombre = prompt("Editar nombre del insumo:", fila[1].textContent);
        const nuevaUnidad = prompt("Editar unidad (Kg, Litros, Unidades):", fila[2].textContent);
        const nuevoStock = prompt("Editar stock inicial:", fila[3].textContent);

        if (nuevoNombre && nuevaUnidad && nuevoStock && !isNaN(nuevoStock) && Number(nuevoStock) >= 0) {
          fila[1].textContent = nuevoNombre;
          fila[2].textContent = nuevaUnidad;
          fila[3].textContent = nuevoStock;
        }
      }
    });
  }
}

// ----------------------------
// Módulo de Inventario con LocalStorage
// ----------------------------
let contadorInventario = 4;

function inicializarInventario() {
  const botones = document.querySelectorAll(".actions button");
  const tabla = document.querySelector("table tbody");

  if (botones.length === 3 && tabla) {
    const btnAgregar = botones[0];
    const btnActualizar = botones[1];
    const btnEliminar = botones[2];

    // Cargar inventario desde localStorage o valores iniciales
    let inventario = JSON.parse(localStorage.getItem("inventario")) || [
      { id: "INV001", nombre: "Café tostado", categoria: "Producto final", stock: 50, unidad: "kg", estado: "Activo" },
      { id: "INV002", nombre: "Materia prima A", categoria: "Insumo", stock: 20, unidad: "kg", estado: "Stock bajo" },
      { id: "INV003", nombre: "Envases", categoria: "Empaque", stock: 200, unidad: "unid", estado: "Activo" }
    ];

    contadorInventario = inventario.length + 1;

    renderizarTabla();

    btnAgregar.addEventListener("click", () => {
      const nombre = prompt("Ingrese el nombre del producto:");
      const categoria = prompt("Ingrese la categoría:");
      const stock = prompt("Ingrese el stock inicial:");
      const unidad = prompt("Ingrese la unidad (kg, unid, litros):");

      if (!nombre || !categoria || !stock || !unidad || isNaN(stock) || Number(stock) < 0) {
        alert("Todos los campos son obligatorios y el stock debe ser un número válido.");
        return;
      }

      const estado = parseInt(stock) <= 20 ? "Stock bajo" : "Activo";
      const id = "INV" + String(contadorInventario).padStart(3, '0');

      inventario.push({ id, nombre, categoria, stock: Number(stock), unidad, estado });
      contadorInventario++;

      guardarInventario();
      renderizarTabla();
    });

    btnActualizar.addEventListener("click", () => {
      const id = prompt("Ingrese el ID del producto que desea actualizar:");
      const producto = inventario.find(p => p.id === id);

      if (!producto) {
        alert("Producto no encontrado.");
        return;
      }

      const nuevoStock = prompt("Ingrese el nuevo stock:", producto.stock);

      if (nuevoStock && !isNaN(nuevoStock) && Number(nuevoStock) >= 0) {
        producto.stock = Number(nuevoStock);
        producto.estado = producto.stock <= 20 ? "Stock bajo" : "Activo";

        guardarInventario();
        renderizarTabla();
        alert("Stock actualizado correctamente.");
      } else {
        alert("El stock ingresado no es válido.");
      }
    });

    btnEliminar.addEventListener("click", () => {
      const id = prompt("Ingrese el ID del producto que desea eliminar:");
      const index = inventario.findIndex(p => p.id === id);

      if (index === -1) {
        alert("Producto no encontrado.");
        return;
      }

      if (confirm(`¿Está seguro que desea eliminar el producto ${inventario[index].nombre}?`)) {
        inventario.splice(index, 1);
        guardarInventario();
        renderizarTabla();
        alert("Producto eliminado correctamente.");
      }
    });

    function renderizarTabla() {
      tabla.innerHTML = "";
      inventario.forEach(item => {
        const fila = `
          <tr>
            <td>${item.id}</td>
            <td>${item.nombre}</td>
            <td>${item.categoria}</td>
            <td>${item.stock}</td>
            <td>${item.unidad}</td>
            <td>${item.estado}</td>
          </tr>
        `;
        tabla.insertAdjacentHTML('beforeend', fila);
      });
    }

    function guardarInventario() {
      localStorage.setItem("inventario", JSON.stringify(inventario));
    }
  }
}

// ----------------------------
// Módulo de Ventas
// ----------------------------
function inicializarVentas() {
  const inputCliente = document.querySelector(".sales-form input[placeholder='Nombre del cliente']");
  const selectProducto = document.querySelector(".sales-form select");
  const inputCantidad = document.querySelector(".sales-form input[type='number']");
  const inputFecha = document.querySelector(".sales-form input[type='date']");
  const btnConfirmar = document.querySelector(".sales-form button.confirmar");
  const btnCancelar = document.querySelector(".sales-form button.cancelar");

  const resumenProducto = document.querySelector(".sales-summary p:nth-child(2) strong");
  const resumenCantidad = document.querySelector(".sales-summary p:nth-child(3) strong");
  const resumenPrecioUnitario = document.querySelector(".sales-summary p:nth-child(4) strong");
  const resumenTotal = document.querySelector(".sales-summary p:nth-child(5) strong");

  if (inputCliente && selectProducto && inputCantidad && inputFecha && btnConfirmar && btnCancelar) {
    const precios = {
      "Café tostado": 15000,
      "Envases": 500,
      "Materia prima A": 8000
    };

    selectProducto.addEventListener("change", actualizarResumen);
    inputCantidad.addEventListener("input", actualizarResumen);

    function actualizarResumen() {
      const producto = selectProducto.value;
      const cantidad = Number(inputCantidad.value);
      const precioUnitario = precios[producto] || 0;
      const total = cantidad > 0 ? cantidad * precioUnitario : 0;

      resumenProducto.textContent = producto;
      resumenCantidad.textContent = `${cantidad} ${producto === "Envases" ? "unid" : "kg"}`;
      resumenPrecioUnitario.textContent = `$${precioUnitario.toLocaleString()}`;
      resumenTotal.textContent = `$${total.toLocaleString()}`;
    }

    btnConfirmar.addEventListener("click", () => {
      const cliente = inputCliente.value.trim();
      const producto = selectProducto.value;
      const cantidad = Number(inputCantidad.value);
      const fecha = inputFecha.value;
      const total = cantidad * (precios[producto] || 0);

      if (!cliente || !producto || !cantidad || !fecha || cantidad <= 0) {
        alert("Por favor complete todos los campos correctamente.");
        return;
      }

      // Guardar venta en LocalStorage
      const ventas = JSON.parse(localStorage.getItem("ventas")) || [];
      ventas.push({ cliente, producto, cantidad, fecha, total });
      localStorage.setItem("ventas", JSON.stringify(ventas));

      alert(`✅ Venta registrada:\nCliente: ${cliente}\nProducto: ${producto}\nCantidad: ${cantidad}\nTotal: $${total.toLocaleString()}`);

      limpiarFormulario();
      actualizarResumen();
    });

    btnCancelar.addEventListener("click", () => {
      if (confirm("¿Desea cancelar la venta?")) {
        limpiarFormulario();
        actualizarResumen();
      }
    });

    function limpiarFormulario() {
      inputCliente.value = "";
      selectProducto.selectedIndex = 0;
      inputCantidad.value = "";
      inputFecha.value = "";
    }

    actualizarResumen();
  }
}

// ----------------------------
// Módulo de Reportes
// ----------------------------
// ----------------------------
// Módulo de Reportes
// ----------------------------
function inicializarReportes() {
  const btnGenerar = document.getElementById("btnGenerarReporte");
  const tipo = document.getElementById("tipoReporte");
  const fechaInicio = document.getElementById("fechaInicial");
  const fechaFin = document.getElementById("fechaFinal");
  const tabla = document.getElementById("tablaReportes");

  if (btnGenerar && tipo && fechaInicio && fechaFin && tabla) {
    btnGenerar.addEventListener("click", () => {
      const tipoSeleccionado = tipo.value;
      const inicio = new Date(fechaInicio.value);
      const fin = new Date(fechaFin.value);

      if (!inicio || !fin || isNaN(inicio) || isNaN(fin)) {
        alert("Por favor seleccione el rango de fechas.");
        return;
      }

      tabla.innerHTML = ""; // Limpiar reportes anteriores

      if (tipoSeleccionado === "Inventario") {
        const inventario = JSON.parse(localStorage.getItem("inventario")) || [];

        inventario.forEach(item => {
          const fila = `
            <tr>
              <td>${new Date().toLocaleDateString()}</td>
              <td>Inventario</td>
              <td>${item.nombre} - Stock actual: ${item.stock}</td>
              <td>$ -</td>
            </tr>
          `;
          tabla.insertAdjacentHTML('beforeend', fila);
        });
      }

      if (tipoSeleccionado === "Ventas") {
        const ventas = JSON.parse(localStorage.getItem("ventas")) || [];

        ventas.forEach(venta => {
          const fechaVenta = new Date(venta.fecha);

          if (fechaVenta >= inicio && fechaVenta <= fin) {
            const fila = `
              <tr>
                <td>${fechaVenta.toLocaleDateString()}</td>
                <td>Venta</td>
                <td>${venta.producto} - ${venta.cantidad} unidades vendidas</td>
                <td>$${venta.total.toLocaleString()}</td>
              </tr>
            `;
            tabla.insertAdjacentHTML('beforeend', fila);
          }
        });
      }
    });
  }
}