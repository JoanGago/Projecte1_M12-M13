package borsa_joan_aleix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import borsa_joan_aleix.models.Empresa;
import borsa_joan_aleix.models.Oferta;
import borsa_joan_aleix.repository.EmpresaRepositori;
import borsa_joan_aleix.repository.OfertaRepositori;

@RestController
@RequestMapping("/ofertes")
public class ControladorOferta {

    private final EmpresaRepositori empresaRepositori;
    private final OfertaRepositori ofertaRepositori;

    @Autowired
    public ControladorOferta(EmpresaRepositori empresaRepositori, OfertaRepositori ofertaRepositori) {
        this.empresaRepositori = empresaRepositori;
        this.ofertaRepositori = ofertaRepositori;
    }

    @PostMapping
    public Oferta crearOferta(@RequestBody Oferta oferta) {
        return ofertaRepositori.save(oferta);
    }

    @GetMapping
    public List<Oferta> obtenerOfertas() {
        return ofertaRepositori.findAll();
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<List<Oferta>> obtenerOfertasPorEmpresa(@PathVariable Long id) {
        java.util.Optional<Empresa> empresaOptional = empresaRepositori.findById(id);
        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            List<Oferta> ofertas = ofertaRepositori.findByEmpresa(empresa);
            return ResponseEntity.ok(ofertas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oferta> actualizarOferta(@PathVariable Long id, @RequestBody Oferta ofertaActualizada) {
        Oferta oferta = ofertaRepositori.findById(id).orElse(null);
        if (oferta != null) {
            oferta.setTitol(ofertaActualizada.getTitol());
            oferta.setDescripcio(ofertaActualizada.getDescripcio());
            Oferta ofertaGuardada = ofertaRepositori.save(oferta);
            return ResponseEntity.ok(ofertaGuardada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOferta(@PathVariable Long id) {
        Oferta oferta = ofertaRepositori.findById(id).orElse(null);
        if (oferta != null) {
            ofertaRepositori.delete(oferta);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
