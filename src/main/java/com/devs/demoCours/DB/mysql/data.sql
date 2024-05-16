INSERT INTO roles (name) VALUES
                             ('ROLE_ADMIN'),
                             ('ROLE_TEACH'),
                             ('ROLE_STUDENT');
INSERT INTO docente
(dni, correo, apellidos, nombre, password, status, activo)
VALUES('00000001', '00000001@admin.com', 'Doe Doe', 'Jhoe', '$2a$10$0KMRaIyqqCxTKDyYjuxd5uK3Yx/BMMF5tc5B0NsAJ8VA3dsLMekf6', 1, 1);

INSERT INTO docents_roles
(docente_id, role_id)
VALUES(1, 1);
