
<script type="text/javascript">
	/*
	 * 登陆注册 
	 */

	function login(){
		var username=$('#username').val();
		var password=$('#password').val();
		if(username!=""&&username!="用户名/邮箱/手机号"&&password!=""&&password!="密码"){
			$("#logform").ajaxSubmit({
		   	     type: "post",
		   	     url: "${basePath}/login",
		   	     dataType: "json",
		   	     success: function(data){
		   	           //返回提示信息       
		   	    	 if(data.resultCode=='200'){
		   	    		 alert(data.resultMsg);
		   	    		 location.href = "${basePath}/index"
		   	    	 } else{
		   	    		 alert(data.resultMsg);
		   	    	 }
		                
		   	     }
		   	 });
		}else{
			alert("用户名和密码不能为空");
		}
		
	}
	//email表单验证start
	function checkUsername(){
		var p=true;
		var username=$('#resusername').val();
		if(username!=""&&username!="用户名"){
			$.post("${basePath}/index_checkUsername.action",'username='+username,function(data){
				if(data.flag){
					$('#usernamemsg').text("用户名已存在").css('color','red');
				}
				else{
					$('#usernamemsg').text("用户名可用").css('color','green');
				}
			},'json');
			var msg=$('#usernamemsg').text();
			if(msg=="用户名可用"){
				return true;
			}else{
				return false;
			}
		}else{
			$('#usernamemsg').text("用户名不能为空").css('color','red');
			p=false;
		}
		return p;
	}
	function checkPassword(){
		var flag=true;
		var password=$('#respassword').val();
		var repassword=$('#resrepassword').val();
		if(password=="密码"||password==""||password==null){
			$('#passwordmsg').text("密码不能为空").css('color','red');
			flag=false;
		}else{
			if(repassword!=password){
				$('#passwordmsg').text("两次密码不相同").css('color','red');
				flag=false;
			}else{
				$('#passwordmsg').text("");
				flag=true;
			}
		}
		return flag;
		
	}
	function checkRepassword(){
		var flag=true;
		var password=$('#respassword').val();
		var repassword=$('#resrepassword').val();
		if(repassword=="确认密码" || repassword=="" || repassword==null){
			$('#repasswordmsg').text("密码不能为空").css('color','red');
			flag=false;
		}else{
			if(repassword!=password){
				$('#repasswordmsg').text("两次密码不相同").css('color','red');
				flag=false;
			}else{
				$('#repasswordmsg').text("");
				flag=true;
			}
		}
		return flag;
	}
	function checkAllPassword(){
		checkPassword();
		checkRepassword();
	}
	function checkEmail(){
		var p=false;
		var email=$('#reemail').val();
		var reg=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,5}){1,3})$/;
       	if(reg.test(email)){
       		$.post("${basePath}/index_checkEmail.action",'email='+email,function(data){
				if(data.flag){
					$('#emailmsg').text("邮箱已存在").css('color','red');
				}else{
					$('#emailmsg').text("邮箱可用").css('color','green');
				}
			});
       		var msg=$('#emailmsg').text();
			if(msg=="邮箱可用"){
				return true;
			}else{
				return false;
			}

       		
       	}else{
       		$('#emailmsg').text("邮箱格式错误").css('color','red');
       		p=false;
       		return p;
       	}
	}
	function checkCode(){
		var checkCode=$('#recheckcode').val();
		if(checkCode==""){
			$('#checkcodemsg').text('验证码不能为空').css('color','red');
			return false;
		}else{
			$('#checkcodemsg').text('');
			return true;
		}
	}
	//email表单验证end
	function regist(){
		if(checkUsername()&&checkEmail()&&checkPassword()&&checkRepassword()&&checkCode()){
			$("#registform").ajaxSubmit({
		   	     type: "post",
		   	     url: "${basePath}/index_regist.action",
		   	     dataType: "json",
		   	     success: function(data){
		   	           //返回提示信息       
		   	    	 if(data.flag){
		   	    		 alert("注册成功 ,激活邮件已发送");
		   	    		$('#myModal').modal('hide')
		   	    	 } else{
		   	    		 alert("注册失败"+data.msg);
		   	    	 }
		                
		   	     }
		   	 });
		}else{
			//console.log(checkUsername());
			//console.log(checkEmail());
			//console.log(checkPassword());
			//console.log(checkRepassword());
			alert("提交失败");
		}
	}
	function changimg(){
		var img1=document.getElementById("checkimg");
		$('#recheckcode').val('');
		img1.src="${basePath}/index_checkImg.action?"+new Date().getTime();
	}
	
	//phone表单验证start
	function checkPhone(){
		//alert(1);
		var p=true;
		var phone=$('#prephone').val();
		//alert(phone);
		if(phone!=""&&phone!="手机号"){
			$.get("${basePath}/index_checkPhone.action",'phone='+phone,function(data){
				if(data.flag){
					$('#phonemsg').text("手机号已存在").css('color','red');
				}
				else{
					$('#phonemsg').text("手机号可用").css('color','green');
				}
			},'json');
			var msg=$('#phonemsg').text();
			if(msg=="手机号可用"){
				return true;
			}else{
				return false;
			}
		}else{
			$('#phonemsg').text("手机号不能为空").css('color','red');
			p=false;
		}
		return p;
	}
	function checkPhoneUsername(){
		var p=true;
		var username=$('#presusername').val();
		if(username!=""&&username!="用户名"){
			$.post("${basePath}/index_checkUsername.action",'username='+username,function(data){
				if(data.flag){
					$('#usernamemsg2').text("用户名已存在").css('color','red');
				}
				else{
					$('#usernamemsg2').text("用户名可用").css('color','green');
				}
			},'json');
			var msg=$('#usernamemsg2').text();
			if(msg=="用户名可用"){
				return true;
			}else{
				return false;
			}
		}else{
			$('#usernamemsg2').text("用户名不能为空").css('color','red');
			p=false;
		}
		return p;
	}
	
	function checkPhonePassword(){
		var flag=true;
		var password=$('#prespassword').val();
		var repassword=$('#presrepassword').val();
		if(password=="密码"||password==""||password==null){
			$('#passwordmsg2').text("密码不能为空").css('color','red');
			flag=false;
		}else{
			if(repassword!=password){
				$('#passwordmsg2').text("两次密码不相同").css('color','red');
				flag=false;
			}else{
				$('#passwordmsg2').text("");
				flag=true;
			}
		}
		return flag;
		
	}
	function checkPhoneRepassword(){
		var flag=true;
		var password=$('#prespassword').val();
		var repassword=$('#presrepassword').val();
		if(repassword=="确认密码" || repassword=="" || repassword==null){
			$('#repasswordmsg2').text("密码不能为空").css('color','red');
			flag=false;
		}else{
			if(repassword!=password){
				$('#repasswordmsg2').text("两次密码不相同").css('color','red');
				flag=false;
			}else{
				$('#repasswordmsg2').text("");
				flag=true;
			}
		}
		return flag;
	}
	function checkPhoneAllPassword(){
		checkPhonePassword();
		checkPhoneRepassword();
	}
	function checkPhoneCode(){
		var checkCode=$('#precheckcode').val();
		if(checkCode==""){
			$('#checkcodemsg2').text('验证码不能为空').css('color','red');
			return false;
		}else{
			$('#checkcodemsg2').text('');
			return true;
		}
	}
	function sendSms(){
		var phone=$('#prephone').val();
		if(phone!=""&&phone!="手机号"){
			if(checkPhone()){
				$.post("${basePath}/index_sendSms.action",'phone='+phone,function(data){
					if(data.flag){
						$('#checkcodemsg2').text(data.msg).css('color','green');
					}
					else{
						$('#checkcodemsg2').text(data.msg).css('color','red');
					}
				},'json');
			}else{
				$('#checkcodemsg2').text("不能发送").css('color','red');
			}
		}else{
			$('#checkcodemsg2').text("手机号不能为空").css('color','red');
		}
	}
	
	//phone表单验证end
	//手机注册
	function phoneRegist(){
		if(checkPhone()&&checkPhoneUsername()&&checkPhonePassword()&&checkPhoneRepassword()&&checkPhoneCode()){
			$("#phoneregistform").ajaxSubmit({
		   	     type: "post",
		   	     url: "${basePath}/index_phoneRegist.action",
		   	     dataType: "json",
		   	     success: function(data){
		   	           //返回提示信息       
		   	    	 if(data.flag){
		   	    		 alert("注册成功 ");
		   	    		$('#phoneregistform')[0].reset();
		   	    		$('#myModal').modal('hide');
		   	    	 } else{
		   	    		 alert("注册失败"+data.msg);
		   	    	 }
		                
		   	     }
		   	 });
		}else{
			//console.log(checkPhone());
			//console.log(checkPhoneUsername());
			//console.log(checkPhonePassword());
			//console.log(checkPhoneRepassword());
			//console.log(checkPhoneCode());
			alert("提交失败");
		}
	}
	
	
	//清空表单
	function clearForm(){
		$("[id$=msg]").text("");
	}
	//忘记密码
	function forgetPassword(){
		window.location.href="${basePath}/userno_forgetPasswordPage.html";
	}
	
