-- V2: Migrations para adicionar a coluna de RANK na tabela de cadastros.

ALTER TABLE tb_cadastro_de_ninjas
ADD COlUMN rank VARCHAR(255);