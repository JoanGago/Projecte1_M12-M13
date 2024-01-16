package borsa_joan_aleix.rest;

import org.springframework.stereotype.Component;

import borsa_joan_aleix.models.Oferta;

@Component
public class OfertaModelAssembler {

    public Oferta toModel(Oferta oferta) {
        return oferta;
    }
}
