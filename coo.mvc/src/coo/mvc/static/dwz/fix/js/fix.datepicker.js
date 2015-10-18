(function($) {
	$.fn.datepicker = function(opts) {
		var setting = {
			box$: "#calendar",
			year$: "#calendar [name=year]",
			month$: "#calendar [name=month]",
			tmInputs$: "#calendar .time :text",
			hour$: "#calendar .time .hh",
			minute$: "#calendar .time .mm",
			second$: "#calendar .time .ss",
			tmBox$: "#calendar .tm",
			tmUp$: "#calendar .time .up",
			tmDown$: "#calendar .time .down",
			close$: "#calendar .close",
			calIcon$: "a.inputDateButton",
			main$: "#calendar .main",
			days$: "#calendar .days",
			dayNames$: "#calendar .dayNames",
			clearBut$: "#calendar .clearBut",
			okBut$: "#calendar .okBut",
			todayBut$: "#calendar .todayBut"
		};

		function changeTmMenu(sltClass) {
			var $tm = $(setting.tmBox$);
			$tm.removeClass("hh").removeClass("mm").removeClass("ss");
			if(sltClass) {
				$tm.addClass(sltClass);
				$(setting.tmInputs$).removeClass("slt").filter("." + sltClass).addClass("slt");
			}
		}

		function clickTmMenu($input, type) {
			$(setting.tmBox$).find("." + type + " li").each(function() {
				var $li = $(this);
				$li.click(function() {
					$input.val($li.text());
				});
			});
		}

		function keydownInt(e) {
			if(!((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode == DWZ.keyCode.DELETE || e.keyCode == DWZ.keyCode.BACKSPACE))) {
				return false;
			}
		}

		function changeTm($input, type) {
			var ivalue = parseInt($input.val()), istart = parseInt($input.attr("start")) || 0, iend = parseInt($input.attr("end"));
			var istep = parseInt($input.attr('step') || 1);
			if(type == 1) {
				if(ivalue <= iend - istep) {
					$input.val(ivalue + istep);
				}
			} else if(type == -1) {
				if(ivalue >= istart + istep) {
					$input.val(ivalue - istep);
				}
			} else if(ivalue > iend) {
				$input.val(iend);
			} else if(ivalue < istart) {
				$input.val(istart);
			}
		}

		return this.each(function() {
			var $this = $(this);
			var dp = new Datepicker($this.val(), opts);

			function generateCalendar(dp) {
				var dw = dp.getDateWrap();
				var minDate = dp.getMinDate();
				var maxDate = dp.getMaxDate();
				var monthStart = new Date(dw.year, dw.month - 1, 1);
				var startDay = monthStart.getDay();
				var dayStr = "";
				if(startDay > 0) {
					monthStart.setMonth(monthStart.getMonth() - 1);
					var prevDateWrap = dp.getDateWrap(monthStart);
					for(var t = prevDateWrap.days - startDay + 1; t <= prevDateWrap.days; t++) {
						var _date = new Date(dw.year, dw.month - 2, t);
						var _ctrClass = (_date >= minDate && _date <= maxDate) ? '' : 'disabled';
						dayStr += '<dd class="other ' + _ctrClass + '" chMonth="-1" day="' + t + '">' + t + '</dd>';
					}
				}
				for(var t = 1; t <= dw.days; t++) {
					var _date = new Date(dw.year, dw.month - 1, t);
					var _ctrClass = (_date >= minDate && _date <= maxDate) ? '' : 'disabled';
					if(t == dw.day) {
						dayStr += '<dd class="slt ' + _ctrClass + '" day="' + t + '">' + t + '</dd>';
					} else {
						dayStr += '<dd class="' + _ctrClass + '" day="' + t + '">' + t + '</dd>';
					}
				}
				for(var t = 1; t <= 42 - startDay - dw.days; t++) {
					var _date = new Date(dw.year, dw.month, t);
					var _ctrClass = (_date >= minDate && _date <= maxDate) ? '' : 'disabled';
					dayStr += '<dd class="other ' + _ctrClass + '" chMonth="1" day="' + t + '">' + t + '</dd>';
				}
				var $days = $(setting.days$).html(dayStr).find("dd");
				$days.not('.disabled').click(function() {
					var $day = $(this);
					if(!dp.hasTime()) {
						$this.val(dp.formatDate(dp.changeDay($day.attr("day"), $day.attr("chMonth"))));
						closeCalendar();
					} else {
						$days.removeClass("slt");
						$day.addClass("slt");
					}
				});
				if(!dp.hasDate())
					$(setting.main$).addClass('nodate');
				if(dp.hasTime()) {
					$("#calendar .time").show();
					var $hour = $(setting.hour$).val(dw.hour).focus(function() {
						changeTmMenu("hh");
					});
					var iMinute = parseInt(dw.minute / dp.opts.mmStep) * dp.opts.mmStep;
					var $minute = $(setting.minute$).val(iMinute).attr('step', dp.opts.mmStep).focus(function() {
						changeTmMenu("mm");
					});
					var $second = $(setting.second$).val(dp.hasSecond() ? dw.second : 0).attr('step', dp.opts.ssStep).focus(function() {
						changeTmMenu("ss");
					});
					$hour.add($minute).add($second).click(function() {
						return false
					});
					clickTmMenu($hour, "hh");
					clickTmMenu($minute, "mm");
					clickTmMenu($second, "ss");
					$(setting.box$).click(function() {
						changeTmMenu();
					});
					var $inputs = $(setting.tmInputs$);
					$inputs.keydown(keydownInt).each(function() {
						var $input = $(this);
						$input.keyup(function() {
							changeTm($input, 0);
						});
					});
					$(setting.tmUp$).click(function() {
						$inputs.filter(".slt").each(function() {
							changeTm($(this), 1);
						});
					});
					$(setting.tmDown$).click(function() {
						$inputs.filter(".slt").each(function() {
							changeTm($(this), -1);
						});
					});
					if(!dp.hasHour())
						$hour.attr("disabled", true);
					if(!dp.hasMinute())
						$minute.attr("disabled", true);
					if(!dp.hasSecond())
						$second.attr("disabled", true);
				}
				if(!opts.showToDay) {
					$(setting.todayBut$).hide();
				}
			}

			function closeCalendar() {
				$(setting.box$).remove();
				$(document).unbind("click", closeCalendar);
			}

			$this.click(function(event) {
				closeCalendar();
				var dp = new Datepicker($this.val(), opts);
				if($this.attr("minDate") && $this.attr("minDate").startsWith("#")) {
					var _min = $($this.attr("minDate"), $this.closest("form")).val();
					if((_min && $this.val() && dp.parseDate(_min).getTime() > dp.parseDate($this.val()).getTime()) || (_min && !$this.val())) {
						dp = new Datepicker(_min, opts);
					}
				}

				if($this.attr("maxDate") && $this.attr("maxDate").startsWith("#")) {
					var _max = $($this.attr("maxDate"), $this.closest("form")).val();
					if((_max && $this.val() && dp.parseDate(_max).getTime() < dp.parseDate($this.val()).getTime())) {
						dp = new Datepicker(_max, opts);
					} else if(_max && !$this.val() && dp.parseDate(_max).getTime() < new Date().getTime()) {
						dp = new Datepicker(_max, opts);
					}
				}

				if(opts.minRelation) {
					if($this.val()) {
						if(opts.minRelation > $this.val()) {
							dp = new Datepicker(opts.minRelation, opts);
						}
					} else {
						if(opts.minRelation > dp.formatDate(new Date())) {
							dp = new Datepicker(opts.minRelation, opts);
						}
					}

				}

				var offset = $this.offset();
				var iTop = offset.top + this.offsetHeight;
				$(DWZ.frag['calendarFrag']).appendTo("body").css({
					left: offset.left + 'px',
					top: iTop + 'px'
				}).show().click(function(event) {
					event.stopPropagation();
				});
				($.fn.bgiframe && $(setting.box$).bgiframe());
				var dayNames = "";
				$.each($.regional.datepicker.dayNames, function(i, v) {
					dayNames += "<dt>" + v + "</dt>"
				});
				$(setting.dayNames$).html(dayNames);
				var dw = dp.getDateWrap();
				var $year = $(setting.year$);
				var yearstart = dp.getMinDate().getFullYear();
				var yearend = dp.getMaxDate().getFullYear();
				for(y = yearstart; y <= yearend; y++) {
					$year.append('<option value="' + y + '"' + (dw.year == y ? 'selected="selected"' : '') + '>' + y + '</option>');
				}
				var $month = $(setting.month$);
				$.each($.regional.datepicker.monthNames, function(i, v) {
					var m = i + 1;
					$month.append('<option value="' + m + '"' + (dw.month == m ? 'selected="selected"' : '') + '>' + v + '</option>');
				});
				generateCalendar(dp);
				$year.add($month).change(function() {
					dp.changeDate($year.val(), $month.val());
					generateCalendar(dp);
				});
				var iBoxH = $(setting.box$).outerHeight(true);
				if(iTop > iBoxH && iTop > $(window).height() - iBoxH) {
					$(setting.box$).css("top", offset.top - iBoxH);
				}
				$(setting.close$).click(function() {
					closeCalendar();
				});
				$(setting.clearBut$).click(function() {
					$this.val("");
					closeCalendar();
				});
				$(setting.okBut$).click(function() {
					var $dd = $(setting.days$).find("dd.slt");
					if($dd.hasClass("disabled"))
						return false;
					var date = dp.changeDay($dd.attr("day"), $dd.attr("chMonth"));
					if(dp.hasTime()) {
						date.setHours(parseInt($(setting.hour$).val()));
						date.setMinutes(parseInt($(setting.minute$).val()));
						date.setSeconds(parseInt($(setting.second$).val()));
					}
					$this.val(dp.formatDate(date));
					closeCalendar();
				});
				$(setting.todayBut$).click(function() {
					$this.val(dp.formatDate(new Date()));
					closeCalendar();
				});
				$(document).bind("click", closeCalendar);
				return false;
			});
			$this.parent().find(setting.calIcon$).click(function() {
				$this.trigger("click");
				return false;
			});
		});
	}
	var Datepicker = function(sDate, opts) {
		this.opts = $.extend({
			pattern: 'yyyy-MM-dd',
			minDate: "1900-01-01",
			maxDate: "2099-12-31",
			mmStep: 1,
			ssStep: 1
		}, opts);
		var now = new Date();
		this.opts.minDate = now.formatDateTm(this.opts.minDate);
		this.opts.maxDate = now.formatDateTm(this.opts.maxDate);
		this.sDate = sDate.trim();
	}
	$.extend(Datepicker.prototype, {
		get: function(name) {
			return this.opts[name];
		},
		_getDays: function(y, m) {
			return m == 2 ? (y % 4 || !(y % 100) && y % 400 ? 28 : 29) : (/4|6|9|11/.test(m) ? 30 : 31);
		},
		_minMaxDate: function(sDate) {
			var _count = sDate.split('-').length - 1;
			var _format = 'y-M-d';
			if(_count == 1)
				_format = 'y-M';
			else if(_count == 0)
				_format = 'y';
			return sDate.parseDate(_format);
		},
		getMinDate: function() {
			return this._minMaxDate(this.opts.minDate);
		},
		getMaxDate: function() {
			var _sDate = this.opts.maxDate;
			var _count = _sDate.split('-').length - 1;
			var _date = this._minMaxDate(_sDate);
			if(_count < 2) {
				var _day = this._getDays(_date.getFullYear(), _date.getMonth() + 1);
				_date.setDate(_day);
				if(_count == 0) {
					_date.setMonth(11);
				}
			}
			return _date;
		},
		getDateWrap: function(date) {
			if(!date)
				date = this.parseDate(this.sDate) || new Date();
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var days = this._getDays(y, m);
			return {
				year: y,
				month: m,
				day: date.getDate(),
				hour: date.getHours(),
				minute: date.getMinutes(),
				second: date.getSeconds(),
				days: days,
				date: date
			}
		},
		changeDate: function(y, m, d) {
			var date = new Date(y, m - 1, d || 1);
			this.sDate = this.formatDate(date);
			return date;
		},
		changeDay: function(day, chMonth) {
			if(!chMonth)
				chMonth = 0;
			var dw = this.getDateWrap();
			return this.changeDate(dw.year, dw.month + parseInt(chMonth), day);
		},
		parseDate: function(sDate) {
			if(!sDate)
				return null;
			return sDate.parseDate(this.opts.pattern);
		},
		formatDate: function(date) {
			return date.formatDate(this.opts.pattern);
		},
		hasHour: function() {
			return this.opts.pattern.indexOf("H") != -1;
		},
		hasMinute: function() {
			return this.opts.pattern.indexOf("m") != -1;
		},
		hasSecond: function() {
			return this.opts.pattern.indexOf("s") != -1;
		},
		hasTime: function() {
			return this.hasHour() || this.hasMinute() || this.hasSecond();
		},
		hasDate: function() {
			var _dateKeys = [
					'y', 'M', 'd', 'E'
			];
			for(var i = 0; i < _dateKeys.length; i++) {
				if(this.opts.pattern.indexOf(_dateKeys[i]) != -1)
					return true;
			}
			return false;
		}
	});
})(jQuery);
