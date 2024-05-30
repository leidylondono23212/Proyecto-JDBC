-- Tabla Profesores (A)
CREATE TABLE Profesores (
    ID_Profesor INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Especialidad VARCHAR(50) NOT NULL,
    Certificaciones VARCHAR(100) NOT NULL,
    Correo_Electronico VARCHAR(50) NOT NULL
);

-- Tabla Cursos (B)
CREATE TABLE Cursos (
    ID_Curso INT PRIMARY KEY AUTO_INCREMENT,
    Nombre_Curso VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(255) NOT NULL,
    Nivel VARCHAR(20) NOT NULL,
    Duracion INT NOT NULL,
    Horario VARCHAR(50) NOT NULL,
    ID_Profesor INT NOT NULL,
    FOREIGN KEY (ID_Profesor) REFERENCES Profesores(ID_Profesor)
);

-- Tabla Estudiantes (D)
CREATE TABLE Estudiantes (
    ID_Estudiante INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Correo_Electronico VARCHAR(50) NOT NULL,
    Telefono VARCHAR(20) NOT NULL,
    Direccion VARCHAR(100) NOT NULL
);

-- Tabla de Unión Estudiantes_Cursos (D_B)
CREATE TABLE Estudiantes_Cursos (
    ID_Estudiante INT NOT NULL,
    ID_Curso INT NOT NULL,
    PRIMARY KEY (ID_Estudiante, ID_Curso),
    FOREIGN KEY (ID_Estudiante) REFERENCES Estudiantes(ID_Estudiante),
    FOREIGN KEY (ID_Curso) REFERENCES Cursos(ID_Curso)
);

-- Tabla Especializaciones (C)
CREATE TABLE Especializaciones (
    ID_Especializacion INT PRIMARY KEY AUTO_INCREMENT,
    ID_Profesor INT NOT NULL,
    Nombre_Especializacion VARCHAR(100) NOT NULL,
    Nivel_Especializacion VARCHAR(50) NOT NULL,
    Institucion VARCHAR(100) NOT NULL,
    FOREIGN KEY (ID_Profesor) REFERENCES Profesores(ID_Profesor)
);


