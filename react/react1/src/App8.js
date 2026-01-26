
function App8(){
	//객체 배열을 이용하여 화면 구성 예제(내일 할 예제)
	const list = [
		{			name : "홍길동",			age : 21			},
		{			name : "둘리",				age : 31			},
		{			name : "고길동",			age : 41			},
	];
	//객체에서 이름만 추출. 객체 배열 => 문자열 배열
	let list2 = list.map(item=> {
		return item.name;
	});
	//map은 기존 []을 다른형태의 []로 변환
	//map은 return 값들을 모아서 새로운 형태의 []로 만듬
	console.log(list2);
	//filter는 기본[]에서 일치하는 값들을 걸러서 같은 형태의 []을 가져옴

	return (
		<div>
			<ul>
				<li>
					<span>홍길동 : </span>
					<span>21</span>
				</li>
				{
				list2.map((text)=>{
					<li>{text}</li>;
				})
				}
			</ul>
		</div>
	)
}

export default App8;