import './ChatBot.css'
import { Link } from 'react-router-dom';
import React, { useState } from 'react';
function ChatBot(){
	const [text, setText] = useState('');
  const [reply, setReply] = useState('');
	const [form, setForm] = useState({msg:'', name:''})
	const [chatList, setChatList] = useState([])
	const chatBody = document.querySelector('#chatBody')
	const inputChange = (e) =>{
		// 1. 만약 키보드 입력 이벤트라면 (onChange)
    if (e.type === 'change') {
      const { name, value } = e.target;
      setForm({ ...form, [name]: value });
    } 
    
    // 2. 만약 전송 이벤트라면 (onSubmit)
    else if (e.type === 'submit') {
      e.preventDefault();
      
      if (form.msg.trim() === '') {
        alert("채팅을 입력하세요");
        return;
      }
		// const chatBody = document.querySelector('#chatBody');
    //   if(chatBody) {
    //     chatBody.innerHTML += `<div class="chat-me"><div class="chat-content">${form.msg}</div></div>`;
    //   }

		sendDataPost('/api/v1/chat', {"Content-Type" : "application/json"}, form)
		// 입력을 비움
		setForm({...form, msg : ''})
		}
	}
	async function sendDataPost(url, headers, data){
		try{	
			setChatList(list=>[...list, {className : 'chat-me', text : form.msg}])
			const response = await fetch(url, {
				method : 'post',
				headers : headers,
				body : JSON.stringify(data)
			})
			if(!response.ok){
				return
			}
			const result = await response.json()

			setChatList(list=>[...list, {className : 'chat-bot', text : result.answer}])

			// // 입력한 내용을 chat-me에 추가
			// chatBody.innerHTML += `<div class="chat-me"><div class="chat-content">${result.msg}</div></div>`;
			// // 서버에서 보낸 내용을 chat-bot에 추가
			// chatBody.innerHTML += `<div class="chat-bot"><div class="chat-content">${result.msg}</div></div>`;
			console.log(result.msg)
		}catch(e){
			console.error(e)
		}
		return
	}
	
  // const onSend = async (e) => {
  //   e.preventDefault();
    
  //   const formData = new FormData();
  //   formData.append('msg', text);

  //   const res = await fetch(`api/v1/chatbot`, {
  //     method: 'POST',
  //     body: formData
  //   });
  //   const data = await res.json();
    
  //   setReply(data.msg);
  // };
	return(
		<div className='chat-container'>
			{/* 메인페이지로 이동하는 링크 추가 */}
			<Link to="/">메인으로 돌아가기</Link>
			<br></br>
			<div className='chat-body' id="chatBody">
				{
					chatList.map((chat, index)=>{
						return(
							<div className={chat.className} key={index}>
								<div className="chat-content">{chat.text}</div>
							</div>
						)
					})
				}
				{/* <div className='chat-me'>
					<div className='chat-content'>dddddd</div>
				</div>
				<div className='chat-bot'>
					<div className='chat-content'>aaaaaa</div>
				</div>
				<div className='chat-bot'>
					<div className='chat-content'>aaaaaa</div>
				</div>
				<div className='chat-me'>
					<div className='chat-content'>dddddd</div>
				</div>
				<div className='chat-bot'>
					<div className='chat-content'>aaaaaa</div>
				</div> */}
			</div>
			
			{/* <form onSubmit={onSend} className='chat-footer'> */}
			<form className='chat-footer' onSubmit={inputChange}>
        {/* <input type='text' name ="msg" className="chat-input" value={text} onChange={(e) => setText(e.target.value)} /> */}
				<input type='text' name ="msg" className="chat-input" value={form.msg} onChange={inputChange} />
        <button type='submit' className='btn btn-chat-submit'>전송</button>
      </form>
		</div>
	)
}

export default ChatBot