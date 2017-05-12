
function latestNews(){
		var newPara = document.createElement("p");
		var add_news = document.createTextNode("The latest News Goes Here !!!!");
		newPara.appendChild(add_news);
		
		var getFooter = document.getElementById("footer");
		
		document.body.insertBefore(newPara,getFooter);
	}