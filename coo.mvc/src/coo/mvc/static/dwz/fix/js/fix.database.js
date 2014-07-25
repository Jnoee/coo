/** 覆盖DWZ的主从结构组件，支持checkbox表单控件。*/
$.fn.extend({
	itemDetail: function(){
		return this.each(function(){
			var $table = $(this).css("clear","both"), $tbody = $table.find("tbody");
			var fields=[];
	
			$table.find("tr:first th[type]").each(function(i){
				var $th = $(this);
				var field = {
					type: $th.attr("type") || "text",
					patternDate: $th.attr("dateFmt") || "yyyy-MM-dd",
					name: $th.attr("name") || "",
					defaultVal: $th.attr("defaultVal") || "",
					size: $th.attr("size") || "12",
					enumUrl: $th.attr("enumUrl") || "",
					lookupGroup: $th.attr("lookupGroup") || "",
					lookupUrl: $th.attr("lookupUrl") || "",
					lookupPk: $th.attr("lookupPk") || "id",
					suggestUrl: $th.attr("suggestUrl"),
					suggestFields: $th.attr("suggestFields"),
					postField: $th.attr("postField") || "",
					fieldClass: $th.attr("fieldClass") || "",
					fieldAttrs: $th.attr("fieldAttrs") || ""
				};
				fields.push(field);
			});
			
			$tbody.find("a.btnDel").click(function(){
				var $btnDel = $(this);
				//：影响时间限制组件的删除功能。
				if ($btnDel.is("[href^=javascript]")){
					$btnDel.parents("tr:first").remove();
					initSuffix($tbody);
					return false;
				}
				
				function delDbData(){
					$.ajax({
						type:'POST', dataType:"json", url:$btnDel.attr('href'), cache: false,
						success: function(){
							$btnDel.parents("tr:first").remove();
							initSuffix($tbody);
						},
						error: DWZ.ajaxError
					});
				}
				
				if ($btnDel.attr("title")){
					alertMsg.confirm($btnDel.attr("title"), {okCall: delDbData});
				} else {
					delDbData();
				}
				
				return false;
			});
	
			var addButTxt = $table.attr('addButton') || "Add New";
			if (addButTxt) {
				var $addBut = $('<div class="button"><div class="buttonContent"><button type="button">'+addButTxt+'</button></div></div>').insertBefore($table).find("button");
				// 屏蔽批量增加输入框
				// var $rowNum = $('<input type="text" name="dwz_rowNum" class="textInput" style="margin:2px;" value="1" size="2"/>').insertBefore($table);
				
				var trTm = "";
				$addBut.click(function(){
					if (! trTm) trTm = trHtml(fields);
					var rowNum = 1;
					// 屏蔽批量增加输入框
					// try{rowNum = parseInt($rowNum.val())} catch(e){}
	
					for (var i=0; i<rowNum; i++){
						var $tr = $(trTm);
						$tr.appendTo($tbody).initUI().find("a.btnDel").click(function(){
							$(this).parents("tr:first").remove();
							initSuffix($tbody);
							return false;
						});
					}
					initSuffix($tbody);
				});
			}
		});
		
		/**
		 * 删除时重新初始化下标
		 */
		function initSuffix($tbody) {
			$tbody.find('>tr').each(function(i){
				$(':input, a.btnLook, a.btnAttach', this).each(function(){
					var $this = $(this), name = $this.attr('name'), val = $this.val();
					if (name) $this.attr('name', name.replaceSuffix(i));
					if ($this.attr('id')) $this.attr('id', $this.attr('id').replaceSuffix(i));
					if ($this.attr('customvalid')) $this.attr('customvalid', $this.attr('customvalid').replaceSuffix(i));
					var lookupGroup = $this.attr('lookupGroup');
					if (lookupGroup) {$this.attr('lookupGroup', lookupGroup.replaceSuffix(i));}
					
					var suffix = $this.attr("suffix");
					if (suffix) {$this.attr('suffix', suffix.replaceSuffix(i));}
					
					if (val && val.indexOf("#index#") >= 0) $this.val(val.replace('#index#',i+1));
				});
			});
		}
		
		function tdHtml(field){
			var html = '', suffix = '';
			
			if (field.name.endsWith("[#index#]")) suffix = "[#index#]";
			else if (field.name.endsWith("[]")) suffix = "[]";
			
			var suffixFrag = suffix ? ' suffix="' + suffix + '" ' : '';
			
			var attrFrag = '';
			if (field.fieldAttrs){
				var attrs = DWZ.jsonEval(field.fieldAttrs);
				for (var key in attrs) {
					attrFrag += key+'="'+attrs[key]+'"';
				}
			}
			switch(field.type){
				// 增加checkbox支持
				case 'checkbox':
					html = '<input type="checkbox" name="'+field.name+'" value="'+field.defaultVal+'" size="'+field.size+'" class="'+field.fieldClass+'" '+attrFrag+'/>';
					break;
				// 增加span文本支持
				case 'span':
					html = '<span class="' + field.fieldClass + '">' + field.defaultVal + '</span>';
					break;
				case 'del':
					html = '<a href="javascript:void(0)" class="btnDel '+ field.fieldClass + '" style="float:none;">删除</a>';
					break;
				case 'lookup':
					var suggestFrag = '';
					if (field.suggestFields) {
						suggestFrag = 'autocomplete="off" lookupGroup="'+field.lookupGroup+'"'+suffixFrag+' suggestUrl="'+field.suggestUrl+'" suggestFields="'+field.suggestFields+'"' + ' postField="'+field.postField+'"';
					}
	
					html = '<input type="hidden" name="'+field.lookupGroup+'.'+field.lookupPk+suffix+'"/>'
						+ '<input type="text" name="'+field.name+'"'+suggestFrag+' lookupPk="'+field.lookupPk+'" size="'+field.size+'" class="'+field.fieldClass+'"/>'
						+ '<a class="btnLook" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' lookupPk="'+field.lookupPk+'" title="查找带回">查找带回</a>';
					break;
				case 'attach':
					html = '<input type="hidden" name="'+field.lookupGroup+'.'+field.lookupPk+suffix+'"/>'
						+ '<input type="text" name="'+field.name+'" size="'+field.size+'" readonly="readonly" class="'+field.fieldClass+'"/>'
						+ '<a class="btnAttach" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' lookupPk="'+field.lookupPk+'" width="560" height="300" title="查找带回">查找带回</a>';
					break;
				case 'enum':
					$.ajax({
						type:"POST", dataType:"html", async: false,
						url:field.enumUrl, 
						data:{inputName:field.name}, 
						success:function(response){
							html = response;
						}
					});
					break;
				case 'date':
					html = '<input type="text" name="'+field.name+'" value="'+field.defaultVal+'" class="date '+field.fieldClass+'" dateFmt="'+field.patternDate+'" size="'+field.size+'" '+attrFrag+'/>';
					break;
				default:
					html = '<input type="text" name="'+field.name+'" value="'+field.defaultVal+'" size="'+field.size+'" class="'+field.fieldClass+'" '+attrFrag+'/>';
					break;
			}
			return '<td align="center">'+html+'</td>';
		}
		
		function trHtml(fields){
			var html = '';
			$(fields).each(function(){
				html += tdHtml(this);
			});
			return '<tr class="unitBox">'+html+'</tr>';
		}
	}
});