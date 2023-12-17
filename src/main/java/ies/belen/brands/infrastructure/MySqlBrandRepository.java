package ies.belen.brands.infrastructure;

import ies.belen.brands.domain.BrandRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import ies.belen.brands.domain.Brand;

@RequestScoped
@Transactional
public class MySqlBrandRepository implements BrandRepository {

    @Inject
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Brand> getAll() {
        var query = "SELECT b FROM Brand b";
        return em.createQuery(query, Brand.class)
                .getResultList();
    }

    @Override
    public Optional<Brand> save(Brand brand) {
        em.persist(brand);
        em.flush();
        return Optional.ofNullable(brand);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        var query = "SELECT b FROM Brand b WHERE b.name = :name";
        return em.createQuery(query, Brand.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.ofNullable(em.find(Brand.class, id));
    }

    @Override
    public void update(Brand brand) {
        em.merge(brand);
    }

    @Override
    public void delete(Brand brand) {
        em.remove(brand);
    }

    @Override
    public void deleteById(Long id) {
        findById(id)
                .ifPresent(brand -> em.remove(brand));
    }

}
