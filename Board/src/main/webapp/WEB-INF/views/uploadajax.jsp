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
	
	small{
		cursor : pointer;
	}

</style>

</head>
<body>

	<div class="fileDrop"></div>
	<div class="uploadedList"></div>
	
	<script type="text/javascript">
	
		$("document").ready(function(){
			
			$(".uploadedList").on("click", "small", function(event){
				
				var that = $(this);
				
				$.ajax({
					type : 'post',
					url : 'deletefile',
					data : {
						filename : $(this).attr('data-ca')
					},
					dataType : 'text',
					success : function(result){
						alert("삭제되었습니다");
						that.parent("div").remove();
					}
				})
				
			});
			
			
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
								str += "<div><a target='_blank' href='/displayfile?filename="+getOriginalLink(data)+"'><img src='displayfile?filename="+data+"' alt='일반파일 썸네일입니다' /></a><p>"+getOriginalName(data)+"</p><small data-ca='"+data+"'>X</small></div>"
							}else{
								str += "<div><a href='/displayfile?filename="+data+"'><img src='/resources/test.png' alt='일반파일 썸네일입니다.'/></a><p>"+getOriginalName(data)+"</p><small data-ca='"+data+"'>X</small></div>"
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
		
		function getOriginalName(data){	
			
			var idx = data.lastIndexOf("_")+1;
			
			return data.substring(idx);
		}
		
		function getOriginalLink(data){
			
			var prefix = data.substring(0, 12); //날짜(폴더위치부분) 살리기
			
			var suffix = data.substring(14); //데이터이름 살리기
			
			return prefix+suffix; //replace를 사용해서 _s를 공백처리해도 같은 결과가 나온다
		}
		
	</script>
</body>
</html>