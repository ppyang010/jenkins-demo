<#assign basePath=request.contextPath />
<script type="text/javascript">
	//定位
	$(function(){
		//省市初始化
		setup();
		
		//$("#localcity").html('北京市');
		function myFun(result){
			var cityName = result.name;
			$("#userCity").html(cityName);
		}
		var myCity = new BMap.LocalCity();
		myCity.get(myFun);
	
	});
	//切换城市
	function changeCity(){
		var s2=$("#s2").val();
		var s3=$("#s3").val();
		var city=$("#localcity").html();
		if(s3!='区、县级市、县'&&s2!='地级市'){
			if(s3.indexOf('市')>0){
				city=s3.replace("市","");
			}else{
				city=s2.replace("市","");
			}
			
		}else if(s3=='区、县级市、县'&&s2!='地级市'){
			city=s2.replace("市","");
		}
		$.post('${basePath}/index_setCity.action','city='+city,function(data){
			$("#localcity").html(city);
			$('#cityModal').modal('hide');
			location.href = "${basePath}/index.html";
		},'json');
		
		
		
	}
	//切换到用户所在地
	function changeToLocalCity(){
		var city=$('#userCity').html();
		$.post('${basePath}/index_setCity.action','city='+city,function(data){
			$("#localcity").html(city);
			$('#cityModal').modal('hide');
			location.href = "${basePath}/index.html";
		},'json');
	}
	//注销
	function logout(){
		$.post('${basePath}/index_logout.action','',function(data){
			if(data.flag){
				location.href = "${basePath}/index.html";
			}
		},'json');
	}
	
	
	//添加收藏
	function addFavorites(nid){
		$.post('${basePath}/favorites_addFavorites.action','nid='+nid,function(data){
			if(data.flag){
				alert(data.msg);
				//initFavoritesTable(pageNum);
				$('[id=sc'+nid+']').text("已收藏");
				$('[id=sc'+nid+']').attr('onclick','removeFavorites('+nid+')');
			}else{
				alert(data.msg);
			}
		},'json');
	}
	//移除收藏 参数为收藏表id 新闻id
	function removeFavorites(nid){
		$.post('${basePath}/favorites_removeFavorites.action','nid='+nid,function(data){
			if(data.flag){
				alert("移除成功");
				$('[id=sc'+nid+']').text("收藏");
				$('[id=sc'+nid+']').attr('onclick','addFavorites('+nid+')');
			}else{
				alert("移除失败"+data.msg);
			}
		},'json');
	}
	
</script>
	<div class="header-top-strip">
			<div class="container">
				<div class="header-top-left">
				
				</div>
				<div class="header-top-right">
				<div class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- Button trigger modal  -->
	<a href="javascript:void(0);" id="localcity"  class="top-tips-a">杭州</a>
	
	<a class="play-icon " data-toggle="modal" data-target="#cityModal" >切换城市</a>
	<!---pop-up-box---->  
					<link href="${basePath}/static/css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
					<script src="${basePath}/static/js/jquery.magnific-popup.js" type="text/javascript"></script>
					<!---//pop-up-box---->
					<!-- 模态框（Modal） -->
					<div class="modal fade" id="cityModal" tabindex="-1" role="dialog" 
   						aria-labelledby="cityModalLabel" aria-hidden="true">
   						
	   					<div class="modal-dialog">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" 
					               aria-hidden="true">×
					            </button>
					            <h4 class="modal-title" id="myModalLabel">
					               选择城市
					            </h4>
					         </div>
					         <div class="modal-body">
					         	<!-- class="list-of-cities" -->
					         	<div class="panel panel-default">
					         		<div class="panel-body">
										<span  class="top-tips-span" >系统检测您当前所在的城市为</span>
										<a id="userCity" class="top-tips-a" href="javascript:void(0);" onclick="changeToLocalCity()"></a>
									</div>
								</div>
								      	<select class="list-of-cities" name="province" id="s1">
							             	<option></option>
							            </select>
							            <select  class="list-of-cities" name="city" id="s2">
							              <option></option>
							            </select>
							            <select  class="list-of-cities" name="town" id="s3">
							             		 <option></option>
							            </select>
					         </div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default" 
					               data-dismiss="modal">关闭
					            </button>
					            <button type="button" class="btn btn-default" onclick="changeCity()">
					               	保存
					            </button>
					         </div>
					      </div><!-- /.modal-content -->
					   </div><!-- /.modal-dialog -->
					</div>

                     <script>
						$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
						});
																						
						});
				</script>
				<!-- Large modal -->
				
				<span class="top-tips-a"  >在线人数 ${sessionCount}</span>
				<#if userInfo??>
				<a class="top-tips-a" href="${basePath}/user_account.html" >${userInfo.name}</a>
				<a class="top-tips-a" href="${basePath}/user_account.html" >个人中心</a>
				<a class="top-tips-a" href="${basePath}/favorites_page.html" >收藏夹</a>
				<a class="top-tips-a" href="${basePath}/newsHistory_page.html" >历史</a>
				<a class="top-tips-a" href="javascript:void(0);" onclick="logout()">注销</a>
				<#elseif otherDeviceLogin?? && otherDeviceLogin == 1 >
					<span class="top-tips-a">其他地方登陆</span>
					<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    				登陆/注册</button>
				<#else>
					<span class="top-tips-a">未登陆</span>
					<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    				登陆/注册</button>
				</#if>

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" 
			    aria-hidden="true">
			    <div class="modal-dialog modal-lg">
			    <!-- log or register start -->
			        <#include "./log_register.ftl">
			    <!-- log or register end -->
			    </div>
			</div>
			<script>
			//$('#myModal').modal('show');
			</script>
			</div>
				<div class="clearfix"></div>
			</div>
		</div>
