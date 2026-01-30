import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Navbar from './component/nav/Navbar';
import Main from './component/body/Main';
import List from './component/body/List';
import Insert from './component/body/Insert';


function App() {
  return (
    <BrowserRouter>
      <Navbar />
    <Routes>
      <Route path={"/"} exact element={<Main/>}></Route>
      <Route path={"/todo/list"} element={<List/>}></Route>
      <Route path={"/todo/insert"} element={<Insert/>}></Route>
    </Routes>
    </BrowserRouter>
    
  );
}

export default App;
