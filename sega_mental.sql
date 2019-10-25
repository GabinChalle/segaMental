CREATE TABLE operations
(
    id_op INT PRIMARY KEY NOT NULL auto_increment,
    score INT NOT NULL,
    id_user INT NOT NULL,
    CONSTRAINT fk_id_user
    FOREIGN KEY (id_user)
    REFERENCES USER(id_user)
);

CREATE TABLE utilisateurs
(
    id_user INT PRIMARY KEY NOT NULL auto_increment,
    pseudo VARCHAR(255),
    password VARCHAR(255),
    id_op INT,
    CONSTRAINT fk_id_op
    FOREIGN KEY (id_op)
    REFERENCES operations(id_op)
);

CREATE TABLE expressions
(
	id_calcul INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	libelle VARCHAR(255),
	res_attendu DOUBLE,
	res_donnee DOUBLE,
	id_op INT,
	CONSTRAINT fk_id_ex
	FOREIGN KEY (id_op)
	REFERENCES operations(id_op)
);

INSERT INTO utilisateurs (pseudo, PASSWORD)
 VALUES
 ('gabin', 'challe'),
 ('corentin', 'gautier'),
 ('yves', 'guerin'),
 ('admin','kze369');
 
 

