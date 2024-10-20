import { useState } from 'react'
import {Button, Center, Container} from "@mantine/core";
// core styles are required for all packages
import '@mantine/core/styles.css';
import {Header} from "./Header/Header.jsx";
import {Outlet} from "react-router-dom";
// import '@mantine/dates/styles.css';
// import '@mantine/dropzone/styles.css';
// import '@mantine/code-highlight/styles.css';
// ...
function App() {
  return (
      <>
          <Header></Header>
          <Container size="md">
              <Outlet/>
          </Container>
      </>

  )
}

export default App
