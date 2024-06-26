CREATE TABLE endereco (
                          id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                          bairro varchar(255) NULL,
                          cidade varchar(255) NULL,
                          estado varchar(2) NULL,
                          logradouro varchar(255) NULL,
                          numero varchar(3) NULL,
                          status boolean NOT NULL DEFAULT true,
                          CONSTRAINT endereco_pkey PRIMARY KEY (id)
);

CREATE TABLE cliente (
                         id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                         nome varchar(10) NULL,
                         endereco_id int8 NULL,
                         status boolean NOT NULL DEFAULT true,
                         CONSTRAINT cliente_pkey PRIMARY KEY (id),
                         CONSTRAINT uk7v21uy9djyl7hh9464kkjsjg0 UNIQUE (endereco_id),
                         CONSTRAINT fk64nr9yt889by5lufr1boo5i4s FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE telefone (
                          id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                          telefone varchar(13) NOT NULL,
                          cliente_id int8 NOT NULL,
                          status boolean NOT NULL DEFAULT true,
                          CONSTRAINT telefone_pkey PRIMARY KEY (id),
                          CONSTRAINT fk8aafha0njkoyoe3kvrwsy3g8u FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);