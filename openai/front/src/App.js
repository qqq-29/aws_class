import Ask from "./Ask";
import List from "./List";
import Main from "./Main";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Translate from "./Translate";
import AdCopy from "./Adcopy";
import Summarize from "./Summarize";
import RagChatBot from "./RagChatBot";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Main/>} />
        <Route path="/list" element={<List/>} />
        <Route path="/ask" element={<Ask/>} />
        <Route path="/translate" element={<Translate/>} />
        <Route path="/ad-copy" element={<AdCopy/>} />
        <Route path="/summarize" element={<Summarize/>} />
        <Route path="/rag-chatbot" element={<RagChatBot/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;