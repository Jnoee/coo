/** 修正uploadify取消文件占用上传文件数量的bug */
var uploadify_cancel = function(inputId, fileId) {
	var swfuploadify = $("#" + inputId).data("uploadify");
	var stats = swfuploadify.getStats();
	if(stats.successful_uploads > 0) {
		stats.successful_uploads -= 1;
	}
	swfuploadify.setStats(stats);
	delete swfuploadify.queueData.files[fileId];

	swfuploadify.cancelUpload(fileId, false);
	var queueDiv = $("#" + swfuploadify.settings.queueID);
	queueDiv.children("#" + fileId).remove();
	
	if(queueDiv.children("div.uploadify-queue-item").size() == 0) {
		var hiddenInput = $("#" + swfuploadify.settings.queueID + "_hidden");
		hiddenInput.val("");
	}
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

var img_onInit = function(instance) {
	var settings = instance.settings;
	settings.itemTemplate = '<div id="${fileID}" class="uploadify-queue-item" style="width:'
			+ settings.imgWidth
			+ 'px">\
		<div class="uploadify-queue-image"></div>\
		<div class="uploadify-progress">\
			<div class="uploadify-progress-bar"><!--Progress Bar--></div>\
		</div>\
		<div class="uploadify-queue-bar">\
			<div><span>${fileName} (${fileSize})</span><span class="data"></span></div>\
			<div><a href="javascript:uploadify_cancel(\'${instanceID}\', \'${fileID}\')" title="删除"><i class="fa fa-trash"></i></a></div>\
		</div>\
	</div>';
}

var img_onUploadStart = function(file) {
	var settings = this.settings;
	var queueDiv = $("#" + settings.queueID);
	if(settings.multi) {
		if(settings.uploadLimit != 0 && queueDiv.children().size() > settings.uploadLimit) {
			this.cancelUpload(file.id, false);
			queueDiv.children("[id=" + file.id + "]").remove();
			alert("上传文件数量不能超过[" + settings.uploadLimit + "]个。");
		}
	} else {
		queueDiv.children("[id!=" + file.id + "]").remove();
	}
}

var img_onUploadSuccess = function(file, data, response) {
	var settings = this.settings;
	var queueDiv = $("#" + settings.queueID);

	var fileDiv = $("#" + file.id);
	var attFile = DWZ.jsonEval(data);
	fileDiv.find(".uploadify-queue-image").append("<img src=" + attFile.path + " width=" + settings.imgWidth + " height=" + settings.imgHeight + " />");
	fileDiv.find(".uploadify-queue-image").append("<input type=hidden name=" + settings.inputName + " value=" + attFile.id + " />");
	fileDiv.find(".uploadify-progress").remove();
	fileDiv.find(".uploadify-queue-bar > div:first").remove();
	
	var hiddenInput = $("#" + settings.queueID + "_hidden");
	hiddenInput.val(file.id);
	
	if(!settings.multi) {
		var stats = this.getStats();
		stats.successful_uploads = 0;
		this.setStats(stats);
		this.queueData.files = [];
	}
}