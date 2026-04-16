//일반 함수들
export const sendData = async(params, callbackFunc)=>{
		console.log(params)
		try{
		// fetch("url")
		// .then(res=> res.json())//성공하면
		// .then(res=> console.log(res))
		// .catch()
		// .finally()

			//URLSearchParams는 객체 안에 있는 변수들을 다음과 같이 만들어줌
			//변수=값&변수=값&변수=값 ...
			//객체={변수:값,변수:값}
			const queryString = new URLSearchParams(params).toString()
			const response = await fetch("/api/v1/ai/ask?"+queryString)
			
			if(!response.ok){
				return
			}
			const result = await response.json()
			//콜백 함수가 있으면 마지막에 콜백함수 실행
			if(callbackFunc)
				callbackFunc(result)
		}catch(e){
			console.error(e)
		}
	}

export const sendData2 = async(url, params, type, callbackFunc)=>{
			console.log(params)
			try{
				const queryString = new URLSearchParams(params).toString()
				const response = await fetch(`${url}?${queryString}`)
				
				if(!response.ok){
					return
				}
				const result = type === 'json' ? await response.json() : await response.text()
				//콜백 함수가 있으면 마지막에 콜백함수 실행
				if(callbackFunc)
					callbackFunc(result)
			}catch(e){
				console.error(e)
			}
		}

export const sendData3 = async(url, params, type, callbackFunc)=>{
			try{
				const queryString = new URLSearchParams(params).toString()
				const response = await fetch(`${url}?${queryString}`)
				if(!response.ok){
					return
				}
				
				const result = type === 'json' ? await response.json() : await response.text()
				if(callbackFunc)
					callbackFunc(result)
	}catch(e){
		console.error(e)
	}
}
