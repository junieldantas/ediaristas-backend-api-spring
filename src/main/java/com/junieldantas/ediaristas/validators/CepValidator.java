package com.junieldantas.ediaristas.validators;

import com.junieldantas.ediaristas.models.Diarista;
import com.junieldantas.ediaristas.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CepValidator implements Validator {

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Diarista.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var diarista = (Diarista) target;
        var cep = diarista.getCep();

        try {
            viaCepService.buscarEnderecoPorCep(cep);
        }catch (RuntimeException e){
            errors.rejectValue("cep", null, e.getMessage());
        }
    }
}
