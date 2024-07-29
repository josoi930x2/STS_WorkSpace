/**
 * 
 */
$(document).ready(function() {
	
  $(".but1").on("click", function () {
	    //alert("checkId의 click 이벤트 실행");
		
		window.open('idCheck', 'idWin', 
		'top=200, left=500, width=400, height=300, location=no, titlebar=no')
	});
	
	
    $('#joinForm').on('submit', function() {
        // 폼 제출을 막기 위해 기본 동작 방지
        //event.preventDefault();
        
        // 유효성 검사

        if ($('#id').val() == "") {
            alert('아이디를 입력하세요.');
            $('#id').focus();
            return false;
        }
        if ($('#pw').val() == "") {
            alert('비밀번호를 입력하세요.');
            $('#pw').focus();
            return false;
        }
        if ($('#pw').val().length < 4 || $('#pw').val().length > 16) {
            alert('비밀번호는 4 ~ 16자 사이로 입력하세요.');
            $('#pw').focus();
            return false;
        }
		
		if ($('#pwdCheck').val() == "") {
		   alert('비밀번호를 재입력하세요.');
		   $('#pwdCheck').focus();
		   return false;
		}
		
		if ($('#pw').val() !== $('#pwdCheck').val()) {
			alert('비밀번호를 정확히 입력하세요.');
		    $('#pwdCheck').focus();
			return false;
		}
		
		
		if ($('#name').val() == "") {
			alert('이름을 입력하세요.');
			$('#name').focus();
			return false;
		}
		
        
        // 추가 유효성 검사 로직을 여기서 계속...

        // 최종 성공 메시지
        alert('회원가입이 완료되었습니다.');
		
    });
	return ture;
});

