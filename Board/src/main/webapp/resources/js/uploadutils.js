/**
 * 
 */
function checkImageType(data) {
	var pattern = /jpg|png|jpeg|gif/i; /* 확장자 확인 (i를 쓰면 대소문자 구분없어짐)*/
	return data.match(pattern);
}

function getOriginalName(data) {

	var idx = data.lastIndexOf("_") + 1;

	return data.substring(idx);
}

function getOriginalLink(data) {

	if(checkImageType(data)){
		var prefix = data.substring(0, 12); //날짜(폴더위치부분) 살리기
		
		var suffix = data.substring(14); //데이터이름 살리기
		
		return prefix + suffix; //replace를 사용해서 _s를 공백처리해도 같은 결과가 나온다
	}else{
		return data;
	}
}