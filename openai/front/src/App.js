import Ask from "./Ask";
import List from "./List";
import Main from "./Main";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Translate from "./Translate";
import AdCopy from "./Adcopy";
import Summarize from "./Summarize";
import RagChatBot from "./RagChatBot";
import ImageText from "./ImageText"


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
        {/*
        ImageText 컴포넌트 생성
        파일선택(이미지), 질문 요소를 추가
        선택한 파일과 질문을 서버(부트)에 전송
        */}
        <Route path="/image-text" element={<ImageText/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;