
//updateForm 자바스크립트

$(document).ready(function() {
    $('#updateForm').submit(validation);
});

function validation(event) {
    let pw = $('#password').val();
    let pw2 = $('#password2').val();
    let name = $('#name').val();

    if(pw == ''){
        if (!confirm("기존 비밀번호를 사용하시겠습니까?")) {
            alert("비밀번호를 새로 입력해주세요");
            return false;
        } else {
            alert("기존 비밀번호가 사용됩니다");
        }
    } else {
        if (pw.length < 4 || pw.length > 16) {
            alert("비밀번호는 4~16자로 입력하세요.");
            return false;
        }
   
        if (pw != pw2) {
            alert("비밀번호를 정확하게 입력하세요.");
            return false;
        }
    }

    if (name == '') {
        alert("이름을 입력하세요.");
        return false;
    }

    return true;
}