<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육과정 관리</title>
<link rel="stylesheet" type="text/css" href="/training/common/css/flexigrid/flexigrid.css">
<link rel="stylesheet" type="text/css" href="/training/common/css/theme/redmond/jquery-ui-1.8.11.custom.css">
<style>

body { font-size: 62.5%; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }

input {
	border:1px solid #C8C8C8;
}

.window {
	background:#DFE8F6;
	display:none;
	label, input { display:block; }
}

.flexigrid div.fbutton .add {
	background: url(/training/common/css/images/add.png) no-repeat center left;
}
.flexigrid div.fbutton .mod {
	background: url(/training/common/css/images/mod.png) no-repeat center left;
}
.flexigrid div.fbutton .delete {
	background: url(/training/common/css/images/close.png) no-repeat center left;
}
.rtbl {
	width:100%;
	text-aglign:center;
	font-size:12px;
}

.rHead {
	width:20%;
	height:24px;
	line-height:24px;
	text-align:right;
	padding-right:12px;
	background:#E5E5E5;
}

.rForm {
	width:80%;
}

#addWindow textarea {
	width:100%;
	height:80px;
}
#addWindow input {
	width:100%;
	height:20px;
}

#addWindow select {
	width:100%;
	height:20px;
}
#addWindow .dateInput {
	width:46%;
	text-align:center;
	font-family:gulim;
}
</style>
<script type="text/javascript" src="/training/common/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="/training/common/js/flexigrid.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.button.js"></script>
<script type="text/javascript" src="/training/common/js/external/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="/training/common/js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/training/common/js/ui/i18n/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery("#courseList").flexigrid({
			url: '/training/action/CourseAdmin/getCourseListAll',
			dataType: 'json',
			colModel : [
				{display: '번호', name : 'num', width : 30, sortable : true, align: 'center'},
				{display: '교육기관', name : 'institutionName', width : 150, sortable : true, align: 'center'},
				{display: '구분', name : 'courseType', width : 60, sortable : true, align: 'center'},
				{display: '과정명', name : 'courseName', width : 120, sortable : true, align: 'left'},
				{display: '내용', name : 'courseInformation', width : 160, sortable : true, align: 'left'},
				{display: '교육시간', name : 'courseTime', width : 80, sortable : true, align: 'center'},
				{display: '교육기간', name : 'courseTerm', width : 80, sortable : true, align: 'center'},
				{display: '교육비', name : 'coursePrice', width : 80, sortable : true, align: 'center'},
				{display: '신청시작일', name : 'courseStartDate', width : 80, sortable : true, align: 'center'},
				{display: '신청종료일', name : 'courseEndDate', width : 80, sortable : true, align: 'center'},
				],
			buttons : [
				{name: '등록', bclass: 'add', onpress : onCommand},
				{name: '수정', bclass: 'mod', onpress : onCommand},
				{name: '삭제', bclass: 'delete', onpress : onCommand},
				{separator: true}
				],
			searchitems : [
				{display: '교육기관명', name : 'institutionName'},
				{display: '교육과정명', name : 'courseName', isdefault: true},
				{display: '구분', name : 'courseType'}
				],
			sortname: "num",
			sortorder: "asc",
			usepager: true,
			title: '교육과정 리스트',
			useRp: true,
			onChangeSort: chSort,
			rp: 10,
			showTableToggleBtn: true,
			height: 252
		});

		jQuery('#addWindow').dialog({
			autoOpen: false,
			height: 400,
			width: 400,
			modal: true,
			buttons: {
				"등록": function() {
					addCourse();
				},
				"취소": function() {
					$( this ).dialog("close");
				}
			},
			close: function() {
				jQuery("#addForm input").val("").removeClass( "ui-state-error" );
			}
		});
		
		jQuery.ajax({
			url:'/training/action/InstitutionAdmin/getInstitutionAll',
			dataType:"json",
			error: function(xhr, status, error) {
				alert("status = " + xhr.status + ", error = " + error);
			},
			success: function(data) {
				jQuery.each(data, function(i, row) {
					jQuery('#institutionId').append("<option value=" + row.institutionId + ">" + row.institutionName + "</option>");
				});
				jQuery('#institutionName').val(jQuery('#institutionId option:selected').text());
			}
		});

		jQuery.ajax({
			url:'/training/action/CourseTypeAdmin/getCourseTypeAll',
			dataType:"json",
			success: function(data) {
				jQuery.each(data, function(i, row) {
					jQuery('#courseTypeId').append("<option value=" + row.typeId + ">" + row.typeName + "</option>");
				});
				jQuery('#courseTypeName').val(jQuery('#courseTypeId option:selected').text());
			}
		});

		jQuery('#institutionId').change( function() {
			jQuery('#institutionName').val(jQuery('#institutionId option:selected').text());
		});

		jQuery('#courseTypeId').change( function() {
			jQuery('#courseTypeName').val(jQuery('#courseTypeId option:selected').text());
		});

		jQuery('.dateInput').datepicker(); //
		//jQuery('#courseEnd').datepicker();
		//$.datepicker.formatDate('yy-mm-dd', new Date(2011, 4 - 1, 5), {dayNamesShort: $.datepicker.regional['ko'].dayNamesShort, dayNames: $.datepicker.regional['ko'].dayNames, monthNamesShort: $.datepicker.regional['ko'].monthNamesShort, monthNAmes: $.datepicker.regional['ko'].monthNames})
		/*var dates = $( "#courseStart, #courseEnd" ).datepicker({
			regional:['ko'],
			dateFormat:"yy-mm-dd"
			onSelect: function( selectedDate ) {
				var option = this.id == "from" ? "minDate" : "maxDate",
					instance = $( this ).data( "datepicker" ),
					date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date ); 
			} 
		}); */
		$.datepicker.setDefaults($.datepicker.regional['ko']);
	});

	function onCommand(com, grid) {
		switch (com) {
			case '등록':
				jQuery('#addWindow').dialog("open");
				break;
			case '삭제':
				jQuery('#courseList').flexReload();
				break;
		}	
	}

	function addCourse() {
		alert(decodeURIComponent(jQuery('#addForm').serialize()));
		jQuery.ajax({
			url:"/training/action/CourseAdmin/insertCourse",
			type:"GET",
			data: jQuery('#addForm').serialize(),
			error: function(xhr, status, error) {
				alert("status = " + xhr.status + ", error = " + error);
			},
			success: function(data) {
				alert(data); /*
				if (data == "true")
					alert("등록 되었습니다.");
				else
					alert("등록에 실패하였습니다."); */
				jQuery('#addWindow').dialog("close");
			}
		});
	}

	function chSort(sortname, sortorder) {
		jQuery('#courseList').flexOptions({
			sortname:sortname,
			sortorder:sortorder
		})
		jQuery('#courseList').flexReload();
	}

	function JSONtoString(object) {
	    var results = [];
	    for (var property in object) {
	        var value = object[property];
	        if (value)
	            results.push(property.toString() + ': ' + value);
	        }
	                 
	        return '{' + results.join(', ') + '}';
	}
