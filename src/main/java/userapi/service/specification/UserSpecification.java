package userapi.service.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import userapi.dao.entity.UserEntity;
import userapi.model.criteria.UserCriteria;
import userapi.util.PredicateUtil;

import static userapi.constant.CriteriaConstant.AGE;
import static userapi.constant.CriteriaConstant.BIRTH_PLACE;
import static userapi.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class UserSpecification implements Specification<UserEntity> {

    private UserCriteria userCriteria;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(userCriteria.getBirthPlace(),
                        birthPlace -> cb.like(root.get(BIRTH_PLACE), applyLikePattern(birthPlace))
                )
                .addNullSafety(userCriteria.getAgeFrom(),
                        ageFrom -> cb.greaterThanOrEqualTo(root.get(AGE), ageFrom))
                .addNullSafety(userCriteria.getAgeTo(),
                        ageTo -> cb.lessThanOrEqualTo(root.get(AGE), ageTo))
                .build();
        return cb.and(predicates);
    }


}
