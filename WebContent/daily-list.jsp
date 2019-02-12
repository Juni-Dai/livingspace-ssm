<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="./js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
    	td div{
    		overflow: hidden;
		    text-overflow: ellipsis;
		    /* 兼容处理方式：后面使用透明图片进行遮挡 */
		    display: -webkit-box;
		    -webkit-line-clamp: 2;
		    -webkit-box-orient: vertical;
    	}
    </style>
  </head>
  
  <body>
  	
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
          <input class="layui-input" placeholder="开始日" name="start" id="start">
          <input class="layui-input" placeholder="截止日" name="end" id="end">
          <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','./daily-record.jsp')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：<c:out value="${dailyCustom.count }" default="0"/> 条</span>
      </xblock>
      <table class="layui-table layui-anim layui-anim-upbit">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th width="10%">ID</th>
            <th width="15%">标题</th>
            <th width="25%">内容</th>
            <th width="20%">感言</th>
            <th width="10%">图片</th>
            <th width="5%">用户</th>
            <th width="10%">发表时间</th>
            <th width="5%">操作</th>
        </thead>
        <tbody>
        	<c:if test="${not empty dailyCustom.dailyList }">
        		<c:forEach items="${dailyCustom.dailyList }" var="daily">
	          		<tr>
			            <td>
			              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${daily.did }'><i class="layui-icon">&#xe605;</i></div>
			            </td>
			           	<td>${daily.did }</td>
			           	<td>${daily.title }</td>
			           	<td><div>${daily.context }</div></td>
			           	<td><div>${daily.records }</div></td>
			           	<td>${daily.pic }</td>
			           	<td>${user.uname }</td>
			           	<td><fmt:formatDate value="${daily.createtime }" pattern="yyy-MM-dd HH:mm:ss"/></td>
			            <td class="td-manage">
			              <a title="查看"  onclick="x_admin_show('查看','dailyDescServlet?did=${daily.did}')" href="javascript:;">
			                <i class="layui-icon">&#xe642;</i>
			              </a>
			              <a title="删除" onclick="member_del(this,${daily.did })" href="javascript:;">
			                <i class="layui-icon">&#xe640;</i>
			              </a>
			            </td>
			         </tr>
	          	</c:forEach>
        	</c:if>
        </tbody>
      </table>
      <div class="page">
        <div>
          <c:if test="${dailyCustom.dailyList.size()>0 }">
          	<a class="prev" <c:if test="${dailyCustom.pageIndex != 1 }">href="dailyListServlet?pageIndex=${dailyCustom.pageIndex-1}"</c:if>>&lt;&lt;</a>
          	
          	<c:forEach begin="0" end="${dailyCustom.pageTotal-1 }" varStatus="list">
          		<c:if test="${dailyCustom.pageIndex==list.index+1 }">
          			<span class="current">${list.index+1}</span>
          		</c:if>
          		<c:if test="${dailyCustom.pageIndex!=list.index+1 }">
					<a class="num" href="dailyListServlet?pageIndex=${list.index+1}" >${list.index+1}</a>
				</c:if>
          		
          	</c:forEach>
          	<a class="next" <c:if test="${dailyCustom.pageIndex!=dailyCustom.pageTotal }">href="dailyListServlet?pageIndex=${dailyCustom.pageIndex+1}"</c:if>>&gt;&gt;</a>
          </c:if>
        </div>
      </div>

    </div>
    <script>
    	
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $.ajax({
            	  method:"post",
            	  url:"dailyDelServlet",
            	  data:{"delId":id},
            	  success:function(data){
            		  //
            	  }
              });
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000},function(){
            	  window.parent.location.href = "dailyListServlet?pageIndex=1";
              });
          });
      }



      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
        	
            //捉到所有被选中的，发异步进行删除
             $.ajax({
            	  url:"dailyDelServlet",
            	  data:{"delId":data},
            	  method:"post",
            	  traditional:true,
            	  dataType:"json",
            	  success:function(data){
            		  //
            	  }
              });
            layer.msg('删除成功', {icon: 1},function(){
            	window.parent.location.href = "dailyListServlet?pageIndex=1";
            });
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
  </body>

</html>