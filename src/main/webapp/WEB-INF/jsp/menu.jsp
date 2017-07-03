<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">

function doSearch(){
	$("#search").submit();
}
function searchMyDataBase(){
	var searchQuery=$('#searchQuery').val();
	//alert(tag);
	location.href = "${pageContext.request.contextPath}/movies_toMoviesList.html?query="+searchQuery;
}
</script>
<div class="header">
				<div class="logo">
					<a href="${pageContext.request.contextPath}/index.html"><h1>My Show</h1></a>
				</div>
				<div class="search">
					<div class="search2" style="display:inline;	float:left;width:100%">
					  <form id="search" action="${pageContext.request.contextPath}/movies_doSearch.html" method="post">
						<i class="fa fa-search"></i>
						 <input name="query" id="searchQuery" type="text" value="按影片名称搜索"  onfocus="this.value = '';" 
						 onblur="if (this.value == '') {this.value = '按影片名称搜索';}"/>
						 
					  </form>
					</div>
					<input type ="button" value="站外搜索" style="display:inline;width:25%;float:right;padding:1px" class="btn btn-default btn-xs" onclick="doSearch()"> 
					<input type ="button" value="站内搜索" style="display:inline;width:25%;float:right;padding:1px" class="btn btn-default btn-xs"  onclick="searchMyDataBase()"> 
				</div>
				<div class="clearfix"></div>
			</div>
	<div class="bootstrap_container">
            <nav class="navbar navbar-default w3_megamenu" role="navigation">
                <div class="navbar-header">
          			<button type="button" data-toggle="collapse" data-target="#defaultmenu" class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
          			<a href="${pageContext.request.contextPath}/index.html" class="navbar-brand"><i class="fa fa-home"></i></a>
				</div><!-- end navbar-header -->
        
            <div id="defaultmenu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html">主页</a></li>	
                    <!-- Mega Menu -->
					<li class="dropdown ">
						<a href="#" data-toggle="dropdown" class="dropdown-toggle">新闻<b class="caret"></b> </a>
                    		 <ul class="dropdown-menu" role="menu">

							<li><a href="${pageContext.request.contextPath}/news_toNewsList.html">全部新闻</a></li>
							<li><a href="${pageContext.request.contextPath}/news_toHotNewsList.html">热点新闻</a></li>
							</ul>
                    </li>
                    <li class="dropdown w3_megamenu-fw">
						<a href="${pageContext.request.contextPath}/movies_toMoviesList.html" class="active">影库</a>
                    </li>
                    <li class="dropdown w3_megamenu-fw">
						<a href="http://139.129.36.195:8080/shopdemo/index.action" class="active" target="_blank">商城</a>
                    </li>
                    <li class="dropdown w3_megamenu-fw">
						<a href="http://www.kuckboy.com/blogsys/public/index.php/index/index"  target="_blank"  class="active">博客</a>
                    </li>
                    
                    <!--  
					<li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Pages<b class="caret"></b></a>
							 <ul class="dropdown-menu" role="menu">

							<li><a href="about.html">About</a></li>
							<li><a href="blog.html">Blog</a></li>
							<li><a href="404.html">404</a></li>
							<li><a href="contact.html">Contact us</a></li>
							<li><a href="faq.html">FAQs</a></li>
							<li><a href="about.html">Current Openings</a></li>
                            <li class="dropdown-submenu">
                                <a href="#">News</a>
								<ul class="dropdown-menu">
																	<li><a href="press.html">Press Release</a></li>
	<li><a href="public-relations.html">Public Relations</a></li>
	<li><a href="press.html">Press Coverage</a></li>
								</ul><!-- end dropdown-menu -->
                            </li><!-- end dropdown-submenu -->
                        </ul>
                       <!-- end dropdown-menu -->
					</li><!-- end standard drop down -->
					-->
					<!-- end dropdown w3_megamenu-fw -->
                </ul><!-- end nav navbar-nav -->
                
				<ul class="nav navbar-nav navbar-right">
					
					<li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Contact Us<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <form id="contact1" action="#" name="contactform" method="post">
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <input type="text" name="name" id="name1" class="form-control" placeholder="Name"> 
                                        <input type="text" name="email" id="email1" class="form-control" placeholder="Email"> 
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <input type="text" name="phone" id="phone1" class="form-control" placeholder="Phone">
                                        <input type="text" name="subject" id="subject1" class="form-control" placeholder="Subject"> 
                                    </div>                 
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <textarea class="form-control" name="comments" id="comments1" rows="6" placeholder="Your Message ..."></textarea>
                                    </div>   
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="pull-right">
                                            <input type="submit" value="SEND" id="submit1" class="btn btn-primary small">
                                        </div>  
                                    </div>
									<div class="clearfix"></div>  
                                </form>
                            </li>
                        </ul>
					</li>
					
				</ul><!-- end nav navbar-nav navbar-right -->
			</div><!-- end #navbar-collapse-1 -->
            
			</nav><!-- end navbar navbar-default w3_megamenu -->
		</div><!-- end container -->
