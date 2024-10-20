import {Fragment} from "react";
import {Card, Container, Group, Image, Space, Text, Title} from "@mantine/core";
import {Link} from "react-router-dom";

const mockCourses = [
    {
        id: 1,
        name: "Course 1",
        image: "https://via.placeholder.com/150",
        linkToCourse: "/course/1"
    },
    {
        id: 2,
        name: "Course 2",
        image: "https://via.placeholder.com/150",
        linkToCourse: "/course/2"
    },
    {
        id: 3,
        name: "Course 3",
        image: "https://via.placeholder.com/150",
        linkToCourse: "/course/3"
    },
]

export function Courses() {
    return (
        <>
            <Title>Courses</Title>
            <Space h="md"/>
            {mockCourses.map(course => (
                <Fragment key={course.id}>
                    <Link to={course.linkToCourse}>
                        <Card withBorder shadow="sm" p="lg" key={course.id}>
                            <Group align="start">
                                <Image src={course.image} alt={course.name}/>
                                <Text weight={500} size="lg" mt="md">
                                    {course.name}
                                </Text>
                            </Group>
                        </Card>
                    </Link>
                    <Space h="md"/>
                </Fragment>
            ))}
        </>
    )
}
