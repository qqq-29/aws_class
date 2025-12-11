const list = document.querySelector(".list");
const input = document.querySelector("#input");
const subbtn = document.querySelector("#subbtn");

form.addEventListener("submit", (e) => e.preventDefault());
document.addEventListener("DOMContentLoaded", function (){
	let objs = localStorage.getItem("list");

		const initData = [
			{ text: "오늘도 열심히 수업 듣자.\n오늘도 화이팅!" },
			{ text: "오늘은 자바 과제 해야겠다" },
		];

		localStorage.setItem("list", JSON.stringify(initData));
		
		objs = localStorage.getItem("list");
	

	objs = JSON.parse(objs);

	if (objs.length === 0){
		list.innerHTML = "<h1>등록된 text이 없습니다.</h1>";
		return;
	}

	objs.forEach(obj =>{
		list.innerHTML += createItem(obj.text);
	});
});


function createItem(text){
	return `
		<li>
		<div class=" mt-1 p-3 bg-light text-black rounded border-radius: 25px;" white-space: pre-line;>
				<span class="title;" data-name="${text}">${text}</span>
				<p class="btn-del">삭제</p>
				</div>
		</li>
	`;
}

subbtn.addEventListener("click", function (){
	let text = input.value.trim();
	if (text=="") {
		alert("공백으로 추가할수 없습니다")
		return;
	}
	if (!text) {
		return;
	}

	if (list.querySelectorAll("li").length > 0){
		list.innerHTML += createItem(text);
	} else {
		list.innerHTML = createItem(text);
	}

	input.value = "";
	saveLS();
});


list.addEventListener("click", function (e) {
	let el = e.target;

	if (el.classList.contains("btn-del")){
		el.parentElement.remove();

		if (list.querySelectorAll("li").length === 0){
			list.innerHTML = "<h1>등록된 text이 없습니다.</h1>";
		}

		saveLS();
	}
});


function saveLS() {
	let items = list.querySelectorAll("li");

	let itemList = [];
	items.forEach(item => {
		let itemTitle = item.querySelector(".title");
		let text = itemTitle.dataset.name;  
		itemList.push(memo);
	});

	localStorage.setItem("list", JSON.stringify(itemList));
}