</script>
<div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;</button>
                <h4 class="modal-title" id="myModalLabel">
                    Don't Wait, Login now!</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-8" style="border-right: 1px dotted #C2C2C2;padding-right: 30px;">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#Login" data-toggle="tab">登陆</a></li>
                            <li><a href="#Registration" data-toggle="tab">注册</a></li>
                            <li><a href="#PhoneRegistration" data-toggle="tab">手机快速注册</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane active" id="Login">
                                <form role="form" id="logform" class="form-horizontal">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                       	 账号</label>
                                    <div class="col-sm-10">
                                        <input type="text" id="username" name="username" class="form-control" id="email1" placeholder="用户名/邮箱/手机号" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1" class="col-sm-2 control-label">
                                       		 密码</label>
                                    <div class="col-sm-10">
                                        <input type="password" id="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="密码" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    <div class="col-sm-10">
                                        <button type="button" class="btn btn-primary btn-sm" onclick="login()">
                                            	登陆</button>
                                        <a href="javascript:void(0);" onclick="forgetPassword()">忘记密码?</a>
                                    </div>
                                </div>
                                </form>
                            </div>
                            <div class="tab-pane" id="Registration">
                                <form id="registform" role="form" class="form-horizontal">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        	用户名</label>
                                    <div class="col-sm-10">
                                          <input name="username"  id="resusername" type="text" class="form-control" placeholder="用户名"  onblur="checkUsername()"/>
                                    	<span id="usernamemsg"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Email</label>
                                    <div class="col-sm-10">
                                        <input name="email" type="email" class="form-control" id="reemail" placeholder="Email" onblur="checkEmail()" />
                                        <span id="emailmsg"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        	密码</label>
                                    <div class="col-sm-10">
                                        <input type="password" id="respassword" name="password" class="form-control"  placeholder="密码" onblur="checkAllPassword()"/>
                                        <span id="passwordmsg"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        	确认密码</label>
                                    <div class="col-sm-10">
                                        <input type="password" id="resrepassword" name="repassword" class="form-control"  placeholder="确认密码" onblur="checkAllPassword()" />
                                        <span id="repasswordmsg"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        	验证码</label>
                                    <div class="col-sm-10">
                                    	<div class="row">
                                    		<div class="col-sm-5">
                                    		<input type="text" id="recheckcode" name="checkcode" class="form-control"  placeholder="验证码"  onblur="checkCode()"/>
                                    		<span id="checkcodemsg"></span>
                                    		</div>
                                    		<div class="col-sm-3">
                                    		<img id="checkimg"  src="${basePath}/index_checkImg.action"  onclick="changimg()" title="点击更换验证码">
                                    		</div>
                                    		
                                    	</div>
                                        <span id="repasswordmsg"></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    <div class="col-sm-10">
                                        <button type="button" class="btn btn-primary btn-sm" onclick="regist()">
                                            	提交</button>
                                        <button type="button" class="btn btn-default btn-sm" 
                                        onclick="javascript: $('#myModal').modal('hide');$('#registform')[0].reset();clearForm(); ">
                                           	 取消</button>
                                    </div>
                                </div>
                                </form>
                            </div>
                            <div class="tab-pane" id="PhoneRegistration">
                                <form role="form" id="phoneregistform" class="form-horizontal">
                                 <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        	手机号</label>
                                    <div class="col-sm-10">
                                        <input name="phone" type="text" class="form-control" id="prephone" placeholder="手机号" onblur="checkPhone()" />
                                        <span id="phonemsg"></span>
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        	用户名</label>
                                    <div class="col-sm-10">
                                          <input name="username"  id="presusername" type="text" class="form-control" placeholder="用户名"  onblur="checkPhoneUsername()"/>
                                    	<span id="usernamemsg2"></span>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        	密码</label>
                                    <div class="col-sm-10">
                                        <input type="password" id="prespassword" name="password" class="form-control"  placeholder="密码" onblur="checkPhoneAllPassword()"/>
                                        <span id="passwordmsg2"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        	确认密码</label>
                                    <div class="col-sm-10">
                                        <input type="password" id="presrepassword" name="repassword" class="form-control"  placeholder="确认密码" onblur="checkPhoneAllPassword()" />
                                        <span id="repasswordmsg2"></span>
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        	验证码</label>
                                    <div class="col-sm-10">
                                    	<div class="row">
                                    		<div class="col-sm-5">
                                    		<input type="text" id="precheckcode" name="checkcode" class="form-control"  placeholder="手机验证码"  onblur="checkPhoneCode()"/>
                                    		<span id="checkcodemsg2"></span>
                                    		</div>
                                    		<div class="col-sm-3">
                                    		<a href="javascript:void(0);" onclick="sendSms()">发送验证码</a>
                                    		</div>
                                    	</div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    <div class="col-sm-10">
                                        <button type="button" class="btn btn-primary btn-sm" onclick="phoneRegist()">
                                            	提交</button>
                                        <button type="button" class="btn btn-default btn-sm" 
                                        onclick="javascript: $('#myModal').modal('hide');$('#phoneregistform')[0].reset();clearForm(); ">
                                           	 取消</button>
                                    </div>
                                </div>
                                </form>
                            </div>
                        </div>
                        
                    </div>
                    
                </div>
            </div>
        </div>
