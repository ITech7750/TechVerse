import {Card, Group, Space, Title, Text, Image} from "@mantine/core";
import {useParams} from "react-router-dom";
import {Fragment} from "react";

const mockCourse = {
    id: 1,
    name: "Course",
    image: "https://via.placeholder.com/150",
    lecturer: "Lecturer",
    lectures: [
        {
            id: 1,
            name: "Lecture 1",
            video: "https://www.youtube.com/watch?v=6IwUl-4o5jI"
        },
        {
            id: 2,
            name: "Lecture 2",
            video: "https://www.youtube.com/watch?v=6IwUl-4o5jI"
        },
        {
            id: 3,
            name: "Lecture 3",
            video: "https://www.youtube.com/watch?v=6IwUl-4o5jI"
        }
    ],
    materials: [
        {
            id: 1,
            name: "Material 1",
            link: "https://www.youtube.com/watch?v=6IwUl-4o5jI"
        },
        {
            id: 2,
            name: "Material 2",
            link: "https://www.youtube.com/watch?v=6IwUl-4o5jI"
        },
        {
            id: 3,
            name: "Material 3",
            link: "https://www.youtube.com/watch?v=6IwUl-4o5jI"
        }
    ]
}
export function Course() {
    const {id: courseId} = useParams()
    return (
        <>
            <Title>{mockCourse.name + " " + courseId}</Title>
            <Space h="md"/>
            <Title order={5}>Lecturer: {mockCourse.lecturer + " " + courseId}</Title>
            <Space h="md"/>
            {mockCourse.lectures.map(lecture => (
                <Fragment key={lecture.id}>
                    <Card withBorder shadow="sm" p="lg" key={lecture.id}>
                        <Group align="start">
                            <Image src={mockCourse.image} alt={mockCourse.name}/>
                            <Text weight={500} size="lg" mt="md">
                                {lecture.name}
                            </Text>
                        </Group>
                    </Card>
                    <Space h="md"/>
                </Fragment>
            ))}
            {mockCourse.materials.map(material => (
                <Fragment key={material.id}>
                    <a href={material.link}>
                        {material.name}
                    </a>
                    <Space h="md"/>
                </Fragment>
            ))}
        </>
    )
}

