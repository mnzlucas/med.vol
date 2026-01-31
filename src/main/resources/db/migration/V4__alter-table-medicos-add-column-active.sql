ALTER TABLE medicos
    ADD ativo boolean;
UPDATE medicos
set ativo = true;
ALTER TABLE medicos
    ALTER COLUMN ativo SET NOT NULL;
