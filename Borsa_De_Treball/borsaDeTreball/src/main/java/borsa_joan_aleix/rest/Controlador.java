package borsa_joan_aleix.rest;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import borsa_joan_aleix.models.Empresa;
import borsa_joan_aleix.repository.EmpresaRepositori;

@RestController
@RequestMapping("/")
public class Controlador {

    private final EmpresaRepositori empresaRepositori;

    @Autowired
    public Controlador(EmpresaRepositori empresaRepositori) {
        this.empresaRepositori = empresaRepositori;
    }

    @GetMapping("/empresas")
    public List<Empresa> todasLasEmpresas() {
        return empresaRepositori.findAll();
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<Empresa> consultarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRepositori.findById(id);
        if (empresa.isPresent()) {
            return ResponseEntity.ok(empresa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/empresas")
    public ResponseEntity<Empresa> afegirEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaRepositori.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Empresa> modificarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        if (empresaRepositori.existsById(id)) {
            empresa.setId(id);
            Empresa empresaModificada = empresaRepositori.save(empresa);
            return ResponseEntity.ok(empresaModificada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        if (empresaRepositori.existsById(id)) {
            empresaRepositori.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
