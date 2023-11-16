package borsa_joan_aleix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import borsa_joan_aleix.models.Empresa;
import borsa_joan_aleix.models.Oferta;

@Repository
public interface OfertaRepositori extends JpaRepository<Oferta, Long> {
    List<Oferta> findByEmpresa(Empresa empresa);
}
