package med.voll.api.infra;

import org.springframework.validation.FieldError;

public record ValidationErrorDTO(String campo, String mensagem) {
    public ValidationErrorDTO(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}