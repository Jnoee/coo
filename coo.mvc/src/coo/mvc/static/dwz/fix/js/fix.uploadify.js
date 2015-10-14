/** 修正uploadify取消文件占用上传文件数量的bug */
var uploadify_cancel = function(inputId, fileId) {
	$("#" + inputId).uploadify("cancel", fileId);
	var swfuploadify = $("#" + inputId).data("uploadify");
	var stats = swfuploadify.getStats();
    stats.successful_uploads -= 1;
    swfuploadify.setStats(stats);
    delete swfuploadify.queueData.files[fileId];
}

/** 覆盖uploadify默认的onSelectError方法 */
var uploadify_onSelectError = function(file, errorCode, errorMsg) {
	var settings = this.settings;
	switch(errorCode) {
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
			this.queueData.errorMsg = '上传文件数量不能超过[' + settings.queueSizeLimit + ']个。';
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			this.queueData.errorMsg = '文件 "' + file.name + '" 大小超过最大限制 [' + settings.fileSizeLimit + ']。';
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			this.queueData.errorMsg = '文件 "' + file.name + '" 是空文件。';
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			this.queueData.errorMsg = '文件 "' + file.name + '" 是不支持的类型 [' + settings.fileTypeDesc + ']。';
			break;
	}
	if (errorCode != SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
		delete this.queueData.files[file.id];
	}
}