<%@page language="java" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>角色列表</title>
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
        <a href="">角色</a>
        <a>
          <cite>角色列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form method="post" action="/role/list.action" class="layui-form layui-col-md12 x-so">
          <input type="text" name="roleName" placeholder="请输入角色名" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加角色','${pageContext.request.contextPath}/role/goAdd.action')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${pageUtil.totalCount} 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>角色名字</th>
            <th>角色描述</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${pageUtil.contentList}" var="data">
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${data.id}'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${data.id}</td>
            <td>${data.roleName}</td>
            <td>${data.roleDesc}</td>
            <td class="td-manage">
              <c:if test="${data.isSuper == 0}">
              <a title="权限分配"  onclick="x_admin_show('权限分配','${pageContext.request.contextPath}/role/editAccessPage.action?id=${data.id}')" href="javascript:;">
                <i class="iconfont">&#xe6ae;</i>
              </a>
              </c:if>
              <a title="编辑"  onclick="x_admin_show('编辑','${pageContext.request.contextPath}/role/goUpdate?id=${data.id}')" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <c:if test="${data.isSuper == 0}">
              <a title="删除" onclick="member_del(this,${data.id})" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
              </c:if>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <a class="prev" href="${pageContext.request.contextPath}/role/list.action?page=${pageUtil.prev}&${pageUtil.queryString}">&lt;&lt;</a>
          <c:forEach items="${pageUtil.pageList}" var="page">
            <c:if test="${page ==  pageUtil.nowPage}">
              <span class="current">${page}</span>
            </c:if>
            <c:if test="${page != pageUtil.nowPage}">
              <a class="num" href="${pageContext.request.contextPath}/role/list.action?page=${page}&${pageUtil.queryString}">${page}</a>
            </c:if>
          </c:forEach>
          <a class="next" href="${pageContext.request.contextPath}/role/list.action?page=${pageUtil.next}&${pageUtil.queryString}">&gt;&gt;</a>
        </div>
      </div>

    </div>
    <script>


      /*删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $.ajax({
                  type:"post",
                  url:"${pageContext.request.contextPath}/role/delete",
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
                  url:"${pageContext.request.contextPath}/role/deleteAll.action",
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
    </script>

  </body>

</html>