const input = document.getElementsByClassName("inputTextCep")[0]
const button = document.getElementById("searchCep")
button.addEventListener('click', ()=>{
	fetch(`viacep.com.br/ws/${input.value}/json/`, (res)=>{
		console.log(res);
	})
});