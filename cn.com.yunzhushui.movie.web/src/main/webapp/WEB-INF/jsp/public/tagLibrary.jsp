<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf"     uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s"      uri="http://www.springframework.org/tags" %>
<%-- <%@ taglib prefix="yzsTld" uri="/yzsTldFunction" %> --%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
  pageContext.setAttribute("path", path+"/");
  pageContext.setAttribute("basePath", basePath);
%>
