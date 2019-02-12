<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>后台管理-admin2.0</title>
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
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form">
          <div class="layui-form-item">
              <label for="title" class="layui-form-label">
                	  标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="title" name="title" value="${descDaily.title }"
                  autocomplete="off" class="layui-input" disabled>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="context" class="layui-form-label">
                  	内容
              </label>
              <div class="">
                  <textarea style="min-width:500px;min-height:200px;max-width:500px;max-height:200px" class="layui-textarea">${descDaily.context }
                  </textarea>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="record" class="layui-form-label">
                  	感言
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="record" name="record" value="${descDaily.records }" style="width:500px"
                  autocomplete="off" class="layui-input" disabled>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="pic" class="layui-form-label">
                  	图片
              </label>
              <div class="layui-input-inline">
                  <img width="220px" height="220px" style="border:1px solid #D2D2D2;"/>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="uname" class="layui-form-label">
                  	用户
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="uname" name="uname" value="${user.uname }"
                  autocomplete="off" class="layui-input" disabled>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="createtime" class="layui-form-label">
                  	发表时间
              </label>
              <div class="layui-input-inline">
              	<input type="text" id="uname" name="uname" value='<fmt:formatDate value="${descDaily.createtime }" pattern="yyy-MM-dd HH:mm:ss"/>'
                  autocomplete="off" class="layui-input" disabled>
                  <p></p>
              </div>
          </div>
      </form>
    </div>
    <script>
      layui.use(['form','layer'], function(){
          $ = layui.jquery;
        var form = layui.form
        ,layer = layui.layer;
      
        //自定义验证规则
        form.verify({
          nikename: function(value){
            if(value.length < 5){
              return '昵称至少得5个字符啊';
            }
          }
          ,pass: [/(.+){6,12}$/, '密码必须6到12位']
          ,repass: function(value){
              if($('#L_pass').val()!=$('#L_repass').val()){
                  return '两次密码不一致';
              }
          }
        });

        //监听提交
        form.on('submit(add)', function(data){
          console.log(data);
          //发异步，把数据提交给php
          layer.alert("增加成功", {icon: 6},function () {
              // 获得frame索引
              var index = parent.layer.getFrameIndex(window.name);
              //关闭当前frame
              parent.layer.close(index);
          });
          return false;
        });
        
        
      });
  </script>
  </body>

</html>