<%@page language="java" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>权限列表</title>
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
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">权限</a>
        <a>
          <cite>权限列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so layui-form-pane">
          <div class="layui-input-inline">
            <select name="accessParentId"  lay-filter="parent">
              <option value="0">父级</option>

            </select>
          </div>
          <div class="layui-input-inline">
            <select name="accessLevel">
              <option value="0">等级</option>
              <option value="1" selected>一级</option>
              <option value="2">二级</option>
            </select>
          </div>
          <div class="layui-input-inline">
            <select name="isMenu">
              <option>是否菜单</option>
              <option value="1" selected>是</option>
              <option value="0">否</option>

            </select>
          </div>
          <input class="layui-input" placeholder="权限URL" name="accessUrl" >
          <input class="layui-input" placeholder="权限名" name="accessName" lay-verify="required" >
          <button class="layui-btn" lay-submit lay-filter="add"><i class="layui-icon"></i>增加</button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <span class="x-right" style="line-height:40px">共有数据：${pageUtil.totalCount} 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>权限名称</th>
            <th>权限规则</th>
            <th>权限等级</th>
            <th>权限父级</th>
            <th>是否页面</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${pageUtil.contentList}" var="content">
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${content.id}'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${content.id}</td>
            <td>${content.accessName}</td>
            <td>${content.accessUrl}</td>
            <td>${content.accessLevel==1 ? "一级" : "二级"}</td>
            <td>${content.accessParentId}</td>
            <td>${content.isMenu==0 ? "否" : "是"}</td>
            <td class="td-manage">
              <a title="编辑"  onclick="x_admin_show('编辑','${pageContext.request.contextPath}/access/goUpdate?id=${content.id}')" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="member_del(this,${content.id})" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <a class="prev" href="${pageContext.request.contextPath}/access/access.action?page=${pageUtil.prev}">&lt;&lt;</a>
          <c:forEach items="${pageUtil.pageList}" var="page">
            <c:if test="${page ==  pageUtil.nowPage}">
              <span class="current">${page}</span>
            </c:if>
            <c:if test="${page != pageUtil.nowPage}">
              <a class="num" href="${pageContext.request.contextPath}/access/access.action?page=${page}">${page}</a>
            </c:if>
          </c:forEach>
          <a class="next" href="${pageContext.request.contextPath}/access/access.action?page=${pageUtil.next}">&gt;&gt;</a>
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

      /**
       * 单条删除
       */
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $.ajax({
                  type:"post",
                  url:"${pageContext.request.contextPath}/access/delAccess",
                  data:{id:id},
                  dataType:"json",
                  success:function (data) {
                      if(data.code == 200){
                          $(obj).parents("tr").remove();
                      }
                      layer.msg(data.msg,{icon:1,time:1000});
                  }
              })
          });
      }

      /**
       * 批量删除
       * @param argument
       */
      function delAll (argument) {

        var ids = tableCheck.getData();
  
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/access/batchDelete",
                data:{ids:ids},
                dataType:"json",
                success:function (data) {
                    if(data.code == 200){
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                    }
                    layer.msg(data.msg,{icon:1,time:1000});
                }
            })

        });

      }


      $(function () {

          // 添加权限
          layui.use('form', function(){
              var form = layui.form;

              // 选中父级 等级是一级
              // 选中非父级 等级是二级
              form.on('select(parent)', function(data){
                  console.log(data.value);
                  if(data.value == 0){
                      $("select[name=accessLevel]").val(1);
                  }else{
                      $("select[name=accessLevel]").val(2);
                  }
                  // 需要从新渲染 下拉框
                  layui.form.render('select');
              });

              form.on('submit(add)', function(data){
                  $.ajax({
                      type:"post",
                      url:"${pageContext.request.contextPath}/access/add.action",
                      data:data.field,
                      dataType:"json",
                      success:function(serverData){
                          layer.msg(serverData.msg,function () {
                              // 自动刷新
                              location.replace(location.href);
                          });
                      }
                  });
                  return false;
              });
          });
          // 找到所有的父级权限
          $.ajax({
              type:"post",
              url:"${pageContext.request.contextPath}/access/parents.action",
              dataType:"json",
              success:function (data) {
                  if(data.code == 200){
                      $(data.data).each(function () {
                          var option = $("<option value='"+this.id+"'>"+this.accessName+"</option>");
                          $("select[name=accessParentId]").append(option);
                      })
                      // 重新渲染页面
                      renderForm();
                  }
              }
          })

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