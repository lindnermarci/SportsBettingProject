// validation script here

const inputs = document.querySelectorAll('input');
const patterns = {
    telephone: /^\d{11}$/,
    username: /^[a-z0-9]{5,12}$/,
    email: /^[a-z0-9\.-]+@[a-z0-9\.-]+\.[a-z]{2,8}(\.[a-z]{2,8})?$/,
    password: /^[\w@-]{8,20}$/,
    slug: /^([a-z0-9-]){8,20}$/
}

//validation
function validate(field,regex){
    if(regex.test(field.value)){
        field.className = "valid";
    }else{
        field.className = "invalid";
    }
}

inputs.forEach((input) => {
    input.addEventListener('keyup',(e) => {
        validate(e.target, patterns[e.target.attributes.name.value]);
    })
})