/** 将序列化数组转换成json对象 */
var serializeArrayToJson = function(serializeArray) {
	var json = {};
	if(!$.isEmptyObject(serializeArray)) {
		jQuery.each(serializeArray, function(i, item){
			json[item.name] = item.value;
		});
	}
	return json;
}