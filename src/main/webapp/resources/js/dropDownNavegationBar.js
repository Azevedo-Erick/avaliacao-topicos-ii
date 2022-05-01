const wrapper = document.getElementById("navegation-component-wrapper");
const listComponent = document.getElementById("navegation-component-list");
const angle = document.getElementById("angle");
wrapper.addEventListener("mouseover", ()=>{
	listComponent.style.display = "flex";
	angle.classList = "pi pi-angle-up"
})

wrapper.addEventListener("mouseleave", ()=>{
	listComponent.style.display = "none";
	angle.classList = "pi pi-angle-down"
})