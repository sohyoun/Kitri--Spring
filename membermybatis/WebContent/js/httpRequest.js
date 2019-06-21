function getXMLHttpRequest(){
	if(window.ActiveXObject){
		try{
			return new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e1){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e2){
				return null;
			}
		}
	}else if(window.XMLHttpRequest){
		return new XMLHttpRequest(); // 서버와 통신할 수 있는 객체
	}else{
		return null;
	}
}

var httpRequest = null;

function sendRequest(url, params, callback, method){
	httpRequest = getXMLHttpRequest();
	
	var httpMethod = method ? method : 'GET';
	if(httpMethod != 'GET' && httpMethod != 'POST'){
		httpMethod = 'GET';
	}
	var httpParams = (params == null || params == '') ? null : params;
	var httpUrl = url;
	if(httpMethod == 'GET' && httpParams != null){ //넘겨줄 값이 있는데 GET방식이면
		httpUrl = httpUrl + "?" + httpParams;
	}
	
	//alert("method == " + httpMethod + "\turl == " + httpUrl + "\tparam == " + httpParams);
	httpRequest.open(httpMethod, httpUrl, true); // true : 비동기 방식 / false : 동기 방식
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.onreadystatechange = callback; //0~3 브라우저에 아직 안넘어온 상태 /4 정상적으로 넘어온 상태
	//alert(httpMethod == 'POST' ? httpParams : null);
	httpRequest.send(httpMethod == 'POST' ? httpParams : null); //post면 send할 때 여기서 parameter 붙임
}