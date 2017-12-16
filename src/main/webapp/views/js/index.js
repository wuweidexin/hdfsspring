$(document).ready(function(){
	$("#translate").click(function(){
		var txt = $("#srctext").val();
		if(txt.trim().length > 0) {
			$.post("dict/translate.action",
				  {
				    text:txt
				  },
				  function(result){
					  var d = result.data;
					  if(d && d.trim().length > 0) {
						  d = d.replace(/"/g, '');
						  $("#show").text(d);
					  } else{
						  $("#show").text('');
					  }
				  }
			 );
		
			
		} else {
			$("#show").text('请输入值');
		}
	})
})