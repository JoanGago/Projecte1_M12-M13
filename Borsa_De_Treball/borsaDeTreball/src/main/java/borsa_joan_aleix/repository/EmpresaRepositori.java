package borsa_joan_aleix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import borsa_joan_aleix.models.Empresa;

public interface EmpresaRepositori extends JpaRepository<Empresa, Long> {

}
