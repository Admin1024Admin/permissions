<%@page language="java" contentType="text/html;charset=utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>权限编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form">
          <input type="hidden" value="${access.id}" name="id">
          <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>父级
              </label>
              <div class="layui-input-inline">
                  <select name="accessParentId">
                      <option value="0">父级</option>

                  </select>
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="x-red">*</span>权限的父级
              </div>
          </div>
          <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>等级
              </label>
              <div class="layui-input-inline">
                  <select name="accessLevel">
                      <option value="0">等级</option>
                      <option value="1" ${access.accessLevel == 1 ? "selected" : ""}>一级</option>
                      <option value="2" ${access.accessLevel == 2 ? "selected" : ""}>二级</option>
                  </select>
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="x-red">*</span>权限的等级
              </div>
          </div>
          <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>是否菜单
              </label>
              <div class="layui-input-inline">
                  <select name="isMenu">
                      <option>是否菜单</option>
                      <option value="1" ${access.isMenu == 1 ? "selected" : ""}>是</option>
                      <option value="0" ${access.isMenu == 0 ? "selected" : ""}>否</option>
                  </select>
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="x-red">*</span>
              </div>
          </div>

          <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>权限的URL
              </label>
              <div class="layui-input-inline">
                  <input class="layui-input" value="${access.accessUrl}" placeholder="权限URL" name="accessUrl" >
              </div>
              <div class="layui-form-mid layui-word-aux">
                  权限的规则
              </div>
          </div>
          <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>权限的名字
              </label>
              <div class="layui-input-inline">
                  <input class="layui-input" value="${access.accessName}" required placeholder="权限名" name="accessName" >
              </div>
          </div>
          <div class="layui-form-item">
              <label class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="edit" lay-submit="">
                  编辑
              </button>
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
          form.on('submit(edit)', function(data){
              $.ajax({
                  type:"post",
                  url:"${pageContext.request.contextPath}/access/update",
                  data:data.field,
                  dataType:"json",
                  success:function(serverData){
                      layer.alert(serverData.msg, {icon: 6},function () {
                          // 获得frame索引
                          var index = parent.layer.getFrameIndex(window.name);
                          // 关闭当前frame
                          parent.layer.close(index);
                          // 刷新父级页面
                          parent.location.replace(parent.location.href);
                      });
                  }
              });
              return false;

          });
          
          
        });


        $(function () {


            // 获取父级id
            var parentId = ${access.accessParentId};

            // 找到所有的父级权限
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/access/parents",
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    if(data.code == 200){
                        console.log($("select[name=accessParentId]"));
                        $(data.data).each(function () {
                            var option = $("<option value='"+this.id+"' "+(this.id == parentId ? "selected" : "")+" >"+this.accessName+"</option>");
                            $("select[name=accessParentId]").append(option);
                            // 重新渲染页面
                            renderForm();
                        })
                    }
                }
            });
        });

        //重新渲染表单
        function renderForm(){
            layui.use('form', function(){
                var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
                form.render();
            });
        }
    </script>

  </body>

</html>