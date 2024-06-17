
INSERT INTO endereco (bairro, cidade, estado, logradouro, numero)
VALUES
    ('Centro', 'São Paulo', 'SP', 'Rua A', '100'),
    ('Jardim', 'Rio de Janeiro', 'RJ', 'Rua B', '200'),
    ('Bela Vista', 'Belo Horizonte', 'MG', 'Rua C', '300');

INSERT INTO cliente (nome, endereco_id)
VALUES
    ('João', 1),
    ('Maria', 2),
    ('Pedro', 3);

INSERT INTO telefone (telefone, cliente_id)
VALUES
    ('9226381217', 1),
    ('6927115324', 2),
    ('6339033544', 3),
    ('6733826239', 1),
    ('9224282323', 2);
