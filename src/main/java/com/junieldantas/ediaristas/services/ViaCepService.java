package com.junieldantas.ediaristas.services;

import com.junieldantas.ediaristas.dtos.ViaCepResponse;
import com.junieldantas.ediaristas.exceptions.CepInvalidoException;
import com.junieldantas.ediaristas.exceptions.CepNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        var url = "https://viacep.com.br/ws/" + cep + "/json/";

        var clienteHttp = new RestTemplate();
        ResponseEntity<ViaCepResponse> response;
        try {
            response = clienteHttp.getForEntity(url, ViaCepResponse.class);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new CepInvalidoException("Cep Inválido");
        }

        if (response.getBody().getCep() == null) {
            throw new CepNaoEncontradoException("Cep não encontrado");
        }

        return response.getBody();
    }
}
