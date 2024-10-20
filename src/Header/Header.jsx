import { useState } from 'react';
import {Container, Group, Burger, Image} from '@mantine/core';
import { useDisclosure } from '@mantine/hooks';
import { MantineLogo } from '@mantinex/mantine-logo';
import classes from './Header.module.css';
import {Link} from "react-router-dom";

const links = [
    { link: '/courses', label: 'Курсы' },
    { link: '/lecturers', label: 'Лекторы' },
    { link: '/materials', label: 'Материалы' },
];

export function Header() {
    const [opened, { toggle }] = useDisclosure(false);
    const [active, setActive] = useState(links[0].link);

    const items = links.map((link) => (
        <Link
            key={link.label}
            to={link.link}
            className={classes.link}
            data-active={active === link.link || undefined}
            onClick={(event) => {
                setActive(link.link);
            }}
        >
            {link.label}
        </Link>
    ));

    return (
        <header className={classes.header}>
            <Container size="md" className={classes.inner}>
                <Link to=''>
                    <Image src="../../logo-mini-without.png" alt="logo" h={120}/>
                </Link>
                <Group gap={5} visibleFrom="xs">
                    {items}
                </Group>

                <Burger opened={opened} onClick={toggle} hiddenFrom="xs" size="sm" />
            </Container>
        </header>
    );
}
