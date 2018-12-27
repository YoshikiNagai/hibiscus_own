<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="./css/headerFooter.css">

<s:if test="#session.mCategoryDtoList == null">
	<s:form action="HomeAction" class="homeAction" />
</s:if>
<s:else>
	<div class="hafcss-header">
		<div class="hafcss-title hafcss-float-left">
			<a href='<s:url action="HomeAction"/>'>Hibiscus</a>
		</div>

		<s:form action="SearchItemAction" class="hafcss-search-form hafcss-float-left">
			<s:select name="categoryId" list="#session.mCategoryDtoList" listValue="categoryName" listKey="categoryId" class="hafcss-select hafcss-button-margin" />

			<s:textfield class="hafcss-search-bar hafcss-button-margin" name="keywords" value="%{keywords }" placeholder="検索ワード"/>

			<s:submit id="submit" value="商品検索" class="hafcss-button-margin"/>
		</s:form>

		<s:if test="#session.logined == 0">
			<s:form action="GoLoginAction" class="hafcss-float-left hafcss-button-margin">
				<s:submit id="submit" value="ログイン" />
			</s:form>
		</s:if>
		<s:else>
			<s:form action="LogoutAction" class="hafcss-float-left hafcss-button-margin">
				<s:submit id="submit" value="ログアウト" />
			</s:form>
		</s:else>

		<s:form action="CartAction" class="hafcss-float-left hafcss-button-margin">
			<s:submit id="submit" value="カート" />
		</s:form>

		<s:form action="ProductListAction" class="hafcss-float-left hafcss-button-margin">
			<s:submit id="submit" value="商品一覧" />
		</s:form>

		<s:if test="#session.logined == 1">
			<s:form action="MyPageAction" class="hafcss-float-left hafcss-button-margin">
				<s:submit id="submit" value="マイページ" />
			</s:form>
		</s:if>
	</div>
</s:else>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("form.homeAction").submit();
	});

	$(window).on("load resize", function(){
		if($(window).width() > 1100){
			$(".hafcss-header").css("width", "100%");
		}else{
			$(".hafcss-header").css("width", "1100px");
		}
	});
</script>
