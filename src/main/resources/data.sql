-- Добавление организации
INSERT INTO `organization` (`university`, `faculty`, `group_name`)
VALUES ('МИФИ', 'ИИКС', 'Б23-514');

-- Добавление пользователя
INSERT INTO `user_table` (`login`, `password`, `email`, `first_name`, `last_name`, `middle_name`, `role`, `is_student_mifi`, `two_factor_auth_enabled`, `organization_id`)
VALUES ('student1', 'strongpassword', 'student1@example.com', 'Иван', 'Иванов', 'Иванович', 'Студент', TRUE, TRUE, 1);


-- Добавление пользователя
INSERT INTO `user_table` (`login`, `password`, `email`, `first_name`, `last_name`, `middle_name`, `role`, `is_student_mifi`, `two_factor_auth_enabled`, `organization_id`)
VALUES ('student2', 'qwerty', 'student2@example.com', 'Петр', 'Петрович', 'Сидоров', 'Студент', TRUE, TRUE, 1);


-- Добавление курса
INSERT INTO `course` (`title`, `description`, `start_date`, `end_date`, `admin_id`)
VALUES ('Математический анализ', 'Курс по математическому анализу для начинающих.', '2021-09-01', '2022-06-30', 1);

-- Добавление форума
INSERT INTO `forum` (`lecture_id`, `title`, `description`)
VALUES (1, 'Форум курса Математический анализ', 'Место для обсуждения лекций и заданий по курсу математического анализа.');

-- Добавление темы на форуме
INSERT INTO `forum_topic` (`forum_id`, `title`, `description`)
VALUES (1, 'Обсуждение пределов', 'Обсуждение вопросов и проблем, связанных с темой пределов функций.');

-- Добавление сообщения на форуме
INSERT INTO `forum_message` (`topic_id`, `user_id`, `reply_id`, `message_text`)
VALUES (1, 1, NULL, 'Как правильно вычислить предел функции x, когда x стремится к нулю?');

-- Добавление участника форума
INSERT INTO `forum_participant` (`forum_id`, `user_id`)
VALUES (1, 1);

-- Добавление файла
INSERT INTO `files` (`file_path`, `file_type`)
VALUES ('/home/itech/Downloads/Classes_2-4.pdf', 'application/pdf');

-- Добавление файла
INSERT INTO `files` (`file_path`, `file_type`)
VALUES ('/home/itech/Downloads/document_5409021021615309527.mp4', 'video/mp4');

-- Добавление конспекта
INSERT INTO `summary` (`title`, `description`)
VALUES ('Конспект лекции 1', 'Детальный конспект первой лекции по введению в математический анализ.');

-- Добавление лекции
INSERT INTO `lecture` (`course_id`, `lecturer_id`, `title`, `description`, `date`, `summary_id`, `forum_id`, `file_id`)
VALUES (1, 1, 'Введение в математический анализ', 'Первая лекция по математическому анализу, введение в пределы.', '2021-09-02 10:00:00', 1, 1, 1);

-- Добавление авторизации
INSERT INTO `authorization` (`user_id`, `token`, `two_factor_enabled`)
VALUES (1, 'abcd1234', TRUE);

-- Добавление токена
INSERT INTO `token` (`login`, `token`, `user_id`)
VALUES ('student1', 'securetoken123', 1);


-- Добавление связи между пользователем, курсом и лекцией
INSERT INTO `course_lectures` (`course_id`, `lecture_id`, `lecture_order`)
VALUES (1, 1, 1);

INSERT INTO `user_course` (`id`,`user_id`, `course_id`)
VALUES (1, 1, 1);