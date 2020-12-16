<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>

<!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 --> 
<%@ include file="/pages/common/header.jsp" %>
<script type="text/javascript">
	$(function(){
		// 绑定删除
		$(".delete").click(function(){
			
			var name = $(this).parents("tr").children(":first").html();	
			var id = $(this).attr("deleteId");
			
			if (confirm("确定删除图书【"+name+"】吗？")){
				// 设置当前页面的url
				location.href = "bookManage/delete?id="+id;
			}
			
			return false;
		});
		
	});

</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		
		<!-- 这是manager管理模块的共同菜单  -->
		<%@ include file="/pages/common/manager_menu.jsp" %>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
			<c:forEach items="${page.list }" var="book">
				<tr>
					<td>${book.title }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<td><a href="bookManage/edit?id=${book.id }">修改</a></td>
					<td><a deleteId="${book.id }" class="delete" href="#">删除</a></td>
				</tr>	
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
		
		<!-- 分页条的引入 -->
		<%@ include file="/pages/common/page.jsp" %>
		
	</div>
	
	<!-- 这是页脚的引入 -->
	<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>