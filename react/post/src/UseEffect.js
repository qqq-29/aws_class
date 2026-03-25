import { useEffect, useState } from "react";

//UseEffect 예제
function UseEffect() {

  let [num, setNum] = useState(1);
  let [num2, setNum2] = useState(1);

  // const click1 = (e) =>{
  //   setNum(num-1)
  // }

  //   const click2 = (e) =>{
  //   setNum(num+1)
  // }

  //의존성 배열 생략 => 랜더링 될때마다 실행
  useEffect(()=>{
    console.log("useEffect 함수 실행. 랜더링 될때마다");
  });

  //의존성 배열 : 빈배열=> 처음 랜더링만 실행
  useEffect(()=>{
    console.log("useEffect 함수 실행. 처음만");
  }, []);

  //의존성 배열 : 변수=> 변수가 바뀔때마다 실행
    useEffect(()=>{
    console.log("useEffect 함수 실행. num이 바뀔때만 실행");
    //setNum(num+1); //주석을 해제하면 무한루프 발생
  }, [num]);

  return (
    <div>
      <button onClick={(e)=>{setNum(num-1)}}>-</button>
      <span style={{padding : "0 10px"}}>{num}</span>
      <button onClick={(e)=>{setNum(num+1)}}>+</button>
      <hr/>
      <span>{num2}</span>
      <button onClick={()=>setNum2(num2 + 1)}>+</button>
    </div>
  );
}

export default UseEffect;
