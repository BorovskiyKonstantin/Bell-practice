package ru.bellintegrator.practice.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Organization> findAll(String name, String inn, Boolean isActive) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> query = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> root = query.from(Organization.class);

        //Условия запроса
        List<Predicate> predicates = new ArrayList<>();
        Predicate findName = criteriaBuilder.equal(root.get("name"), name);
        predicates.add(findName);
        if (inn != null) {
            Predicate findInn = criteriaBuilder.equal(root.get("inn"), inn);
            predicates.add(findInn);
        }
        if (isActive != null) {
            Predicate findIsActive = criteriaBuilder.equal(root.get("isActive"), isActive);
            predicates.add(findIsActive);
        }
        query.select(root);
        query.where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(query).getResultList();
    }

    @Override
    public Organization findById(Integer id) {
        return Optional.ofNullable(em.find(Organization.class, id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Организация не найдена"));
    }

    @Override
    public void save(Organization org) {
        if (org.getId() == null){
            em.persist(org);
        }
        else {
            em.merge(org);
        }
    }


}
