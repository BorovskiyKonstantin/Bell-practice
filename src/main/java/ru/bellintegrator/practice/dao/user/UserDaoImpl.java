package ru.bellintegrator.practice.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll(Integer officeId, String firstName, String lastName, String middleName, String position,
                              String docCode, String citizenshipCode) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);

        //Условия запроса
        List<Predicate> predicates = new ArrayList<>();
        if (firstName != null){
            predicates.add(criteriaBuilder.equal(userRoot.get("firstName"), firstName));
        }
        if (lastName != null){
            predicates.add(criteriaBuilder.equal(userRoot.get("lastName"), lastName));
        }
        if (middleName != null){
            predicates.add(criteriaBuilder.equal(userRoot.get("middleName"), middleName));
        }
        if (position != null){
            predicates.add(criteriaBuilder.equal(userRoot.get("position"), position));
        }
        if (docCode != null){
            predicates.add(criteriaBuilder.equal(userRoot.get("document").get("docType").get("code"), docCode));
        }
        if (citizenshipCode != null){
            predicates.add(criteriaBuilder.equal(userRoot.get("country").get("code"), citizenshipCode));
        }

        query.select(userRoot);
        query.where(predicates.toArray(new Predicate[]{}));
        List<User> resultList = em.createQuery(query).getResultList();

        return resultList;
    }

    @Override
    public User loadById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }
}
