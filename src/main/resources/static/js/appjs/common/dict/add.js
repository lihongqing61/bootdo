/*
$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
	submitHandler: function () {
		save();
    }
});

function save () {
	$.ajax({
        cache : true,
        type : "POST",
        url : "/common/dict/save",
        data : $('#signupForm').serialize(), // 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("网络超时");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }

        }
	});
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入名字"
            }
        }
    })
}
*/



$(document).ready(function()
{
    initPlsfList();
});
//初始化grid列表
function initPlsfList(){
    //缓存表列数组,key 为页面元素的name ，value 对应数据库中的字段
    var cellArray = new Array();
    cellArray["zoneID"] ="ZONE_ID";
    cellArray["factorPG"] ="FACTOR_PG";
    cellArray["factorQG"] ="FACTOR_QG";
    cellArray["factorPL"] ="FACTOR_PL";
    cellArray["factorQL"] ="FACTOR_QL";
    cellArray["valid"] ="VALID";


    var caseID = '${caseID}';
    $("#plsfList").jqGrid({
        url:"<c:url value='/lfc/powerlsfactor/queryData?caseID="+caseID+"' />",
        datatype: "json",
        mtype:"POST",
        height: "auto",
        width: "auto",
        colNames:[
            'id',
            "<fmt:message key='case.valid'/>",
            "<fmt:message key='zone'/>",
            "<fmt:message key='pasf'/>",
            "<fmt:message key='pisf'/>",
            "<fmt:message key='lasf'/>",
            "<fmt:message key='lisf'/>",
            'modeID'
        ],
        colModel:[
            {name:'id',index:'id', width:100,hidden:true},
            {name:'valid',index:'valid', width:100,editable:true,
                formatter:formatValid,
                editable:true,edittype:'checkbox',
                editoptions:{value:'1:0',defaultValue:'1'}
            },
            {name:'zoneName',index:'zoneName', width:150,editable:true},
            {name:'factorPG',index:'factorPG', width:100,editable:true},
            {name:'factorQG',index:'factorQG', width:100,editable:true},
            {name:'factorPL',index:'factorPL', width:100,editable:true},
            {name:'factorQL',index:'factorQL', width:100,editable:true},
            {name:'caseID',index:'caseID', width:100,hidden:true},
        ],
        rowNum:10,
        rowList:[10,20,30],
        pager: '',
        cellEdit:true,
        viewrecords: true,
        jsonReader: { repeatitems : false, id: "id" },
        viewsortcols:[false,'horizontal',false],
        sortable:false,
        sortorder:"asc",
        sortname:"id",
        multiselect: true,
        cellurl:"<c:url value='/lfc/powerlsfactor/save'/>",
        cellsubmit: 'remote',
        gridComplete: function() {
            var $selecAll = $("#cb_plsfList");
            var cb_title = "<fmt:message key='select.all' bundle='${commonResources}'/>" ;
            if($selecAll){
                $selecAll.attr("title",cb_title);
            }
            //设置全选checkbox title
            var rowIds = jQuery("#plsfList").jqGrid('getDataIDs');
            for(var k=0; k<rowIds.length; k++) {
                var curRowData = jQuery("#plsfList").jqGrid('getRowData', rowIds[k]);
                var curChk = $("#"+rowIds[k]+"").find(":checkbox");
                //curChk.attr('title', curRowData.modeName);   //给checkbox赋予额外的属性值
            }

        },
        onSortCol:function(index,iCol,sortorder){
            return false ;
        },
        ondblClickRow: function (rowid,iRow,iCol,e) {
            /*var $plsfList = $("#plsfList");
            if (isRowNeedSave($plsfList)){
             showMessage("请先保存");
            }else{
             $("#operate").val("update");
             newrowid = rowid ;
             $plsfList.setGridParam({cellEdit:false});
             $plsfList.jqGrid('editRow', rowid, true);
             //确定按钮可用
                $("#confirm_btn").attr("disabled",false);

            }*/
        },
        beforeSubmitCell:function(rowid, cellname, value, iRow, iCol){
            //列提交前的拦截方法
            var $plsfList = $("#plsfList") ;
            var $editUrl = '<c:url value='/lfc/powerlsfactor/save'/>' ;
            //设置列提交的url。updateCellName：要编辑的列名 ；updateCellValue ：是编辑的值
            $editUrl = addParamToUrl($editUrl,'updateCellName',iCol == 3 ? cellArray['zoneID'] :cellArray[cellname]);
            $editUrl = addParamToUrl($editUrl,'updateCellValue',iCol == 3 ? $("#zone_id").val():value);
            //给jqgrid 从新设置cellurl 值
            $plsfList.setGridParam({cellurl:$editUrl});
            return false ;
        },
        afterEditCell:function(rowid, cellname, value, iRow, iCol){
            //动态修改lie时，当列 变为可修改状态时，给列add一个button，且列中元素不可编辑，点击button  弹出一个模态窗口，可以选择元素 ，赋值给grid当前编辑列中单行表单域中.
            $("#"+rowid+" input[type='checkbox']").attr("checked",value == "<fmt:message key='case.valid'/>" ? true:false);
            if(iCol==3){
                $("#irowNum").val(rowid);
                var $data = $("#"+rowid +">td"); //获取这个行里所有的td元素，即：获取所有子元素
                $zoneInput = $data.find("input").eq("1") ;
                $zoneInput.css("width","100px");
                $zoneInput.attr("disabled",true);
                $zoneInput.after("<input type='button' value='选择' onclick='fnCallDialogForEidt()' />");
            }
        }

    });


//grid添加新的一行
    var newrowid ;
    function addRow()
    {
        $("#operate").val("");
        var selectedId = $("#plsfList").jqGrid("getGridParam", "selrow");
        var ids = jQuery("#plsfList").jqGrid('getDataIDs');
        //获得当前最大行号（数据编号）
        var rowid = Math.max.apply(Math,ids);
        //获得新添加行的行号（数据编号）
        newrowid = rowid+1;
        var dataRow = {
            id: "",
            valid:"",
            zoneID:'',
            factorPG:'',
            factorQG:'',
            factorPL:'',
            factorQL:'',
            caseID:''
        };

        //将新添加的行插入到第一列
        $("#plsfList").jqGrid("addRowData", newrowid, dataRow, "first");
        //设置grid单元格不可编辑
        $("#plsfList").setGridParam({cellEdit:false});
        //设置grid单元格可编辑
        $('#plsfList').jqGrid('editRow', newrowid, false);
        //确定按钮可用
        $("#confirm_btn").attr("disabled",false);
        //给添加的列加选择按钮
        var $zoneInput = $("#"+newrowid+"_zoneName");
        $zoneInput.attr("disabled",true).css("width",100);
        $zoneInput.after("<input type='button' value='选择' onclick='fnCallDialogForEidt()' />");

    }


    function insertPlsf(){
        var $plsfList = $("#plsfList") ;
        var $operate = $("#operate").val();
        //设置grid单元格可编辑
        $plsfList.setGridParam({cellEdit:true});
        //设置grid行不可编辑
        //$plsfList.jqGrid('editRow', newrowid, false);
        //拼接请求的url
        var url = '<%=basePath%>'+"/lfc/powerlsfactor/save" ;
        var $params = $plsfList.find("input[id^="+newrowid+"]");
        var $check_val = $params.eq(0).is(':checked') ? 1:0;
        url = addParamToUrl(url,'valid',$check_val);
        url = addParamToUrl(url,'zoneID',$("#zone_id").val());
        url = addParamToUrl(url,'factorPG',$params.eq(2).val());
        url = addParamToUrl(url,'factorQG',$params.eq(3).val());
        url = addParamToUrl(url,'factorPL',$params.eq(4).val());
        url = addParamToUrl(url,'factorQL',$params.eq(5).val());

        var $caseID = $("#caseID").val();
        url = addParamToUrl(url,'caseID',$caseID);

        $.ajax({url:url,type:"post",timeout:5000,
            success:function(data){
                showMessage(data);
                reloadGrid();
            }
        });
        //将新添加行号 初始为空
        newrowid = '' ;
        //确定按钮不可用
        $("#confirm_btn").attr("disabled",true);
    }


//格式zone列输出内容
    function formatZone(cellvalue, options, rowObject){
        if(cellvalue == 0){
            return 0;
        }else if(cellvalue == 1){
            return 1;
        }else if(cellvalue == 2){
            return 2;
        }else{
            return 3;
        }
    }


    function isRowNeedSave($jqgrid){
        var $editTr = $jqgrid.find("tr[editable=1]") ;
        var flag = false ;
        if ( $editTr && $editTr.length > 0){
            flag = true ;
        }
        return flag ;
    }


    function cancel(){
        reloadGrid();
        //确定按钮不可用
        $("#confirm_btn").attr("disabled",true);
        //设置grid单元格可编辑
        $("#plsfList").setGridParam({cellEdit:true});
        //设置grid单元格可编辑
        $('#plsfList').jqGrid('editRow', newrowid, true);
    }



    function fnCallDialogForEidt(){
        //获得当前行号（数据编号）
        var returnValue = "";
        returnValue = window.showModalDialog("<c:url value='/element/zone/query?caseID="+$("#caseID").val()+"' />",window,"");
        if(returnValue==""||returnValue==null)
            return;
        var $plsfList = $("#plsfList");
        var $params = $plsfList.find("input[id$='zoneName']");
        var names = returnValue.split(",");
        $params.eq(0).val(names[1]);
        $("#zone_id").val(names[0]);
    }