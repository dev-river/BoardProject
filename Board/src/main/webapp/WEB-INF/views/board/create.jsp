<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="/resources/js/uploadutils.js" type="text/javascript"></script>

<style type="text/css">
	.fileDrop{
		width : 80%;
		height : 100px;
		border : 1px dotted red;
		background-color : lightslategray;
		margin : auto;
	}
	
	.uploadedList{
		list-style : none;
	}

</style>

</head>
<body>
	
	<div class="container">
		<div class="row">
		
			<h1>게시물 등록</h1>
				
			<form action="/board/create" method="post"> <!-- action을 비워둬도 /board/create로 가긴한다 -->
				<div class="form-group">
					<label for="title">제목</label>
					<input required id="title" name="title" class="form-control">
				</div>
				<div class="form-group">
					<label for="writer">작성자</label>
					<input required id="writer" name="writer" class="form-control">
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea required id="content" name="content" class="form-control" rows="3"></textarea>
				</div>
				
			</form>
			
			<div class="form-group">
				<label>업로드할 파일을 드랍시키세요</label>
				<div class="fileDrop"></div> 
			</div>
			
			<ul class="uploadedList clearfix">
				
			</ul>
			
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="등록">
				<input id="reset" type="reset" class="btn btn-warning" value="취소">
				<!-- 취소하면 등록해뒀던 이미지 삭제하고 list.jsp로 이동하게 만들어보기 -->
			</div>
		</div>
	</div>

<script type="text/javascript">
	
	$("document").ready(function(){
		
		$("input[type='submit']").click(function(event){
			event.preventDefault();
			
			var str="";
			/* var arr = $(".delbtn");
			for(var i=0;i<arr.length;i++){
				alert(arr[i].getAttribute("data-ca"));
			} */
			
			$(".delbtn").each(function(index){
				str += "<input name='files["+index+"]' value='"+$(this).attr("data-ca")+"' type='hidden' />"
			});
			
			$("form").append(str);
			$("form").submit();
			
		});
		
		/* $("input[type='reset']").click(function(event){
			event.preventDefault();
			
		}); */
		
		$(".uploadedList").on("click",".delbtn", function(event){
			event.preventDefault();
			var that = $(this);
			
			$.ajax({
				type : 'post',
				url : '/deletefile',
				data : {
					filename : $(this).attr('data-ca')
				},
				dataType : 'text',
				success : function(result){
					alert("삭제되었습니다");
					that.parent("div").parent("li").remove();
				}
			});
		});
		
		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
		});
		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			
			var arr = event.originalEvent.dataTransfer.files;
			
			for(var idx=0; idx<arr.length; idx++){
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
						var str = "<li class='col-xs-3'>";
						
						if(checkImageType(data)){
							str += "<span>"+
								"<img alt='업로드한 이미지 파일' src='/displayfile?filename="+data+"'>"+
							"</span>"+
							"<div>"+
								"<a href='/displayfile?filename="+getOriginalLink(data)+"'>"+getOriginalName(data)+"</a>"+
								"<a data-ca='"+data+"' href='/deletefile?filename="+data+"' class='btn btn-default btn-xs delbtn'>"+
									"<span class='glyphicon glyphicon-remove'></span>"+
								"</a>"+
							"</div>";
						}else{
							str += "<span>"+
								"<img alt='업로드한 일반 파일' src='/resources/test.png'>"+
							"</span>"+
							"<div>"+
								"<a href='/displayfile?filename="+getOriginalLink(data)+"'>"+getOriginalName(data)+"</a>"+
								"<a data-ca='"+data+"' href='/deletefile?filename="+data+"' class='btn btn-default btn-xs delbtn'>"+
									"<span class='glyphicon glyphicon-remove'></span>"+
								"</a>"+
							"</div>";
						}
						
						str+="</li>";
						
						$(".uploadedList").append(str);
					}
				});
				
			}
			
		});
	});
	
</script>

</body>
</html>