/** 修正uploadify取消文件占用上传文件数量的bug */
var uploadify_cancel = function(inputId, fileId) {
	$("#" + inputId).uploadify("cancel", fileId);
	var swfuploadify = $("#" + inputId).data("uploadify");
	var stats = swfuploadify.getStats();
	if(stats.successful_uploads > 0) {
		stats.successful_uploads -= 1;
	}
    swfuploadify.setStats(stats);
    delete swfuploadify.queueData.files[fileId];
}

var uploadify_onSelectError = function(file, errorCode, errorMsg) {
	var settings = this.settings;
	switch(errorCode) {
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
			this.queueData.errorMsg = "上传文件数量不能超过[" + settings.queueSizeLimit + "]个。";
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			this.queueData.errorMsg = "文件 \"" + file.name + "\" 大小超过最大限制 [" + settings.fileSizeLimit + "]。";
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			this.queueData.errorMsg = "文件 \"" + file.name + "\" 是空文件。";
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			this.queueData.errorMsg = "文件 \"" + file.name + "\" 是不支持的类型 [" + settings.fileTypeDesc + "]。";
			break;
	}
}

var imgInput_onInit = function(instance) {
	var settings = instance.settings;
	settings.itemTemplate = '<div id="${fileID}" class="uploadify-queue-item" style="width:' + settings.imgWidth + 'px">\
		<div class="cancel">\
			<span>${fileName} (${fileSize})</span><span class="data"></span>\
			<a href="javascript:uploadify_cancel(\'${instanceID}\', \'${fileID}\')">X</a>\
		</div>\
		<div class="uploadify-queue-image"></div>\
		<div class="uploadify-progress">\
			<div class="uploadify-progress-bar"><!--Progress Bar--></div>\
		</div>\
	</div>';
}

var imgInput_onUploadSuccess = function(file, data, response) {
	var settings = this.settings;
	
	var fileDiv = $("#" + file.id);
	var json = DWZ.jsonEval(data);
	fileDiv.find(".uploadify-queue-image").append("<img src=" + json.path + " width=" + settings.imgWidth + " height=" + settings.imgHeight + " />");
	fileDiv.find(".uploadify-queue-image").append("<input type=hidden name=" + settings.inputName + ".id id=" + settings.inputName +".id value=" + json.id + " />");
	fileDiv.find(".cancel > span").remove();
	fileDiv.find(".uploadify-progress").remove();
	
	var queueDiv = $("#" + settings.queueID);
	queueDiv.children("[id!=" + file.id + "]").remove();
	
	var stats = this.getStats();
    stats.successful_uploads = 0;
    this.setStats(stats);
    this.queueData.files = [];
}