
INSERT INTO endereco (bairro, cidade, estado, logradouro, numero)
VALUES
    ('Centro', 'São Paulo', 'SP', 'Rua A', '100'),
    ('Jardim', 'Rio de Janeiro', 'RJ', 'Rua B', '200'),
    ('Bela Vista', 'Belo Horizonte', 'MG', 'Rua C', '300');

INSERT INTO cliente (nome, endereco_id)
VALUES
    ('João Silva', 1),
    ('Maria Oliveira', 2),
    ('Pedro Santos', 3);

INSERT INTO telefone (telefone, cliente_id)
VALUES
    ('11999999999', 1),
    ('21988888888', 2),
    ('31977777777', 3),
    ('11966666666', 1),
    ('21955555555', 2);
