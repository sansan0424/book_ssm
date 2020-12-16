<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	// 查询指定页码
	$("#pn_button").click(function(){
		var num = $("#pn_input").val();
		location.href = "${url}pageNo="+num;
	});
});

</script>

<!-- 要求：连续展示5个页码 -->
<div id="page_nav">

	<a href="${url }pageNo=1">首页</a>
	<c:if test="${!page.isFirstPage }">
		<a href="${url }pageNo=${page.prePage }">上一页</a>
	</c:if>
	
	<!-- 循环展示页码 -->
	<c:forEach items="${page.navigatepageNums}" var="num">
		<c:if test="${page.pageNum == num }">
			【${num }】
		</c:if>
		<c:if test="${page.pageNum != num }">
			<a href="${url }pageNo=${num }">${num }</a>
		</c:if>
	</c:forEach>
	
	<c:if test="${!page.isLastPage }">
		<a href="${url }pageNo=${page.nextPage }">下一页</a>
	</c:if>
	<a href="${url }pageNo=${page.pages }">末页</a>
	共${page.pages }页，${page.total }条记录 到第<input name="pn" id="pn_input"/>页
	<input id="pn_button" type="button" value="确定">
</div>