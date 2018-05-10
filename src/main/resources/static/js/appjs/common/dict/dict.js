var prefix = "/common/dict/";
$(function () {
    load();
});

function load() {
    loadType();     //1. 加载类型
    loadListData();
}

function loadType() {
    var html = "";
    $.get(prefix + 'type', function (data) {
        for (var i = 0; i < data.length; i++) {
            html += '<option value="' + data[i].type + '">' + data[i].description + '</option>'
        }
        $(".chosen-select").append(html);
        $(".chosen-select").chosen({
            maxHeight: 200
        });
        /**
         * 下拉事件
         */
        $('.chosen-select').on('change', function(e, params) {
            console.log(params.selected);
            var opt = {
                query : {
                    type : params.selected,
                }
            }
            $('#exampleTable').bootstrapTable('refresh', opt);
        });
    }, 'json');
}

function loadListData() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method: 'post',
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        url: prefix + 'list',
        toolbar : '#exampleToolbar',
        dataType: 'json',
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        pageSize : 10, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        pagination : true, // 设置为true会在底部显示分页条
        striped : true, // 设置为true会有隔行变色效果
        singleSelect : true, // 设置为true将禁止多选
        iconSize : 'outline',
        queryParams : function(params) {
            var params = {
                //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                limit : params.limit,
                offset : params.offset,
                // name:$('#searchName').val(),
                type : $('#searchName').val(),
            };
            return params;
        },
        columns: [
            {checkbox: true},
            {field: 'id', title: '编号'},
            {field: 'name', title: '标签名'},
            {field: 'value', title: '数据值', width: '100px'},
            {field: 'type', title: '类型'},
            {field: 'description',title: '描述'},
            {visible: false,field: 'sort',title: '排序（升序）'},
            {visible: false,field: 'parentId',title: '父级编号'},
            {visible: false,field: 'createBy',title: '创建者'},
            {visible: false,field: 'createDate',title: '创建时间'},
            {visible: false,field: 'updateBy',title: '更新者'},
            {visible: false, field: 'updateDate',title: '更新时间' },
            {visible: false,field: 'remarks',title: '备注信息'},
            {visible: false,field: 'delFlag',title: '删除标记' },
            {title: '操作',field: 'id',align: 'center', formatter: function (value, row, index) {
                    var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                        + row.id
                        + '\')"><i class="fa fa-edit"></i></a> ';
                    var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                        + row.id
                        + '\')"><i class="fa fa-remove"></i></a> ';
                    var f = '<a class="btn btn-success btn-sm ' + s_add_h + '" href="#" title="增加"  mce_href="#" onclick="addD(\''
                        + row.type + '\',\'' + row.description
                        + '\')"><i class="fa fa-plus"></i></a> ';
                    return e + d + f;
                }
            }]
    });
}

/**
 * 查询事件
 */
function reLoad() {
    var opt = {
        query : {
            type : $('.chosen-select').val(),
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);
}

function add() {
    layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + 'add' // iframe的url
    });
}