
        <%@page import="java.util.Iterator"%>
        <%@page import="java.lang.Integer"%>
        <%@page import="com.paris5.miage1.trombinoscope.controllers.Pagination"%>
        <div id="tnt_pagination">
        
        <%
            Pagination pagination = (Pagination) request.getAttribute("pagination");
            if(pagination.getCurrentPage()>1){
                %><a href="trombinoscope?action=search&type=<jsp:getProperty name="filtre" property="type" />&page=<%= pagination.getCurrentPage()-1 %>">Prev</a><%
            }
            else{
                %><span class="disabled_tnt_pagination">Prev</span><%
            }
            Iterator it = pagination.iterator();
            while(it.hasNext()){
                Integer p = (Integer) it.next();
                if(pagination.getCurrentPage()==p){
                    %><span class="active_tnt_link"><%= p %></span><%
                }
                else if(p==0){
                    %> ... <%
                }
                else{
                    %><a href="trombinoscope?action=search&type=<jsp:getProperty name="filtre" property="type" />&page=<%=p%>"> <%= p %></a><%
               }
            }
            
            if(pagination.getCurrentPage()< pagination.getNombrePages()){
                %><a href="trombinoscope?action=search&type=<jsp:getProperty name="filtre" property="type" />&page=<%= pagination.getCurrentPage()+1%>">Next</a><%
            }
            else{
                %><span class="disabled_tnt_pagination">Next</span><%
            }
        %>
        </div>