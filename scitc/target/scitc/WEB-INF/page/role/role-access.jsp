<%@page language="java" contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
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
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">
                    拥有权限
                </label>
                <table  class="layui-table layui-input-block">
                    <tbody class="accessBody">
                    <tr>
                        <td>
                            <input type="checkbox" name="like1[write]" lay-skin="primary" title="用户管理">
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="id[]" lay-skin="primary" type="checkbox" title="用户停用" value="2">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户删除">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户修改">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户改密">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户列表">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户改密">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户列表">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户改密">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="用户列表">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>

                            <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="文章管理">
                        </td>
                        <td>
                            <div class="layui-input-block">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="文章添加">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="文章删除">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="文章修改">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="文章改密">
                                <input name="id[]" lay-skin="primary" type="checkbox" value="2" title="文章列表">
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
                <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="edit">更新权限</button>
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
                // 获取所有选中的checkBox的值
                var ids = "";
                $("input:checked").each(function () {
                    ids+=$(this).val()+",";
                });

              $.ajax({
                  type:"post",
                  url:"${pageContext.request.contextPath}/role/editAccess.action",
                  data:{roleId:${param.id},ids:ids},
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

            var roleAccessList = null;
            // 获取角色的权限
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/access/getRoleAccess.action",
                async: false,
                data:{id:${param.id}},
                dataType:"json",
                success:function(data){
                    if(data.code == 200){
                        roleAccessList = data.data;
                    }
                }
            });

            console.log(roleAccessList);

            // 获取所有的权限
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/access/getAllAccess.action",
                async: false,
                dataType:"json",
                success:function(data){
                    if(data.code == 200){
                         $(".accessBody").empty();
                         $(data.data).each(function () {
                             // 第一层
                             var tr = $('<tr>\n' +
                                '    <td>\n' +
                                '        <input type="checkbox" '+(checkAccess(this.parent.id,roleAccessList) ? "checked" : "")+' name="accessId" lay-skin="primary" value="'+this.parent.id+'" title="'+this.parent.accessName+'">\n' +
                                '    </td>\n' +
                                '</tr>');
                             //第二层
                             var td = $('<td><div class="layui-input-block"></div></td>');
                             $(this.child).each(function () {
                                var checkbox = $('<input name="accessId" '+(checkAccess(this.id,roleAccessList) ? "checked" : "")+' lay-skin="primary" type="checkbox" title="'+this.accessName+'" value="'+this.id+'">');
                                //追加
                                td.find("div").append(checkbox);
                             });
                             tr.append(td);
                             $(".accessBody").append(tr);
                             // 重新渲染页面
                             renderForm();
                         });
                    }
                }
            });
        });


        /**
         * 判断是否有该权限
         * @param id : 权限ID
         * @param roleAccessList : 角色的权限id的列表
         */
        function checkAccess(id,roleAccessList) {
            var res = false;
            $(roleAccessList).each(function () {
                if(id == this.id){
                    res = true;
                    return;
                }
            });

            return res;
        }

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