</script>
</head>
<body>
<table id="courseList"></table>

<div id="addWindow" title="교육과정 등록" style="display:none">
	<form name="addForm" id="addForm">
	<table class="rtbl">
	<tr>
		<td class="rHead ui-corner-all">교육기관</td>
		<td class="rForm">
			<select name="institutionId" id="institutionId" class="text ui-widget-content" >
			</select>
			<input type="hidden" name="institutionName" id="institutionName" value="" />
		</td>
	</tr>
	<tr>
		<td class="rHead ui-corner-all">구분</td>
		<td class="rForm">
			<select name="courseTypeId" id="courseTypeId" class="text ui-widget-content" >
			</select>
			<input type="hidden" name="courseTypeName" id="courseTypeName" value=""/>
		</td>
	</tr>
	<tr>
		<td class="rHead ui-corner-all">과정명</td>
		<td class="rForm">
			<input type="text" name="courseName" id="courseName" value="" class="text ui-widget-content" />
		</td>
	</tr>
	<tr>
		<td class="rHead ui-corner-all">내용</td>
		<td class="rForm">
			<textarea type="text" name="courseInfomation" id="courseInfomation" class="text ui-widget-content"></textarea>
		</td>
	</tr>
	<tr>
		<td class="rHead ui-corner-all">교육시간</td>
		<td class="rForm">
			<input type="text" name="courseTime" id="courseTime" class="text ui-widget-content" />
		</td>
	</tr>
	<tr>
		<td class="rHead ui-corner-all">교육기간</td>
		<td class="rForm">
			<input type="text" anme=courseTerm" id="courseTerm" class="text ui-widget-content" />
		</td>
	</tr>
	<tr>
		<td class="rHead ui-corner-all">교육비</td>
		<td class="rForm">
			<input type="text" name="coursePrice" id="coursePrice" class="text ui-widget-content" />
		</td>
	</tr>
	<tr>
		<td class="rHead ui-corner-all">신청기간</td>
		<td class="rForm">
			<input type="text" anme=courseStart" id="courseStart" class="text ui-widget-content dateInput" /> ~ 
			<input type="text" anme=courseEnd" id="courseEnd" class="text ui-widget-content dateInput" />
		</td>
	</tr>
	</table>
	</form>
</div>
</body>
</html>