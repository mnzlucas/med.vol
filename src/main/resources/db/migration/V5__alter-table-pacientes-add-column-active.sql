ALTER TABLE pacientes
    ADD ativo boolean;
UPDATE pacientes
set ativo = true;
ALTER TABLE pacientes
    ALTER COLUMN ativo SET NOT NULL;
