<style>
a{
color:white;
text-decoration:none;
}
a:hover{
color:#d65c37;
 
}
</style>
<body style="background-color:#333">
<div style="margin-left:30%">
<div style="color:white;margin-left:40%;font-family:Saira Condensed;display:inline">

<br>░█▀▀█ ─▀─ █▀▀█ █──█ ░█▄─░█ █──█ █▀▀█ █▀▀█ 
<br>░█─▄▄ ▀█▀ █▄▄█ █▄▄█ ░█░█░█ █▀▀█ █▄▄█ █──█
<br>░█▄▄█ ▀▀▀ ▀──▀ ▄▄▄█ ░█──▀█ ▀──▀ ▀──▀ █▀▀▀
<div style="padding:40px;line-height:10px;font-family:Monospace">
 <img src="https://www.seeklogo.net/wp-content/uploads/2016/09/facebook-icon-preview-200x200.png" width="30px" >
 <div style="position:relative;top:-20px;left:30px">Fb: <a href="https://www.facebook.com/GiayNhapcoder">Giấy Nháp</a> </div>

<img src="http://www.free-icons-download.net/images/mypages-icon-18538.png" width="30px" >

<div style="position:relative;top:-20px;left:30px"> Fanpage: <a href="https://www.facebook.com/ketbankhonggoihan/">Chat với người lạ</a> </div>

</div>
</div>
</div>
<hr width="100%">
<div style="color:white;padding:40px;line-height:20px;font-family:Monospace">
 *Your Configs <br>
 <div style="margin-left:10px">
 HOST NAME: <span th:text="${hostname}" /> <br>
 PAGE: <span th:text="${PageID}" />  <br>
 ACCESS TOKEN: <span th:text="${token	}" />  <br>
 </div>
</div>
<hr width="100%">
<div style="color:white;padding:40px;line-height:20px;font-family:Monospace">
 - Config access_token , page id, appkey trong class Configs<br>
 - ProcessMain,ProcessMessage là 2 tiến trình xử lý trong đó. ProcessMain xử lý dữu liệu nền từ facebook gửi đến. rồi chuyền qua ProcessMessage xứ lý gửi trả kết quả...
 
</div>

</body>
