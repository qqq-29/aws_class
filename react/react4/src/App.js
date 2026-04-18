import { useState } from "react";


function App() {
  return (
    <div className="App">
      <Main/>
    </div>
  );
}

function Main() {
  let[isOpen1, setisOpen1]=useState(false)
  let[isOpen2, setisOpen2]=useState(false)
  
  const showEx = (label) =>{
    if(label==="예제1"){
      setisOpen1(!isOpen1)
      setisOpen2(false)
    }else if(label==="예제2"){
      setisOpen2(!isOpen2)
      setisOpen1(false)
    }
  }

  return (
    <div className="App">
      <h1>메인</h1>
      <div>
        <button onClick={()=>showEx("예제1")}>예제1 : {isOpen1 ? "접기" : "보기"}</button>
        <button onClick={()=>showEx("예제2")}>예제2 : {isOpen2 ? "접기" : "보기"}</button>
      </div>
      {
        isOpen1 ? <Ex1/> : null
      }
      {
        isOpen2 ? <Ex2/> : null
      }

    </div>
  );
}

function Ex1(){
  let[num, SetNum] = useState(0)

  return (
    <div>
        <h1>예제1</h1>
        <button onClick={()=>SetNum(num+1)}>+</button>
        <input type="text" value={num} readOnly={true}/>
        <button onClick={()=>SetNum(num-1)}>-</button>
    </div>
  );
}

//Ex2를 생성해서 Main에서 호출하세요
//Ex2는 input태그와 h1태그로 구성
//input태그에 값을 입력하면 h1태그에 출력

function Ex2(){
  let [input, SetInput] = useState("");

  const inputChange=(e)=>{
    SetInput(e.target.value);
  }

  return(
    <div>
      <h1>예제2</h1>
      <input type="text" onChange={inputChange}/>
      <h2>{input}</h2>
    </div>
  );
}

export default App;
