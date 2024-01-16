package borsa_joan_aleix.rest;

import org.springframework.stereotype.Component;

import borsa_joan_aleix.models.Empresa;

@Component
public class EmpresaModelAssembler {

    public Empresa toModel(Empresa empresa) {
        return empresa;
    }
}
