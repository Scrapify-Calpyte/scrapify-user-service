package com.scrapify.scrapifyuser.specification;

import com.scrapify.scrapifyuser.dto.pagination.SearchCriteria;
import com.scrapify.scrapifyuser.util.DateUtil;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

public class BaseSpecification {

    Predicate getPredicate(SearchCriteria criteria, From root,
                           CriteriaQuery<?> query,
                           CriteriaBuilder builder) {
        Predicate predicate = null;
        switch (criteria.getOperation()) {
            case ">":
                predicate = builder.greaterThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
                break;
            case "<":
                predicate = builder.lessThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
                break;
            case ":":
                predicate = getContainsPredicate(criteria, root, builder);
                break;
            case "<>":
                predicate = getDatePredicate(criteria, root, builder);
                break;
            case "::":
                predicate = root.get(criteria.getKey()).in(criteria.getValues());
                break;
            case "=":
                predicate = getEqualsPredicate(criteria, root, builder);
                break;
            default:

        }
        return predicate;
    }

    private Predicate getDatePredicate(SearchCriteria criteria, From root, CriteriaBuilder builder) {
        String startDateStr = criteria.getValue().toString().split("-")[0];
        String endDateStr = criteria.getValue().toString().split("-")[1];
        try {
            return builder.between(root.get(criteria.getKey()), DateUtil.filterToDate(startDateStr), DateUtil.filterToDate(endDateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Predicate getContainsPredicate(SearchCriteria criteria, From root, CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                Expression<String> path = root.get(criteria.getKey());
                Expression<String> lowerCase = builder.lower(path);
                predicate = builder.like(lowerCase , "%" + criteria.getValue().toString().toLowerCase() + "%" );
            }
            return predicate;
        } else {
            Expression<String> filterKeyExp = root.get(criteria.getKey()).as(String.class);
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                filterKeyExp = builder.lower(filterKeyExp);
                 predicate = builder.like(filterKeyExp ,"%" + criteria.getValue().toString().trim().toLowerCase() + "%");
//            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            return predicate;
        }
    }


    private Predicate getEqualsPredicate(SearchCriteria criteria, From root, CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                Expression<String> path = root.get(criteria.getKey());
                Expression<String> lowerCase = builder.lower(path);
                predicate = builder.equal(lowerCase,criteria.getValue().toString());
            }
            return predicate;
        } else {
            Expression<String> filterKeyExp = root.get(criteria.getKey()).as(String.class);
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                filterKeyExp = builder.lower(filterKeyExp);
                predicate = builder.like(filterKeyExp ,"%" + criteria.getValue().toString().trim().toLowerCase() + "%");
//            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            return predicate;
        }
    }

}
