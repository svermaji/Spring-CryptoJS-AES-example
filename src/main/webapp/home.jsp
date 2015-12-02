<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AES Example</title>
</head>
<body>
<h1>AES Example</h1>
<div>
<textarea id="plaintext" placeholder="Enter plain text here."></textarea>
</div>

<div>
<input id = "btn" type="submit" value="Encrypt"/>
</div>
<div id="result"></div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/aes.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pbkdf2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/AesUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<Script Language='Javascript'>

$(document).ready(function() {
    alert ("hello");

    $("#btn").click(function() {
        alert ($("#plaintext").val());
        $.post('<%=request.getContextPath()%>/dec', {
          dataToDec: encryptString ($("#plaintext").val())
        }, function(data) {
          //alert('Plaintext: ' + data);
        });
    });
});
</script>
</body>
</html>