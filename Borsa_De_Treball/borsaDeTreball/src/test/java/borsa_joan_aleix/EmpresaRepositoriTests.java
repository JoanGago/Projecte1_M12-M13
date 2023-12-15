package borsa_joan_aleix;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import borsa_joan_aleix.models.Empresa;
import borsa_joan_aleix.repository.EmpresaRepositori;

@DataJpaTest
public class EmpresaRepositoriTests {

    @Autowired
    private EmpresaRepositori empresaRepositori;

    @Test
    public void testGuardarEmpresa() {
        
        Empresa empresa = new Empresa();
        empresa.setNom("Empresa de Prova");
        empresa.setDescripcio("Dasasasasa");

        
        Empresa empresaGuardada = empresaRepositori.save(empresa);

        
        assertThat(empresaGuardada.getId()).isNotNull();
        assertThat(empresaGuardada.getNom()).isEqualTo("Empresa de Prova");
    }

    @Test
    public void testConsultarEmpresa() {
        
        Empresa empresa = new Empresa();
        empresa.setNom("Empresa Consulta");
        empresa.setDescripcio("asasas");
        empresaRepositori.save(empresa);

        
        Empresa empresaConsultada = empresaRepositori.findById(empresa.getId()).orElse(null);

        
        assertThat(empresaConsultada).isNotNull();
        assertThat(empresaConsultada.getNom()).isEqualTo("Empresa Consulta");
    }

    @Test
    public void testModificarEmpresa() {
        
        Empresa empresa = new Empresa();
        empresa.setNom("Empresa Original");
        empresa.setDescripcio("asasasad");
        empresaRepositori.save(empresa);

        
        empresa.setNom("Empresa Modificada");
        empresaRepositori.save(empresa);

        
        Empresa empresaModificada = empresaRepositori.findById(empresa.getId()).orElse(null);

        
        assertThat(empresaModificada).isNotNull();
        assertThat(empresaModificada.getNom()).isEqualTo("Empresa Modificada");
    }

    @Test
    public void testEliminarEmpresa() {
        
        Empresa empresa = new Empresa();
        empresa.setNom("Empresa per a eliminació");
        empresa.setDescripcio("asasasa");
        empresaRepositori.save(empresa);

        
        empresaRepositori.delete(empresa);

        
        assertThat(empresaRepositori.findById(empresa.getId())).isEmpty();
    }
}