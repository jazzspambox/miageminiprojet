
        <%@page import="java.util.ArrayList"%>
        <%@page import="java.lang.Integer"%>
        <%@page import="java.util.concurrent.CountDownLatch"%>
        <%@page import="com.paris5.miage1.trombinoscope.controllers.Pagination"%>
        <div id="tnt_pagination">
        
        <%
            Pagination pagination = (Pagination) request.getAttribute("pagination");
            if(pagination.getCurrentPage()>1){
                %><a href="tombinoscope?action=search&type=<%=request.getParameter("search_type") %>&page=<%= pagination.getCurrentPage() -1%>">Prev</a><%
            }
            else{
                %><span class="disabled_tnt_pagination">Prev</span><%
            }
            ArrayList<Integer> list = (ArrayList<Integer>) pagination.getList();
            for(Integer p:list){
                System.out.println(p);
                if(pagination.getCurrentPage()==p){
                    %><span class="active_tnt_link"><%= p %></span><%
                }
                else if(p==0){
                    %> ... <%
                }
                else{
                    %><a href="tombinoscope?action=search&type=<%=request.getParameter("search_type") %>&page=<%=p%>"> <%= p %></a><%
               }
            }
            
            if(pagination.getCurrentPage()< pagination.getNombrePages()){
                %><a href="tombinoscope?action=search&type=<%=request.getParameter("search_type") %>&page=<%= pagination.getCurrentPage()+1%>">Next</a><%
            }
            else{
                %><span class="disabled_tnt_pagination">Next</span><%
            }
        %>
        </div>
