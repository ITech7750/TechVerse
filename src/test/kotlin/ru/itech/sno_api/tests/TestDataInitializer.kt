package ru.itech.sno_api.tests

import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.itech.sno_api.entity.*
import ru.itech.sno_api.repository.*
import java.time.LocalDate

@Configuration
open class TestDataInitializer {

    @Bean
    @Transactional
    open fun initData(
        organizationRepository: OrganizationRepository,
        userRepository: UserRepository,
        courseRepository: CourseRepository,
        forumRepository: ForumRepository,
        forumTopicRepository: ForumTopicRepository,
        forumMessageRepository: ForumMessageRepository,
        forumParticipantRepository: ForumParticipantRepository,
        fileRepository: FilesRepository,
        summaryRepository: SummaryRepository,
        lectureRepository: LectureRepository,
        courseLecturesRepository: CourseLectureRepository,
        userCourseRepository: UserCourseRepository

    ): CommandLineRunner {
        return CommandLineRunner {
            insertTestData(
                organizationRepository,
                userRepository,
                courseRepository,
                forumRepository,
                forumTopicRepository,
                forumMessageRepository,
                forumParticipantRepository,
                fileRepository,
                summaryRepository,
                lectureRepository,
                courseLecturesRepository,
                userCourseRepository,
            )
        }
    }

    private fun insertTestData(
        organizationRepository: OrganizationRepository,
        userRepository: UserRepository,
        courseRepository: CourseRepository,
        forumRepository: ForumRepository,
        forumTopicRepository: ForumTopicRepository,
        forumMessageRepository: ForumMessageRepository,
        forumParticipantRepository: ForumParticipantRepository,
        fileRepository: FilesRepository,
        summaryRepository: SummaryRepository,
        lectureRepository: LectureRepository,
        courseLecturesRepository: CourseLectureRepository,
        userCourseRepository: UserCourseRepository

    ) {
        // Добавление организации
        val organization = organizationRepository.save(
            OrganizationEntity(
                organizationId = 1,
                isSoftDeleted = true,
                university = "МИФИ",
                faculty = "ИИКС",
                groupName = "Б23-514"
            )
        )

        // Добавление пользователей
        val user1 = userRepository.save(
            UserEntity(
                userId = 1,
                login = "student1",
                password = "strongpassword",
                email = "student1@example.com",
                firstName = "Иван",
                lastName = "Иванов",
                middleName = "Иванович",
                role = "Студент",
                isStudentMifi = true,
                twoFactorAuthEnabled = true,
                organization = organization
            )
        )
        val user2 = userRepository.save(
            UserEntity(
                userId = 2,
                login = "student2",
                password = "qwerty",
                email = "student2@example.com",
                firstName = "Петр",
                lastName = "Петрович",
                middleName = "Сидоров",
                role = "Студент",
                isStudentMifi = true,
                twoFactorAuthEnabled = true,
                organization = organization
            )
        )

        // Добавление курса
        val course = courseRepository.save(
            CourseEntity(
                courseId = 1,
                title = "Математический анализ",
                description = "Курс по математическому анализу для начинающих.",
                startDate = LocalDate.of(2021, 9, 1),
                endDate = LocalDate.of(2022, 6, 30),
                admin = user1
            )
        )

        // Добавление файла
        val file1 = fileRepository.save(
            FilesEntity(
                fileId = 1,
                filePath = "/home/itech/Downloads/Classes_2-4.pdf",
                fileType = "application/pdf"
            )
        )
        val file2 = fileRepository.save(
            FilesEntity(
                fileId = 2,
                filePath = "/home/itech/Downloads/document_5409021021615309527.mp4",
                fileType = "video/mp4"
            )
        )

        // Добавление конспекта
        val summary = summaryRepository.save(
            SummaryEntity(
                summaryId = 1,
                title = "Конспект лекции 1",
                description = "Детальный конспект первой лекции по введению в математический анализ."
            )
        )

        // Добавление лекции
        val lecture = lectureRepository.save(
            LectureEntity(
                lectureId = 1,
                course = course,
                lecturer = user1,
                title = "Введение в математический анализ",
                description = "Первая лекция по математическому анализу, введение в пределы.",
                date = LocalDate.of(2021, 9, 2),
                summary = summary,
                forum = null,
                file = file1
            )
        )

        // Добавление форума
        val forum = forumRepository.save(
            ForumEntity(
                lecture = lecture,
                title = "Форум курса Математический анализ",
                description = "Место для обсуждения лекций и заданий по курсу математического анализа."
            )
        )

        // Добавление темы на форуме
        val forumTopic = forumTopicRepository.save(
            ForumTopicEntity(
                forum = forum,
                title = "Обсуждение пределов",
                description = "Обсуждение вопросов и проблем, связанных с темой пределов функций."
            )
        )

        // Добавление сообщения на форуме
        forumMessageRepository.save(
            ForumMessageEntity(
                topic = forumTopic,
                user = user1,
                messageText = "Как правильно вычислить предел функции x, когда x стремится к нулю?"
            )
        )

        // Добавление участника форума
        forumParticipantRepository.save(
            ForumParticipantEntity(
                forum = forum,
                user = user1
            )
        )




        // Добавление связи между курсом и лекцией
        courseLecturesRepository.save(
            CourseLectureEntity(
                id = 1,
                course = course,
                lecture = lecture,
                lectureOrder = 1
            )
        )

        // Добавление связи между пользователем и курсом
        userCourseRepository.save(
            UserCourseEntity(
                id = 1,
                user = user1,
                course = course
            )
        )
    }
}
