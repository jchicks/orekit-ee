<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Sample Application</title>
</head>
<body>
  <h1>Ooooooohhhh Snap!!!!</h1>
  <h3>This isn't good</h3>
  <br>
  <br>
  
  <pre>
    <spring:eval expression="T(org.apache.commons.lang3.exception.ExceptionUtils).getStackTrace(exception)"/>
  </pre>
</body>
</html>