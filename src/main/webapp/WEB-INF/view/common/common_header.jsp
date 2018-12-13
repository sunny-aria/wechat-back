<%--
  Created by IntelliJ IDEA.
  User: sunny
  Date: 2018/7/27
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--navbar-static-top 静态头部  navbar-inverse  反色导航栏-->
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">天睿科技</a>
        </div>

        <!--靠左-->
        <ul class="nav navbar-nav navbar-left">
            <li class="active"><a href="#">用户列表</a></li>
            <li><a href="#">用户群组</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    其他 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">图文消息</a></li>
                    <li><a href="#">创建菜单</a></li>
                    <li class="divider"></li>
                    <li><a href="#">创建菜单</a></li>
                    <li class="divider"></li>
                    <li><a href="#">图文消息</a></li>
                </ul>
            </li>
        </ul>


        <!--靠右-->
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
        </ul>
        <p class="navbar-text navbar-right">Runoob 用户登录</p>
    </div>
</nav>


