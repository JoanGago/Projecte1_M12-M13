package borsa_joan_aleix;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import borsa_joan_aleix.models.Empresa;
import borsa_joan_aleix.models.Oferta;
import borsa_joan_aleix.repository.EmpresaRepositori;
import borsa_joan_aleix.repository.OfertaRepositori;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OfertaRepositoriTests {

    @Autowired
    private OfertaRepositori ofertaRepositori;

    @Autowired
    private EmpresaRepositori empresaRepositori;

    @Test
    public void testGuardarOferta() {
        
        Empresa empresa = new Empresa();
        empresa.setNom("Empresa per Oferta");
        empresa.setDescripcio("Descripció de l'empresa per la oferta");
        empresaRepositori.save(empresa);
        Oferta oferta = new Oferta();
        oferta.setTitol("Oferta de Treball");
        oferta.setDescripcio("Descripció de la oferta");
        oferta.setEmpresa(empresa);

        
        Oferta ofertaGuardada = ofertaRepositori.save(oferta);

        
        assertThat(ofertaGuardada.getId()).isNotNull();
        assertThat(ofertaGuardada.getTitol()).isEqualTo("Oferta de Treball");
    }

    @Test
    public void testConsultarOferta() {

        Empresa empresa = new Empresa();
        empresa.setNom("Empresa Consulta");
        empresa.setDescripcio("Descripció per a la consulta");
        empresaRepositori.save(empresa);

        
        Oferta oferta = new Oferta();
        oferta.setTitol("Oferta Consulta");
        oferta.setDescripcio("Descripció per a la consulta");
        oferta.setEmpresa(empresa);
        ofertaRepositori.save(oferta);

        
        Oferta ofertaConsultada = ofertaRepositori.findById(oferta.getId()).orElse(null);

        
        assertThat(ofertaConsultada).isNotNull();
        assertThat(ofertaConsultada.getTitol()).isEqualTo("Oferta Consulta");
    }
    
    
    @Test
    public void testModificarOferta() {
        
        Empresa empresa = new Empresa();
        empresa.setNom("Empresa per a Modificació");
        empresa.setDescripcio("aass");
        empresaRepositori.save(empresa);

        
        Oferta oferta = new Oferta();
        oferta.setTitol("Oferta Original");
        oferta.setDescripcio("Descripció original de l'oferta");
        oferta.setEmpresa(empresa);
        ofertaRepositori.save(oferta);

        
        oferta.setTitol("Oferta Modificada");
        ofertaRepositori.save(oferta);

        
        Oferta ofertaModificada = ofertaRepositori.findById(oferta.getId()).orElse(null);

        
        assertThat(ofertaModificada).isNotNull();
        assertThat(ofertaModificada.getTitol()).isEqualTo("Oferta Modificada");
    }

    @Test
    public void testEliminarOferta() {
        
        Empresa empresa = new Empresa();
        empresa.setNom("Empresa per a Eliminació");
        empresa.setDescripcio("Descripció de l'empresa per a la eliminació");
        empresaRepositori.save(empresa);
        Oferta oferta = new Oferta();
        oferta.setTitol("Oferta per a Eliminació");
        oferta.setDescripcio("Descripció de l'oferta per a la eliminació");
        oferta.setEmpresa(empresa);
        ofertaRepositori.save(oferta);

        
        ofertaRepositori.delete(oferta);

        
        assertThat(ofertaRepositori.findById(oferta.getId())).isEmpty();
    }

    
}
