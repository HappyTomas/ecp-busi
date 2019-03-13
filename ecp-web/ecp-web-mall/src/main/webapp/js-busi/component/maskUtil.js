var timecloseNewDiv;
var docEle = function() {
	return document.getElementById(arguments[0]) || false;
}

function addMask(_id){
	var m = "mask";
	if (docEle(_id))
		document.body.removeChild(docEle(_id));
	if (docEle(m))
		document.body.removeChild(docEle(m));

	// mask遮罩层
	var newMask = document.createElement("div");
	newMask.id = m;
	newMask.style.position = "absolute";
	newMask.style.zIndex = "9998";
	_scrollWidth = document.documentElement.scrollWidth;
	_scrollHeight = document.documentElement.scrollHeight;
	newMask.style.width = _scrollWidth + "px";
	newMask.style.height = _scrollHeight + "px";
	newMask.style.top = "0px";
	newMask.style.left = "0px";
	newMask.style.background = "#ffffff";
	newMask.style.filter = "alpha(opacity=10)";
	newMask.style.opacity = "0.20";
	document.body.appendChild(newMask);
}

/* 打开遮罩层 */
function openNewDiv(_id) {
	addMask(_id);
	// 新弹出层
	var newDiv = document.createElement("div");
	newDiv.id = _id;
	newDiv.style.position = "absolute";
	newDiv.style.zIndex = "9999";
	newDivWidth = 205;
	newDivHeight = 61;
	newDiv.style.width = newDivWidth + "px";
	newDiv.style.height = newDivHeight + "px";
	newDiv.style.top = (document.documentElement.scrollTop + document.documentElement.clientHeight
			/ 2 - newDivHeight / 2)
			+ "px";
	newDiv.style.left = (document.documentElement.scrollLeft + document.documentElement.clientWidth
			/ 2 - newDivWidth / 2)
			+ "px";
	// newDiv.style.background = "#EFEFEF";
	newDiv.style.border = "0";
	newDiv.style.padding = "5px";
	var imageUrl = "../../images/card.png";
	newDiv.innerHTML = "<img src='" + imageUrl + "' />";
	document.body.appendChild(newDiv);
	// 弹出层滚动居中
	function newDivCenter() {
		newDiv.style.top = (document.documentElement.scrollTop
				+ document.documentElement.clientHeight / 2 - newDivHeight / 2)
				+ "px";
		newDiv.style.left = (document.documentElement.scrollLeft
				+ document.documentElement.clientWidth / 2 - newDivWidth / 2)
				+ "px";
	}
	if (document.all) {
		window.attachEvent("onscroll", newDivCenter);
	} else {
		window.addEventListener('scroll', newDivCenter, false);
	}
	//timecloseNewDiv = setInterval("closeNewDiv('" + _id + "')", 10000);
}

/* 关闭遮罩层 */
function closeNewDiv(_id) {
	var m = "mask";
	var newDiv = document.getElementById(_id);
	function newDivCenter() {
		newDiv.style.top = (document.documentElement.scrollTop
				+ document.documentElement.clientHeight / 2 - newDivHeight / 2)
				+ "px";
		newDiv.style.left = (document.documentElement.scrollLeft
				+ document.documentElement.clientWidth / 2 - newDivWidth / 2)
				+ "px";
	}
	if (document.all) {
		window.detachEvent("onscroll", newDivCenter);
	} else {
		window.removeEventListener('scroll', newDivCenter, false);
	}
	if (newDiv != null) {
		document.body.removeChild(docEle(_id));
		document.body.removeChild(docEle(m));
	}
	//clearInterval(timecloseNewDiv);
	return false;
}
