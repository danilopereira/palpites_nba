CREATE TABLE conferencia(
    id integer NOT NULL PRIMARY KEY,
    nome varchar(255) NOT NULL
);

CREATE TABLE `time` (
 id integer NOT NULL PRIMARY KEY,
 nome varchar(255) NOT NULL,
 escudo varchar(255),
 conferencia_id integer NOT NULL,
 FOREIGN KEY (conferencia_id) REFERENCES conferencia (id)
);


CREATE TABLE resultado(
    id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
    time1_id integer NOT NULL,
    time2_id integer NOT NULL,
    resultado_time1 integer NOT NULL,
    resultado_time2 integer NOT NULL,
    FOREIGN KEY (time1_id) REFERENCES `time` (id),
    FOREIGN KEY (time2_id) REFERENCES `time` (id)
);