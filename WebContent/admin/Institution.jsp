<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교육기관 관리</title>
<link rel="stylesheet" type="text/css" href="/training/common/css/flexigrid/flexigrid.css">
<link rel="stylesheet" type="text/css" href="/training/common/css/theme/redmond/jquery-ui-1.8.11.custom.css">
<style>

body { font-size: 62.5%; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
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
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery("#institutionList").flexigrid({
			url: '/training/action/InstitutionAdmin/getInstitutionList',
			dataType: 'json',
			colModel : [
				{display: '번호', name : 'num', width : 40, sortable : false, align: 'center'},
				{display: '교육기관', name : 'institutionName', width : 200, sortable : true, align: 'left'},
				{display: '주소', name : 'institutionAddress', width : 320, sortable : true, align: 'left'},
				{display: '연락처', name : 'institutionCall', width : 120, sortable : true, align: 'center'}
				],
			buttons : [
				{name: '등록', bclass: 'add', onpress : onCommand},
				{name: '수정', bclass: 'mod', onpress : onCommand},
				{name: '삭제', bclass: 'delete', onpress : onCommand},
				{separator: true}
				],
			searchitems : [
				{display: '교육기관명', name : 'institutionName', isdefault: true},
				{display: '연락처', name : 'institutionCall'}
				],
			sortname: "institutionName",
			sortorder: "asc",
			usepager: true,
			title: '교육기관 리스트',
			useRp: true,
			onChangeSort: chSort,
			rp: 10,
			showTableToggleBtn: true,
			height: 252
		});

		jQuery('#addWindow').dialog({
			autoOpen: false,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
				"등록": function() {
					addInstitution();
				},
				"취소": function() {
					$( this ).dialog("close");
				}
			},
			close: function() {
				jQuery("#addForm input").val("").removeClass( "ui-state-error" );
			}
		});
	});
/*		
	function test(com,grid)
	{
		if (com=='Delete')
			{
				confirm('Delete ' + $('.trSelected',grid).length + ' items?')
			}
		else if (com=='등록')
			{
				alert('Add New Item');
			}			
	}
*/
	function onCommand(com, grid) {
		switch (com) {
			case '등록':
				jQuery('#addWindow').dialog("open");
				break;
			case '삭제':
				deleteInstitution(grid);
				break;
		}	
	}

	function addInstitution() {
		jQuery.ajax({
			url:"/training/action/InstitutionAdmin/insertInstitution",
			type:"GET",
			data: jQuery('#addForm').serialize(),
			error: function(xhr, status, error) {
				alert("status = " + xhr.status + ", error = " + error);
			},
			success: function(data) {
				jQuery('#addWindow').dialog("close");
				if (data == true)
					alert("등록 되었습니다.");
				else
					alert("등록에 실패하였습니다.");
				jQuery('#institutionList').flexReload();
			}
		});
	}

	function deleteInstitution(grid) {
		if (jQuery('.trSelected',grid).length <= 0) {
			alert("삭제할 기관을 선택하여 주십시오.");
		} else {
			if (confirm(jQuery('.trSelected',grid).length + "개 기관을 정말 삭제 하시겠습니까?")) {
				var ids = "";
				jQuery.each(jQuery('.trSelected',grid), function(i, row) {
					ids += row.id.substr(3,row.id.length) + ",";
				});
				ids = "institutionIds="+ ids.substr(0, ids.length-1);
				jQuery.ajax({
					url:"/training/action/InstitutionAdmin/deleteInstitution?"+ids,
					type:"GET",
					error: function(xhr, status, error) {
						alert("status = " + xhr.status + ", error = " + error);
					},
					success: function(data) {
						jQuery('#addWindow').dialog("close");
						if (data > 0)
							alert("삭제 되었습니다.");
						else
							alert("삭제에 실패하였습니다.");
						jQuery('#institutionList').flexReload();
					}
				});
			}
		}
	}

	function chSort(sortname, sortorder) {
		jQuery('#insitutionList').flexOptions({
			sortname:sortname,
			sortorder:sortorder
		});
		jQuery('#institutionList').flexReload();
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
<table id="institutionList"></table>

<div id="addWindow" title="교육기관 등록" style="display:none">
	<form name="addForm" id="addForm">
	<fieldset>
		<label for="inputNmae">교육기관명</label>
		<input type="text" name="inputName" id="inputName" class="text ui-widget-content ui-corner-all" />
		<label for="inputAddress">주소</label>
		<input type="text" name="inputAddress" id="inputAddress" value="" class="text ui-widget-content ui-corner-all" />
		<label for="inputCall">연락처</label>
		<input type="text" name="inputCall" id="inputCall" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
</body>
</html>