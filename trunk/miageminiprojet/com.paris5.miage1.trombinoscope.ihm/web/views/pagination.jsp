
        <%@page import="com.paris5.miage1.trombinoscope.controllers.Pagination"%>
        <%
            Pagination pagination = (Pagination) request.getAttribute("pagination");
            for(int j=1; j<=pagination.getNombrePages(); j++){
                if(pagination.getCurrentPage()==j){
                    %><span> <%= j %></span><%
                }
                else{
                    %><a href="tombinoscope?action=search&type=<%=request.getParameter("search_type") %>&page=<%=j%>"> <%= j %></a><%
               }
            }
        %>
