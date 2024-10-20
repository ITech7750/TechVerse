import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import {createTheme, MantineProvider} from '@mantine/core'
import {
    createBrowserRouter, Navigate,
    RouterProvider,
} from "react-router-dom";
import {Courses} from "./Pages/Courses.jsx";
import {Lecturers} from "./Pages/Lecturers.jsx";
import {Materials} from "./Pages/Materials.jsx";
import {Course} from "./Pages/Course.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        children: [
            {
                path: "/",
                element: <Navigate to="/courses"/>
            },
            {
                path: "/courses",
                element: <Courses/>
            },
            {
                path: "/lecturers",
                element: <Lecturers/>
            },
            {
                path: "/materials",
                element: <Materials/>
            },
            {
                path: "/course/:id",
                element: <Course/>
            },
            {
                path: "*",
                element: <Navigate to="/"/>
            }
        ],
    }
]);

const theme = createTheme({
    colorScheme: 'dark',
    primaryColor: 'violet',
})
ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <MantineProvider theme={theme} defaultColorScheme='dark'>
            <RouterProvider router={router} />
        </MantineProvider>
    </React.StrictMode>,
)
