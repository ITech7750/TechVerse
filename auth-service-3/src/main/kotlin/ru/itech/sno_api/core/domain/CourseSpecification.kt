package ru.itech.sno_api.core.domain

import org.springframework.data.jpa.domain.Specification
import ru.itech.sno_api.entity.CourseEntity
import jakarta.persistence.criteria.Predicate
import java.time.LocalDate

class CourseSpecification(
    private val title: String?,
    private val description: String?,
    private val startDate: LocalDate?,
    private val endDate: LocalDate?
) : Specification<CourseEntity> {

    override fun toPredicate(
        root: jakarta.persistence.criteria.Root<CourseEntity>,
        query: jakarta.persistence.criteria.CriteriaQuery<*>,
        criteriaBuilder: jakarta.persistence.criteria.CriteriaBuilder
    ): Predicate? {
        val predicates = mutableListOf<Predicate>()

        title?.let {
            predicates.add(criteriaBuilder.like(root.get("title"), "%$it%"))
        }

        description?.let {
            predicates.add(criteriaBuilder.like(root.get("description"), "%$it%"))
        }

        startDate?.let {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), it))
        }

        endDate?.let {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), it))
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }
}
