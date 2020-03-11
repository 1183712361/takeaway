<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${AppPath }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${AppPath }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${AppPath }/css/main.css">
	
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${loginUser.uname }<span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="${AppPath }/user/userInfo?uid=${loginUser.uid }"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="${AppPath }/user/index" style="color:red;"><i class="glyphicon glyphicon-user"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;" id="userForm">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" id="searchValue" name="queryVal" placeholder="请输入查询条件" >
    </div>
  </div>
  <button type="button" id="btnSearch" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" onclick="delUsers()" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${AppPath }/user/userAdd'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
<form id="delUserForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="checkAll" ></th>
                  <th>账号</th>
                  <th>名称</th>
                  <th>地址</th>
                  <th>状态</th>
                  <th>角色</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              
              	<tbody id="tableContent">
              
              	
            
               
              	</tbody>
             
			  <tfoot>
			  
			  
			      <tr >
				     <td colspan="6" align="center">
						<ul class="pagination"  id="page">
					
							 </ul>
					 </td>
				 </tr>  

			  
			  
			  </tfoot>
            </table>
            
            <input type="hidden" id="loginRid" name="rid" value="${loginUser.role.rid }">
           </form>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${AppPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${AppPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${AppPath }/script/docs.min.js"></script>
	<script src="${AppPath }/layer/layer.js"></script>
        <script type="text/javascript">
        var searchFlag=false;
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    
			    //条件查询
			    $("#btnSearch").click(function(){
			    	var searchValue=$("#searchValue").val();
			    	if(searchValue!=""){
			    		searchFlag=true;
			    	}
			    	 goPage(1);
			    });
			    
		           
				    //选择所有
		     		$("#checkAll").click(function(){
	            		var flag=$(this).prop("checked");
		            	$.each($("#tableContent :checkbox"),function(index,obj){
		            		obj.checked=flag;
		            	});
		            });
		            
				    //页面加载完成加载页面信息
		            goPage(1);
		            
            });
            
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            
           
          
            
            function goPage(currentPage){
            	var jsonData={"currentPage":currentPage};
            	if(searchFlag){
            		jsonData.queryVal=$("#searchValue").val();        		
            	}
            	var rid=$("#loginRid").val();
            	if(rid=="2"){
            	
	            	$.ajax({
	            		cache:false,
	            		url:"${AppPath }/user/userByPage",
	            		type:"post",
	            		data:jsonData,
	            		async:true,
	            		success:function(result){
	            			
	            			if(result.flag){
	            				
	             				var tableStr="";
	             			
	            				$.each(result.obj.data,function(i,user){
	            					  tableStr+="<tr>";
	            					  tableStr+="<td>"+(i+1)+"</td>";
	            					  tableStr+="<td><input type='checkbox'  name='uids' value="+user.uid+"></td>";
	            	                  tableStr+="<td>"+user.uaccount+"</td>";
	            	                  tableStr+="<td>"+user.uname+"</td>";
	            	                  tableStr+="<td>"+user.uaddress+"</td>";
	            	                  tableStr+="<td>"+user.ustatus+"</td>";
	            	                  tableStr+="<td>"+user.role.rname+"</td>";
	            	                  tableStr+="<td>";
	            					  tableStr+="<button type='button' onclick='assignRole("+user.uid+")' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
	            					  tableStr+="<button type='button' onclick='updateUser("+user.uid+")' class='btn btn-primary btn-xs'><i class=' glyphicon glyphicon-pencil'></i></button>";
	            					  tableStr+="<button type='button' onclick='deleteUser("+user.uid+")' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";
	            					  tableStr+="</td>";
	            					  tableStr+="</tr>";
	            					
	            				});
	            					  $("#tableContent").html(tableStr);
	            					  
	            					 
	            					  var pageStr="";
	            					  
	            					  pageStr+="<li class='"+(result.obj.currentPage==1?'disabled':'')+"'><a href='#' onclick='goPage("+((result.obj.currentPage-1)<=1?1:result.obj.currentPage-1)+")'>上一页</a></li>";
	            					  for (var i = 1; i <= result.obj.pages; i++) {
										if(i==result.obj.currentPage){
		            					  pageStr+="<li class='active'><a href='#' onclick='goPage("+(i)+")'>"+i+" <span class='sr-only'>(current)</span></a></li>";
										}else{
											
		            					  pageStr+="<li><a href='#' onclick='goPage("+(i)+")'>"+i+"</a></li>";
										}
									 }
	            					  pageStr+="<li class='"+(result.obj.currentPage==result.obj.pages?'disabled':'')+"'><a href='#' onclick='goPage("+((result.obj.currentPage+1)>=result.obj.pages?result.obj.pages:result.obj.currentPage+1)+")'>下一页</a></li>";
	            					  
	            					  $("#page").html(pageStr);
	            			}else{
	            				layer.msg("查询失败!", {time:1000, icon:0, shift:5}, function(){});
	            			}
	            				
	            		}
	            	});
            	}else{
            		  goBusiness();
            		  function goBusiness(){
                      	window.location.href="${AppPath}/menutype/index";
                      }
            		
            	}
	           
            }
            
            function updateUser(uid){
            	window.location.href="${AppPath}/user/userUpdForm?uid="+uid;
            }
            
        
            
        </script>
  </body>
</html>
