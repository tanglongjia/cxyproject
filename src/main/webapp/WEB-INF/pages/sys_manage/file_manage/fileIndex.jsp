<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel">
			<div class="panel-heading bk-bg-primary">
				<h6>
					<i class="fa fa-indent red"></i>文件管理
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-2 control-label" for="input-small">文件名称：</label>
					<div class="col-sm-2">
						<input type="text" id="fileName" name="fileName"
							class="form-control input-sm" placeholder="文件名称" />
					</div>
					<label class="col-sm-2 control-label" for="input-small">文件类型：</label>
					<div class="col-sm-2">
						<select class="form-control" id="fileType" name="fileType">
							<option value="000">全部</option>
							<c:forEach items="${filedList}" var="field">
								<option value="${field['dicValue']}">${field['dicKey']}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-4" style="text-align: right">
						<button type="button" class="btn btn-success" onclick="search()">查询</button>
						<button id="btn_add" type="button" class="btn btn-success"
							data-toggle="modal" data-target="#fileModal">
							<span aria-hidden="true"></span>上传文件
						</button>
						<button id="btn_madd" type="button" class="btn btn-success"
							data-toggle="modal" data-target="#mfileModal">
							<span aria-hidden="true"></span>多文件上传
						</button>
						<button type="button" class="btn btn-success" onclick="deleteFile()">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 用户列表 -->
<div class="row" id="dataDiv"></div>

<!-- 上传文件模块-->
<div class="modal fade bs-example-modal-lg" id="fileModal" tabindex="0"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" id="btnCancel">&times;</button>
				<h4 class="modal-title" id="myModalLabel">上传文件</h4>
			</div>
			<form action="" method="post" enctype="multipart/form-data" id="fileForm">
				<div class="modal-body">
					<div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="file-input">上传文件：</label>
							<div class="col-md-4">
								<input type="file" id="file" name="file">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2  control-label">文件类型：</label>
							<div class="col-lg-4 ">
								<select class="form-control" id="addFileType" name="addFileType">
									<c:forEach items="${filedList}" var="field">
										<option value="${field['dicValue']}">${field['dicKey']}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer" style="border-top:none;margin-top: 10px;">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="btnClose">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="ajaxSubmitForm()">保存</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 多文件上传 -->
<div class="modal fade bs-example-modal-lg" id="mfileModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg" style="width: 950px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" id="btnMCancel">&times;</button>
				<h4 class="modal-title" id="myMModalLabel">多文件上传</h4>
			</div>
			<form action="" method="post" enctype="multipart/form-data" id="mfileForm">
				<div class="modal-body">
					<div>
				        <div class="row form-group">
				            <label class="col-lg-2">文件上传:</label>
				            <div class="col-lg-12">
				                <input id="input-id" name="mfile" multiple type="file" data-show-caption="true">
				            </div>
				        </div>
						<div class="form-group">
							<label class="col-lg-2  control-label">文件类型：</label>
							<div class="col-lg-4 ">
								<select class="form-control" id="addMFileType" name="addMFileType">
									<c:forEach items="${filedList}" var="field">
										<option value="${field['dicValue']}">${field['dicKey']}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer" style="border-top:none;margin-top: 10px;">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="btnMClose">关闭</button>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>





<script type="text/javascript">
	search();
	function search(pageid){
		if(pageid==undefined){
			pageid=1;
		}
		$.ajax({
			url:'<%=basePath%>bsFile/fileData',
			data:{
				fileName:$("#fileName").val(),
				fileType:$("#fileType").val(),
				page:pageid
			},
			type:'post',
			success:function(msg){
				$("#dataDiv").html(msg);
				$("#fileTable").bootstrapTable({
					totalRows:$("#pageTotal").val(),
					pagination : true, //启动分页  
					pageSize : 10, //每页显示的记录数  
					pageNumber : pageid, //当前第几页  
					pageList : [10], //记录数可选列表  
					onlyInfoPagination : false,
					sidePagination : "server", //表示服务端请求  
					onPageChange : function(number,size){
						search(number);
					},
					onClickRow:function(item, $element){
						$("#fileId").val(item[0]);
					}
				});
			},
			error:function(err){
			
			}
		});
	}
	
	
	function deleteFile(){
		var fileId = $("#fileId").val();
		if(fileId == null || fileId ==''){
			toastr.warning("请选择要删除的行！");
			return ;
		}
		
		$.ajax({
			url:'<%=basePath%>bsFile/delFile',
			data:{
				id:fileId
			},
			type:'post',
			success:function(msg){
				toastr.success("删除成功！");
				search();
			},
			error:function(err){
				toastr.error("删除失败！");
			}
		});
	}
	
	 function ajaxSubmitForm() {
		var option = {
			url : '<%=basePath%>bsFile/upload',
			type : 'POST',
 			dataType : 'json',
 			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$("#fileModal").modal('hide');
				toastr.info("上传文件成功！");
				search();
          	},
          	error: function(data) {
          		
          	}
       };
      $("#fileForm").ajaxSubmit(option);
      return false; //最好返回false，因为如果按钮类型是submit,则表单自己又会提交一次;返回false阻止表单再次提交
  	}
  	
  	$(function () {
        initFileInput("input-id");
    })

    function initFileInput(ctrlName) {
        var control = $('#' + ctrlName);
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: '<%=basePath%>bsFile/mupload', //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            uploadExtraData:{fileType:$("#addMFileType").val()},
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            //dropZoneEnabled: true,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            //maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

        }).on('filepreupload', function(event, data, previewId, index) {     //上传中
            var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
            console.log('文件正在上传');
        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            console.log('文件上传成功！'+data.id);

        }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
            console.log('文件上传失败！'+data.id);

        })
    }
  	 
</script>