package ru.itech.sno_api.core.domain

import org.springframework.data.jpa.domain.Specification
import ru.itech.sno_api.entity.LectureEntity
import jakarta.persistence.criteria.Predicate
import ru.itech.sno_api.entity.UserEntity
import java.time.LocalDate
import java.time.ZoneId

class LectureSpecification(
    private val title: String?,
    private val lecturerId: Long?,
    private val startDate: LocalDate?,
    private val endDate: LocalDate?
) : Specification<LectureEntity> {

    override fun toPredicate(
        root: jakarta.persistence.criteria.Root<LectureEntity>,
        query: jakarta.persistence.criteria.CriteriaQuery<*>,
        criteriaBuilder: jakarta.persistence.criteria.CriteriaBuilder
    ): Predicate? {
        val predicates = mutableListOf<Predicate>()

        title?.let {
            predicates.add(criteriaBuilder.like(root.get("title"), "%$it%"))
        }

        lecturerId?.let {
            predicates.add(criteriaBuilder.equal(root.get<UserEntity>("lecturer").get<Long>("id"), it))
        }

        startDate?.let {
            val startDateAsDate = java.util.Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant())
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), startDateAsDate))
        }

        endDate?.let {
            val endDateAsDate = java.util.Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant())
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), endDateAsDate))
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }
}
