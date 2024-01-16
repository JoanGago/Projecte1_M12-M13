package borsa_joan_aleix.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String descripcio;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Oferta> ofertes = new ArrayList<>();

    public Empresa() {
    }

    public Empresa(Long id, String nom, String descripcio, List<Oferta> ofertes) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.ofertes = ofertes;
    }

    public List<Oferta> getOfertes() {
        return ofertes;
    }

    public void setOfertes(List<Oferta> ofertes) {
        this.ofertes = ofertes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void agregarOferta(Oferta oferta) {
        ofertes.add(oferta);
        oferta.setEmpresa(this);
    }

    public void removerOferta(Oferta oferta) {
        ofertes.remove(oferta);
        oferta.setEmpresa(null);
    }
}
