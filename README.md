CREATE DATABASE Inventario;

USE Inventario;

CREATE TABLE Categorias (
    categoria_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE Productos(
    producto_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(50) NOT NULL,
    precio FLOAT NOT NULL,
    inStock INT NOT NULL,
    foto VARCHAR(100) NOT NULL,
    categoria_id INT NOT NULL,
    
    -- Relación con la tabla Categorias
    FOREIGN KEY (categoria_id) REFERENCES Categorias(categoria_id)
);

CREATE TABLE Tipos_Procesos(
    proceso_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proceso VARCHAR(7) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE Detalles_Procesos(
    detalle_proceso_id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    proceso_id INT NOT NULL,
    
    -- Relación con las tablas Productos y Tipos_Procesos
    FOREIGN KEY (producto_id) REFERENCES Productos(producto_id),
    FOREIGN KEY (proceso_id) REFERENCES Tipos_Procesos(proceso_id)
);

CREATE TABLE Proveedores(
    proveedor_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proveedor VARCHAR(50) NOT NULL
);

CREATE TABLE Detalles_Productos(
    detalle_producto_id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    proveedor_id INT NOT NULL,
    
    -- Relación con las tablas Productos y Proveedores
    FOREIGN KEY (producto_id) REFERENCES Productos(producto_id),
    FOREIGN KEY (proveedor_id) REFERENCES Proveedores(proveedor_id)
);




DATOS DE MENTIRA------------------------------------

INSERT INTO Categorias (nombre_categoria, descripcion) VALUES 
('Electrónica', 'Dispositivos y gadgets electrónicos'),
('Ropa', 'Prendas de vestir y accesorios'),
('Alimentos', 'Productos comestibles y bebidas'),
('Juguetes', 'Juguetes para niños de todas las edades'),
('Muebles', 'Mobiliario para el hogar y oficina'),
('Libros', 'Libros y material de lectura'),
('Herramientas', 'Herramientas y equipos para bricolaje'),
('Deportes', 'Equipos y ropa deportiva'),
('Belleza', 'Productos de belleza y cuidado personal'),
('Jardinería', 'Productos y herramientas para jardinería'),
('Hogar', 'Artículos de uso doméstico'),
('Oficina', 'Suministros y muebles de oficina'),
('Automotriz', 'Accesorios y piezas para automóviles'),
('Mascotas', 'Productos para el cuidado de mascotas'),
('Joyería', 'Joyas y accesorios'),
('Salud', 'Productos para el cuidado de la salud'),
('Tecnología', 'Dispositivos tecnológicos y accesorios'),
('Electrodomésticos', 'Aparatos eléctricos para el hogar'),
('Viajes', 'Accesorios y servicios para viajeros'),
('Construcción', 'Materiales y herramientas para construcción'),
('Papelería', 'Artículos de papelería y oficina'),
('Fotografía', 'Equipos y accesorios para fotografía'),
('Música', 'Instrumentos y equipos musicales'),
('Videojuegos', 'Consolas y accesorios para videojuegos'),
('Arte', 'Materiales y suministros artísticos'),
('Cocina', 'Utensilios y electrodomésticos de cocina'),
('Baño', 'Accesorios y productos para el baño'),
('Calzado', 'Zapatos y calzado deportivo'),
('Juguetes Educativos', 'Juguetes que fomentan el aprendizaje'),
('Bebés', 'Productos para el cuidado de bebés'),
('Ferretería', 'Artículos de ferretería y bricolaje'),
('Lencería', 'Ropa interior y de dormir'),
('Bolsos y Maletas', 'Bolsos, maletas y mochilas'),
('Accesorios para el Hogar', 'Decoración y accesorios para el hogar'),
('Iluminación', 'Lámparas y sistemas de iluminación'),
('Relojes', 'Relojes y cronógrafos'),
('Ciclismo', 'Bicicletas y accesorios para ciclistas'),
('Camping', 'Equipo y accesorios para camping'),
('Peluquería', 'Productos y equipos de peluquería'),
('Seguridad', 'Equipos y sistemas de seguridad'),
('Climatización', 'Sistemas de calefacción y aire acondicionado'),
('Jardín', 'Plantas y accesorios de jardinería'),
('Cerámica', 'Artículos de cerámica y porcelana'),
('Vidrio', 'Productos de vidrio y cristalería'),
('Textiles', 'Tejidos y productos textiles'),
('Gafas', 'Gafas de sol y ópticas'),
('Ropa Deportiva', 'Prendas de vestir para deportes'),
('Manualidades', 'Materiales para manualidades y bricolaje');







