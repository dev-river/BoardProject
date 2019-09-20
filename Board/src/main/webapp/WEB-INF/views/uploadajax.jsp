<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
	.fileDrop{
		width: 100%;
		height: 200px;
		border: 1px dotted red;
	}
	
	.uploadedList{
		
	}

</style>

</head>
<body>

	<div class="fileDrop"></div>
	<div class="uploadedList"></div>
	
	<script type="text/javascript">
	
		$("document").ready(function(){
			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});
			$(".fileDrop").on("drop", function(event){
				event.preventDefault();
				
				var arr = event.originalEvent.dataTransfer.files; /* 드래그&드롭 후 파일의 정보를 arr에 넣음 */
				
				for(var idx=0;idx<arr.length;idx++){
					
					var file = arr[idx];
				
					var formData = new FormData();
					formData.append("file", file);
					
					$.ajax({
						type : 'post',
						url : '/uploadajax',
						data : formData,
						dataType : 'text',
						contentType : false,
						processData : false,
						success : function(data){
							var str='';
							
							if(checkImageType(data)){
								str += "<img src='displayfile?filename="+data+"' alt='일반파일 썸네일입니다' />"
							}else{
								str += "<img src='/resources/test.png' alt='일반파일 썸네일입니다.'/>"
							}
							
							$(".uploadedList").append(str);
						}
					});
				} /* for문을 여기까지 돌려주면 다중 업로드가 가능하다 */
			});
		});
		
		function checkImageType(data){
			var pattern = /jpg|png|jpeg|gif/i; /* 확장자 확인 (i를 쓰면 대소문자 구분없어짐)*/
			return data.match(pattern);
		}
		
	</script>
</body>
</html>