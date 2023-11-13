package borsa_joan_aleix.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import borsa_joan_aleix.models.Empresa;
import borsa_joan_aleix.models.Oferta;
import borsa_joan_aleix.repository.EmpresaRepositori;


@RestController
@RequestMapping("/ofertes")
public class ControladorOferta {
    @Autowired
    private OfertaRepositori ofertaRepositori;
    
    @Autowired
    private EmpresaRepositori empresaRepositori;
    
    @PostMapping
    public Oferta crearOferta(@RequestBody Oferta oferta) {
        return ofertaRepositori.save(oferta);
    }
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Oferta> actualitzarOferta(@PathVariable Long id, @RequestBody Oferta ofertaActualitzada) {
        Oferta oferta = ofertaRepositori.findById(id).orElse(null);
        if (oferta != null) {
            oferta.setTitol(ofertaActualitzada.getTitol());

            Oferta ofertaGuardada = ofertaRepositori.save(oferta);
            return ResponseEntity.ok(ofertaGuardada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    
}