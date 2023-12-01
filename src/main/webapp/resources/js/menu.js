const hambuerger = document.querySelector('.hamburger');
const menu = document.querySelector('.menu-navegacion');
console.log(menu)
console.log(hambuerger)

hambuerger.addEventListener('click',()=>{
     menu.classList.toggle("spread")
}) 

window.addEventListener('click', e=>{
    if(menu.classList.contains('spread')&& e.target != menu && e.target !=hambuerger){
        menu.classList.toggle("spread")
    }
})