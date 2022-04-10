const input = document.getElementById("inputTextCep")

input.addEventListener('keydown', ()=>{
	fetch(`viacep.com.br/ws/${input.value}/json/`, (res)=>{
		console.log(res);
	})
});