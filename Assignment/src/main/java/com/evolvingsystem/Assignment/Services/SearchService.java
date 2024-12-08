package com.evolvingsystem.Assignment.Services;

import com.evolvingsystem.Assignment.Entity.PhoneNumber;
import com.evolvingsystem.Assignment.Repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public List search(String areaCode, String state, String category) {
        Specification<PhoneNumber> spec = new Specification<PhoneNumber>() {
            @Override
            public Predicate toPredicate(Root<PhoneNumber> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                if (areaCode != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("areaCode"), areaCode));
                }
                if (state != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("state"), state));
                }
                if (category != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("category"), category));
                }

                return predicate;
            }
        };

        return phoneNumberRepository.findAll(spec);
    }
}
